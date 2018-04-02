package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends Entity{
    public Enemy(float x, float y, float velx, float vely, float width, float height, TextureRegion texture, float scale, float PPM) {
        super(x, y, velx, vely, width, height, texture, scale, PPM);
    }
    public Enemy(float x, float y, float width, float height, TextureRegion texture, float scale, float PPM) {
        super(x, y, width, height, texture, scale, PPM);
    }
}
