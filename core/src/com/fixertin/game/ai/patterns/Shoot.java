package com.fixertin.game.ai.patterns;

import com.fixertin.game.entities.Bullet;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.MainGameScreen;

public class Shoot {
    private Entity shooter;
    private Bullet bullet;
    private int bulletsPerShot;
    private float bulletSpread, angle, angleChangePerTick, fireRate;

    public Shoot(Entity shooter, Bullet bullet, int bulletsPerShot, float fireRate, float bulletSpread, float angle, float angleChangePerTick){
        this.shooter = shooter;
        this.bullet = bullet;
        this.bulletsPerShot = bulletsPerShot;
        this.bulletSpread = bulletSpread;
        this.angle = angle;
        bullet.setAngle(angle);
        this.angleChangePerTick = angleChangePerTick;
    }

    private float timer = fireRate;
    public void update(float deltaTime){
        if(timer >= fireRate){
            for(int i=0; i<bulletsPerShot; i++){
                Bullet temp = new Bullet(shooter.getPosition().x, shooter.getPosition().y,
                        bullet.getBoundingBox().width, bullet.getBoundingBox().height,
                        bullet.getTexture(), bullet.getScale(), bullet.getPPM(), bullet.getAngle()+(bulletSpread*i),
                        bullet.getAcceleration(), bullet.getAngleChangePerTick(), bullet.getSpeed());

                MainGameScreen.addBullets(temp);
            }
            timer = 0;
        }
        timer += deltaTime;
        angle += angleChangePerTick * deltaTime;
        bullet.setAngle(angle);

    }
}
