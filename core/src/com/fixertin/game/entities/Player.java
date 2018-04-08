package com.fixertin.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.util.BulletMath;
import com.fixertin.game.util.Constant;

public class Player extends Entity{
    public float speed = 25f/ Constant.PPM;
    public int health, maxHealth;
    public float shotGap = .2f;

    public Player(float x, float y, float velx, float vely, float width, float height, Sprite texture, float scale, float PPM, float angle, int maxHealth) {
        super(x, y, velx, vely, width, height, texture, scale, PPM, angle);
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    @Override
    public void update(float deltaTime){
        if(!dead){
            input(deltaTime);
            position.mulAdd(velocity, deltaTime);
            if(acceleration != null)
                velocity.mulAdd(acceleration, deltaTime);
            //centerBoundingBoxOnTexture();
            updateBoundingBox();
        } else {
            die();
        }
    }

    private float shootTimer=shotGap;
    public void input(float delta){
        setVelocity(0, 0);
        if(Gdx.input.isKeyPressed(Input.Keys.W)|| Gdx.input.isKeyPressed(Input.Keys.UP)){
            velocity.y = speed + speed*delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)|| Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velocity.x = -speed + -speed*delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)|| Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            velocity.y = -speed + -speed*delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)|| Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velocity.x = speed + speed*delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)&&shootTimer<=0){
            shoot();
            shootTimer=shotGap;
        }
        if(shootTimer>0){
            shootTimer -= delta;
        }
    }

    private void die(){

    }

    private void isGoingOffScreen(Vector2 position){
        if(position.x > MainGameScreen.getViewPortHeight()){

        }
    }

    private void shoot(){
        MainGameScreen.addPlayerBullets(new Entity(
                position.x,
                position.y,
                0,
                140/Constant.PPM,
                30/Constant.PPM/Constant.scale,
                30/Constant.PPM/Constant.scale,
                MainGameScreen.assets.hammerAndSickle,
                Constant.scale*2.5f,
                Constant.PPM,
                0));
    }

    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public void lowerHealth(int amount){
        if(health <= 0)
            return;
        health -= amount;
    }
}
