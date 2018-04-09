package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    protected Vector2 position, velocity, acceleration;
    protected float width, height, scale, textureWidth, textureHeight, angle;
    protected final float PPM;
    protected Sprite activeTexture;
    protected Sprite[] textures;
    protected Rectangle boundingBox;
    protected boolean removed, dead, shooting;


    /**
     *
     * @param x midpoint x of the texture and bounding box. not scaled for ppm
     * @param y midpoint y of the texture and bounding box. not scaled for ppm
     * @param velx not scaled for PPM
     * @param vely not scaled for PPM
     * @param width not scaled for scale or PPM
     * @param height not scaled for scale or PPM
     * @param texture
     * @param scale used for drawing the texture
     * @param PPM used for drawing the texture
     */
    public Entity(float x, float y, float velx, float vely, float width, float height, Sprite texture, float scale, float PPM, float angle){
        position = new Vector2(x, y);
        velocity = new Vector2(velx, vely);
        this.width = width;
        this.height = height;
        this.activeTexture = texture;
        this.scale = scale;
        this.PPM = PPM;
        this.angle = angle;
        boundingBox = new Rectangle(x, y, width, height);
        textureWidth = texture.getRegionWidth();
        textureHeight = texture.getRegionHeight();
    }
    public Entity(float x, float y, float velx, float vely, float width, float height, Sprite[] textures, float scale, float PPM, float angle){
        position = new Vector2(x, y);
        velocity = new Vector2(velx, vely);
        this.width = width;
        this.height = height;
        this.textures = textures;
        this.activeTexture = textures[0];
        this.scale = scale;
        this.PPM = PPM;
        this.angle = angle;
        boundingBox = new Rectangle(x, y, width, height);
        textureWidth = activeTexture.getRegionWidth();
        textureHeight = activeTexture.getRegionHeight();
    }
    public Entity(Vector2 position, Vector2 velocity, float width, float height, Sprite texture, float scale, float PPM, float angle){
        this.position = position;
        this.velocity = velocity;
        this.width = width;
        this.height = height;
        this.activeTexture = texture;
        this.scale = scale;
        this.PPM = PPM;
        this.angle = angle;
        boundingBox = new Rectangle(position.x, position.y, width, height);
        textureWidth = texture.getRegionWidth();
        textureHeight = texture.getRegionHeight();
    }
    public Entity(float x, float y, Vector2 velocity, float width, float height, Sprite texture, float scale, float PPM, float angle, Vector2 acceleration){
        this.position = new Vector2(x, y);
        this.velocity = velocity;
        this.width = width;
        this.height = height;
        this.activeTexture = texture;
        this.scale = scale;
        this.PPM = PPM;
        this.angle = angle;
        boundingBox = new Rectangle(position.x, position.y, width, height);
        textureWidth = texture.getRegionWidth();
        textureHeight = texture.getRegionHeight();
        this.acceleration = acceleration;
    }

    public void update(float deltaTime){
        position.mulAdd(velocity, deltaTime);
        if(acceleration != null)
            velocity.mulAdd(acceleration, deltaTime);
        //centerBoundingBoxOnTexture();
        updateBoundingBox();
    }

    public void render(Batch batch, ShapeRenderer sp, float deltaTime) {
        textureWidth = activeTexture.getRegionWidth();
        textureHeight = activeTexture.getRegionHeight();
        update(deltaTime);
        batch.begin();
        batch.draw(activeTexture, position.x - activeTexture.getRegionWidth()/scale/PPM/2f,
                position.y - activeTexture.getRegionHeight()/scale/PPM/2f,
                activeTexture.getRegionWidth()/scale/PPM,
                activeTexture.getRegionHeight()/scale/PPM);
        batch.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        sp.rect(boundingBox.x - boundingBox.width/2,
                boundingBox.y - boundingBox.height/2,
                boundingBox.width,
                boundingBox.height);
        sp.end();
    }

    protected void updateBoundingBox(){
        boundingBox.setPosition(position);
    }

    //Getters and Setters
    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(float x, float y) {
        position.set(x, y);
        updateBoundingBox();
    }
    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(float velx, float vely) {
        velocity.set(velx, vely);
    }
    public Sprite getTexture() {
        return activeTexture;
    }
    public void setTexture(Sprite texture) {
        this.activeTexture.setRegion(texture);
    }
    public float getAngle() {
        return angle;
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }
    public Vector2 getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }
    public Rectangle getBoundingBox() {
        return boundingBox;
    }
    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    public boolean isShooting() {
        return shooting;
    }
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    /**
     *
     * @return width divided by the scale and PPM
     */
    public float getTextureWidth() {
        return textureWidth/scale/PPM;
    }

    /**
     *
     * @return height divided by the scale and PPM
     */
    public float getTextureHeight() {
        return textureHeight/scale/PPM;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    public void setActiveTexture(int index){
        activeTexture = new Sprite(textures[index]);
    }
}
