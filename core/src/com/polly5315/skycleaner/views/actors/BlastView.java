package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBlastViewModel;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructible;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleViewModel;

public class BlastView extends Actor implements IDestructibleViewModel.IDestructibleListener {
    private IBlastViewModel _viewModel;
    private Animation _animation;
    private float _stateTime = 0;

    public BlastView(IBlastViewModel viewModel, Animation animation) {
        if (viewModel == null)
            throw new IllegalArgumentException("viewModel cannot be null");
        if (animation == null)
            throw new IllegalArgumentException("animation cannot be null");
        _viewModel = viewModel;
        _animation = animation;
        _viewModel.addDestructibleListener(this);
    }

    @Override
    public void onDestroyed(IDestructible destructible) {
        if (destructible == _viewModel)
            remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        _stateTime += delta;
        if (_stateTime > _animation.getAnimationDuration())
            remove();
        //обновить кадр текущей анимации, если надо.
        //если это анимация разрушения, и она закончилась, разрушиться.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        final TextureRegion keyFrame = _animation.getKeyFrame(_stateTime);
        batch.draw(
                keyFrame,
                _viewModel.getX() - keyFrame.getRegionWidth() / 2f,
                _viewModel.getY() - keyFrame.getRegionHeight() / 2f
        );
    }
}
