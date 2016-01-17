package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBulletViewModel;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructible;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleViewModel;

public class BulletView extends Actor implements IDestructibleViewModel.IDestructibleListener {
    private IBulletViewModel _viewModel;
    private Texture _texture;

    public BulletView(IBulletViewModel viewModel, Texture texture) {
        if (viewModel == null)
            throw new IllegalArgumentException("viewModel cannot be null");
        if (texture == null)
            throw new IllegalArgumentException("texture cannot be null");
        _viewModel = viewModel;
        _viewModel.addDestructibleListener(this);
        _texture = texture;
    }

    @Override
    public void onDestroyed(IDestructible destructible) {
        if (destructible == _viewModel)
            remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //обновить кадр текущей анимации, если надо.
        //если это анимация разрушения, и она закончилась, разрушиться.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(
                _texture,
                _viewModel.getX() - _texture.getWidth() / 2,
                _viewModel.getY() - _texture.getHeight() / 2
        );
    }
}
