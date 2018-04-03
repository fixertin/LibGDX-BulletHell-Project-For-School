package com.fixertin.game.ai.patterns;

import com.fixertin.game.entities.Entity;

public class Arc extends Pattern{
    public float shotgap, startAngle, arcSize;
    public float speed, acceleration;

    public Arc(Entity shooter, float shotgap, float startAngle, float arcSize) {
        super(shooter);
    }

    @Override
    public void fillBullets() {

    }
}
