package com.mogsev.game.planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mogsev.game.entity.Enemy;
import com.mogsev.game.util.LoadTexture;

/**
 * Created by zhenya on 28.08.2015.
 */
public class Planet extends Actor {
    private static final String TAG = "Planet";
    private final Texture texture = new Texture(Gdx.files.internal("planet.png"));
    //private final TextureRegion region = new TextureRegion(texture, 500, 500);
    private float deltaTime;

    public Planet() {
        setSize(400, 400);
        setPosition(MathUtils.random(50), MathUtils.random(50));
        addAction(Actions.moveTo(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100, 200));
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
        deltaTime += delta;
        if (!hasActions()) {
            int x = MathUtils.random(Gdx.graphics.getWidth());
            int y = MathUtils.random(Gdx.graphics.getHeight());
            if (x < 100) {
                x -= 400;
            }
            if (y < 100) {
                y -= 400;
            }
            addAction(Actions.moveTo(x, y, MathUtils.random(100, 200)));
        }
        if (deltaTime > 4.0f) {
            this.getParent().addActor(new Enemy());
            deltaTime = 0;
        }

    }
}
