package com.fixertin.game.ai;

import com.fixertin.game.ai.patterns.Arc;
import com.fixertin.game.entities.Enemy;

public class ShootArc extends AI{
    public Arc pattern;

    public ShootArc(Enemy e, float shotgap, float startAngle, float arcSize, float speed) {
        super(e);
        pattern = new Arc(e, shotgap, startAngle, arcSize, speed);
    }

    @Override
    public void reset(Enemy e) {
        start();
    }

    @Override
    public void update(float delta) {
        if(isRunning()){
            pattern.fillBullets();
            succeed();
        }
    }
}
