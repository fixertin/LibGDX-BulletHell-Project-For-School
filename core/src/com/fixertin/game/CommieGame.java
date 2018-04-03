package com.fixertin.game;

import com.badlogic.gdx.Game;

import com.fixertin.game.screens.GameScreen;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.screens.Test;

public class CommieGame extends Game {
	public static GameScreen mainGameScreen;
	public static Test test;

	@Override
	public void create () {
		mainGameScreen = new MainGameScreen();
		test = new Test();
		setScreen(mainGameScreen);
	}
	
	@Override
	public void dispose () {
		mainGameScreen.dispose();
		test.dispose();
	}
}
