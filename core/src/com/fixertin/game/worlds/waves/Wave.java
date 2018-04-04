package com.fixertin.game.worlds.waves;

import com.fixertin.game.entities.Enemy;
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.util.Constant;

import java.util.ArrayList;

public abstract class Wave {
    protected final float PPM = Constant.PPM;
    protected final float scale = Constant.scale;
    protected MainGameAssets assets;
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Wave(MainGameAssets assets){
        this.assets = assets;
    }

    public abstract void addEnemies();

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
}
