package com.fixertin.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fixertin.game.screens.GameScreen;
import com.fixertin.game.screens.MainGameScreen;

public class CommieGame extends Game {
	public static GameScreen mainGameScreen;
	
	@Override
	public void create () {
		mainGameScreen = new MainGameScreen();
		setScreen(mainGameScreen);
	}
	
	@Override
	public void dispose () {
		mainGameScreen.dispose();

	}
}
