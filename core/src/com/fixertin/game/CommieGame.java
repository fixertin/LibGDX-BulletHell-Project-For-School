package com.fixertin.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Screen;
import com.fixertin.game.screens.GameScreen;
import com.fixertin.game.screens.LostScreen;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.screens.Test;

public class CommieGame extends Game {
	public static GameScreen mainGameScreen;
	public static LostScreen loseScreen;
	public static Test test;


	@Override
	public void create () {
		mainGameScreen = new MainGameScreen(this);
		test = new Test();
		loseScreen = new LostScreen();
		setScreen(mainGameScreen);
	}
	
	@Override
	public void dispose () {
		mainGameScreen.dispose();
		test.dispose();
		loseScreen.dispose();
	}



}
