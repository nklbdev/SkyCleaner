package com.polly5315.skycleaner.viewModel.gameViewModel.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.polly5315.skycleaner.viewModel.gameViewModel.IEntityManager;

public class Enemy extends EntityBase implements IEnemy {
    private final float _speed = 5;
    private final Body _body;
    private final IEntityManager<IBlast> _blastManager;

    private static final BodyDef _bodyDef;
    private static final FixtureDef _fixtureDef;
    private static final Shape _shape;

    static {
        _bodyDef = new BodyDef() {{
            //bullet = true;
            fixedRotation = true;
            type = BodyType.DynamicBody;
        }};
        _shape = new CircleShape() {{
            setRadius(1);
        }};
        _fixtureDef = new FixtureDef() {{
            //isSensor = true;
            shape = _shape;
        }};
    }

    public Enemy(float x, float y, World world, IEntityManager<IBlast> blastManager) {
        if (world == null)
            throw new IllegalArgumentException("world cannot be null");
        if (blastManager == null)
            throw new IllegalArgumentException("blastManager cannot be null");
        _blastManager = blastManager;
        _body = world.createBody(_bodyDef);
        _body.setUserData(this);
        _body.createFixture(_fixtureDef);
        _body.setTransform(x/20f, y/20f, 0);
        _body.setLinearVelocity(0, -_speed);
    }

    @Override
    public void onUpdate(float time) {
        //
    }

    @Override
    public void onDestroy() {
        _blastManager.addEntity(new Blast(getX(), getY()));
        _body.getWorld().destroyBody(_body);
    }

    @Override
    public float getX() {
        if (isDestroyed())
            return 0;
        return _body.getPosition().x * 20;
    }

    @Override
    public float getY() {
        if (isDestroyed())
            return 0;
        return _body.getPosition().y * 20;
    }
}
