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

    public enum states{
        running,
        lose,
        win
    }


    @Override
    public void show() {
        assets.loadAssets();
        player = new Player(0, 0, 0, 0, 30/Constant.PPM/scale, 30/Constant.PPM/scale, assets.bernie, Constant.scale*2.5f, Constant.PPM, 0, 25);

        worldManager = new WorldManager(assets);
        worldManager.worlds.get(activeIndex).init();
    }




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

        worldManager.worlds.get(activeIndex).update(delta);
        if(worldManager.worlds.get(activeIndex).finished){
            if(activeIndex+1 < worldManager.worlds.size()){
                activeIndex++;
                worldManager.worlds.get(activeIndex).init();
            } else {
                System.out.println("no more worlds");
            }
        }

        //Batch draw
        batch.begin();

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
