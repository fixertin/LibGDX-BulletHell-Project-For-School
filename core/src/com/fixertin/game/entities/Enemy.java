package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.ai.*;
import com.fixertin.game.screens.MainGameScreen;


import java.util.ArrayList;

public class Enemy extends Entity{
    public ArrayList<AI> movements = new ArrayList<AI>();
    public int index = 0;
    public float dyingTime = .75f;
    public int health;
    public boolean hit;



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
    public Enemy(float x, float y, float velx, float vely, float width, float height, Sprite[] textures, float scale, float PPM, float angle) {
        super(x, y, velx, vely, width, height, textures, scale, PPM, angle);
        health = 5;

    }
    public Enemy(float x, float y, float velx, float vely, float width, float height, Sprite[] textures, float scale, float PPM, float angle, int health) {
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
                setActiveTexture(2);
            dyingTimer += deltaTime;
            if(dyingTimer >= dyingTime){
                setRemoved(true);
            }
        }

    }

    @Override
    public void render(Batch batch, ShapeRenderer sp, float deltaTime) {
        textureWidth = activeTexture.getRegionWidth();
        textureHeight = activeTexture.getRegionHeight();
        update(deltaTime);
        if(hit){
            activeTexture.setColor(Color.RED);
            hit = false;
        } else {
            activeTexture.setColor(Color.WHITE);
        }
        batch.begin();
        activeTexture.setBounds(position.x - activeTexture.getRegionWidth()/scale/PPM/2f,
                position.y - activeTexture.getRegionHeight()/scale/PPM/2f,
                activeTexture.getRegionWidth()/scale/PPM,
                activeTexture.getRegionHeight()/scale/PPM);
        activeTexture.draw(batch);
        batch.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        sp.rect(boundingBox.x - boundingBox.width/2,
                boundingBox.y - boundingBox.height/2,
                boundingBox.width,
                boundingBox.height);
        sp.end();
    }

    public void removeHealth(int amount){
        health -= amount;
        hit = true;
    }

    public void addMoveTo(float distance, float angle, float speed){
        movements.add(new MoveTo(this, distance, angle, speed));
    }
    public void addShootArc(Sprite texture, float shotgap, float startAngle, float arcSize, float speed){
        movements.add(new ShootArc(this, texture, shotgap, startAngle, arcSize, speed));
    }
    public void addShootCircle(Sprite texture, float shotgap, float speed){
        movements.add(new ShootCircle(this, texture, shotgap, speed));
    }
    public void addShootAndTurn(Sprite texture, float timeUntilFinished, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount, float timeUntilTurn, float shotTimeGap, float acceleration){
        movements.add(new ShootAndTurn(this, texture, timeUntilFinished, amount, spreadAngle, startAngle, speed, incrementAngleAmount, timeUntilTurn, shotTimeGap, acceleration));
    }
    public void addWait(float timeUntilFinished){
        movements.add(new Wait(this, timeUntilFinished));
    }
    public void addMoveInCurve(Vector2[] dataSet, float speed){
        movements.add(new MoveInCurve(this, dataSet, speed));
    }



}
