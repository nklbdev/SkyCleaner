package com.polly5315.skycleaner;

import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;

public class Ship implements IShip {
    private boolean _isDestroyed = false;
    private IWeapon _weapon;
    private float _desiredPosition;
    private float _x, _y;
    private float _forwardSpeed, _sideSpeed;
    private Set<IDestructibleListener> _destructibleListeners = new HashSet<IDestructibleListener>();

    @Override
    public void setWeapon(IWeapon weapon) {
        _weapon = weapon;
    }

    @Override
    public void setForwardSpeed(float forwardSpeed) {
        _forwardSpeed = forwardSpeed;
    }

    @Override
    public void setSideSpeed(float sideSpeed) {
        _sideSpeed = sideSpeed;
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
    public void fire() {
        if (_weapon != null)
            _weapon.fire();
    }

    @Override
    public void setDesiredPosition(float desiredPosition) {
        _desiredPosition = desiredPosition;
    }

    @Override
    public void update(float time) {
        _y += _forwardSpeed * time;
        if (_desiredPosition != _x) {
            final float path = _desiredPosition - _x;
            final float step = Math.signum(path) * _sideSpeed * time;
            _x += path * path > step * step ? step : path;
        }
        if (_weapon != null)
            _weapon.update(time);
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
