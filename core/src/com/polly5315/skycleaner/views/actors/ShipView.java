package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.Side;
import com.polly5315.skycleaner.viewModel.gameViewModel.IShipViewModel;

public class ShipView extends Actor {
    private final IShipViewModel _viewModel;
    private Texture _texture;

    public ShipView(IShipViewModel viewModel) {
        if (viewModel == null)
            throw new IllegalArgumentException("viewModel cannot be null");
        _viewModel = viewModel;
        _texture = new Texture("ship.png");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            _viewModel.setMovingSide(Side.LEFT);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            _viewModel.setMovingSide(Side.RIGHT);
        else
            _viewModel.setMovingSide(null);

        _viewModel.setFiring(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(
                _texture,
                _viewModel.getX() - _texture.getWidth() / 2,
                _viewModel.getY() - _texture.getHeight() / 2);
    }
}
