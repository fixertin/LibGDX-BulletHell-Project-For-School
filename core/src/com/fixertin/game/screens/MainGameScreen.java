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
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.worlds.World;
import com.fixertin.game.worlds.waves.WorldManager;

import java.util.ArrayList;

public class MainGameScreen extends GameScreen{
    public TextureAtlas testAtlas;
    public Enemy test;
    public static MainGameAssets assets = new MainGameAssets();
    public static WorldManager worldManager;
    public static int activeIndex = 0;


    @Override
    public void show() {
        assets.loadAssets();
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

        for(Entity e : entities){

            e.render(batch, sp, Gdx.graphics.getDeltaTime());
        }
        entities.removeIf(entity -> entity.isRemoved());
        for(Entity b : bullets){
            b.render(batch, sp, Gdx.graphics.getDeltaTime());
            if(isEntityOffScreen(b))
                b.setRemoved(true);
        }
        bullets.removeIf(bullet -> bullet.isRemoved());


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
}
