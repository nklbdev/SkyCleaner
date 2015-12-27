package com.polly5315.skycleaner;

public class StandardWeapon implements IWeapon {
    private boolean _isFiring;
    private float _firingInterval = 0.5f;
    private float _timeSinseLastFiring;
    private IBulletPool _bulletPool;
    private IPlaceable _pivot;

    public StandardWeapon(IBulletPool bulletPool, IPlaceable pivot) {
        if (bulletPool == null)
            throw new IllegalArgumentException("bulletPool cannot be null");
        if (pivot == null)
            throw new IllegalArgumentException("pivot cannot be null");
        _bulletPool = bulletPool;
        _pivot = pivot;
    }

    @Override
    public void fire() {
        _isFiring = true;
    }

    @Override
    public void update(float time) {
        _timeSinseLastFiring += time;
        if (_isFiring && _timeSinseLastFiring > _firingInterval) {
            _bulletPool.addBullet(_pivot.getX(), _pivot.getY());
            _timeSinseLastFiring = 0;
        }
        _isFiring = false;
    }
}
