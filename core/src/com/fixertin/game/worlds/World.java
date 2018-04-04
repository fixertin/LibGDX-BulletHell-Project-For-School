package com.fixertin.game.worlds;

import com.fixertin.game.entities.Enemy;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.worlds.waves.Wave;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    public ArrayList<Wave> waves = new ArrayList<Wave>();
    public int activeIndex;
    public boolean finished;

    public World(ArrayList<Wave> waves){
        this.waves = waves;
    }

    public void init(){
        finished = false;
        activeIndex = 0;
        for(Wave w : waves){
            w.addEnemies();
        }
        startWave(waves.get(activeIndex));
    }
    public void update(float delta){
        List<Entity> enemiesStillInWorld = MainGameScreen.getEntities().stream().filter(enemy -> enemy instanceof Enemy).collect(Collectors.toList());
        if(enemiesStillInWorld.isEmpty())
            nextWave();
    }
    public void startWave(Wave wave){
        finished = false;
        for(Enemy e : wave.getEnemies()){
            Enemy temp = e;
            MainGameScreen.addEntity(temp);
        }
    }
    public void nextWave(){
        if(activeIndex+1 >= waves.size()){
            //already at last wave
            finished = true;
        } else {
            activeIndex++;
            startWave(waves.get(activeIndex));
        }
    }

}
