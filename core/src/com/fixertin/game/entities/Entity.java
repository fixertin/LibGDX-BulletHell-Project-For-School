package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    private Vector2 position, velocity;
    private float width, height;
    private TextureRegion texture;
    private Rectangle boundingBox;

    public Entity(float x, float y, float velx, float vely, float width, float height, TextureRegion texture){
        position = new Vector2(x, y);
        velocity = new Vector2(velx, vely);
        this.width = width;
        this.height = height;
        this.texture = texture;
        boundingBox = new Rectangle(x, y+height, width, height);
    }
    public Entity(float x, float y, float width, float height, TextureRegion texture){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        this.width = width;
        this.height = height;
        this.texture = texture;
        boundingBox = new Rectangle(x, y+height, width, height);
    }

    public void update(float deltaTime){
        position.mulAdd(velocity, deltaTime);

    }

    public void render(Batch batch, ShapeRenderer sp, float deltaTime) {
        update(deltaTime);
        batch.begin();
        batch.draw(texture, position.x, position.y, width, height);
        batch.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        //sp.rect(position.x, position.y, width, height);
        sp.rect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        sp.end();
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
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public TextureRegion getTexture() {
        return texture;
    }
    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
}
