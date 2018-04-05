package com.fixertin.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.util.Constant;

public class Player extends Entity{
    public float speed = 5f/ Constant.PPM;

    public Player(float x, float y, float velx, float vely, float width, float height, TextureRegion texture, float scale, float PPM, float angle) {
        super(x, y, velx, vely, width, height, texture, scale, PPM, angle);
    }

    @Override
    public void update(float deltaTime){
        input();
        position.mulAdd(velocity, deltaTime);
        if(acceleration != null)
            velocity.mulAdd(acceleration, deltaTime);
        //centerBoundingBoxOnTexture();
        updateBoundingBox();
    }

    public void input(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)|| Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.y += speed/PPM;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)|| Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= speed/PPM;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)|| Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.y -= speed/PPM;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)|| Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.x += speed/PPM;
        }
    }

}
