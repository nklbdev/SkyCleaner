package com.polly5315.skycleaner.viewModel.gameViewModel.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.polly5315.skycleaner.viewModel.gameViewModel.IEntityManager;

public class Bullet extends EntityBase implements IBullet {
    private final float _maxLifeTime = 3;
    private final float _speed = 10;
    private float _lifeTime = 0;
    private final IEntityManager<IBlast> _blastManager;
    private final Body _body;
    private boolean _isDestroyAllowed = false;

    private static final BodyDef _bodyDef;
    private static final FixtureDef _fixtureDef;
    private static final Shape _shape;

    static {
        _bodyDef = new BodyDef() {{
            bullet = true;
            fixedRotation = true;
            type = BodyType.DynamicBody;
        }};
        _shape = new CircleShape() {{
            setRadius(0.1f);
        }};
        _fixtureDef = new FixtureDef() {{
            isSensor = true;
            shape = _shape;
        }};
    }

    public Bullet(float x, float y, IEntityManager<IBlast> blastManager, World world) {
        if (blastManager == null)
            throw new IllegalArgumentException("blastManager cannot be null");
        if (world == null)
            throw new IllegalArgumentException("world cannot be null");
        _body = world.createBody(_bodyDef);
        _body.setUserData(this);
        _body.createFixture(_fixtureDef);
        _body.setTransform(x/20, y/20, 0);
        _body.setLinearVelocity(0, _speed);
        _blastManager = blastManager;
    }

    @Override
    public float getX() {
        if (isDestroyed())
            return 0;
        return _body.getPosition().x*20;
    }

    @Override
    public float getY() {
        if (isDestroyed())
            return 0;
        return _body.getPosition().y*20;
    }

    @Override
    public void onUpdate(float time) {
        _lifeTime += time;
        if (_lifeTime > _maxLifeTime)
            allowDestroy();
    }

    @Override
    public void onDestroy() {
        _blastManager.addEntity(new Blast(getX(), getY()));
        _body.getWorld().destroyBody(_body);
    }
}
