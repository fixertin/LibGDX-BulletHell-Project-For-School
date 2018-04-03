package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    protected Vector2 position, velocity;
    protected float width, height, scale, textureWidth, textureHeight, angle;
    protected final float PPM;
    protected TextureRegion texture;
    protected Rectangle boundingBox;
    protected boolean removed;

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
    public Entity(float x, float y, float velx, float vely, float width, float height, TextureRegion texture, float scale, float PPM, float angle){
        position = new Vector2(x, y);
        velocity = new Vector2(velx, vely);
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.scale = scale;
        this.PPM = PPM;
        this.angle = angle;
        boundingBox = new Rectangle(x, y, width, height);
        textureWidth = texture.getRegionWidth();
        textureHeight = texture.getRegionHeight();
    }
    public Entity(Vector2 position, Vector2 velocity, float width, float height, TextureRegion texture, float scale, float PPM, float angle){
        this.position = position;
        this.velocity = velocity;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.scale = scale;
        this.PPM = PPM;
        this.angle = angle;
        boundingBox = new Rectangle(position.x, position.y, width, height);
        textureWidth = texture.getRegionWidth();
        textureHeight = texture.getRegionHeight();
    }

    public void update(float deltaTime){
        position.mulAdd(velocity, deltaTime);
        //centerBoundingBoxOnTexture();
        updateBoundingBox();
    }

    public void render(Batch batch, ShapeRenderer sp, float deltaTime) {
        update(deltaTime);
        batch.begin();
        batch.draw(texture, position.x - texture.getRegionWidth()/scale/PPM/2f,
                position.y - texture.getRegionHeight()/scale/PPM/2f,
                texture.getRegionWidth()/scale/PPM,
                texture.getRegionHeight()/scale/PPM);
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
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    public TextureRegion getTexture() {
        return texture;
    }
    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
    public float getAngle() {
        return angle;
    }
    public void setAngle(float angle) {
        this.angle = angle;
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
        removed = removed;
    }
}
