package com.fixertin.game.ai;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.entities.Enemy;

public class MoveInCurve extends AI{
    private int k = 70;
    private Vector2[] points = new Vector2[k];
    private Vector2[] dataSet;
    private Vector2 out = new Vector2();
    private float speed;
    private float current = 0;
    private CatmullRomSpline<Vector2> myCatmull;


    public MoveInCurve(Enemy e, Vector2[] dataSet, float speed) {
        super(e);
        this.dataSet = dataSet;
        this.speed = speed;
        myCatmull = new CatmullRomSpline<Vector2>(dataSet, false);
    }

    @Override
    public void reset(Enemy e) {
        this.e = e;
        start();
    }

    @Override
    public void update(float delta) {
        if(isRunning()){
            current += delta * speed;
            if(current >= 1)
                succeed();
            myCatmull.valueAt(out, current);
            e.setPosition(new Vector2(out.x, out.y));
        }
    }
}
