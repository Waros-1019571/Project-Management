package avans.groep15.themoviedb.application.listeners;

import androidx.lifecycle.LifecycleOwner;

import java.util.List;

import avans.groep15.themoviedb.domain.WatchList;

public interface ListListener extends LifecycleOwner {
    void listAdded(List<WatchList> lists);
}
