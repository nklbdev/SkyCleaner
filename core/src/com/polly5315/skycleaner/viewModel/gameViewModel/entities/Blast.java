package com.polly5315.skycleaner.viewModel.gameViewModel.entities;

public class Blast extends EntityBase implements IBlast {
    private float _x, _y;
    private final float _velocityX, _velocityY;
    private final float _maxLifeTime = 3;
    private float _lifeTime = 0;

    public Blast(float x, float y, float velocityX, float velocityY) {
        _x = x;
        _y = y;
        _velocityX = velocityX;
        _velocityY = velocityY;
    }

    @Override
    protected void onUpdate(float time) {
        _x += _velocityX * time;
        _y += _velocityY * time;
        _lifeTime += time;
        if (_lifeTime >= _maxLifeTime)
            allowDestroy();
    }

    @Override
    public float getX() {
        return _x;
    }

    @Override
    public float getY() {
        return _y;
    }
}
