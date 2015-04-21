package com.dolgov.gameworld;

import com.dolgov.gameobjects.Bird;
import com.dolgov.gameobjects.ScrollHandler;
import com.dolgov.zbhelpers.AssetLoader;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameWorld {

    private Bird bird;
    private ScrollHandler scroller;
    private boolean isAlive = true;

    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta){
        bird.update(delta);
        scroller.update(delta);
        if (isAlive && scroller.collides(bird)) {
            // Clean up on game over
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
        }
    }

    public Bird getBird(){
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }
}
