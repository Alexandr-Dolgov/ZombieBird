package com.dolgov.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexandr on 20.04.2015.
 */
public class Scrollable {

    // Protected ����� private, �� ��������� ������������� � �������� �������.
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        // ���� ������ Scrollable ����� �� �����:
        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    // Reset: ����� �������������� � �������� ������, ���� ���������� �������
    // ������ ���������
    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    // ������ ������� � ��������� ������
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void stop() {
        velocity.x = 0;
    }
}
