package com.polly5315.skycleaner.viewModel.gameViewModel.entities.factories;

import com.badlogic.gdx.physics.box2d.World;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBlast;
import com.polly5315.skycleaner.viewModel.gameViewModel.IEntityManager;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.Bullet;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBullet;

public class BulletFactory implements IBulletFactory {
    private final IEntityManager<IBlast> _blastManager;
    private final World _world;

    public BulletFactory(IEntityManager<IBlast> blastManager, World world) {
        if (blastManager == null)
            throw new IllegalArgumentException("blastManager cannot be null");
        if (world == null)
            throw new IllegalArgumentException("world cannot be null");
        _blastManager = blastManager;
        _world = world;
    }

    @Override
    public IBullet create(float x, float y) {
        return new Bullet(x, y, _blastManager, _world);
    }
}
