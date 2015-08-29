package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mogsev.game.util.LoadTexture;

/**
 * Created by zhenya on 26.08.2015.
 */
public class Player extends LifeActor {
    private static final String TAG = "Player";
    private final Texture texture = new Texture(Gdx.files.internal("player.png"));
    private final TextureRegion region = new TextureRegion(texture, 32, 0, 32, 32);
    //private TextureRegion life = new TextureRegion(LoadTexture.PLAYER, 38, 25, 1, 1);

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
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
        //batch.draw(life, getX(), getY() + 65, 64, 3 );
    }

    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getX() > (Gdx.graphics.getWidth() - getWidth())) {
            clearActions();
        }
        accelerometer();
    }

    private void accelerometer() {
        if (Gdx.input.getRotation() > 0 && Gdx.input.getRotation() < 90) {
            this.clearActions();
            this.addAction(Actions.moveTo(Gdx.graphics.getWidth(), 0, 2));
        }
        if (Gdx.input.getRotation() > 270 && Gdx.input.getRotation() < 360) {
            this.clearActions();
            this.addAction(Actions.moveTo(0, 0, 2));
        }
    }
}