package com.polly5315.skycleaner;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorldPresenter implements IPresenter, IWorldListener {
    private boolean _isDestroyed = false;
    private Set<IDestructibleListener> _destructibleListeners = new HashSet<IDestructibleListener>();
    private IWorld _world;
    private Map<IEntity, IPresenter> _entityPresenters = new HashMap<IEntity, IPresenter>();
    private IPresenterFactory _presenterFactory;

    public WorldPresenter(IWorld world, IPresenterFactory presenterFactory) {
        if (world == null)
            throw new IllegalArgumentException("world cannot be null");
        if (presenterFactory == null)
            throw new IllegalArgumentException("presenterFactory cannot be null");
        _world = world;
        _world.addWorldListener(this);
        _presenterFactory = presenterFactory;
    }

    @Override
    public void draw(Batch batch) {
        for (IPresenter presenter : _entityPresenters.values())
            presenter.draw(batch);
    }

    @Override
    public void update(float time) {
        for (IPresenter presenter : _entityPresenters.values())
            presenter.update(time);
    }

    @Override
    public void onEntityAdded(Object sender, IEntity entity) {
        _entityPresenters.put(entity, _presenterFactory.createPresenter(entity));
    }

    @Override
    public void onEntityRemoved(Object world, IEntity entity) {
        _entityPresenters.remove(entity);
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
