package com.fixertin.game.ai;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fixertin.game.ai.patterns.Shot;
import com.fixertin.game.entities.Enemy;

public class ShootAndTurn extends AI{
    private Shot[] p;
    private float timeUntilFinished, rateOfTurn;
    private int shotFrameGap, framesUntilTurn;
    private float overallTimer = 0;
    private int currentFrameTurn;
    private int currentFrameShoot;



    public ShootAndTurn(Enemy e, Sprite texture, float timeUntilFinished, int amount,
                        float spreadAngle, float startAngle, float speed, float incrementAngleAmount,
                        int framesUntilTurn, int shotFrameGap, float acceleration, float rateOfTurn, boolean reverse) {
        super(e);
        p = new Shot[1];
        p[0] = new Shot(e, texture, amount, spreadAngle, startAngle, speed, incrementAngleAmount, acceleration, rateOfTurn, reverse);
        this.framesUntilTurn = framesUntilTurn;
        this.shotFrameGap = shotFrameGap;
        this.timeUntilFinished = timeUntilFinished;
        currentFrameShoot = shotFrameGap;
        this.rateOfTurn = rateOfTurn;

    }
    public ShootAndTurn(Enemy e, Sprite texture, int groupAmount, float groupSpreadAngle, float timeUntilFinished, int amount,
                        float spreadAngle, float startAngle, float speed, float incrementAngleAmount,
                        int framesUntilTurn, int shotFrameGap, float acceleration, float rateOfTurn, boolean reverse) {
        super(e);
        p = new Shot[groupAmount];
        for(int i=0; i<p.length; i++){
            p[i] = new Shot(e, texture, amount, spreadAngle, startAngle + i*groupSpreadAngle, speed, incrementAngleAmount, acceleration, rateOfTurn, reverse);
        }

        this.framesUntilTurn = framesUntilTurn;
        this.shotFrameGap = shotFrameGap;
        this.timeUntilFinished = timeUntilFinished;
        currentFrameShoot = shotFrameGap;
        this.rateOfTurn = rateOfTurn;
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
                turnShots();
                currentFrameTurn=0;
            }
            currentFrameTurn++;
            if(shotFrameGap > 0){
                //only shoot when timer is >= shotFrameGap
                currentFrameShoot--;
                if(currentFrameShoot <= 0){
                    fillShots();
                    currentFrameShoot = shotFrameGap;
                }
            } else {
                fillShots();
            }
            turnLogic();
            if(overallTimer >= timeUntilFinished && timeUntilFinished > 0){
                e.setActiveTexture(0);
                succeed();
            }

        }
    }

    private void turnLogic(){
        if(rateOfTurn != 0){
            for(Shot s : p){
                s.turnLogic();
            }
        }
    }

    private void turnShots(){
        for(Shot s : p){
            s.turn();
        }
    }
    private void fillShots(){
        for(Shot s : p){
            s.fillBullets();
        }
    }

}
