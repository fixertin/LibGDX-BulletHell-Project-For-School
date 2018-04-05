package com.fixertin.game.screens.graphics;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.util.Utility;

import java.util.Map;

public class MainGameAssets {
    private TextureAtlas testAtlas,
            bulletAtlas,
            headsAtlas;
    public TextureRegion untitled,
            untitled2,
            untitled3,
            untitled4,
            bitcoin,
            email,
            fist,
            hammerAndSickle,
            bernie,
            hillaryAngry,
            hillarySmug,
            hillarySuprise,
            trumpAngry,
            trumpSmug,
            trumpWeird;
    public TextureRegion[] hillaryFaces = new TextureRegion[3],
                            trumpFaces = new TextureRegion[3];

    public void loadAssets(){
        Utility.loadTextureAtlas("textures/test.atlas");
        testAtlas = Utility.getTextureAtlas("textures/test.atlas");
        Utility.loadTextureAtlas("textures/Bullets.atlas");
        bulletAtlas = Utility.getTextureAtlas("textures/Bullets.atlas");
        Utility.loadTextureAtlas("textures/Heads.atlas");
        headsAtlas = Utility.getTextureAtlas("textures/Heads.atlas");
        //when all atlases are done load regions
        setTextureRegions();
    }

    private void setTextureRegions(){
        untitled = testAtlas.findRegion("Untitled");
        untitled2 = testAtlas.findRegion("Untitled2");
        untitled3 = testAtlas.findRegion("Untitled3");
        untitled4 = testAtlas.findRegion("Untitled4");
        bitcoin = bulletAtlas.findRegion("Bitcoin");
        email = bulletAtlas.findRegion("hillaryEmail");
        fist = bulletAtlas.findRegion("fist");
        hammerAndSickle = bulletAtlas.findRegion("hammerAndSickle");
        bernie = headsAtlas.findRegion("bernie");

        hillarySmug = headsAtlas.findRegion("hillarySmug");
        hillaryAngry = headsAtlas.findRegion("hillaryAngry");
        hillarySuprise = headsAtlas.findRegion("hillarySuprise");
        hillaryFaces[0] = hillarySmug;
        hillaryFaces[1] = hillaryAngry;
        hillaryFaces[2] = hillarySuprise;

        trumpSmug = headsAtlas.findRegion("trumpSmug");
        trumpWeird = headsAtlas.findRegion("trumpWeird");
        trumpAngry = headsAtlas.findRegion("trumpAngry");
        trumpFaces[0] = trumpSmug;
        trumpFaces[1] = trumpWeird;
        trumpFaces[2] = trumpAngry;
    }

    public void unloadAssets(){
        Utility.dispose();
    }

}
