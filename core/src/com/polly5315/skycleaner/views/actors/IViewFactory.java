package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleViewModel;

public interface IViewFactory<TViewModel extends IDestructibleViewModel> {
    <T extends TViewModel> Actor create(T viewModel);
}
