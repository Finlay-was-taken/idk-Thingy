package com.finlay.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;

public class PongGame extends ApplicationAdapter {
	OrthographicCamera cam;
	SpriteBatch batch;
	Texture img;
	float delta;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera(); // (0, 0) is the initial centre
		img = new Texture("textures/badlogic.jpg");
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		cam.viewportWidth = width;
		cam.viewportHeight = height;
		cam.update();
	}

	@Override
	public void render () {
		delta = Math.min(0.05f, Gdx.graphics.getDeltaTime()); // Time since the last frame
		// Delta is clamped to a maximum of 0.05 seconds to avoid skipping too many frames ("jumps")

		ScreenUtils.clear(1, 0, 0, 1);

		cam.rotate(45 * delta); // Simple camera test
		cam.update(); // The camera must be updated after its properties have been changed in order for them to apply

		batch.setProjectionMatrix(cam.combined); // Sprites drawn using batch are transformed by the camera beforehand
		/* Setting the projection matrix to a scaled identity matrix will draw relative to the screen, which is useful for GUI:
		 * batch.setProjectionMatrix((new Matrix4()).scl(1 / size);
		 */
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
