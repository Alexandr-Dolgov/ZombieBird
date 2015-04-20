package com.dolgov.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexandr on 19.04.2015.
 */
public class Bird {
    private Vector2 position;       //���������� ������, �������� ������� �� � � � ��� ����� �����
    private Vector2 velocity;       //������������� ������ ��������� �� � � �
    private Vector2 acceleration;   //� ������ ���������� �� � � �

    private float rotation;
    private int width;
    private int height;

    public Bird (float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }

    public void update (float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 200) {
            velocity.y = 200;
        }
        position.add(velocity.cpy().scl(delta));
    }

    public boolean isFalled() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return velocity.y > 70;
    }

    public void onClick() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return  position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }
}
