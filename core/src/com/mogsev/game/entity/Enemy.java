package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;
import com.mogsev.game.util.LoadTexture;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by zhenya on 26.08.2015.
 */
public class Enemy extends LifeActor {
    private static final String TAG = "Enemy";
    private final Texture texture = new Texture(Gdx.files.internal("enemy.png"));
    private static int enemyCount;
    public static Pool<Enemy> pool = new ReflectionPool<Enemy>(Enemy.class);
    private float deltaTime;
    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    public Enemy() {
        enemyCount++;
        Gdx.app.log(TAG, "new Enemy " + enemyCount);
        setSize(WIDTH, HEIGHT);
        setPosition(getRandomWidth(), Gdx.graphics.getHeight());
        addAction(Actions.moveTo(getRandomWidth(), getRandomHeight(), 15));
        setLifeCount(1000);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!hasActions()) {
            //int x = MathUtils.random(100, Gdx.graphics.getWidth() - WIDTH);
            //int y = MathUtils.random(100, Gdx.graphics.getHeight() - HEIGHT);
            addAction(Actions.moveTo(getRandomWidth(), getRandomHeight(), 15));
        }
        deltaTime += delta;
        if (deltaTime > 1.5f) {
            this.getParent().addActor(fire(getX() + 32, getY() + 32));
            Gdx.app.log(TAG, " " + deltaTime);
            deltaTime = 0;
        }
    }

    /**
     * Return bullet
     *
     * @param posX
     * @param posY
     * @return
     */
    private BulletEnemy fire(float posX, float posY) {
        BulletEnemy bullet = BulletEnemy.pool.obtain();
        bullet.setPosition(posX, posY);
        bullet.addAction(moveTo(MathUtils.random(Gdx.graphics.getWidth()), -10, 3));
        //removeActor(bullet);
        return bullet;
    }

    /**
     * Return random value height
     *
     * @return
     */
    private int getRandomWidth() {
        return MathUtils.random(100, Gdx.graphics.getWidth() - WIDTH);
    }

    /**
     * Return random value height
     *
     * @return
     */
    private int getRandomHeight() {
        return MathUtils.random(100, Gdx.graphics.getHeight() - HEIGHT);
    }
}