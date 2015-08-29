package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


/**
 * Created by zhenya on 26.08.2015.
 */
public class Bullet extends Actor {
    private static final String TAG = "Bullet";
    private static final float WIDTH = 3;
    private static final float HEIGHT = 9;
    private Texture texture = new Texture(Gdx.files.internal("bullet.png"));
    //private TextureRegion region = new TextureRegion(texture, 0, 0, 2, 6);
    public static Pool<Bullet> pool = Pools.get(Bullet.class);
    private static int bulletCount;
    private Rectangle rectangle;

    public Bullet() {
        bulletCount++;
        setSize(WIDTH, HEIGHT);
        rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Gdx.app.log(TAG, "new BulLet " + bulletCount);
    }

    @Deprecated
    public Bullet(float x, float y, float posX, float posY) {
        this();
        Gdx.app.log(TAG, "new BulLet" + x + " " + y + " " + posX + " " + posY);
        setPosition(posX, posY);
        addAction(Actions.moveTo(x, Gdx.graphics.getHeight(), 3));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), WIDTH, HEIGHT);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!(getY() < Gdx.graphics.getHeight())) {
            this.remove();
            this.clearActions();
            pool.free(this);
            Gdx.app.log(TAG, "pool free");
        }
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

    public int shot() {
        return 100;
    }



}