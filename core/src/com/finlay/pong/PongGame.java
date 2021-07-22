package com.finlay.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PongGame extends ApplicationAdapter {
	Viewport view;
	OrthographicCamera cam;
	SpriteBatch batch;
	Texture img, bg;
	float delta;
	final int WORLD_WIDTH = 100, WORLD_HEIGHT = 100;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("textures/badlogic.jpg");
		bg = new Texture("textures/bg.png");
		cam = new OrthographicCamera();
		view = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, cam); // Fixes the aspect ratio
		view.apply(true);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		view.update(width, height);
	}

	@Override
	public void render () {
		delta = Math.min(0.05f, Gdx.graphics.getDeltaTime()); // Time since the last frame
		// Delta is clamped to a maximum of 0.05 seconds to avoid skipping too many frames ("jumps")

		ScreenUtils.clear(0, 0, 0, 1);

		cam.rotate(45 * delta); // Simple camera test
		cam.update(); // The camera must be updated after its properties have been changed in order for them to apply

		// Draws a background texture to fill the viewport
		batch.setProjectionMatrix(new Matrix4());
		batch.begin();
		batch.draw(bg, -1, -1, 2, 2);
		batch.end();

		batch.setProjectionMatrix(cam.combined); // Sprites drawn using batch are transformed by the camera beforehand
		/* Setting the projection matrix to a scaled identity matrix will draw relative to the screen, which is useful for GUI:
		 * batch.setProjectionMatrix((new Matrix4()).scl(1 / size);
		 */
		batch.begin();
		batch.draw(img, 0, 0, 40, 40);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		bg.dispose();
	}
}
