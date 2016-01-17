package com.polly5315.skycleaner.views.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBulletViewModel;
import com.polly5315.skycleaner.views.actors.BulletView;

public class BulletViewFactory implements IActorFactory<IBulletViewModel> {
    private final Texture _texture;

    public BulletViewFactory(Texture texture) {
        if (texture == null)
            throw new IllegalArgumentException("texture cannot be null");
        _texture = texture;
    }

    @Override
    public Actor create(IBulletViewModel viewModel) {
        return new BulletView(viewModel, _texture);
    }
}
