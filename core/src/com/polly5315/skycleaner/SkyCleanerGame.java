package com.polly5315.skycleaner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkyCleanerGame extends ApplicationAdapter {
	SpriteBatch _batch;
	IWorld _world;
    IShip _ship;
    IPresenter _worldPresenter;

	@Override
	public void create () {
		_batch = new SpriteBatch();
        _world = new World();
        IBulletPool bulletPool = new BulletPool(20, new BulletFactory());
        _worldPresenter = new WorldPresenter(_world, new PresenterFactory());
        _ship = new Ship();
        _ship.setPosition(100, 100);
        _ship.setSideSpeed(200);
        _ship.setWeapon(new StandardWeapon(bulletPool, _ship));
        _world.addEntity(bulletPool);
        _world.addEntity(_ship);
	}

	@Override
	public void render () {
        final float time = Gdx.graphics.getDeltaTime();

        _ship.setDesiredPosition(Gdx.input.getX());
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            _ship.fire();
        _ship.update(time);

        _world.update(time);
        _worldPresenter.update(time);

        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_batch.begin();
        _worldPresenter.draw(_batch);
		_batch.end();
	}
}
