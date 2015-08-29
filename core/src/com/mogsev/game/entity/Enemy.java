package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by zhenya on 26.08.2015.
 */
public class Enemy extends LifeActor {
    private static final String TAG = "Enemy";
    private Texture texture = new Texture(Gdx.files.internal("enemy.png"));
    private static int enemyCount;
    public static Pool<Enemy> pool = new ReflectionPool<Enemy>(Enemy.class);
    private float deltaTime;
    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;
    private boolean active;
    private Rectangle rectangle;

    public Enemy() {
        enemyCount++;
        Gdx.app.log(TAG, "new Enemy " + enemyCount);
        setSize(WIDTH, HEIGHT);
        setStartPosition();
        addAction(Actions.moveTo(getRandomWidth(), getRandomHeight(), 15));
        setLifeCount(1000);
        rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        setActive(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Return bounds of the object
     * @return
     */
    public Rectangle getBound() {
        rectangle.set(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (active) {
            if (!hasActions()) {
                addAction(Actions.moveTo(getRandomWidth(), getRandomHeight(), 15));
            }
            deltaTime += delta;
            if (deltaTime > 1.5f) {
                this.getParent().addActor(fire(getX() + 32, getY() + 32));
                //Gdx.app.log(TAG, " " + deltaTime);
                deltaTime = 0;
            }
        } else {
            this.remove();
            this.clearActions();
            Enemy.pool.free(this);
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
        return MathUtils.random(0, Gdx.graphics.getWidth() - WIDTH);
    }

    /**
     * Return random value height
     *
     * @return
     */
    private int getRandomHeight() {
        return MathUtils.random(Gdx.graphics.getHeight() / 4, Gdx.graphics.getHeight() - HEIGHT);
    }

    /**
     * Set active
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Set start position
     */
    public void setStartPosition() {
        setPosition(getRandomWidth(), Gdx.graphics.getHeight());
    }
}