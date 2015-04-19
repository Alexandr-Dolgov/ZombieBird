package com.dolgov.gameworld;

import com.dolgov.gameobjects.Bird;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameWorld {

    private Bird bird;

    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
    }

    public void update(float delta){
        bird.update(delta);
    }

    public Bird getBird(){
        return bird;
    }
}
