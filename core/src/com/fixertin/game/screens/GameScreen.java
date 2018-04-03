package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fixertin.game.entities.Entity;

import java.util.ArrayList;

public abstract class GameScreen implements Screen{
    private static final String TAG = GameScreen.class.getSimpleName();

    protected OrthographicCamera camera = null;
    protected Batch batch = new SpriteBatch();
    protected ShapeRenderer sp = new ShapeRenderer();
    protected static ArrayList<Entity> entities = new ArrayList<Entity>();
    protected static ArrayList<Entity> bullets = new ArrayList<Entity>();
    protected final float PPM = 10f;

    protected static class VIEWPORT{
        static float viewportWidth;
        static float viewportHeight;
        static float virtualWidth;
        static float virtualHeight;
        static float physicalWidth;
        static float physicalHeight;
        static float aspectRatio;

    }


    public GameScreen(){
        setupViewport(10,10);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT.viewportWidth,
                VIEWPORT.viewportHeight);

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

    public static void addEntity(Entity entity){
        if(entity == null)
            return;
        entities.add(entity);
    }

    public static void removeEntity(Entity entity){
        if(entity == null)
            return;
        else if(!entities.contains(entity))
            return;
        entities.remove(entity);
    }
    public static void addBullets(Entity entity){
        if(entity == null)
            return;
        bullets.add(entity);
    }
    public static void removeBullets(Entity entity){
        if(entity == null)
            return;
        else if(!bullets.contains(entity))
            return;
        bullets.remove(entity);
    }
    public static boolean isEntityOffScreen(Entity entity){
        if((entity.getPosition().x + entity.getTextureWidth()) < 0 - VIEWPORT.viewportWidth/2 ||
                (entity.getPosition().x - entity.getTextureWidth()) > VIEWPORT.viewportWidth/2 ||
                (entity.getPosition().y - entity.getTextureHeight()) > VIEWPORT.viewportHeight/2 ||
                (entity.getPosition().y + entity.getTextureHeight()) < 0 - VIEWPORT.viewportHeight/2) {
            System.out.println(entity.getPosition().x + " " + entity.getPosition().y);
            System.out.println(entity.getTextureWidth() + " " + entity.getTextureHeight());
            System.out.println("Entity removed");
            return true;
        }
        else
            return false;
    }
}
