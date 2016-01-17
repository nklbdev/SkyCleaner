package com.polly5315.skycleaner.views.factories;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleViewModel;

public interface IActorFactory<TViewModel extends IDestructibleViewModel> {
    <T extends TViewModel> Actor create(T viewModel);
}
