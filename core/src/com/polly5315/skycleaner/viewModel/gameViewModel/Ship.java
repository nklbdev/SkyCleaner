package com.polly5315.skycleaner.viewModel.gameViewModel;

import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBullet;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.factories.IBulletFactory;

public class Ship implements IShip {
    private Side _movingSide;
    private float _x = 50, _y = 50;
    private final IEntityManager<IBullet> _bulletManager;
    private final IBulletFactory _bulletFactory;
    private boolean _isFiring = false;
    private final float _speed = 200;

    private final float _firingInterval = 0.1f;
    private float _timeSinceLastFiring = _firingInterval;
    private boolean _fireBurst = false;


    public Ship(IEntityManager<IBullet> bulletManager, IBulletFactory bulletFactory) {
        if (bulletManager == null)
            throw new IllegalArgumentException("bulletManager cannot be null");
        if (bulletFactory == null)
            throw new IllegalArgumentException("bulletFactory cannot be null");
        _bulletManager = bulletManager;
        _bulletFactory = bulletFactory;
    }

    @Override
    public void setFiring(boolean isFiring) {
        _isFiring = isFiring;
    }

    @Override
    public void setMovingSide(Side side) {
        _movingSide = side;
    }

    @Override
    public float getX() {
        return _x;
    }

    @Override
    public float getY() {
        return _y;
    }

    @Override
    public void update(float time) {
        //MOVING
        if (_movingSide == Side.LEFT)
            _x -= time * _speed;
        else if (_movingSide == Side.RIGHT)
            _x += time * _speed;

        //FIRING
        _timeSinceLastFiring += time;
        if (_isFiring) {
            if (_timeSinceLastFiring >= _firingInterval) {
                if (_fireBurst) {
                    while (_timeSinceLastFiring >= _firingInterval) {
                        _bulletManager.addEntity(_bulletFactory.create(_x, _y + 20));
                        _timeSinceLastFiring -= _firingInterval;
                    }
                } else {
                    _bulletManager.addEntity(_bulletFactory.create(_x, _y + 20));
                    _timeSinceLastFiring = 0;
                }
            }
            _fireBurst = true;
        } else {
            _fireBurst = false;
        }

    }
}
