package com.fixertin.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.entities.Enemy;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.util.BulletMath;

public class MoveTo extends AI{
    private float distance, angle;
    private Vector2 startPosition;
    private Vector2 velocity;

    public MoveTo(Enemy e, float distance, float angle, float speed) {
        super(e);
        this.distance = distance;
        this.angle = angle;
        velocity = BulletMath.getVelocity(speed, angle);
        this.startPosition = new Vector2(e.getPosition().x, e.getPosition().y);
    }

    @Override
    public void reset(Enemy e) {
        this.e = e;
        startPosition = new Vector2(e.getPosition().x, e.getPosition().y);
        start();
    }

    @Override
    public void update() {
        if(isRunning()){
            if(distance == 0) {
                e.setVelocity(velocity);
            }else if(startPosition.dst(e.getPosition()) >= distance){
                succeed();
                e.setVelocity(new Vector2(0, 0));
            }else{
                e.setVelocity(velocity);
            }
            if(MainGameScreen.isEntityOffScreen(e) && isRunning()){
                e.setRemoved(true);
                succeed();
            }
        }
    }
}
