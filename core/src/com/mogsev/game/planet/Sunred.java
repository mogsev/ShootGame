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
public class Sunred extends Actor {
    private static final String TAG = "Sunred";
    private final Texture texture = new Texture(Gdx.files.internal("sun_red.png"));
    //private final TextureRegion region = new TextureRegion(texture, 800, 800);

    public Sunred() {
        setSize(200, 200);
        setPosition(MathUtils.random(300, 400), MathUtils.random(300, 500));
        addAction(Actions.moveTo(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 200, 250));
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
            int y = 300 + MathUtils.random(200);
            if (x < 100) {
                x -= 200;
            }
            if (y < 100) {
                y -= 200;
            }
            addAction(Actions.moveTo(x, y, MathUtils.random(200, 300)));
        }
    }
}
