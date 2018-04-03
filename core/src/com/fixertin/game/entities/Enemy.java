package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.ai.AI;
import com.fixertin.game.ai.MoveTo;
import com.fixertin.game.ai.patterns.Shoot;
import com.fixertin.game.ai.patterns.ShootPattern;


import java.util.ArrayList;
import java.util.Stack;

public class Enemy extends Entity{
    public ArrayList<AI> movements = new ArrayList<AI>();
    public ShootPattern test;
    public int index = 0;


    /**
     * @param x       midpoint x of the texture and bounding box. not scaled for ppm
     * @param y       midpoint y of the texture and bounding box. not scaled for ppm
     * @param velx    not scaled for PPM
     * @param vely    not scaled for PPM
     * @param width   not scaled for scale or PPM
     * @param height  not scaled for scale or PPM
     * @param texture
     * @param scale   used for drawing the texture
     * @param PPM     used for drawing the texture
     * @param angle
     */
    public Enemy(float x, float y, float velx, float vely, float width, float height, TextureRegion texture, float scale, float PPM, float angle) {
        super(x, y, velx, vely, width, height, texture, scale, PPM, angle);
        makeTest()
    }

    public void makeTest(){
        Bullet bullet = new Bullet(0, 0, 3/PPM, 3/PPM,
        float height, TextureRegion texture, float scale, float PPM, float angle, float acceleration,
        float angleChangePerTick, float speed);
        Shoot shoot = new Shoot();
    }

    @Override
    public void update(float deltaTime){
        if(movements.get(index).getState() == null)
            movements.get(index).start();
        if(movements.get(index).isSuccess() && index+1 < movements.size()){
            index+=1;
            movements.get(index).reset(this);
        }else if(index+1 <= movements.size()){
            movements.get(index).update();
        }else{
            removed = true;
        }
        super.update(deltaTime);
    }

    public void addMoveTo(float distance, float angle, float speed){
        movements.add(new MoveTo(this, distance, angle, speed));
    }


}
