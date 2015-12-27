package com.polly5315.skycleaner;

import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;

public class Bullet implements IEntity, IPlaceable, IInitable<Bullet.InitParams> {
    public static class InitParams {
        public float x;
        public float y;
    }

    private boolean _isDestroyed = true;
    private Set<IDestructibleListener> _destructibleListeners = new HashSet<IDestructibleListener>();
    private float _x, _y;
    private float _speed = 300;

    @Override
    public void init(InitParams initParams) {
        if (initParams == null)
            throw new IllegalArgumentException("initParams cannot be null");
        _isDestroyed = false;
        _x = initParams.x;
        _y = initParams.y;
    }

    @Override
    public void update(float time) {
        if (_isDestroyed)
            return;
        _y += _speed * time;
        if (_y > 400)
            destroy();
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
    public void setX(float x) {
        _x = x;
    }

    @Override
    public void setY(float y) {
        _y = y;
    }

    @Override
    public void setPosition(float x, float y) {
        _x = x;
        _y = y;
    }

    @Override
    public void setPosition(Vector2 position) {
        _x = position.x;
        _y = position.y;
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
