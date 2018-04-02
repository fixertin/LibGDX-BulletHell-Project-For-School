package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.util.Utility;

public class MainGameScreen extends GameScreen{
    public TextureAtlas testAtlas;
    public TextureRegion untitled, untitled2, untitled3, untitled4;
    public Entity test;

    private float scale = 5f;


    @Override
    public void show() {
        Utility.loadTextureAtlas("textures/test.atlas");
        testAtlas = Utility.getTextureAtlas("textures/test.atlas");
        untitled = testAtlas.findRegion("Untitled");
        untitled2 = testAtlas.findRegion("Untitled2");
        untitled3 = testAtlas.findRegion("Untitled3");
        untitled4 = testAtlas.findRegion("Untitled4");
        test = new Entity(10/PPM, 10/PPM, 10/PPM, 10/PPM, untitled, scale, PPM);
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
        float test = untitled.getRegionHeight();
        test = test/4;
        test = test/PPM;
        batch.begin();
        //batch.draw(untitled, 0, 0, untitled.getRegionWidth()/scale/PPM, untitled.getRegionHeight()/scale/PPM);
        //batch.draw(untitled2, 10/PPM, 10/PPM, untitled2.getRegionWidth()/scale/PPM, untitled2.getRegionHeight()/scale/PPM);
        //batch.draw(untitled3, 20/PPM, 20/PPM, untitled3.getRegionWidth()/scale/PPM, untitled3.getRegionHeight()/scale/PPM);
        //batch.draw(untitled4, 30/PPM, 30/PPM, untitled4.getRegionWidth()/scale/PPM, untitled4.getRegionHeight()/scale/PPM);
        //batch.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        //sp.rect(0, 0, 10/ PPM, 10/ PPM);
        //sp.rect(10/PPM, 10/PPM, 10/ PPM, 10/ PPM);
        //sp.rect(20/PPM, 20/PPM, 10/ PPM, 10/ PPM);
        //sp.rect(30/PPM, 30/PPM, 10/ PPM, 10/ PPM);
        //sp.rect(40/PPM, 40/PPM, 10/ PPM, 10/ PPM);
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
        //Utility.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        sp.dispose();
        Utility.dispose();
    }
}
