package avans.groep15.themoviedb.datastorage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import avans.groep15.themoviedb.domain.LoginData;
import avans.groep15.themoviedb.domain.responses.LoginResult;
import avans.groep15.themoviedb.domain.responses.SessionResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository extends Repository {
    private final static String TAG = AccountRepository.class.getSimpleName();
    private static volatile AccountRepository instance;
    private final MutableLiveData<String> token;
    private final MutableLiveData<Boolean> hasLoggedIn;
    private final MutableLiveData<String> username;
    private final MutableLiveData<String> sessionId;

    // Singleton pattern
    private AccountRepository() {
        token = new MutableLiveData<>();
        hasLoggedIn = new MutableLiveData<>();
        username = new MutableLiveData<>();
        sessionId = new MutableLiveData<>();
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public LiveData<String> getTokenObservable() {
        return this.token;
    }

    public LiveData<Boolean> getHasLoggedInObservable() {
        return this.hasLoggedIn;
    }

    public LiveData<String> getUsernameObservable() {
        return this.username;
    }

    public LiveData<String> getSessionIdObservable() {
        return this.sessionId;
    }

    public void getToken() {
        Log.d(TAG, "Retrieving token");
        ApiService api = super.getApiService();
        Call<LoginResult> call = api.getToken(getApiKey());

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
                if (response.body() == null) {
                    Log.e(TAG, "Error retrieving token");
                    token.setValue(null);
                    return;
                }
                token.setValue(response.body().getRequest_token());
                Log.i(TAG, "Retrieved token");
            }

            @Override
            public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Error retrieving token: " + t.getMessage());
                token.setValue(null);
            }
        });
    }

    public void performLogin(LoginData loginData) {
        Log.d(TAG, "Logging in");
        ApiService api = super.getApiService();
        Call<LoginResult> call = api.login(getApiKey(), loginData);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
                if (response.body() == null) {
                    Log.e(TAG, "Error logging in: no response body");
                    hasLoggedIn.setValue(false);
                    return;
                }
                hasLoggedIn.setValue(true);
                username.setValue(loginData.getUsername());
                getSessionId();
                Log.i(TAG, "Logged in");
            }

            @Override
            public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Error logging in: " + t.getMessage());
                hasLoggedIn.setValue(false);
            }
        });
    }

    public void getSessionId() {
        Log.d(TAG, "Getting session id");
        ApiService api = super.getApiService();
        Call<SessionResult> call = api.getSessionId(getApiKey(), new LoginResult(this.token.getValue()));

        call.enqueue(new Callback<SessionResult>() {
            @Override
            public void onResponse(@NonNull Call<SessionResult> call, @NonNull Response<SessionResult> response) {
                if (response.body() == null) {
                    Log.e(TAG, "Error getting session ID: no response body");
                    sessionId.setValue(null);
                    return;
                }
                sessionId.setValue(response.body().getSession_id());
                Log.i(TAG, "Received session ID");
            }

            @Override
            public void onFailure(@NonNull Call<SessionResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Error getting session ID: " + t.getMessage());
                sessionId.setValue(null);
            }
        });
    }

    public void performLogOut() {
        token.postValue(null);
        hasLoggedIn.postValue(false);
        username.postValue(null);
        sessionId.postValue(null);
    }

}
