package com.dolgov.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.dolgov.zbhelpers.AssetLoader;

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

    private Circle boundingCircle;

    private boolean isAlive;

    public Bird (float x, float y, int width, int height) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);

        this.width = width;
        this.height = height;

        boundingCircle = new Circle();

        isAlive = true;
    }

    public void update (float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 200) {
            velocity.y = 200;
        }
        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // ��������� ������ ������� �������
        if (velocity.y < 0) {
            rotation -= 600 * delta;
            if (rotation < -20) {
                rotation = -20;
            }
        }

        // ��������� �� ������� �������
        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return (velocity.y > 70) || !isAlive;
    }

    public void onClick() {
        if (isAlive) {
            AssetLoader.flap.play();
            velocity.y = -140;
        }
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

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die(){
        isAlive = false;
        velocity.y = 0;
    }

    public void decelerate(){
        acceleration.y = 0;
    }
}
