package avans.groep15.themoviedb.datastorage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import avans.groep15.themoviedb.domain.LoginData;
import avans.groep15.themoviedb.domain.responses.LoginResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository extends Repository {
    private final static String TAG = LoginRepository.class.getSimpleName();
    private static volatile LoginRepository instance;
    private final MutableLiveData<String> token;
    private final MutableLiveData<Boolean> hasLoggedIn;

    // Singleton pattern
    private LoginRepository() {
        token = new MutableLiveData<>();
        hasLoggedIn = new MutableLiveData<>();
    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public LiveData<String> getTokenObservable() {
        return this.token;
    }

    public LiveData<Boolean> getHasLoggedInObservable() {
        return this.hasLoggedIn;
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
                Log.i(TAG, "Logged in");
            }

            @Override
            public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Error logging in: " + t.getMessage());
                hasLoggedIn.setValue(false);
            }
        });
    }

}
