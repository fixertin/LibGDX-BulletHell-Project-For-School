package com.fixertin.game.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BulletMath {
    /**
     *
     * @param speed pass variable in terms of pixels per meter
     * @param angle
     * @return
     */
    public static Vector2 getVelocity(float speed, float angle){
        return new Vector2(MathUtils.cos(MathUtils.degreesToRadians * angle)*speed, MathUtils.sin(MathUtils.degreesToRadians * angle)*speed);
    }



}
