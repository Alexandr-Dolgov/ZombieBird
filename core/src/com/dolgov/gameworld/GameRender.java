package com.dolgov.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameRender {

    private GameWorld world;
    private OrthographicCamera camera;

    public GameRender(GameWorld world){
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, 204);
    }

    public void render(){
        Gdx.app.log("GameRender", "render");
    }
}
