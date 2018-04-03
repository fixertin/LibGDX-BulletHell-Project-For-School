package com.fixertin.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;

public class Test implements Screen {
    protected Batch batch = new SpriteBatch();
    protected ShapeRenderer sp = new ShapeRenderer();
    int k = 70;
    Vector2[] points = new Vector2[k];
    Vector2[] dataSet;
    float speed = 0.15f;
    float current = 0;

    @Override
    public void show() {
        dataSet = new Vector2[8];
        dataSet[0] = new Vector2(0.0f, 100.0f);
        dataSet[1] = new Vector2(200.0f, 250.0f);
        dataSet[2] = new Vector2(300.0f, 220.0f);
        dataSet[3] = new Vector2(450.0f, 98.0f);
        dataSet[4] = new Vector2(550.0f, 300.0f);
        dataSet[5] = new Vector2(650.0f, 700.0f);
        dataSet[6] = new Vector2(750.0f, 10.0f);
        dataSet[7] = new Vector2(700.0f, 20.0f);

        CatmullRomSpline<Vector2> myCatmull = new CatmullRomSpline <Vector2>(dataSet, true);
        for (int i = 0; i < k; ++i) {
            points[i] = new Vector2();
            myCatmull.valueAt(points[i], ((float) i) / ((float) k - 1));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.WHITE);
        sp.identity();
        for (int i = 0; i < k - 1; ++i) {

            sp.line(points[i], points[i + 1]);
        }
        sp.end();

        float spriteX = first.x + (second.x - first.x) * t;
        float spriteY = first.y + (second.y - first.y) * t;

        sp.begin(ShapeRenderer.ShapeType.Filled);
        sp.setColor(Color.RED);
        sp.circle(spriteX, spriteY, 5);
        sp.end();
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

    }
}
