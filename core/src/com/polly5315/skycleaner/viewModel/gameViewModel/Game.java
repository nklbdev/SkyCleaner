package com.polly5315.skycleaner.viewModel.gameViewModel;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.*;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.factories.BulletFactory;

public class Game implements IGame {
    private final float _timeStep = 1/300f;
    private float _timeBuffer = 0;
    private final IShip _ship;
    private final World _world;
    private IEntityManager<IBullet> _bulletManager = new EntityManager<IBullet>();
    private IEntityManager<IBlast> _blastManager = new EntityManager<IBlast>();
    private IEntityManager<IEnemy> _enemyManager = new EntityManager<IEnemy>();

    public Game() {
        _world = new World(new Vector2(0, 0), true);
        _world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Object a = contact.getFixtureA().getBody().getUserData();
                Object b = contact.getFixtureB().getBody().getUserData();
                if (a instanceof Bullet && b instanceof Enemy) {
                    ((Bullet) a).allowDestroy();
                    ((Enemy) b).allowDestroy();
                } else if (a instanceof Enemy && b instanceof Bullet) {
                    ((Enemy) a).allowDestroy();
                    ((Bullet) b).allowDestroy();
                }
            }

            @Override
            public void endContact(Contact contact) { }
            @Override
            public void preSolve(Contact contact, Manifold oldManifold) { }
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) { }
        });
        _ship = new Ship(_bulletManager, new BulletFactory(_world));
        _enemyManager.addEntity(new Enemy(100, 500, _world, _blastManager));
        _enemyManager.addEntity(new Enemy(500, 600, _world, _blastManager));
        _enemyManager.addEntity(new Enemy(300, 700, _world, _blastManager));
        _enemyManager.addEntity(new Enemy(150, 900, _world, _blastManager));
        _enemyManager.addEntity(new Enemy(400, 1050, _world, _blastManager));
        _enemyManager.addEntity(new Enemy(50, 1200, _world, _blastManager));
        _enemyManager.addEntity(new Enemy(300, 1400, _world, _blastManager));
        _enemyManager.addEntity(new Enemy(100, 1550, _world, _blastManager));
    }

    @Override
    public IShipViewModel getShip() {
        return _ship;
    }

    @Override
    public IDestructibleManagerViewModel<? extends IBulletViewModel> getBulletManagerViewModel() {
        return _bulletManager;
    }

    @Override
    public IDestructibleManagerViewModel<? extends IBlastViewModel> getBlastManagerViewModel() {
        return _blastManager;
    }

    @Override
    public IDestructibleManagerViewModel<? extends IEnemyViewModel> getEnemyManagerViewModel() {
        return _enemyManager;
    }

    @Override
    public void update(float time) {
        _timeBuffer += time;
        while (_timeBuffer >= _timeStep) {
            _world.step(_timeStep, 6, 2);
            _ship.update(_timeStep);
            _bulletManager.update(_timeStep);
            _blastManager.update(_timeStep);
            _enemyManager.update(_timeStep);
            _timeBuffer -= _timeStep;
        }
    }
}
