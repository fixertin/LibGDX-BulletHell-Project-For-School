package com.fixertin.game.ai;

import com.fixertin.game.ai.patterns.Shot;
import com.fixertin.game.entities.Enemy;

public class InfiniteShootAndTurn extends AI{
    private Shot p;
    private int amount = 0, shotsUntilTurn;
    private float shotTimeGap;
    private float timer = 0;

    /**
     *
     * @param e
     * @param amount if 0 shoot infinitely
     * @param spreadAngle
     * @param startAngle
     * @param speed
     * @param incrementAngleAmount
     * @param shotsUntilTurn if 0 or 1 turn every shot
     * @param shotTimeGap time until next shot
     */
    public InfiniteShootAndTurn(Enemy e, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount, int shotsUntilTurn, float shotTimeGap) {
        super(e);
        p = new Shot(e, amount, spreadAngle, startAngle, speed, incrementAngleAmount);
        this.shotsUntilTurn = shotsUntilTurn;
        this.shotTimeGap = shotTimeGap;
    }

    @Override
    public void reset(Enemy e) {
        start();
    }


    @Override
    public void update(float delta) {
        if(isRunning()){
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
        }
    }
}
