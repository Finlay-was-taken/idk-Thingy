package com.finlay.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.finlay.pong.PongGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "oi oi";
		config.width = 700;
		config.height = 700;
		new LwjglApplication(new PongGame(), config);
	}
}
