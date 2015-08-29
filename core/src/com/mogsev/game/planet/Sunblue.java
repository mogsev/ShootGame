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
public class Sunblue extends Planet {
    private static final String TAG = "Sunblue";

    public Sunblue() {
        setTexture(new Texture(Gdx.files.internal("sun_blue.png")));
        setSize(300, 300);
        setLimitXY(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 300.0f, Gdx.graphics.getHeight() - 150);
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
