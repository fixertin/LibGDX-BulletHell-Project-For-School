package com.fixertin.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fixertin.game.CommieGame;
import com.fixertin.game.screens.SplineTestScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 720;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;

		new LwjglApplication(new CommieGame(), config);
	}
}
