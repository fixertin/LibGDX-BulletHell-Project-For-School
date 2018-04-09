package com.fixertin.game.ai;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fixertin.game.ai.patterns.Shot;
import com.fixertin.game.entities.Enemy;

public class ShootAndTurn extends AI{
    private Shot p;
    private float timeUntilFinished;
    private int shotFrameGap, framesUntilTurn;
    private float overallTimer = 0;

    private int currentFrameTurn;
    private int currentFrameShoot;

    public ShootAndTurn(Enemy e, Sprite texture, float timeUntilFinished, int amount,
                        float spreadAngle, float startAngle, float speed, float incrementAngleAmount,
                        int framesUntilTurn, int shotFrameGap, float acceleration) {
        super(e);
        p = new Shot(e, texture, amount, spreadAngle, startAngle, speed, incrementAngleAmount, acceleration);
        this.framesUntilTurn = framesUntilTurn;
        this.shotFrameGap = shotFrameGap;
        this.timeUntilFinished = timeUntilFinished;
        currentFrameShoot = shotFrameGap;
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
            if (currentFrameTurn >= framesUntilTurn){
                p.turn();
                currentFrameTurn=0;
            }
            currentFrameTurn++;
            if(shotFrameGap > 0){
                //only shoot when timer is >= shotFrameGap
                currentFrameShoot--;
                if(currentFrameShoot <= 0){
                    p.fillBullets();
                    currentFrameShoot = shotFrameGap;
                }
            } else {
                p.fillBullets();
            }
            if(overallTimer >= timeUntilFinished && timeUntilFinished > 0){
                e.setActiveTexture(0);
                succeed();
            }
        }
    }
}
