package com.polly5315.skycleaner;

import com.polly5315.skycleaner.viewModel.gameViewModel.Game;
import com.polly5315.skycleaner.views.screens.GameView;

public class SkyCleanerGame extends com.badlogic.gdx.Game {
	@Override
	public void create () {
        setScreen(new GameView(new Game()));
	}
}
