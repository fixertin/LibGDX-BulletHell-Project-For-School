package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.ai.*;
import com.fixertin.game.screens.MainGameScreen;


import java.util.ArrayList;

public class Enemy extends Entity{
    public ArrayList<AI> movements = new ArrayList<AI>();
    public int index = 0;
    public float dyingTime = .75f;
    public int health;




    /**
     * @param x       midpoint x of the texture and bounding box. not scaled for ppm
     * @param y       midpoint y of the texture and bounding box. not scaled for ppm
     * @param velx    not scaled for PPM
     * @param vely    not scaled for PPM
     * @param width   not scaled for scale or PPM
     * @param height  not scaled for scale or PPM
     * @param textures textures
     * @param scale   used for drawing the texture
     * @param PPM     used for drawing the texture
     * @param angle
     */
    public Enemy(float x, float y, float velx, float vely, float width, float height, TextureRegion[] textures, float scale, float PPM, float angle) {
        super(x, y, velx, vely, width, height, textures, scale, PPM, angle);
        health = 5;

    }
    public Enemy(float x, float y, float velx, float vely, float width, float height, TextureRegion[] textures, float scale, float PPM, float angle, int health) {
        super(x, y, velx, vely, width, height, textures, scale, PPM, angle);
        this.health = health;
    }

    private float dyingTimer=0;
    @Override
    public void update(float deltaTime){
        if(health <= 0){
            dead = true;
        }
        if(!movements.isEmpty() && !dead){
            if(movements.get(index).getState() == null)
                movements.get(index).reset(this);
            if(movements.get(index).isSuccess() && index+1 < movements.size()){
                index+=1;
                movements.get(index).reset(this);
            }else if(index+1 <= movements.size() && !movements.get(index).isSuccess()){
                movements.get(index).update(deltaTime);
            }else{
                removed = true;
            }
        }
        if(!dead){
            super.update(deltaTime);
        }
        if(dead){
            if(dyingTimer == 0)
                activeTexture = textures[2];
            dyingTimer += deltaTime;
            if(dyingTimer >= dyingTime){
                setRemoved(true);
            }
        }

    }

    public void removeHealth(int amount){
        health -= amount;
    }

    public void addMoveTo(float distance, float angle, float speed){
        movements.add(new MoveTo(this, distance, angle, speed));
    }
    public void addShootArc(TextureRegion texture, float shotgap, float startAngle, float arcSize, float speed){
        movements.add(new ShootArc(this, texture, shotgap, startAngle, arcSize, speed));
    }
    public void addShootCircle(TextureRegion texture, float shotgap, float speed){
        movements.add(new ShootCircle(this, texture, shotgap, speed));
    }
    public void addShootAndTurn(TextureRegion texture, float timeUntilFinished, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount, float timeUntilTurn, float shotTimeGap, float acceleration){
        movements.add(new ShootAndTurn(this, texture, timeUntilFinished, amount, spreadAngle, startAngle, speed, incrementAngleAmount, timeUntilTurn, shotTimeGap, acceleration));
    }
    public void addWait(float timeUntilFinished){
        movements.add(new Wait(this, timeUntilFinished));
    }
    public void addMoveInCurve(Vector2[] dataSet, float speed){
        movements.add(new MoveInCurve(this, dataSet, speed));
    }



}
