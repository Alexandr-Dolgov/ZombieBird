package com.dolgov.gameworld;

import com.badlogic.gdx.Gdx;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameRender {

    private GameWorld world;

    public GameRender(GameWorld world){
        this.world = world;
    }

    public void render(){
        Gdx.app.log("GameRender", "render");
    }
}
