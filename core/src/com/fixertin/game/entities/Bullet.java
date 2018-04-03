package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.util.BulletMath;

public class Bullet extends Entity{
    private float acceleration, speed, angleChangePerTick;

    public Bullet(float x, float y, float width,
                  float height, TextureRegion texture, float scale, float PPM, float angle, float acceleration,
                  float angleChangePerTick, float speed) {
        super(x, y, width, height, texture, scale, PPM, angle);
        this.acceleration = acceleration;
        this.angleChangePerTick = angleChangePerTick;
    }

    @Override
    public void update(float deltaTime){
        velocity = BulletMath.getVelocity(speed, angle);
        position.mulAdd(velocity, deltaTime);
        //centerBoundingBoxOnTexture();
        updateBoundingBox();
        speed += acceleration*deltaTime;
        angle += angleChangePerTick*deltaTime;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAngleChangePerTick() {
        return angleChangePerTick;
    }

    public void setAngleChangePerTick(float angleChangePerTick) {
        this.angleChangePerTick = angleChangePerTick;
    }
}
