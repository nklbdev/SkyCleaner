package com.polly5315.skycleaner.views.factories;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.IShipViewModel;

public interface IShipViewFactory {
    Actor createShipView(IShipViewModel viewModel);
}
