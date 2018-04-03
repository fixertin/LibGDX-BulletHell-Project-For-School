package com.fixertin.game.ai;

import com.fixertin.game.entities.Enemy;

public class Wait extends AI{
    private float timeUntilFinish;

    public Wait(Enemy e, float timeUntilFinish) {
        super(e);
        this.timeUntilFinish = timeUntilFinish;
    }

    @Override
    public void reset(Enemy e) {
        start();
    }

    private float timer;
    @Override
    public void update(float delta) {
        if(isRunning()){
            timer += delta;
            if(timer >= timeUntilFinish)
                succeed();
        }
    }
}
