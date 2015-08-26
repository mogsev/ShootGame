package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mogsev.game.util.LoadTexture;

/**
 * Created by zhenya on 26.08.2015.
 */
public class Player extends Actor {
    private static final String TAG = "Player";
    private TextureRegion region = new TextureRegion(LoadTexture.PLAYER, 32, 0, 32, 32);

    public Player() {
        Gdx.app.log(TAG, "new Player");
        setName("Player");
        setSize(64, 64);
        setPosition(0, 0);




/**
        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "touchDown");
                Gdx.app.log(TAG, Gdx.graphics.getHeight() + " " + Gdx.graphics.getWidth() + " " + getX());
                if (getX() > Gdx.graphics.getWidth() - 64) {
                    setX(0);
                } else {
                    setX(getX() + 10);
                }
                return true;
            }
        });*/
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.setColor(getColor());
        batch.draw(region, getX(), getY(), 64, 64);
    }

    public Actor hit(float x, float y, boolean touchable) {
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }
}
