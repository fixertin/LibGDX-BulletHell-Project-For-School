package com.fixertin.game.ai.patterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.util.BulletMath;
import com.fixertin.game.util.Constant;

public class Shot extends Pattern{
    private int amount;
    private float spreadAngle, startAngle, speed, incrementAngleAmount;
    private float acceleration;

    public Shot(Entity shooter, Sprite texture, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount, float acceleration) {
        super(shooter, texture);
        this.amount = amount;
        this.spreadAngle = spreadAngle;
        this.startAngle = startAngle;
        this.speed = speed;
        this.incrementAngleAmount = incrementAngleAmount;
        this.acceleration = acceleration;
    }

    @Override
    public void fillBullets() {
        for(int i=0; i<amount; i++){
            MainGameScreen.addBullets(
                    new Entity(
                            shooter.getPosition().x,
                            shooter.getPosition().y,
                            BulletMath.getVelocity(speed, startAngle+(i*spreadAngle)),
                            25/Constant.PPM/Constant.scale,
                            25/Constant.PPM/Constant.scale,
                            texture,
                            Constant.scale*3f,
                            Constant.PPM,
                            startAngle+(i*spreadAngle),
                            BulletMath.getVelocity(acceleration, startAngle+(i*spreadAngle)))
            );
        }
    }

    public void turn(){
        startAngle += incrementAngleAmount;
    }

}
