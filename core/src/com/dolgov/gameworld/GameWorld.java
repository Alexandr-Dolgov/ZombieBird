package com.dolgov.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.dolgov.gameobjects.Bird;
import com.dolgov.gameobjects.ScrollHandler;
import com.dolgov.zbhelpers.AssetLoader;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameWorld {

    public enum GameState{
        READY,
        RUNNING,
        GAMEOVER,
        HIGHSCORE
    }
    private GameState currentState;

    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;

    private int midPointY;

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
        this.midPointY = midPointY;
    }

    public void update(float delta){
        switch (currentState){
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }


    public void updateReady(float delta){
        //TODO пока ничего не делаем
    }

    public void updateRunning(float delta){

        if (delta > 0.15f) {
            delta = 0.15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public Bird getBird(){
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady(){
        return (currentState == GameState.READY);
    }

    public void start(){
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
}
