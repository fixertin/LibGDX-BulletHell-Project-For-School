package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    private Vector2 position, velocity;
    private float width, height, scale;
    private final float PPM;
    private TextureRegion texture;
    private Rectangle boundingBox;

    /**
     *
     * @param x not scaled for PPM
     * @param y not scaled for PPM
     * @param velx not scaled for PPM
     * @param vely not scaled for PPM
     * @param width not scaled for scale or PPM
     * @param height not scaled for scale or PPM
     * @param texture
     * @param scale used for drawing the texture
     * @param PPM used for drawing the texture
     */
    public Entity(float x, float y, float velx, float vely, float width, float height, TextureRegion texture, float scale, float PPM){
        position = new Vector2(x, y);
        velocity = new Vector2(velx, vely);
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.scale = scale;
        this.PPM = PPM;
        boundingBox = new Rectangle(x, y, width, height);

    }
    public Entity(float x, float y, float width, float height, TextureRegion texture, float scale, float PPM){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.scale = scale;
        this.PPM = PPM;
        boundingBox = new Rectangle(x, y, width, height);
    }

    public void update(float deltaTime){
        position.mulAdd(velocity, deltaTime);
        centerBoundingBoxOnTexture();
    }

    public void render(Batch batch, ShapeRenderer sp, float deltaTime) {
        update(deltaTime);
        batch.begin();
        batch.draw(texture, position.x, position.y, texture.getRegionWidth()/scale/PPM, texture.getRegionHeight()/scale/PPM);
        batch.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        sp.rect(boundingBox.x,
                boundingBox.y,
                boundingBox.width,
                boundingBox.height);
        sp.end();
    }

    private void centerBoundingBoxOnTexture(){
        boundingBox.setX((position.x + (texture.getRegionWidth()/scale/PPM/2f)) - boundingBox.getWidth()/2f);
        boundingBox.setY((position.y + (texture.getRegionHeight()/scale/PPM/2f)) - boundingBox.getHeight()/2f);
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
}
