package com.fixertin.game.ai.patterns;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.util.BulletMath;
import com.fixertin.game.util.Constant;

public class CirclePattern extends Pattern{
    public float shotGap;
    public float speed;

    public CirclePattern(Entity shooter, Sprite texture, float shotGap, float speed) {
        super(shooter, texture);
        this.shotGap = shotGap;
        this.speed = speed;
    }

    @Override
    public void fillBullets() {
        for(int i=0; i<(360/shotGap); i++){
            MainGameScreen.addBullets(
                    new Entity(
                            shooter.getPosition().x,
                            shooter.getPosition().y,
                            BulletMath.getVelocity(speed, i*shotGap),
                            20/Constant.PPM/Constant.scale,
                            20/Constant.PPM/Constant.scale,
                            texture,
                            Constant.scale*2.5f,
                            Constant.PPM,
                            i*shotGap,
                            null)
            );
        }
    }
}
