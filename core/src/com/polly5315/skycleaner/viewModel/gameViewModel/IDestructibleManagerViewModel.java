package com.polly5315.skycleaner.viewModel.gameViewModel;

import java.util.Collection;

public interface IDestructibleManagerViewModel<TItem extends IDestructibleViewModel> {
    interface IDestructibleManagerViewModelListener<TItem extends IDestructibleViewModel> {
        <T extends TItem> void onItemAdded(T item);
    }
    <T extends IDestructibleManagerViewModelListener> void addDestructibleManagerViewModelListener(T listener);
    <T extends IDestructibleManagerViewModelListener> void removeDestructibleManagerViewModelListener(T listener);
    Collection<TItem> getItems();
}
