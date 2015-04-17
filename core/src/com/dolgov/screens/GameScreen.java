package com.dolgov.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.dolgov.gameworld.GameRender;
import com.dolgov.gameworld.GameWorld;

/**
 * Created by Alexandr on 16.04.2015.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRender render;

    public GameScreen(){
        Gdx.app.log("GameScreen", "attached");
        world = new GameWorld();
        render = new GameRender();
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        world.update(delta);//delta исползуем чтобы сделать обновление мира зависимым от FPS
        render.render();
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
