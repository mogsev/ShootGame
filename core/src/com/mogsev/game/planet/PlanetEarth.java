package com.mogsev.game.planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mogsev.game.entity.Enemy;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

/**
 * Created by zhenya on 29.08.2015.
 */
public class PlanetEarth extends Planet {
    private static final String TAG = "PlanetEarth";
    private float deltaTime;

    public PlanetEarth() {
        setTexture(new Texture(Gdx.files.internal("planet.png")));
        setSize(400, 400);
        setLimitTime(4.0f, true);
        setLimitXY(Gdx.graphics.getWidth() - 200, 50.0f);
        setDuration(10.0f, 15.0f);
        setPosition(getRandomX(), getRandomY());
        addAction(Actions.moveTo(getRandomX(), getRandomY(), getRandomDuration()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        newEnemy(delta);
    }

    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }

    /**
     * Checking create new Enemy object
     * @param delta
     */
    private void newEnemy(float delta) {
        if (isEnemy()) {
            deltaTime += delta;
            if (deltaTime > getLimitTime()) {
                this.getParent().addActor(createEnemy());
                deltaTime = 0;
            }
        }
    }

    /**
     * Create Enemy object
     * @return
     */
    private Enemy createEnemy() {
        Enemy enemy = Enemy.pool.obtain();
        enemy.setActive(true);
        enemy.setStartPosition();
        enemy.setLifeCount(1000);
        return enemy;
    }
}
