package com.polly5315.skycleaner;

public interface IWorldListener {
    void onEntityAdded(Object sender, IEntity entity);

    void onEntityRemoved(Object world, IEntity entity);
}
