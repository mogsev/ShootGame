package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Created by zhenya on 29.08.2015.
 */
public class Missile extends Actor {
    private static final String TAG = "Missile";
    private final Texture texture = new Texture(Gdx.files.internal("missile.png"));
    private final TextureRegion region = new TextureRegion(texture, 4, 3, 14, 45);
    public static Pool<Missile> pool = Pools.get(Missile.class);
    private static int count;
    private Rectangle rectangle;

    public Missile() {
        count++;
        setSize(14, 45);
        setPosition(100, 50);
        rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Gdx.app.log(TAG, "new Missile " + count);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
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
