package com.mogsev.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ModelInfluencer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Random;

/**
 * Created by zhenya on 26.08.2015.
 */
public class Enemy extends Actor {
    private static final String TAG = "Enemy";
    private Texture texture = new Texture(Gdx.files.internal("enemy.png"));;
    private TextureRegion region;

    public Enemy() {
        Gdx.app.log(TAG, "new Enemy");
        region = new TextureRegion(texture, 32, 0, 32, 32);
        setSize(64, 64);
        setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
        addAction(Actions.moveTo(-100, 100, 15));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.setColor(getColor());
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }


}
