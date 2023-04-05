package avans.groep15.themoviedb.datastorage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import avans.groep15.themoviedb.domain.AddMovie;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.WatchList;
import avans.groep15.themoviedb.domain.responses.ListResult;
import avans.groep15.themoviedb.domain.responses.MovieResult;
import avans.groep15.themoviedb.domain.responses.StatusResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepository extends Repository {
    private final static String TAG = ListRepository.class.getSimpleName();
    private static volatile ListRepository instance;
    private final MutableLiveData<List<WatchList>> watchLists;
    private final MutableLiveData<Boolean> success;
    private final AccountRepository accountRepository = AccountRepository.getInstance();

    private ListRepository() {
        watchLists = new MutableLiveData<>();
        success = new MutableLiveData<>();
    }

    public static ListRepository getInstance() {
        if (instance == null) {
            instance = new ListRepository();
        }
        return instance;
    }

    public LiveData<List<WatchList>> getWatchListObservable() {
        return this.watchLists;
    }

    public LiveData<Boolean> getSuccessObservable() {
        return this.success;
    }

    public void postWatchLists(WatchList newWatchList) {
        Log.d(TAG, "Posting watch lists");
        ApiService api = super.getApiService();
        Call<ListResult> call = api.createList(getApiKey(), accountRepository.getSessionIdObservable().getValue(), newWatchList);

        call.enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(@NonNull Call<ListResult> call, @NonNull Response<ListResult> response) {
                if (response.body() == null) {
                    Log.e(TAG, "Error adding list: no response body");
                    return;
                }
                List<WatchList> listsFromLiveData = watchLists.getValue();
                if (listsFromLiveData == null) {
                    listsFromLiveData = new ArrayList<>();
                }
                newWatchList.setId(response.body().getList_id());
                listsFromLiveData.add(newWatchList);
                watchLists.postValue(listsFromLiveData);
                Log.i(TAG, "Added list");
            }

            @Override
            public void onFailure(@NonNull Call<ListResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Error adding list: " + t.getMessage());
            }
        });
    }

    public void addToWatchList(AddMovie addMovie) {
        Log.d(TAG, "Adding to list");
        ApiService api = super.getApiService();
        Call<StatusResult> call = api.addToList(addMovie.getList_id(), getApiKey(), accountRepository.getSessionIdObservable().getValue(), addMovie);

        call.enqueue(new Callback<StatusResult>() {
            @Override
            public void onResponse(@NonNull Call<StatusResult> call, @NonNull Response<StatusResult> response) {
                if (response.body() == null) {
                    Log.e(TAG, "Error adding to list: no response body");
                    success.postValue(false);
                    return;
                }
                success.postValue(response.body().isSuccess());
                if (response.body().isSuccess()) {
                    Log.i(TAG, "Added list");
                } else {
                    Log.i(TAG, "Failed to add to list");
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatusResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Error adding to list: " + t.getMessage());
            }
        });
    }
}
