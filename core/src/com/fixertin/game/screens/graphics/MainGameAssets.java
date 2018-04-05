package com.fixertin.game.screens.graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.util.Utility;

import java.util.Map;

public class MainGameAssets {
    private TextureAtlas testAtlas,
            bulletAtlas,
            headsAtlas,
            backgroundAtlas;
    public Sprite untitled,
            untitled2,
            untitled3,
            untitled4,
            bitcoin,
            email,
            fist,
            hammerAndSickle,
            bernie,
            bernieHappy,
            bernieSurprise,
            hillaryAngry,
            hillarySmug,
            hillarySuprise,
            trumpAngry,
            trumpSmug,
            trumpWeird,
            background,
            sadBernieBackground,
            winScreenBackground;
    public Sprite[] hillaryFaces = new Sprite[3],
                            trumpFaces = new Sprite[3],
                            bernieFaces = new Sprite[3];

    public void loadAssets(){
        Utility.loadTextureAtlas("textures/test.atlas");
        testAtlas = Utility.getTextureAtlas("textures/test.atlas");
        Utility.loadTextureAtlas("textures/Bullets.atlas");
        bulletAtlas = Utility.getTextureAtlas("textures/Bullets.atlas");
        Utility.loadTextureAtlas("textures/Heads.atlas");
        headsAtlas = Utility.getTextureAtlas("textures/Heads.atlas");
        Utility.loadTextureAtlas("textures/Backgrounds.atlas");
        backgroundAtlas = Utility.getTextureAtlas("textures/Backgrounds.atlas");
        //when all atlases are done load regions
        setTextureRegions();
    }

    private void setTextureRegions(){
        untitled = new Sprite(testAtlas.findRegion("Untitled"));
        untitled2 = new Sprite(testAtlas.findRegion("Untitled2"));
        untitled3 = new Sprite(testAtlas.findRegion("Untitled3"));
        untitled4 = new Sprite(testAtlas.findRegion("Untitled4"));
        bitcoin = new Sprite(bulletAtlas.findRegion("Bitcoin"));
        email = new Sprite(bulletAtlas.findRegion("hillaryEmail"));
        fist = new Sprite(bulletAtlas.findRegion("fist"));
        hammerAndSickle = new Sprite(bulletAtlas.findRegion("hammerAndSickle"));
        bernie = new Sprite(headsAtlas.findRegion("bernie"));
        bernieHappy = new Sprite(headsAtlas.findRegion("bernieHappy"));
        bernieSurprise = new Sprite(headsAtlas.findRegion("bernieSurprise"));

        hillarySmug = new Sprite(headsAtlas.findRegion("hillarySmug"));
        hillaryAngry = new Sprite(headsAtlas.findRegion("hillaryAngry"));
        hillarySuprise = new Sprite(headsAtlas.findRegion("hillarySuprise"));
        hillaryFaces[0] = hillarySmug;
        hillaryFaces[1] = hillaryAngry;
        hillaryFaces[2] = hillarySuprise;

        trumpSmug = new Sprite(headsAtlas.findRegion("trumpSmug"));
        trumpWeird = new Sprite(headsAtlas.findRegion("trumpWeird"));
        trumpAngry = new Sprite(headsAtlas.findRegion("trumpAngry"));
        trumpFaces[0] = trumpSmug;
        trumpFaces[1] = trumpWeird;
        trumpFaces[2] = trumpAngry;

        background = new Sprite(backgroundAtlas.findRegion("background"));
        sadBernieBackground = new Sprite(backgroundAtlas.findRegion("sadBernie"));
        winScreenBackground = new Sprite(backgroundAtlas.findRegion("winScreen"));

    }

    public void unloadAssets(){
        Utility.dispose();
    }

}
