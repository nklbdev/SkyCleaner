package com.polly5315.skycleaner.viewModel.gameViewModel.entities.factories;

import com.badlogic.gdx.physics.box2d.World;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBlast;
import com.polly5315.skycleaner.viewModel.gameViewModel.IEntityManager;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.Bullet;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBullet;

public class BulletFactory implements IBulletFactory {
    private final World _world;

    public BulletFactory(World world) {
        if (world == null)
            throw new IllegalArgumentException("world cannot be null");
        _world = world;
    }

    @Override
    public IBullet create(float x, float y) {
        return new Bullet(x, y, _world);
    }
}
