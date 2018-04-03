package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fixertin.game.entities.Enemy;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.util.Constant;
import com.fixertin.game.util.Utility;

public class MainGameScreen extends GameScreen{
    public TextureAtlas testAtlas;
    public Enemy test;
    public static MainGameAssets assets = new MainGameAssets();

    //scale of screen when setting up viewport
    private float scale = 10f;


    @Override
    public void show() {
        assets.loadAssets();
        test = new Enemy(0, 10/PPM,0, 0, 10/PPM, 10/PPM, assets.untitled, scale/2f, PPM, 0);
        test.addInfiniteShootAndTurn(4, 90, 270, 20/PPM, 10, 1, 0.2f);
        entities.add(test);
    }

    @Override
    public void render(float delta) {
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
        sp.rect(0, 0, 10/PPM, 10/PPM);
        sp.end();

        for(Entity e : entities){
            e.render(batch, sp, Gdx.graphics.getDeltaTime());
        }
        //entities.removeIf(entity -> entity.isRemoved());
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
