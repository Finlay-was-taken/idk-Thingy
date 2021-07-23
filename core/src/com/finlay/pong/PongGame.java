package com.finlay.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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
	AssetManager assets;
	Texture bg, ballTexture;
	float delta;
	final int WORLD_WIDTH = 100, WORLD_HEIGHT = 100;
	boolean getAssets;
	Matrix4 identity;
	Ball_thingy ball;

	@Override
	public void create () {
		batch = new SpriteBatch();

		assets = new AssetManager();
		assets.load("textures/bg.png", Texture.class);
		assets.load("textures/ball.png", Texture.class);
		getAssets = true;

		identity = new Matrix4();

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

		if(!assets.update(16)) {
			ScreenUtils.clear(0, 0, 1, 1);
			// Asset loading screen
			// assets.getProgress();
			return;
		}

		ScreenUtils.clear(0, 0, 0, 1);

		if(getAssets) { // Only called once, right after asset loading finishes
			getAssets = false;
			bg = assets.get("textures/bg.png", Texture.class);
			ballTexture = assets.get("textures/ball.png", Texture.class);

			ball = new Ball_thingy(ballTexture, 20, 30, 50,50,20,20);
		}

		// Draws a background texture to fill the viewport
		batch.setProjectionMatrix(identity);
		batch.begin();
		batch.draw(bg, -1, -1, 2, 2);
		batch.end();

		batch.setProjectionMatrix(cam.combined); // Sprites drawn using batch are transformed by the camera beforehand
		/* Setting the projection matrix to a scaled identity matrix will draw relative to the screen, which is useful for GUI:
		 * batch.setProjectionMatrix((new Matrix4()).scl(1 / size);
		 */
		batch.begin();
		ball.draw_stuff(batch);
		batch.end();

		ball.hit_stuff();
		ball.move_stuff(delta);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assets.dispose();
	}
}
