package com.fixertin.game.ai;

import com.fixertin.game.ai.patterns.Shot;
import com.fixertin.game.entities.Enemy;

public class InfiniteShootAndTurn extends AI{
    private Shot p;
    private int amount = 0;

    /**
     *
     * @param e
     * @param amount if 0 shoot infinitely
     * @param spreadAngle
     * @param startAngle
     * @param speed
     * @param incrementAngleAmount
     * @param shotsUntilTurn if 0 or 1 turn every shot
     */
    public InfiniteShootAndTurn(Enemy e, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount, int shotsUntilTurn) {
        super(e);
        p = new Shot(e, amount, spreadAngle, startAngle, speed, incrementAngleAmount);
    }

    @Override
    public void reset(Enemy e) {
        start();
    }

    @Override
    public void update() {
        if(isRunning()){

        }
    }
}
