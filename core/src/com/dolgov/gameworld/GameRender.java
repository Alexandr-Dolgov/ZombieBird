package com.dolgov.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameRender {

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    public GameRender(GameWorld world){
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, 204);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(){
        Gdx.app.log("GameRender", "render");
        /*
         * 1. Рисуем черный задний фон, чтобы избавится от моргания и следов от передвигающихся объектов
         */
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*
         * 2. Отрисовываем однотонный квадрат
         */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(
                world.getRect().x,
                world.getRect().y,
                world.getRect().width,
                world.getRect().height
        );
        shapeRenderer.end();
        /*
         * 3. Отрисовываем рамку для квадрата
         */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(
                world.getRect().x,
                world.getRect().y,
                world.getRect().width,
                world.getRect().height
        );
        shapeRenderer.end();
    }
}
