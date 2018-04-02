package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.screens.graphics.MainGameAssets;
import com.fixertin.game.util.Utility;

public class MainGameScreen extends GameScreen{
    public TextureAtlas testAtlas;
    public Entity test, testIntersect;
    public MainGameAssets assets = new MainGameAssets();

    private float scale = 5f;


    @Override
    public void show() {
        assets.loadAssets();
        test = new Entity(10/PPM, 10/PPM,2/PPM, 0, 10/PPM, 10/PPM, assets.untitled, scale, PPM);
        testIntersect = new Entity(25/PPM, 10/PPM, 10/PPM, 10/PPM, assets.untitled2, scale, PPM);
        entities.add(test);
        entities.add(testIntersect);
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

        sp.end();

        for(Entity e : entities){
            e.render(batch, sp, Gdx.graphics.getDeltaTime());
        }
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
