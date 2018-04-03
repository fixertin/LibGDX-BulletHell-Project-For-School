package com.fixertin.game.ai.patterns;

import com.badlogic.gdx.math.MathUtils;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.util.BulletMath;
import com.fixertin.game.util.Constant;

public class Arc extends Pattern{
    public float shotgap, startAngle, arcSize;
    public float speed;

    public Arc(Entity shooter, float shotgap, float startAngle, float arcSize, float speed) {
        super(shooter);
        this.shotgap = shotgap;
        this.startAngle = startAngle;
        this.arcSize = arcSize;
        this.speed = speed;
    }

    @Override
    public void fillBullets() {
        int bulletAmount = MathUtils.round((float)arcSize / shotgap);
        System.out.println(bulletAmount);
        for(int i=0; i<bulletAmount; i++){
            MainGameScreen.addBullets(
                    new Entity(
                    shooter.getPosition().x,
                    shooter.getPosition().y,
                    BulletMath.getVelocity(speed, startAngle+(i*shotgap)),
                    4/Constant.PPM,
                    4/Constant.PPM,
                    MainGameScreen.assets.bitcoin,
                    20f,
                    Constant.PPM,
                    startAngle+(i*shotgap),
                    null)
            );
        }
    }



}
