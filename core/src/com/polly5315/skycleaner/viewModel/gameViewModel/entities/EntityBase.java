package com.polly5315.skycleaner.viewModel.gameViewModel.entities;

import java.util.HashSet;
import java.util.Set;

public abstract class EntityBase implements IEntity {
    private boolean _isDestroyed = false;
    private Set<IDestructibleListener> _destructibleListeners = new HashSet<IDestructibleListener>();
    private boolean _isDestroyAllowed = false;

    @Override
    public boolean isDestroyed() {
        return _isDestroyed;
    }

    @Override
    public void allowDestroy() {
        _isDestroyAllowed = true;
    }

    protected void onDestroy() {
        //
    }

    private void destroy() {
        if (_isDestroyed)
            return;
        onDestroy();
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

    protected void onUpdate(float time) {
        //
    }

    @Override
    public void update(float time) {
        if (_isDestroyed)
            return;
        onUpdate(time);
        if (_isDestroyAllowed)
            destroy();
    }
}
