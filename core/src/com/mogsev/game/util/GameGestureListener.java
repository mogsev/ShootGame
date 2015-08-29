package com.mogsev.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mogsev.game.entity.Bullet;
import com.mogsev.game.entity.Missile;
import com.mogsev.game.entity.Player;
import com.mogsev.game.screen.GameScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

/**
 * Created by zhenya on 29.08.2015.
 */
public class GameGestureListener implements GestureListener {
    private static final String TAG = "GameGestureListener";
    private GameScreen gameScreen;
    private Player player;
    private Group group;

    public GameGestureListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.player = gameScreen.getPlayer();
        this.group = gameScreen.getGroup();
    }

    //******************** Begin GestureDetector.GestureListener *********************************
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "TouchDown");
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
    public boolean tap(float x, float y, int count, int button) {
        Gdx.app.log(TAG, "Tap");
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        Gdx.app.log(TAG, "LongPress");
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Gdx.app.log(TAG, "fling");
        group.addActor(fireMissile(velocityX, velocityY, player.getX() + 32, player.getY() + 32));
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log(TAG, "Pan");
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "panStop");
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        Gdx.app.log(TAG, "Pinch");
        return true;
    }

    //******************** End GestureDetector.GestureListener *********************************

    private Bullet fireBullet(float x, float y, float posX, float posY) {
        Bullet bullet = Bullet.pool.obtain();
        bullet.setPosition(posX, posY);
        bullet.addAction(moveTo(x, Gdx.graphics.getHeight(), 3));
        //removeActor(bullet);
        return bullet;
    }

    private Missile fireMissile(float x, float y, float posX, float posY) {
        Missile missile = Missile.pool.obtain();
        missile.setPosition(posX, posY);
        missile.addAction(moveTo(x, Gdx.graphics.getHeight(), 5));
        //removeActor(bullet);
        return missile;
    }

}
