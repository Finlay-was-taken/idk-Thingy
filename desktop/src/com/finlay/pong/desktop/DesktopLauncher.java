package com.finlay.pong.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.finlay.pong.PongGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Pong");
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setIdleFPS(60);
		config.setWindowedMode(700, 700);
		config.setWindowSizeLimits(300, 300, -1, -1);
		config.setResizable(true);
		config.useOpenGL3(true, 3, 3);
		config.setDecorated(true); // Could be used for windowed fullscreen
		config.setWindowIcon(Files.FileType.Internal, "textures/icon.png");
		new Lwjgl3Application(new PongGame(), config);
	}
}
