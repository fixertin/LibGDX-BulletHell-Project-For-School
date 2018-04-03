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

    int k = 70;
    Vector2[] points = new Vector2[k];
    Vector2[] dataSet;
    float speed = 0.15f;
    float current = 0;


    @Override
    public void show() {
        assets.loadAssets();


        dataSet = new Vector2[8];
        dataSet[0] = new Vector2(-200.0f/PPM/10, -100.0f/PPM/10);
        dataSet[1] = new Vector2(000.0f/PPM/10, 50.0f/PPM/10);
        dataSet[2] = new Vector2(100.0f/PPM/10, 20.0f/PPM/10);
        dataSet[3] = new Vector2(250.0f/PPM/10, -102.0f/PPM/10);
        dataSet[4] = new Vector2(350.0f/PPM/10, 100.0f/PPM/10);
        dataSet[5] = new Vector2(450.0f/PPM/10, 500.0f/PPM/10);
        dataSet[6] = new Vector2(550.0f/PPM/10, -190.0f/PPM/10);
        dataSet[7] = new Vector2(500.0f/PPM/10, -180.0f/PPM/10);

        CatmullRomSpline<Vector2> myCatmull = new CatmullRomSpline <Vector2>(dataSet, false);
        for (int i = 0; i < k; ++i) {
            points[i] = new Vector2();
            myCatmull.valueAt(points[i], ((float) i) / ((float) k - 1));
        }
        test = new Enemy(points[0].x, points[0].y,0, 0, 10/PPM, 10/PPM, assets.untitled, scale, PPM, 0);
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

    private Vector2 test(){
        current += Gdx.graphics.getDeltaTime() * speed;
        if(current >= 1) //if it's reached the second point
            current -= 1;
        float place = current * k;
        Vector2 first = points[(int)place];
        Vector2 second;
        if(((int)place+1) < k)
        {
            second = points[(int)place+1];
        }
        else
        {
            second = points[(int)place]; //or finish, in case it does not loop.
            speed = -speed;
        }
        float t = place - ((int)place); //the decimal part of place


        float spriteX = first.x + (second.x - first.x) * t;
        float spriteY = first.y + (second.y - first.y) * t;
        return new Vector2(spriteX, spriteY);
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
        for (int i = 0; i < k - 1; ++i) {

            sp.line(points[i], points[i + 1]);
        }
        sp.end();

        for(Entity e : entities){

            e.render(batch, sp, Gdx.graphics.getDeltaTime());
            e.setPosition(test());
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
