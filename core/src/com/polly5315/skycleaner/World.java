package com.polly5315.skycleaner;

import java.util.HashSet;
import java.util.Set;

public class World implements IWorld, IDestructibleListener {
    private Set<IEntity> _entities = new HashSet<IEntity>();
    private Set<IWorldListener> _listeners = new HashSet<IWorldListener>();

    @Override
    public void addEntity(IEntity entity) {
        if (entity == null)
            return;
        if (!_entities.add(entity))
            return;
        entity.addDestructibleListener(this);
        for (IWorldListener listener : _listeners)
            listener.onEntityAdded(this, entity);
    }

    @Override
    public void removeEntity(IEntity entity) {
        if (entity == null)
            return;
        if (!_entities.remove(entity))
            return;
        entity.removeDestructibleListener(this);
        for (IWorldListener listener : _listeners)
            listener.onEntityRemoved(this, entity);
    }

    @Override
    public void addWorldListener(IWorldListener listener) {
        if (listener != null)
            _listeners.add(listener);
    }

    @Override
    public void removeWorldListener(IWorldListener listener) {
        if (listener != null)
            _listeners.remove(listener);
    }

    @Override
    public void update(float time) {
        for (IEntity entity : _entities)
            entity.update(time);
    }

    @Override
    public void onDestroyed(IDestructible destructible) {
        if (destructible instanceof IEntity)
            removeEntity((IEntity) destructible);
    }
}
