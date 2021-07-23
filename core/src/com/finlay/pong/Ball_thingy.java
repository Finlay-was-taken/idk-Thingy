package com.finlay.pong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ball_thingy {
    float xvel, yvel, xpos, ypos;
    int ball_width, ball_height;
    Texture img;

    public Ball_thingy(Texture texture, float xvel, float yvel, float xpos, float ypos, int ball_width, int ball_height){
        this.img = texture;
        this.xvel = xvel;
        this.yvel = yvel;
        this.xpos = xpos;
        this.ypos = ypos;
        this.ball_height = ball_height;
        this.ball_width = ball_width;
    }

    public void move_stuff(float delta){
        xpos += xvel * delta;
        ypos += yvel * delta;
    }

    public void hit_stuff(){
        if(xpos+ball_width >= 100 || xpos <= 0 ){xvel = -xvel;}
        if(ypos+ball_height >= 100 || ypos <= 0) {yvel = -yvel;}
    }

    public void draw_stuff(SpriteBatch batch){
        batch.draw(img,xpos,ypos,ball_width,ball_height);
    }

}
