package com.dolgov.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dolgov.gameobjects.Bird;
import com.dolgov.gameobjects.Grass;
import com.dolgov.gameobjects.Pipe;
import com.dolgov.gameobjects.ScrollHandler;
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
    private int gameWidth;
    private int gameHeight;

    // Game Objects
    private Bird bird;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    public GameRender(GameWorld world, int gameWidth, int gameHeight, int midPointY){
        this.world = world;

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, gameWidth, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
    }

    private void initGameObjects() {
        bird = world.getBird();
        scroller = world.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    public void render(float runTime){
        // Заполним задний фон одним цветом
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Стартуем ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Отрисуем Background цвет
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, gameWidth, midPointY + 66);

        // Отрисуем Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, gameWidth, 11);

        // Отрисуем Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, gameWidth, 52);

        // Заканчиваем ShapeRenderer
        shapeRenderer.end();

        // Стартуем SpriteBatch
        batcher.begin();
        // Отменим прозрачность
        // Это хорошо для производительности, когда отрисовываем картинки без прозрачности
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, gameWidth, 43);

        // 1. Отрисовываем Grass
        drawGrass();

        // 2. Отрисовываем Pipes
        drawPipes();

        // 3. Отрисовываем Skulls (необходима прозрачность)
        batcher.enableBlending();
        drawSkulls();

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

        // Переводим int в String
        String score = world.getScore() + "";

        // Отрисуем тень
        AssetLoader.shadow.draw(batcher, score, (gameWidth / 2) - (3 * score.length()), 12);
        // Отрисуем сам текст
        AssetLoader.font.draw(batcher, score, (gameWidth / 2) - (3 * score.length() - 1), 11);

        // Заканчиваем SpriteBatch
        batcher.end();
    }

    private void drawGrass() {
        // отрисуем траву
        batcher.draw(AssetLoader.grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(AssetLoader.grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        // Временный код, извините за кашу :)
        // Мы это починим, как только закончим с Pipe классом.

        batcher.draw(AssetLoader.skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(AssetLoader.skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(AssetLoader.skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(AssetLoader.skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(AssetLoader.skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(AssetLoader.skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        // Временный код, извините за кашу :)
        // Мы это починим, как только закончим с Pipe классом.
        batcher.draw(AssetLoader.bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(AssetLoader.bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(AssetLoader.bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(AssetLoader.bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(AssetLoader.bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(AssetLoader.bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }
}