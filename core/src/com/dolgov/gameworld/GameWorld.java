package com.dolgov.gameworld;

import com.dolgov.gameobjects.Bird;
import com.dolgov.gameobjects.ScrollHandler;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameWorld {

    private Bird bird;
    private ScrollHandler scroller;

    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta){
        bird.update(delta);
        scroller.update(delta);
    }

    public Bird getBird(){
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }
}
