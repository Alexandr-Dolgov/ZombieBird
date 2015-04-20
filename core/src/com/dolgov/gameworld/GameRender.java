package com.dolgov.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dolgov.gameobjects.Bird;
import com.dolgov.zbhelpers.AssetLoader;

/**
 * Created by Alexandr on 17.04.2015.
 */
public class GameRender {

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;

    private Bird bird;

    public GameRender(GameWorld world, int gameHeight, int midPointY){
        this.world = world;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
    }

    private void initGameObjects() {
        bird = world.getBird();
    }

    public void render(float runTime){
        Gdx.app.log("GameRender", "render");

        // �������� ������ ��� ����� ������
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // �������� ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // �������� Background ����
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // �������� Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // �������� Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        // ����������� ShapeRenderer
        shapeRenderer.end();

        // �������� SpriteBatch
        batcher.begin();
        // ������� ������������
        // ��� ������ ��� ������������������, ����� ������������ �������� ��� ������������
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

        // ������ ����� ������������, ������� �������� ��
        batcher.enableBlending();

        if (bird.shouldntFlap()) {
            batcher.draw(
                    AssetLoader.bird,
                    bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(),
                    1, 1,
                    bird.getRotation()
            );
        } else {
            batcher.draw(
                    AssetLoader.birdAnimation.getKeyFrame(runTime),
                    bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(),
                    1, 1,
                    bird.getRotation()
            );
        }

        // ����������� SpriteBatch
        batcher.end();
    }
}