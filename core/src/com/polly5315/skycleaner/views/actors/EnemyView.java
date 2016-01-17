package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructible;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleViewModel;
import com.polly5315.skycleaner.viewModel.gameViewModel.IEnemyViewModel;

public class EnemyView extends Actor implements IDestructibleViewModel.IDestructibleListener {
    private IEnemyViewModel _viewModel;
    private Texture _texture;

    public EnemyView(IEnemyViewModel viewModel) {
        if (viewModel == null)
            throw new IllegalArgumentException("viewModel cannot be null");
        _viewModel = viewModel;
        _viewModel.addDestructibleListener(this);
        _texture = new Texture("enemy.png");
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

