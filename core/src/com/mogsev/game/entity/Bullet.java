package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mogsev.game.util.LoadTexture;

import java.awt.Rectangle;

/**
 * Created by zhenya on 26.08.2015.
 */
public class Bullet extends Actor {
    private static final String TAG = "Bullet";
    private TextureRegion region = new TextureRegion(LoadTexture.BULLET, 0, 0, 2, 6);

    public Bullet(float x, float y, float posX, float posY) {
        Gdx.app.log(TAG, "new BulLet" + x + " " + y + " " + posX + " " + posY);
        setSize(x, y);
        setPosition(posX, posY);
        addAction(Actions.moveTo(x, y, 10));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.setColor(getColor());
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }

    public Actor hit(float x, float y, boolean touchable) {
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }
}
