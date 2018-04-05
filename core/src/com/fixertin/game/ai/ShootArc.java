package com.fixertin.game.ai;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.ai.patterns.Arc;
import com.fixertin.game.entities.Enemy;

public class ShootArc extends AI{
    public Arc pattern;

    public ShootArc(Enemy e, Sprite texture, float shotgap, float startAngle, float arcSize, float speed) {
        super(e);
        pattern = new Arc(e, texture, shotgap, startAngle, arcSize, speed);
    }

    @Override
    public void reset(Enemy e) {
        start();
        e.setActiveTexture(1);
    }

    @Override
    public void update(float delta) {
        if(isRunning()){
            pattern.fillBullets();
            e.setActiveTexture(0);
            succeed();

        }
    }
}
