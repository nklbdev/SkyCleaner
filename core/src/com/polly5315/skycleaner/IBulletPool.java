package com.polly5315.skycleaner;

public interface IBulletPool extends IEntity {
    void addBullet(float x, float y);
    Iterable<Bullet> getBullets();
}
