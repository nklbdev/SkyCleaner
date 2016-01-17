package com.polly5315.skycleaner.views.factories;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.IEnemyViewModel;
import com.polly5315.skycleaner.views.actors.EnemyView;

public class EnemyViewFactory implements IActorFactory<IEnemyViewModel> {
    @Override
    public Actor create(IEnemyViewModel viewModel) {
        return new EnemyView(viewModel);
    }
}
