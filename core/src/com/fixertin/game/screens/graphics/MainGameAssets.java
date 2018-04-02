package com.fixertin.game.screens.graphics;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.util.Utility;

import java.util.Map;

public class MainGameAssets {
    private TextureAtlas testAtlas,
            bulletAtlas;
    public TextureRegion untitled,
            untitled2,
            untitled3,
            untitled4,
            bitcoin;

    public void loadAssets(){
        Utility.loadTextureAtlas("textures/test.atlas");
        testAtlas = Utility.getTextureAtlas("textures/test.atlas");
        Utility.loadTextureAtlas("textures/Bullet.atlas");
        bulletAtlas = Utility.getTextureAtlas("textures/Bullet.atlas");

        //when all atlases are done load regions
        setTextureRegions();
    }

    private void setTextureRegions(){
        untitled = testAtlas.findRegion("Untitled");
        untitled2 = testAtlas.findRegion("Untitled2");
        untitled3 = testAtlas.findRegion("Untitled3");
        untitled4 = testAtlas.findRegion("Untitled4");
        bitcoin = bulletAtlas.findRegion("bitCoin");
    }

    public void unloadAssets(){
        Utility.dispose();
    }

}
