package com.mogsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mogsev.game.entity.Bullet;
import com.mogsev.game.entity.BulletEnemy;
import com.mogsev.game.entity.Enemy;
import com.mogsev.game.entity.LifeActor;
import com.mogsev.game.entity.Missile;
import com.mogsev.game.entity.Player;
import com.mogsev.game.planet.Background;
import com.mogsev.game.planet.PlanetEarth;
import com.mogsev.game.planet.Sunblue;
import com.mogsev.game.planet.Sunred;
import com.mogsev.game.util.GameGestureListener;
import com.mogsev.game.util.GameStatus.Status;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by zhenya on 26.08.2015.
 */
public class GameScreen implements Screen{
    private static final String TAG = "GameScreen";
    private Stage stage;
    private Player player;
    private Group group;
    private Status gameStatus;
    private BitmapFont font = new BitmapFont();

    public GameScreen(SpriteBatch batch) {
        gameStatus = Status.GAME_RUNNING;
        Gdx.app.log(TAG, "Accelerometer is " + Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer));
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);
        //Gdx.input.setInputProcessor(stage);


        group = new Group();

        player = new Player();

        group.addActor(new Background());
        group.addActor(new Sunred());
        group.addActor(new Sunblue());
        group.addActor(new PlanetEarth());

        group.addActor(player);
        //group.addActor(new Missile());


        group.addActor(new Enemy());


        stage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (y < player.getHeight()) {
                    player.clearActions();
                    if (player.getX() < x) {
                        if (x - player.getX() > 200) {
                            player.addAction(Actions.moveTo(x, 0, 3));
                        } else {
                            player.addAction(Actions.moveTo(x, 0, 1));
                        }
                    } else {
                        if (player.getX() - x > 200) {
                            player.addAction(Actions.moveTo(x, 0, 3));
                        } else {
                            player.addAction(Actions.moveTo(x, 0, 1));
                        }
                    }
                } else {
                    group.addActor(fireBullet(x, y, player.getX() + 32, player.getY() + 32));
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(group);
    }

    @Override
    public void show() {
        font.setColor(Color.WHITE);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(new GameGestureListener(this)));
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        switch (gameStatus) {
            case GAME_RUNNING:
                Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                checkCollisions(); // Check collision
                //stage.getBatch().begin();
                //stage.getBatch().draw(background, 500, 500);
                //stage.getBatch().end();
                stage.act(delta);
                stage.draw();
                showFps(stage); // Show FPS
                break;
            case GAME_PAUSED:
                //this.stage.getBatch().begin();
                //this.pause();
                //this.stage.getBatch().end();
                break;
            case GAME_RESUME:

                break;
            case GAME_OVER:

                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        gameStatus = Status.GAME_PAUSED;
        BulletEnemy.pool.clear();
        Bullet.pool.clear();
        Enemy.pool.obtain();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }



    private void checkCollisions() {
        SnapshotArray actors = group.getChildren();
        for (int i = 0; i < actors.size; i++) {
            Object objA = actors.get(i);
            if (objA instanceof Bullet) {
                Bullet bullet = (Bullet) objA;
                for (int n = 0; n < actors.size; n++) {
                    Object objB = actors.get(n);
                    if (objB instanceof Enemy) {
                        Enemy enemy = (Enemy) objB;
                        if (bullet.getBound().overlaps(enemy.getBound())) {
                            //Gdx.app.log(TAG, "group count " + actors.size);
                            bullet.remove();
                            bullet.clearActions();
                            Bullet.pool.free(bullet);
                            enemy.changeLifeCount(bullet.shot(), LifeActor.Action.REDUCE);
                            //Gdx.app.log(TAG, " " + enemy.getLifeCount());
                            if (enemy.getLifeCount() <= 0) {
                                //enemy.remove();
                                enemy.setActive(false);
                            }
                            //Gdx.app.log(TAG, "!-!");
                        }
                    }
                }
            }
        }
    }

    private void showFps(Stage stage) {
        stage.getBatch().begin();
        font.draw(stage.getBatch(), "Fps: " + Gdx.graphics.getFramesPerSecond(), 0, 15);
        stage.getBatch().end();
    }

    public Group getGroup() {
        return group;
    }

    public Player getPlayer() {
        return player;
    }

    private Bullet fireBullet(float x, float y, float posX, float posY) {
        Bullet bullet = Bullet.pool.obtain();
        bullet.setPosition(posX, posY);
        bullet.addAction(moveTo(x, Gdx.graphics.getHeight(), 3));
        //removeActor(bullet);
        return bullet;
    }
}