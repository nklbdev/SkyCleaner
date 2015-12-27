package com.polly5315.skycleaner;

public interface IWorld extends IUpdateable {
    void addEntity(IEntity entity);
    void removeEntity(IEntity entity);
    void addWorldListener(IWorldListener listener);
    void removeWorldListener(IWorldListener listener);
}
