package com.mogsev.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mogsev.game.screen.GameScreen;

public class MyGame extends Game {
	private static final String TAG = "MyGame";
	private SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
        setScreen(new GameScreen(batch));
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
