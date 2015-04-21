package com.dolgov.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Alexandr on 20.04.2015.
 */
public class Pipe extends Scrollable {

    private Random random;

    private Rectangle skullUp, skullDown, barUp, barDown;

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;

    private float groundY;

    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        random = new Random();
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        // �������� update ����� ������������� ������ (Scrollable)
        super.update(delta);

        // ����� set() ��������� ��������� ���������� �������� ���� ���� - x, y
        // ������ � width � height ��������������
        barUp.set(position.x, position.y, width, height);
        barDown.set(
                position.x,
                position.y + height + VERTICAL_GAP,
                width,
                groundY - (position.y + height + VERTICAL_GAP)
        );

        // ������ ������ 24 �������. ������ ����� ����� 22 �������. ��� ��� �����
        // ������ ���� ������ �� 1 ������� ����� (��� ��� ����� ����� �������������
        // ������������ �����).

        // �������� �����������: (SKULL_WIDTH - width) / 2
        skullUp.set(
                position.x - (SKULL_WIDTH - width) / 2,
                position.y + height - SKULL_HEIGHT,
                SKULL_WIDTH,
                SKULL_HEIGHT
        );
        skullDown.set(
                position.x - (SKULL_WIDTH - width) / 2,
                barDown.y,
                SKULL_WIDTH,
                SKULL_HEIGHT
        );
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = random.nextInt(90) + 15;
    }

    public boolean collides(Bird bird) {
        if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), barDown)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullDown)
            );
        }
        return false;
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }
}
