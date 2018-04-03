package com.fixertin.game.ai;

import com.fixertin.game.ai.patterns.Shot;
import com.fixertin.game.entities.Enemy;

public class ShootAndTurn extends AI{
    private Shot p;
    private int amount = 0, shotsUntilTurn;
    private float shotTimeGap, timeUntilFinished;
    private float timer = 0;
    private float overallTimer = 0;

    public ShootAndTurn(Enemy e, float timeUntilFinished, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount, int shotsUntilTurn, float shotTimeGap, float acceleration) {
        super(e);
        p = new Shot(e, amount, spreadAngle, startAngle, speed, incrementAngleAmount, acceleration);
        this.shotsUntilTurn = shotsUntilTurn;
        this.shotTimeGap = shotTimeGap;
        this.timeUntilFinished = timeUntilFinished;
    }

    @Override
    public void reset(Enemy e) {
        start();
    }


    @Override
    public void update(float delta) {
        if(isRunning()){
            overallTimer += delta;
            if(shotTimeGap > 0){
                //only shoot when timer is >= shotTimeGap
                timer -= delta;
                if(timer <= 0){
                    p.fillBullets();
                    timer = shotTimeGap;
                    if(shotsUntilTurn > 1){
                        amount++;
                        if(amount >= shotsUntilTurn){
                            amount = 0;
                            p.turn();
                        }
                    }else{
                        p.turn();
                    }
                }
            } else {
                p.fillBullets();
                timer = shotTimeGap;
                if(shotsUntilTurn > 1){
                    amount++;
                    if(amount >= shotsUntilTurn){
                        amount = 0;
                        p.turn();
                    }
                }
            }
            if(overallTimer >= timeUntilFinished && timeUntilFinished > 0){
                succeed();
            }
        }
    }
}
