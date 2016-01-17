package com.polly5315.skycleaner.viewModel.gameViewModel.entities;

public class Blast extends EntityBase implements IBlast {
    private final float _x, _y;
    private final float _maxLifeTime = 3;
    private float _lifeTime = 0;

    public Blast(float x, float y) {
        _x = x;
        _y = y;
    }

    @Override
    protected void onUpdate(float time) {
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
