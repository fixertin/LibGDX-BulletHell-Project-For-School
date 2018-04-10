package com.fixertin.game.worlds.waves.World1;

import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.entities.Enemy;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.worlds.waves.Wave;

public class World1Wave1 extends Wave {
    public World1Wave1(MainGameAssets assets) {
        super(assets);
    }

    @Override
    public void addEnemies() {
        Enemy test = new Enemy(0, 0,0, 0, 70/PPM/scale,
                70/PPM/scale, assets.hillaryFaces, scale*2, PPM, 0);
        Vector2[] dataSet = new Vector2[4];
        dataSet[0] = new Vector2(-600.0f/PPM/10, 200.0f/PPM/10);
        dataSet[1] = new Vector2(-400.0f/PPM/10, 350.0f/PPM/10);
        dataSet[2] = new Vector2(050.0f/PPM/10, 190.0f/PPM/10);
        dataSet[3] = new Vector2(150.0f/PPM/10, 110.0f/PPM/10);
        //dataSet[4] = new Vector2(100.0f/PPM/10, 120.0f/PPM/10);
        test.addMoveInCurve(dataSet, 0.25f);
        test.addWait(.5f);
        test.addShootAndTurn(assets.email, 2, 180, 5f, 4, 30, 0,
                30/PPM, 15, 6, 6, -2, .2f, true);
        test.addWait(1f);
        test.addShootAndTurn(assets.email, 2f, 2, 180, 0,
                30/PPM, 10, 10, 10, -5, 0, false);
        test.addWait(1f);
        test.addShootAndTurn(assets.email, 15f, 2, 180, 0,
                30/PPM, 10, 5, 5, 5, .5f, true);
        test.addWait(1f);
        test.addShootAndTurn(assets.email, 2, 180, 5f, 3, 30, 0,
                30/PPM, 10, 10, 10, 0, 0, false);
        test.addWait(1f);
        test.addShootAndTurn(assets.email, 2, 180, 5f, 3, 30, 0,
                30/PPM, -12, 2, 7, -2, 0, false);
        test.addWait(1f);
        test.addMoveTo(10/PPM, 180, 20/PPM);
        test.addWait(.3f);
        test.addShootAndTurn(assets.email ,7, 4, 90, 0, 40/PPM,
                10, 5, 10, -10, 0, false);
        test.addWait(2);
        test.addShootAndTurn(assets.email, 7, 2, 90, 0, 20/PPM,
                20, 10, 10, -2, 0, false);
        test.addMoveTo(10/PPM, 90, 5/PPM);
        test.addWait(1f);
        test.addShootArc(assets.email, 20, 270, 360, 15/PPM);
        test.addWait(.5f);
        test.addShootArc(assets.email, 20, 280, 360, 15/PPM);
        test.addWait(.2f);
        test.addShootArc(assets.bitcoin, 20, 180, 180, 25/PPM);
        test.addMoveTo(0, 90, 10/PPM);
        enemies.add(test);

    }


}
