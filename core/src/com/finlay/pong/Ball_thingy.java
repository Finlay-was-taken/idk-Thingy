package com.finlay.pong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ball_thingy {
    Vector2 vel,pos;
    int radius;
    Texture img;

    public Ball_thingy(Texture texture, Vector2 vel, Vector2 pos,int radius) {
        this.img = texture;
        this.vel = vel;
        this.pos = pos;
        this.radius = radius;
    }

    public void update_stuff(float delta) {
        if(pos.x + radius >= 100 || pos.x - radius<= 0 ) vel.x = -vel.x;
        if(pos.y + radius >= 100 || pos.y- radius <= 0) vel.y = -vel.y;
        pos.x += vel.x * delta;
        pos.y += vel.y * delta;
    }


    public void draw_stuff(SpriteBatch batch) {
        batch.draw(img, pos.x-radius, pos.y-radius, radius*2, radius*2);
    }

}
