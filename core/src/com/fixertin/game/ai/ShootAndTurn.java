package com.fixertin.game.ai;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.ai.patterns.Shot;
import com.fixertin.game.entities.Enemy;

public class ShootAndTurn extends AI{
    private Shot p;
    private int amount = 0;
    private float shotTimeGap, timeUntilFinished, timeUntilTurn;
    private float timer = 0;
    private float turnTimer = 0;
    private float overallTimer = 0;

    public ShootAndTurn(Enemy e, TextureRegion texture, float timeUntilFinished, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount, float timeUntilTurn, float shotTimeGap, float acceleration) {
        super(e);
        p = new Shot(e, texture, amount, spreadAngle, startAngle, speed, incrementAngleAmount, acceleration);
        this.timeUntilTurn = timeUntilTurn;
        this.shotTimeGap = shotTimeGap;
        this.timeUntilFinished = timeUntilFinished;
    }

    @Override
    public void reset(Enemy e) {
        start();
        e.setActiveTexture(1);
    }


    @Override
    public void update(float delta) {
        if(isRunning()) {
            overallTimer += delta;
            if (turnTimer >= timeUntilTurn){
                p.turn();
                turnTimer=0;
            }
            turnTimer += delta;
            if(shotTimeGap > 0){
                //only shoot when timer is >= shotTimeGap
                timer -= delta;
                if(timer <= 0){
                    p.fillBullets();
                    timer = shotTimeGap;
                }
            } else {
                p.fillBullets();
                timer = shotTimeGap;
            }
            if(overallTimer >= timeUntilFinished && timeUntilFinished > 0){
                e.setActiveTexture(0);
                succeed();
            }
        }
    }
}
