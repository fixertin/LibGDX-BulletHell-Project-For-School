package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fixertin.game.screens.graphics.MainGameAssets;

public class WinScreen implements Screen {
    private static final String TAG = WinScreen.class.getSimpleName();

    protected Batch batch = new SpriteBatch();
    protected ShapeRenderer sp = new ShapeRenderer();
    public static MainGameAssets assets = new MainGameAssets();

    protected OrthographicCamera camera = null;

    public Color black = new Color(0, 0, 0, 1);
    public Color white = new Color(1, 1, 1, 1);

    protected static class VIEWPORT{
        static float viewportWidth;
        static float viewportHeight;
        static float virtualWidth;
        static float virtualHeight;
        static float physicalWidth;
        static float physicalHeight;
        static float aspectRatio;

    }

    public WinScreen(){
        setupViewport(100,100);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT.viewportWidth,
                VIEWPORT.viewportHeight);
    }

    @Override
    public void show() {
        assets.loadAssets();
        assets.winMusic.setVolume(.1f);
        assets.winMusic.setLooping(true);
        assets.winMusic.play();
    }

    private final float alphaIncrease = .01f;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(0,
                0, 0f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        sp.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setColor(black.lerp(white, alphaIncrease));
        batch.draw(assets.winScreenBackground,
                -VIEWPORT.viewportWidth/2,
                -VIEWPORT.viewportHeight/2,
                VIEWPORT.viewportWidth,
                VIEWPORT.viewportHeight);
        batch.end();
        //System.out.println(Color.WHITE.toString());
    }

    @Override
    public void resize(int width, int height) {
        setupViewport(100,100);
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
        assets.winMusic.stop();
    }

    @Override
    public void dispose() {
        assets.unloadAssets();
    }

    protected void setupViewport(int width, int height){
        //Make the viewport a percentage of the total display area
        VIEWPORT.virtualWidth = width;
        VIEWPORT.virtualHeight = height;

        //Current viewport dimensions
        VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
        VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;

        //pixel dimensions of display
        VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
        VIEWPORT.physicalHeight = Gdx.graphics.getHeight();

        //aspect ratio for current viewport
        VIEWPORT.aspectRatio = (VIEWPORT.virtualWidth / VIEWPORT.virtualHeight);

        //update viewport if there could be skewing
        if( VIEWPORT.physicalWidth / VIEWPORT.physicalHeight >= VIEWPORT.aspectRatio){
            //Letterbox left and right
            VIEWPORT.viewportWidth = VIEWPORT.viewportHeight * (VIEWPORT.physicalWidth/VIEWPORT.physicalHeight);
            VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
        }else{
            //letterbox above and below
            VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
            VIEWPORT.viewportHeight = VIEWPORT.viewportWidth * (VIEWPORT.physicalHeight/VIEWPORT.physicalWidth);
        }



        Gdx.app.log(TAG, "WorldRenderer: virtual: (" + VIEWPORT.virtualWidth + "," + VIEWPORT.virtualHeight + ")" );
        Gdx.app.log(TAG, "WorldRenderer: viewport: (" + VIEWPORT.viewportWidth + "," + VIEWPORT.viewportHeight + ")" );
        Gdx.app.log(TAG, "WorldRenderer: physical: (" + VIEWPORT.physicalWidth + "," + VIEWPORT.physicalHeight + ")" );
    }

}
