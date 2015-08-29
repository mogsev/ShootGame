package com.mogsev.game.planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by zhenya on 28.08.2015.
 */
public class Sunred extends Planet {
    private static final String TAG = "Sunred";

    public Sunred() {
        setTexture(new Texture(Gdx.files.internal("sun_red.png")));
        setSize(200, 200);
        setLimitXY(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() - 350);
        setDuration(15.0f, 30.0f);
        setPosition(getRandomX(), getRandomY());
        addAction(Actions.moveTo(getRandomX(), getRandomY(), getRandomDuration()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
