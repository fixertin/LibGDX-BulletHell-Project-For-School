package com.fixertin.game.ai.patterns;

import com.fixertin.game.entities.Entity;

public abstract class Pattern {
    protected Entity shooter;

    public Pattern(Entity shooter){
        this.shooter = shooter;
    }

    public abstract void fillBullets();

    public Entity getShooter() {
        return shooter;
    }
    public void setShooter(Entity shooter) {
        this.shooter = shooter;
    }
}
