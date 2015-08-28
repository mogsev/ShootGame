package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.mogsev.game.util.LoadTexture;

/**
 * Created by zhenya on 28.08.2015.
 */
public class BulletEnemy extends Actor {
    private static final String TAG = "BulletEnemy";
    private static final float WIDTH = 3;
    private static final float HEIGHT = 9;
    private Texture texture = new Texture(Gdx.files.internal("bullet.png"));
    private TextureRegion region = new TextureRegion(texture, 0, 0, 2, 6);
    public static Pool<BulletEnemy> pool = Pools.get(BulletEnemy.class);
    private static int bulletCount;

    public BulletEnemy() {
        bulletCount++;
        setSize(WIDTH, HEIGHT);
        Gdx.app.log(TAG, "new BulLetEnemy " + bulletCount);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), WIDTH, HEIGHT);
    }

    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (this.getY() < 0) {
            this.remove();
            //this.getParent().removeActor(this);
            this.clearActions();
            pool.free(this);
            //Gdx.app.log(TAG, "group count " + this.getParent().getChildren().size);
        }
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), WIDTH, HEIGHT);
    }

    public int shot() {
        return 100;
    }

}
