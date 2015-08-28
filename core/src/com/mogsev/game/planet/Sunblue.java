package com.mogsev.game.planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mogsev.game.util.LoadTexture;

/**
 * Created by zhenya on 28.08.2015.
 */
public class Sunblue extends Actor {
    private static final String TAG = "Sunblue";
    private final Texture texture = new Texture(Gdx.files.internal("sun_blue.png"));
    //private final TextureRegion region = new TextureRegion(texture, 800, 800);

    public Sunblue() {
        setSize(300, 300);
        setPosition(MathUtils.random(50, 150), MathUtils.random(600, 800));
        addAction(Actions.moveTo(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 200, 300));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Actor hit(float x, float y, boolean touchable) {
        return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this : null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!hasActions()) {
            int x = MathUtils.random(Gdx.graphics.getWidth());
            int y = MathUtils.random(Gdx.graphics.getHeight());
            if (x < 100) {
                x -= 300;
            }
            if (y < 100) {
                y -= 300;
            }
            addAction(Actions.moveTo(x, y, MathUtils.random(200, 300)));
        }
    }
}
