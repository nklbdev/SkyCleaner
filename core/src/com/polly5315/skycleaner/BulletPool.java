package com.polly5315.skycleaner;

import java.util.*;

public class BulletPool implements IBulletPool, IEntity {
    private boolean _isDestroyed;
    private LinkedList<Bullet> _availableBullets = new LinkedList<Bullet>();
    private Set<Bullet> _aliveBullets = new HashSet<Bullet>();
    private Set<IDestructibleListener> _destructibleListeners = new HashSet<IDestructibleListener>();
    private Set<Bullet> _readOnlyAliveBullets;
    private Bullet.InitParams _initParams = new Bullet.InitParams();

    public BulletPool(int capacity, IBulletFactory bulletFactory) {
        if (capacity < 0)
            throw new IllegalArgumentException("capacity cannot be negative");
        if (bulletFactory == null)
            throw new IllegalArgumentException("bulletFactory cannot be null");
        for (int i = 0; i < capacity; i++) {
            Bullet bullet = bulletFactory.createBullet();
            _availableBullets.add(bullet);
        }
        _readOnlyAliveBullets = Collections.unmodifiableSet(_aliveBullets);
        _isDestroyed = false;
    }

    @Override
    public void addBullet(float x, float y) {
        if (_availableBullets.size() <= 0)
            return;
        Bullet bullet = _availableBullets.pop();
        _initParams.x = x;
        _initParams.y = y;
        bullet.init(_initParams);
        _aliveBullets.add(bullet);
    }

    @Override
    public Iterable<Bullet> getBullets() {
        return _readOnlyAliveBullets;
    }

    @Override
    public void update(float time) {
        Iterator<Bullet> it = _aliveBullets.iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            bullet.update(time);
            if (bullet.isDestroyed()) {
                it.remove();
                _availableBullets.add(bullet);
            }
        }
    }

    @Override
    public boolean isDestroyed() {
        return _isDestroyed;
    }

    @Override
    public void destroy() {
        if (_isDestroyed)
            return;
        _isDestroyed = true;
        for (IDestructibleListener listener : _destructibleListeners)
            listener.onDestroyed(this);
    }

    @Override
    public void addDestructibleListener(IDestructibleListener listener) {
        if (listener != null)
            _destructibleListeners.add(listener);
    }

    @Override
    public void removeDestructibleListener(IDestructibleListener listener) {
        if (listener != null)
            _destructibleListeners.remove(listener);
    }
}
