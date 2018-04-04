package com.fixertin.game.worlds.waves;

import com.fixertin.game.screens.MainGameScreen;
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.worlds.World;
import com.fixertin.game.worlds.waves.World1.World1Wave1;
import com.fixertin.game.worlds.waves.World1.World2Wave1;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {
    private MainGameAssets assets;

    public ArrayList<World> worlds = new ArrayList<World>();

    public WorldManager(MainGameAssets assets){
        this.assets = assets;
        makeWorlds();
    }

    private void makeWorlds(){
        ArrayList<Wave> waves = new ArrayList<Wave>();
        waves.add(new World1Wave1(assets));
        //waves.add(new World1Wave1(assets));
        worlds.add(new World(waves));

        ArrayList<Wave> waves2 = new ArrayList<Wave>();
        waves2.add(new World2Wave1(assets));
        waves2.add(new World1Wave1(assets));
        worlds.add(new World(waves2));

    }

}
