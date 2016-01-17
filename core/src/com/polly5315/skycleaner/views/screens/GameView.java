package com.polly5315.skycleaner.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.polly5315.skycleaner.viewModel.gameViewModel.*;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBlastViewModel;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBulletViewModel;
import com.polly5315.skycleaner.views.actors.*;
import com.polly5315.skycleaner.views.factories.BlastViewFactory;
import com.polly5315.skycleaner.views.factories.BulletViewFactory;
import com.polly5315.skycleaner.views.factories.EnemyViewFactory;

public class GameView extends ScreenAdapter {
    private final IGameViewModel _viewModel;
    private final Stage _stage;
    SpriteBatch _batch;
    //private float _minWorldWidth = 100, _minWorldHeight = 100, _maxWorldWith = 500, _maxWorldHeight = 500;

    public GameView(IGameViewModel viewModel) {
        _batch = new SpriteBatch();
        if (viewModel == null)
            throw new IllegalArgumentException("viewModel cannot be null");
        _viewModel = viewModel;
        //_stage = new Stage(new ExtendViewport(_minWorldWidth, _minWorldHeight, _maxWorldWith, _maxWorldHeight));
        _stage = new Stage();
        TextureAtlas atlas = new TextureAtlas("explosion.atlas");
        _stage.addActor(new DestructibleViewManager<IBulletViewModel>(_viewModel.getBulletManagerViewModel(), new BulletViewFactory(new Texture("bullet.png"))));
        _stage.addActor(new DestructibleViewManager<IEnemyViewModel>(_viewModel.getEnemyManagerViewModel(), new EnemyViewFactory()));
        _stage.addActor(new ShipView(_viewModel.getShip()));
        _stage.addActor(new DestructibleViewManager<IBlastViewModel>(_viewModel.getBlastManagerViewModel(), new BlastViewFactory(new Animation(0.1f, atlas.getRegions()))));
    }

    @Override
    public void render(float delta) {
        _viewModel.update(delta);
        _stage.act(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _stage.draw();
    }
}
