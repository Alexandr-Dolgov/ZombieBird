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
}
