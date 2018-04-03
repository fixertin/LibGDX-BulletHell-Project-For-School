package com.fixertin.game.ai.patterns;

import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.util.BulletMath;
import com.fixertin.game.util.Constant;

public class Shot extends Pattern{
    private int amount;
    private float spreadAngle, startAngle, speed, incrementAngleAmount;

    public Shot(Entity shooter, int amount, float spreadAngle, float startAngle, float speed) {
        super(shooter);
        this.amount = amount;
        this.spreadAngle = spreadAngle;
        this.startAngle = startAngle;
        this.speed = speed;
    }
    public Shot(Entity shooter, int amount, float spreadAngle, float startAngle, float speed, float incrementAngleAmount) {
        super(shooter);
        this.amount = amount;
        this.spreadAngle = spreadAngle;
        this.startAngle = startAngle;
        this.speed = speed;
    }

    @Override
    public void fillBullets() {
        for(int i=0; i<amount; i++){
            MainGameScreen.addBullets(
                    new Entity(
                            shooter.getPosition().x,
                            shooter.getPosition().y,
                            BulletMath.getVelocity(speed, startAngle+(i*spreadAngle)),
                            4/Constant.PPM,
                            4/Constant.PPM,
                            MainGameScreen.assets.bitcoin,
                            20f,
                            Constant.PPM,
                            startAngle+(i*spreadAngle))
            );
        }
    }

    public void turn(){
        startAngle += incrementAngleAmount;
    }

}
