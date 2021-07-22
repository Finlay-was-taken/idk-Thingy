package com.finlay.pong.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.finlay.pong.PongGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pong";
		config.vSyncEnabled = true;
		config.foregroundFPS = 60;
		config.backgroundFPS = 30;
		config.fullscreen = false;
		config.width = 700;
		config.height = 700;
		config.resizable = true;
		config.samples = 4; // MSAA sample number
		config.undecorated = false; // Could be used for windowed fullscreen
		config.addIcon("textures/icon.png", Files.FileType.Internal);
		new LwjglApplication(new PongGame(), config);
	}
}
