package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fixertin.game.CommieGame;
import com.fixertin.game.entities.Enemy;
import com.fixertin.game.entities.Entity;
import com.fixertin.game.util.Constant;

import java.util.ArrayList;

public abstract class GameScreen implements Screen{
    private static final String TAG = GameScreen.class.getSimpleName();

    protected OrthographicCamera camera = null;
    protected Batch batch = new SpriteBatch();
    protected ShapeRenderer sp = new ShapeRenderer();
    protected static ArrayList<Enemy> entities = new ArrayList<Enemy>();
    protected static ArrayList<Entity> bullets = new ArrayList<Entity>();
    protected static ArrayList<Entity> playerBullets = new ArrayList<Entity>();
    protected final float PPM = Constant.PPM;
    protected final float scale = Constant.scale;
    protected CommieGame game;

    protected static class VIEWPORT{
        static float viewportWidth;
        static float viewportHeight;
        static float virtualWidth;
        static float virtualHeight;
        static float physicalWidth;
        static float physicalHeight;
        static float aspectRatio;

    }


    public GameScreen(CommieGame game){
        this.game = game;
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

    public static void addEntity(Enemy entity){
        if(entity == null)
            return;
        entities.add(entity);
    }
    public static void removeEntity(Enemy entity){
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
    public static void addPlayerBullets(Entity entity){
        if(entity == null)
            return;
        playerBullets.add(entity);
    }
    public static void removePlayerBullets(Entity entity){
        if(entity == null)
            return;
        else if(!playerBullets.contains(entity))
            return;
        playerBullets.remove(entity);
    }

    public static boolean isEntityOffScreen(Entity entity){
        if((entity.getPosition().x + entity.getTextureWidth()/2f) < 0 - VIEWPORT.viewportWidth/2f || //left
                (entity.getPosition().x - entity.getTextureWidth()/2f) > VIEWPORT.viewportWidth/2f || //right
                (entity.getPosition().y - entity.getTextureHeight()/2f) > VIEWPORT.viewportHeight/2f || //up
                (entity.getPosition().y + entity.getTextureHeight()/2f) < 0 - VIEWPORT.viewportHeight/2f) {//down
            //System.out.println(entity.getPosition().x + " " + entity.getPosition().y);
            //System.out.println(entity.getTextureWidth() + " " + entity.getTextureHeight());
            //System.out.println("Entity removed");
            return true;
        }
        else
            return false;
    }
    public static ArrayList<Enemy> getEntities(){
        return entities;
    }

    public static float getViewportWidth(){
        return VIEWPORT.viewportWidth;
    }
    public static float getViewPortHeight(){
        return VIEWPORT.viewportHeight;
    }


}
