package com.fixertin.game.ai;

import com.fixertin.game.ai.patterns.CirclePattern;
import com.fixertin.game.entities.Enemy;

public class ShootCircle extends AI{
    private CirclePattern p;

    public ShootCircle(Enemy e, float shotGap, float speed) {
        super(e);
        p = new CirclePattern(e, shotGap, speed);
    }

    @Override
    public void reset(Enemy e) {
        start();
    }

    @Override
    public void update(float delta) {
        if(isRunning()){
            p.fillBullets();
            succeed();
        }
    }
}
