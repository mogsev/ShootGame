package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.mogsev.game.util.LoadTexture;


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

    public Bullet() {
        bulletCount++;
        setSize(WIDTH, HEIGHT);
        Gdx.app.log(TAG, "new BulLet " + bulletCount);
    }

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

    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!(getY() < Gdx.graphics.getHeight())) {
            this.remove();
            this.clearActions();
            pool.free(this);
        }
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), WIDTH, HEIGHT);
    }

    public int shot() {
        return 100;
    }


}