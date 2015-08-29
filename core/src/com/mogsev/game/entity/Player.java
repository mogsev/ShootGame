package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by zhenya on 26.08.2015.
 */
public class Player extends LifeActor {
    private static final String TAG = "Player";
    private final Texture texture = new Texture(Gdx.files.internal("player.png"));
    private final TextureRegion region = new TextureRegion(texture, 32, 0, 32, 32);
    private Rectangle rectangle;


    public Player() {
        Gdx.app.log(TAG, "new Player");
        setName("Player");
        setSize(64, 64);
        setPosition(Gdx.graphics.getWidth() / 2, 0);

        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "touchDown");
                return true;
            }
        });
        rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
        //batch.draw(life, getX(), getY() + 65, 64, 3 );
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!checkLimitRight()) {
            this.clearActions();
        }
        accelerometer();
    }

    /**
     * Accelerometer check action
     */
    private void accelerometer() {
        //Gdx.app.log(TAG, "getRotation: " + Gdx.input.getRotation());
        //Gdx.app.log(TAG, "getNativeOrientation: " + Gdx.input.getNativeOrientation());
        //Gdx.app.log(TAG, "Gdx.input.getAccelerometerX(): " + Gdx.input.getAccelerometerX());
        //Gdx.app.log(TAG, "Gdx.input.getAccelerometerY(): " + Gdx.input.getAccelerometerY());
        //Gdx.app.log(TAG, "Gdx.input.getAccelerometerZ(): " + Gdx.input.getAccelerometerZ());
        if (Gdx.input.getAccelerometerX() < 0.0f && checkLimitRight()) {
            if (Gdx.input.getAccelerometerX() < -2.5f) {
                setX(getX() + 1.4f);
            } else {
                setX(getX() + 0.9f);
            }
        } else if (checkLimitLeft()) {
            if (Gdx.input.getAccelerometerX() > 2.5f) {
                setX(getX() - 1.4f);
            } else {
                setX(getX() - 0.9f);
            }
        }
    }

    /**
     * Check limit right
     * @return
     */
    private boolean checkLimitRight() {
        return (getX() < (Gdx.graphics.getWidth() - getWidth())) ? true : false;
    }

    /**
     * Check limit left
     * @return
     */
    private boolean checkLimitLeft() {
        return (getX() > -0.0f) ? true : false;
    }

    /**
     * Return bounds of the object
     * @return
     */
    public Rectangle getBound() {
        //return new Rectangle(getX(), getY(), WIDTH, HEIGHT);
        rectangle.set(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }
}