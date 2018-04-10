package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fixertin.game.CommieGame;
import com.fixertin.game.entities.Enemy;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.entities.Player;
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.util.Constant;
import com.fixertin.game.worlds.World;
import com.fixertin.game.worlds.waves.WorldManager;

import java.util.ArrayList;

public class MainGameScreen extends GameScreen{
    public TextureAtlas testAtlas;
    public Enemy test;
    public static MainGameAssets assets = new MainGameAssets();
    public static WorldManager worldManager;
    public static int activeIndex = 0;
    public static Player player;

    public boolean win = false;



    public enum states{
        running,
        lose,
        win
    }

    public MainGameScreen(CommieGame game){
        super(game);

    }



    @Override
    public void show() {
        assets.loadAssets();
        player = new Player(0, 0, 0, 0,
                30/Constant.PPM/scale, 30/Constant.PPM/scale,
                assets.bernie, Constant.scale*2.5f, Constant.PPM,
                0, 15);

        worldManager = new WorldManager(assets);
        worldManager.worlds.get(activeIndex).init();
        assets.gameMusic.setLooping(true);
        assets.gameMusic.setVolume(.05f);
        assets.gameMusic.play();
    }



    private float alpha = 0;
    private final float alphaIncrease = .01f;

    private FPSLogger logger = new FPSLogger();
    @Override
    public void render(float delta) {
        logger.log();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        camera.position.set(0,
                0, 0f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        sp.setProjectionMatrix(camera.combined);


        if(player.getHealth() <= 0 || win){
            player.setDead(true);
            if(alpha <= 1){
                alpha += alphaIncrease;
            }else if(alpha > 1){
                alpha = 1;
            }

        }



        if(alpha < 1){
            worldManager.worlds.get(activeIndex).update(delta);
            if(worldManager.worlds.get(activeIndex).finished){
                if(activeIndex+1 < worldManager.worlds.size()){
                    activeIndex++;
                    worldManager.worlds.get(activeIndex).init();
                } else {
                    win = true;
                }
            }


            //Batch draw
            batch.begin();
            batch.draw(assets.background,
                    -VIEWPORT.viewportWidth/2,
                    -VIEWPORT.viewportHeight/2,
                    VIEWPORT.viewportWidth,
                    VIEWPORT.viewportHeight);
            batch.end();


            //ShapeRenderer draw
            sp.begin(ShapeRenderer.ShapeType.Line);
            sp.setColor(Color.RED);

            sp.end();

            for(Entity b : bullets){
                b.render(batch, sp, delta);
                if(isEntityOffScreen(b))
                    b.setRemoved(true);
                else if(b.getBoundingBox().overlaps(player.getBoundingBox())){
                    player.lowerHealth(1);
                    b.setRemoved(true);
                }
            }
            for(Entity pb : playerBullets){
                pb.render(batch, sp, delta);
                if(isEntityOffScreen(pb))
                    pb.setRemoved(true);
                else {
                    for(Enemy e : entities){
                        if (pb.getBoundingBox().overlaps(e.getBoundingBox())){
                            e.removeHealth(1);
                            pb.setRemoved(true);
                        }
                    }
                }
            }
            for(Enemy e : entities){
                e.render(batch, sp, delta);
            }
            entities.removeIf(entity -> entity.isRemoved());
            bullets.removeIf(bullet -> bullet.isRemoved());
            playerBullets.removeIf(pbullet -> pbullet.isRemoved());
            player.render(batch, sp, delta);
            drawHealthBar(-VIEWPORT.viewportWidth/2 + .3f, VIEWPORT.viewportHeight/2 - .6f, 60/PPM, 3.5f/PPM, player);

        } else if(!win){
            game.setScreen(CommieGame.loseScreen);
        } else if(win){
            game.setScreen(CommieGame.winScreen);
        }

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sp.setProjectionMatrix(camera.combined);
        sp.begin(ShapeRenderer.ShapeType.Filled);
        sp.setColor(new Color(0, 0, 0, alpha));
        sp.rect(-VIEWPORT.viewportWidth/2,
                -VIEWPORT.viewportHeight/2,
                VIEWPORT.viewportWidth,
                VIEWPORT.viewportHeight);
        sp.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }


    @Override
    public void resize(int width, int height) {
        setupViewport(10,10);
        camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        assets.gameMusic.setLooping(false);
        assets.gameMusic.stop();
        assets.unloadAssets();
    }

    @Override
    public void dispose() {
        batch.dispose();
        sp.dispose();
        assets.unloadAssets();
    }

    public void drawHealthBar(float x, float y, float width, float height, Player p){
        float percent = (float)p.getHealth()/(float)p.getMaxHealth();
        float healthBarWidth = width * percent;
        sp.begin(ShapeRenderer.ShapeType.Filled);
        sp.setColor(Color.SCARLET);
        sp.rect(x, y, width, height);
        sp.setColor(Color.GREEN);
        sp.rect(x, y, healthBarWidth, height);
        sp.end();
    }

}
