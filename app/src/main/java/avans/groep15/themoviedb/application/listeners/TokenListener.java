package avans.groep15.themoviedb.application.listeners;

import androidx.lifecycle.LifecycleOwner;

public interface TokenListener extends LifecycleOwner {
    void receiveToken(String token);
}
