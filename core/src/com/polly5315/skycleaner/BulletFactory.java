package com.polly5315.skycleaner;

public class BulletFactory implements IBulletFactory {
    @Override
    public Bullet createBullet() {
        return new Bullet();
    }
}
