package com.dolgov.gameobjects;

import java.util.Random;

/**
 * Created by Alexandr on 20.04.2015.
 */
public class Pipe extends Scrollable {

    private Random random;

    public Pipe(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        random = new Random();
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = random.nextInt(90) + 15;
    }

}
