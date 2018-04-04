package com.fixertin.game.ai.patterns;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.entities.Entity;

public abstract class Pattern {
    protected Entity shooter;
    protected TextureRegion texture;

    public Pattern(Entity shooter, TextureRegion texture){
        this.shooter = shooter;
        this.texture = texture;
    }

    public abstract void fillBullets();

    public Entity getShooter() {
        return shooter;
    }
    public void setShooter(Entity shooter) {
        this.shooter = shooter;
    }
}
