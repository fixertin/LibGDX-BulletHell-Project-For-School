package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    public Vector2 position, velocity;
    public float width, height;

    public Entity(float x, float y, float velx, float vely, float width, float height){
        position = new Vector2(x, y);
        velocity = new Vector2(velx, vely);
        this.width = width;
        this.height = height;
    }
    public Entity(float x, float y, float width, float height){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        this.width = width;
        this.height = height;
    }

    public void update(float deltaTime){
        position.mulAdd(velocity, deltaTime);

    }

    public void render(Batch batch, ShapeRenderer sp, float deltaTime) {
        update(deltaTime);
        batch.begin();

        batch.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        sp.rect(position.x, position.y, width, height);
        sp.end();
    }
}
