package com.fixertin.game.ai;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.ai.patterns.CirclePattern;
import com.fixertin.game.entities.Enemy;

public class ShootCircle extends AI{
    private CirclePattern p;

    public ShootCircle(Enemy e, TextureRegion texture, float shotGap, float speed) {
        super(e);
        p = new CirclePattern(e, texture, shotGap, speed);
    }

    @Override
    public void reset(Enemy e) {
        start();
        e.setActiveTexture(1);
    }

    @Override
    public void update(float delta) {
        if(isRunning()){
            p.fillBullets();
            e.setActiveTexture(0);
            succeed();
        }
    }
}
