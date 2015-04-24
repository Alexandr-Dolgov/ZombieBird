package com.dolgov.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.dolgov.gameworld.GameRender;
import com.dolgov.gameworld.GameWorld;
import com.dolgov.zbhelpers.InputHadler;

/**
 * Created by Alexandr on 16.04.2015.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRender render;
    private float runTime = 0;

    public GameScreen(){
        Gdx.app.log("GameScreen", "attached");

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float scale = screenWidth / gameWidth;
        float gameHeight = screenHeight / scale;

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        render = new GameRender(world, (int) gameWidth, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHadler(world.getBird()));
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);//delta исползуем чтобы сделать обновление мира зависимым от FPS
        render.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
