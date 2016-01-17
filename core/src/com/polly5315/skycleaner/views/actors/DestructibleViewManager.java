package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleManagerViewModel;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleViewModel;

public class DestructibleViewManager<TItem extends IDestructibleViewModel> extends Group implements IDestructibleManagerViewModel.IDestructibleManagerViewModelListener<TItem> {
    private final IViewFactory<TItem> _viewFactory;

    public DestructibleViewManager(IDestructibleManagerViewModel<? extends TItem> viewModelManager, IViewFactory<TItem> viewFactory) {
        if (viewModelManager == null)
            throw new IllegalArgumentException("viewModelManager cannot be null");
        if (viewFactory == null)
            throw new IllegalArgumentException("viewFactory cannot be null");
        _viewFactory = viewFactory;
        for (TItem viewModel : viewModelManager.getItems())
            addActor(_viewFactory.create(viewModel));
        viewModelManager.addDestructibleManagerViewModelListener(this);
    }

    @Override
    public <T extends TItem> void onItemAdded(T item) {
        addActor(_viewFactory.create(item));
    }
}
