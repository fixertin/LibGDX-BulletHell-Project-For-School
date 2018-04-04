package com.fixertin.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity{
    public Player(float x, float y, float velx, float vely, float width, float height, TextureRegion texture, float scale, float PPM, float angle) {
        super(x, y, velx, vely, width, height, texture, scale, PPM, angle);
    }
}
