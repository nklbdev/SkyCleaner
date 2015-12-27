package com.polly5315.skycleaner;

public class PresenterFactory implements IPresenterFactory {
    @Override
    public IPresenter createPresenter(IEntity entity) {
        if (entity instanceof BulletPool)
            return new BulletPoolPresenter((BulletPool) entity);
        if (entity instanceof IShip)
            return new ShipPresenter((IShip) entity);
        throw new IllegalArgumentException("unsupported entity type");
    }
}
