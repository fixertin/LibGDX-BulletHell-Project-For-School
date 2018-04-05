package com.fixertin.game.ai.patterns;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.util.BulletMath;
import com.fixertin.game.util.Constant;

public class Arc extends Pattern{
    public float shotgap, startAngle, arcSize;
    public float speed;

    public Arc(Entity shooter, Sprite texture, float shotgap, float startAngle, float arcSize, float speed) {
        super(shooter, texture);
        this.shotgap = shotgap;
        this.startAngle = startAngle;
        this.arcSize = arcSize;
        this.speed = speed;
    }

    @Override
    public void fillBullets() {
        int bulletAmount = MathUtils.round((float)arcSize / shotgap);
        for(int i=0; i<bulletAmount; i++){
            MainGameScreen.addBullets(
                    new Entity(
                    shooter.getPosition().x,
                    shooter.getPosition().y,
                    BulletMath.getVelocity(speed, startAngle+(i*shotgap)),
                    25/Constant.PPM/Constant.scale,
                    25/Constant.PPM/Constant.scale,
                    texture,
                    Constant.scale*3f,
                    Constant.PPM,
                    startAngle+(i*shotgap),
                    null)
            );
        }
    }



}
