package com.dolgov.gameobjects;

/**
 * Created by Alexandr on 20.04.2015.
 */
public class Grass extends Scrollable {

    // Когда констуктор Grass вызван – вызовите конструтор родителя (Scrollable)
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }
}
