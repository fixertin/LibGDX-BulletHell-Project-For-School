package com.fixertin.game.screens.graphics;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fixertin.game.util.Utility;

import java.util.Map;

public class MainGameAssets {
    public Music winMusic,
            loseMusic,
            gameMusic;

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
            winScreenBackground,
            fade;
    public Sprite[] hillaryFaces = new Sprite[3],
                            trumpFaces = new Sprite[3],
                            bernieFaces = new Sprite[3];

    private Utility utility;

    public MainGameAssets(){
        utility = new Utility();
    }

    public void loadAssets(){
        utility.loadTextureAtlas("textures/test.atlas");
        testAtlas = utility.getTextureAtlas("textures/test.atlas");
        utility.loadTextureAtlas("textures/Bullets.atlas");
        bulletAtlas = utility.getTextureAtlas("textures/Bullets.atlas");
        utility.loadTextureAtlas("textures/Heads.atlas");
        headsAtlas = utility.getTextureAtlas("textures/Heads.atlas");
        utility.loadTextureAtlas("textures/Backgrounds.atlas");
        backgroundAtlas = utility.getTextureAtlas("textures/Backgrounds.atlas");
        //when all atlases are done load regions
        setTextureRegions();
        utility.loadMusic("music/gameSong.mp3");
        gameMusic = utility.getMusic("music/gameSong.mp3");
        utility.loadMusic("music/winSong.mp3");
        winMusic = utility.getMusic("music/winSong.mp3");
        utility.loadMusic("music/loseSong.mp3");
        loseMusic = utility.getMusic("music/loseSong.mp3");

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
        bernieFaces[0] = bernie;
        bernieFaces[1] = bernieHappy;
        bernieFaces[2] = bernieSurprise;

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
        fade = new Sprite(backgroundAtlas.findRegion("fade"));

    }

    public void unloadAssets(){
        utility.dispose();
    }

}
