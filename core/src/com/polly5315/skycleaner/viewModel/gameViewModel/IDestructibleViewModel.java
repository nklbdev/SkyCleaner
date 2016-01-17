package com.polly5315.skycleaner.viewModel.gameViewModel;

public interface IDestructibleViewModel {
    interface IDestructibleListener {
        void onDestroyed(IDestructible destructible);
    }
    boolean isDestroyed();
    void addDestructibleListener(IDestructibleListener listener);
    void removeDestructibleListener(IDestructibleListener listener);
}
