package com.mogsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.mogsev.game.planet.Planet;
import com.mogsev.game.entity.Player;
import com.mogsev.game.planet.Sunblue;
import com.mogsev.game.planet.Sunred;
import com.mogsev.game.util.GameStatus.Status;

import com.mogsev.game.util.LoadTexture;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by zhenya on 26.08.2015.
 */
public class GameScreen implements Screen {
    private static final String TAG = "GameScreen";
    //private TextureRegion background = new TextureRegion(LoadTexture.PLANET, 500, 500);
    private Stage stage;
    private Player player;
    private Group group;
    private Status gameStatus;

    public GameScreen(SpriteBatch batch) {
        gameStatus = Status.GAME_RUNNING;

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);
        Gdx.input.setInputProcessor(stage);

        group = new Group();

        player = new Player();

        group.addActor(new Sunred());
        group.addActor(new Sunblue());
        group.addActor(new Planet());

        group.addActor(player);


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
                    group.addActor(fire(x, y, player.getX() + 32, player.getY() + 32));
                }
                return true;
            }
        });
        stage.addActor(group);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        switch (gameStatus) {
            case GAME_RUNNING:
                Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                checkCollisions();
                //stage.getBatch().begin();
                //stage.getBatch().draw(background, 500, 500);
                //stage.getBatch().end();
                stage.act(delta);
                stage.draw();
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
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //stage.dispose();
    }

    private Bullet fire(float x, float y, float posX, float posY) {
        Bullet bullet = Bullet.pool.obtain();
        bullet.setPosition(posX, posY);
        bullet.addAction(moveTo(x, Gdx.graphics.getHeight(), 3));
        //removeActor(bullet);
        return bullet;
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
                            Gdx.app.log(TAG, "group count " + actors.size);
                            bullet.remove();
                            enemy.changeLifeCount(bullet.shot(), LifeActor.Action.REDUCE);
                            Gdx.app.log(TAG, " " + enemy.getLifeCount());
                            if (enemy.getLifeCount() <= 0) {
                                enemy.remove();
                            }
                            Gdx.app.log(TAG, "!-!");
                        }
                    }
                }
            }
        }
    }
}