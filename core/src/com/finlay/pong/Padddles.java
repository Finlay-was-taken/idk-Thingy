package com.finlay.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Padddles {
    Vector2 dimensions, pos;
    Texture img;
    int vel;
    boolean paddle_side;

    public Padddles(Texture texture,Vector2 pos,Vector2 dimensions, boolean left){
        this.dimensions = dimensions;
        this.pos = pos;
        this.img = texture;
        this.paddle_side = left;
    }
    public void update_shmupdate(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && pos.y + dimensions.y <= 100 && !paddle_side){vel = 30;}
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && pos.y >= 0 && !paddle_side){vel = -30;}
        else if(Gdx.input.isKeyPressed(Input.Keys.W) && pos.y + dimensions.y <= 100 && paddle_side){vel = 30;}
        else if (Gdx.input.isKeyPressed(Input.Keys.S) && pos.y >= 0 && paddle_side){vel = -30;}
        else vel = 0;
        pos.y += vel*delta;
//        if(pos.y+dimensions.y>= 100 || pos.y<= 0)vel = 0;
    }

    public void draw_shmaw(SpriteBatch batch){
        batch.draw(img, pos.x, pos.y, dimensions.x, dimensions.y);

    }
}
