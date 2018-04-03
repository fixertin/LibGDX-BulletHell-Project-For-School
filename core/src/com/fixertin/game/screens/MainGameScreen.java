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

public class MainGameScreen extends GameScreen{
    public TextureAtlas testAtlas;
    public Enemy test;
    public static MainGameAssets assets = new MainGameAssets();

    //scale of screen when setting up viewport
    private float scale = 5f;




    @Override
    public void show() {
        assets.loadAssets();


        Vector2[] dataSet = new Vector2[8];
        dataSet[0] = new Vector2(-200.0f/PPM/10, -100.0f/PPM/10);
        dataSet[1] = new Vector2(000.0f/PPM/10, 50.0f/PPM/10);
        dataSet[2] = new Vector2(100.0f/PPM/10, 20.0f/PPM/10);
        dataSet[3] = new Vector2(250.0f/PPM/10, -102.0f/PPM/10);
        dataSet[4] = new Vector2(350.0f/PPM/10, 100.0f/PPM/10);
        dataSet[5] = new Vector2(450.0f/PPM/10, 500.0f/PPM/10);
        dataSet[6] = new Vector2(550.0f/PPM/10, -190.0f/PPM/10);
        dataSet[7] = new Vector2(500.0f/PPM/10, -180.0f/PPM/10);


        test = new Enemy(0, 0,0, 0, 10/PPM, 10/PPM, assets.untitled, scale, PPM, 0);
        test.addMoveInCurve(dataSet, 0.15f);
        //test.addShootAndTurn(7, 3, 90, 270, 40/PPM, 10, 1, 0.2f, -10);
        //test.addWait(2);
        //test.addShootAndTurn(7, 4, 90, 0, 20/PPM, 20, 1, 0.2f, -2);
        //test.addMoveTo(10/PPM, 90, 5/PPM);
        //test.addWait(1f);
        //test.addShootArc(20, 270, 360, 15/PPM);
        //test.addWait(.5f);
        //test.addShootArc(20, 280, 360, 15/PPM);
        entities.add(test);
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
