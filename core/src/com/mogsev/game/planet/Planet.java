package com.mogsev.game.planet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by zhenya on 28.08.2015.
 */
public class Planet extends Actor {
    private static final String TAG = "Planet";
    private Texture texture;
    private float limitMinX;
    private float limitMaxX;
    private float limitMinY;
    private float limitMaxY;
    private float minDuration;
    private float maxDuration;
    private float limitTime;
    private boolean isEnemy = false;

    public Planet() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        newAction();
    }

    protected void newAction() {
        if (!hasActions()) {
            addAction(Actions.moveTo(getRandomX(), getRandomY(), getRandomDuration()));
        }
    }

    protected float getRandomX() {
        return MathUtils.random(limitMinX, limitMaxX);
    }

    protected float getRandomY() {
        return MathUtils.random(limitMinY, limitMaxY);
    }

    protected float getRandomDuration() {
        return MathUtils.random(minDuration, maxDuration);
    }

    protected void setLimitTime(float limitTime, boolean isEnemy) {
        this.limitTime = limitTime;
        this.isEnemy = isEnemy;
    }

    protected void setTexture(Texture texture) {
        this.texture = texture;
    }

    protected void setLimitXY(float limitMaxX, float limitMaxY) {
        this.limitMinX = 0;
        this.limitMaxX = limitMaxX;
        this.limitMinY = 0;
        this.limitMaxY = limitMaxY;
    }

    protected void setLimitXY(float limitMaxX, float limitMinY, float limitMaxY) {
        this.limitMinX = 0;
        this.limitMaxX = limitMaxX;
        this.limitMinY = limitMinY;
        this.limitMaxY = limitMaxY;
    }

    protected void setLimitXY(float limitMinX, float limitMaxX, float limitMinY, float limitMaxY) {
        this.limitMinX = limitMinX;
        this.limitMaxX = limitMaxX;
        this.limitMinY = limitMinY;
        this.limitMaxY = limitMaxY;
    }

    protected void setDuration(float minDuration, float maxDuration) {
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
    }

    protected float getLimitTime() {
        return limitTime;
    }

    protected boolean isEnemy() {
        return isEnemy;
    }
}
