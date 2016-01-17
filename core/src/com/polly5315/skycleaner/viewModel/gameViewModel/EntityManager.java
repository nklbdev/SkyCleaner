package com.polly5315.skycleaner.viewModel.gameViewModel;

import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IEntity;

import java.util.*;

public class EntityManager<TEntity extends IEntity> implements IEntityManager<TEntity> {
    private final Set<TEntity> _entities = new HashSet<TEntity>();
    private final Set<IDestructibleManagerViewModelListener> _listeners = new HashSet<IDestructibleManagerViewModelListener>();

    @Override
    public void addEntity(TEntity entity) {
        if (entity != null)
            if (_entities.add(entity))
                for (IDestructibleManagerViewModelListener listener : _listeners)
                    listener.onItemAdded(entity);
    }

    @Override
    public <T extends IDestructibleManagerViewModelListener> void addDestructibleManagerViewModelListener(T listener) {
        if (listener != null)
            _listeners.add(listener);
    }

    @Override
    public <T extends IDestructibleManagerViewModelListener> void removeDestructibleManagerViewModelListener(T listener) {
        if (listener != null)
            _listeners.remove(listener);
    }

    @Override
    public Collection<TEntity> getItems() {
        return Collections.unmodifiableCollection(_entities);
    }

    @Override
    public void update(float time) {
        Iterator<TEntity> iterator = _entities.iterator();
        while (iterator.hasNext()) {
            TEntity entity = iterator.next();
            if (entity.isDestroyed())
                iterator.remove();
            else
                entity.update(time);
        }
    }
}
