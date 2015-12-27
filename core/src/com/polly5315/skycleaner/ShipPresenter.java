package com.polly5315.skycleaner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.HashSet;
import java.util.Set;

public class ShipPresenter implements IPresenter, IDestructibleListener {
    private boolean _isDestroyed = false;
    private IShip _ship;
    private Texture _texture;
    private Set<IDestructibleListener> _destructibleListeners = new HashSet<IDestructibleListener>();

    public ShipPresenter(IShip ship) {
        if (ship == null)
            throw new IllegalArgumentException("ship cannot be null");
        _ship = ship;
        _ship.addDestructibleListener(this);
        _texture = new Texture("ship.png");
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(_texture, _ship.getX() - _texture.getWidth() / 2, _ship.getY() - _texture.getHeight() / 2);
    }

    @Override
    public void update(float time) {
        //
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
    public void onDestroyed(IDestructible destructible) {
        destroy();
    }
}
