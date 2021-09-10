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

    public void update_stuff(float delta, Padddles paddle1, Padddles paddle2) {
        if(pos.x + radius >= 100 || pos.x - radius<= 0 || check_hcol(paddle1, paddle2)||
                check_ccol(paddle1, paddle2))vel.x = -vel.x;
        if(pos.y + radius >= 100 || pos.y- radius <= 0)vel.y = -vel.y;
        if(check_vcol(paddle1, paddle2)||check_ccol(paddle1, paddle2)) {
            if (pos.x < 50 && pos.y < paddle1.pos.y) pos.y = paddle1.pos.y - radius;
            else if (pos.x < 50 && pos.y > paddle1.pos.y + paddle1.dimensions.y)
                pos.y = paddle1.pos.y + paddle1.dimensions.y + radius;
            else if (pos.x > 50 && pos.y > paddle2.pos.y + paddle2.dimensions.y)
                pos.y = paddle2.pos.y + paddle2.dimensions.y + radius;
            else pos.y = paddle2.pos.y - radius;
            vel.y = -vel.y;
        }
        pos.x += vel.x * delta;
        pos.y += vel.y * delta;
    }


    public void draw_stuff(SpriteBatch batch) {
        batch.draw(img, pos.x-radius, pos.y-radius, radius*2, radius*2);
    }

    public boolean check_hcol(Padddles paddle1, Padddles paddle2){
        if(pos.x - radius <= paddle1.pos.x + paddle1.dimensions.x && pos.y< paddle1.pos.y + paddle1.dimensions.y
                && pos.y > paddle1.pos.y) return true;
        if(pos.x + radius >= paddle2.pos.x && pos.y< paddle2.pos.y + paddle2.dimensions.y
                && pos.y > paddle2.pos.y)return true;
        return false;
    }

    public boolean check_vcol(Padddles paddle1, Padddles paddle2){
        if((pos.x <= paddle1.pos.x + paddle1.dimensions.x && pos.x - radius > paddle1.pos.x) &&
                (pos.y - radius<= paddle1.pos.y + paddle1.dimensions.y && pos.y+radius >= paddle1.pos.y)) return true;
        if((pos.x > paddle2.pos.x && pos.x + radius < paddle2.pos.x + paddle2.dimensions.x)
                && (pos.y - radius <= paddle2.pos.y + paddle2.dimensions.y && pos.y+radius >= paddle2.pos.y)) return true;
        return false;
    }

    public boolean check_ccol(Padddles paddle1, Padddles paddle2){
        if(pos.dst2(paddle1.pos.cpy().add(paddle1.dimensions.cpy())) <radius*radius) return true;
        if(pos.dst2(paddle1.pos.cpy().add(paddle1.dimensions.x,0)) < radius*radius) return true;
        if(pos.dst2(paddle2.pos.cpy().add(0,paddle2.dimensions.y)) < radius*radius) return true;
        if(pos.dst2(paddle2.pos.cpy()) < radius*radius) return true;

        return false;
    }

}
