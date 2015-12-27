package com.polly5315.skycleaner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.HashSet;
import java.util.Set;

public class BulletPoolPresenter implements IPresenter {
    private IBulletPool _bulletPool;
    private Texture _texture;
    private boolean _isDestroyed = false;
    private Set<IDestructibleListener> _destructibleListeners = new HashSet<IDestructibleListener>();

    public BulletPoolPresenter(BulletPool bulletPool) {
        if (bulletPool == null)
            throw new IllegalArgumentException("bulletPool cannot be null");
        _bulletPool = bulletPool;
        _texture = new Texture("bullet.png");
    }

    @Override
    public void draw(Batch batch) {
        for (Bullet bullet : _bulletPool.getBullets())
            batch.draw(_texture, bullet.getX() - _texture.getWidth() / 2, bullet.getY() - _texture.getHeight() / 2);
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

    @Override
    public void update(float time) {

    }
}
