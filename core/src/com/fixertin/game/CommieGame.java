package com.fixertin.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Screen;
import com.fixertin.game.screens.*;

public class CommieGame extends Game {
	public static GameScreen mainGameScreen;
	public static LostScreen loseScreen;
	public static WinScreen winScreen;
	public static Test test;


	@Override
	public void create () {
		mainGameScreen = new MainGameScreen(this);
		test = new Test();
		loseScreen = new LostScreen();
		winScreen = new WinScreen();
		setScreen(mainGameScreen);
	}
	
	@Override
	public void dispose () {
		mainGameScreen.dispose();
		test.dispose();
		loseScreen.dispose();
		winScreen.dispose();
	}



}
