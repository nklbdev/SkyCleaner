package com.polly5315.skycleaner;

public interface IDestructible {
    boolean isDestroyed();
    void destroy();
    void addDestructibleListener(IDestructibleListener listener);
    void removeDestructibleListener(IDestructibleListener listener);
}
