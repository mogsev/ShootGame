package com.mogsev.game.planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mogsev.game.entity.Enemy;

/**
 * Created by zhenya on 28.08.2015.
 */
public class Planet extends Actor {
    private static final String TAG = "Planet";
    private final Texture texture = new Texture(Gdx.files.internal("planet.png"));
    private float deltaTime;
    private float limitX;
    private float limitY;
    private float minDuration;
    private float maxDuration;
    private float limitTime;
    private boolean isEnemy;

    public Planet() {
        setSize(400, 400);
        setLimitTime(4.0f, true);
        setLimitXY(Gdx.graphics.getWidth() - 200, 400.0f);
        setDuration(15.0f, 30.0f);
        setPosition(getRandomX(), getRandomY());
        addAction(Actions.moveTo(getRandomX(), getRandomY(), getRandomDuration()));
    }

    private void setLimitXY(float limitX, float limitY) {
        this.limitX = limitX;
        this.limitY = limitY;
    }

    private void setDuration(float minDuration, float maxDuration) {
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        newAction();
        newEnemy(delta);
    }

    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }

    private void newAction() {
        if (!hasActions()) {
            addAction(Actions.moveTo(getRandomX(), getRandomY(), getRandomDuration()));
        }
    }

    private void newEnemy(float delta) {
        if (isEnemy) {
            deltaTime += delta;
            if (deltaTime > limitTime) {
                this.getParent().addActor(new Enemy());
                deltaTime = 0;
            }
        }
    }

    private float getRandomX() {
        return MathUtils.random(limitX);
    }

    private float getRandomY() {
        return MathUtils.random(limitY) - getHeight();
    }

    private float getRandomDuration() {
        return MathUtils.random(minDuration, maxDuration);
    }

    public void setLimitTime(float limitTime, boolean isEnemy) {
        this.limitTime = limitTime;
        this.isEnemy = isEnemy;
    }
}
