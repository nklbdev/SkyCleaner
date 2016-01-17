package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.IEnemyViewModel;

public class EnemyViewFactory implements IViewFactory<IEnemyViewModel> {
    @Override
    public Actor create(IEnemyViewModel viewModel) {
        return new EnemyView(viewModel);
    }
}
