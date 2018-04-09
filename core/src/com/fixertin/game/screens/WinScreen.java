package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fixertin.game.screens.graphics.MainGameAssets;

public class WinScreen implements Screen {

    protected Batch batch = new SpriteBatch();
    protected ShapeRenderer sp = new ShapeRenderer();
    public static MainGameAssets assets = new MainGameAssets();

    public Color black = new Color(0, 0, 0, 1);
    public Color white = new Color(1, 1, 1, 1);

    @Override
    public void show() {
        assets.loadAssets();
    }

    private final float alphaIncrease = .01f;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setColor(black.lerp(white, alphaIncrease));
        batch.draw(assets.winScreenBackground,
                0,
                0,
                assets.winScreenBackground.getRegionWidth(),
                assets.winScreenBackground.getRegionHeight());
        batch.end();
        //System.out.println(Color.WHITE.toString());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        assets.unloadAssets();
    }
}
