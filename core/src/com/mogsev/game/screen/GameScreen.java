package com.mogsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mogsev.game.entity.Bullet;
import com.mogsev.game.entity.Enemy;
import com.mogsev.game.entity.Player;

import javax.swing.Action;

/**
 * Created by zhenya on 26.08.2015.
 */
public class GameScreen implements Screen {
    private static final String TAG = "GameScreen";
    private Stage stage;
    private Player player;

    public GameScreen(SpriteBatch batch) {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);
        Gdx.input.setInputProcessor(stage);

        player = new Player();
        stage.addActor(player);
        stage.addActor(new Enemy());

        stage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (y < player.getHeight()) {
                    player.addAction(Actions.moveTo(x, 0, 15));
                } else {
                    stage.addActor(new Bullet(x, y, player.getX() + 32, player.getY() + 32));
                }
                return true;
            }
        });


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
