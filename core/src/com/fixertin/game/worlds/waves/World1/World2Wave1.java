package com.fixertin.game.worlds.waves.World1;

import com.fixertin.game.entities.Enemy;
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.worlds.waves.Wave;

public class World2Wave1 extends Wave {
    public World2Wave1(MainGameAssets assets) {
        super(assets);
    }

    @Override
    public void addEnemies() {
        Enemy test = new Enemy(0, 5.5f,0, 0, 70/PPM/scale, 70/PPM/scale,
                assets.trumpFaces, scale*2, PPM, 0);
        test.addMoveTo(4, 270, 25/PPM);
        test.addShootAndTurn(assets.bitcoin, 3, 4, 90, 0,
                10/PPM, 20, 1, 5, 0);
        test.addWait(.5f);
        test.addShootArc(assets.bitcoin, 10, 180, 180, 5/PPM);
        test.addMoveTo(0, 45, 10/PPM);
        enemies.add(test);

        Enemy test2 = new Enemy(-7.5f, 0, 0, 0, 70/PPM/scale,
                70/PPM/scale, assets.hillaryFaces, scale*2, PPM, 0);
        test2.addMoveTo(5.7f, 45, 17/PPM);
        test2.addShootArc(assets.email, 15, 270, 90, 25/PPM);
        test2.addMoveTo(5.7f, 0, 17/PPM);
        test2.addShootArc(assets.email, 15, 180, 90, 25/PPM);
        test2.addMoveTo(5.7f, 0, 17/PPM);
        enemies.add(test2);
    }
}
