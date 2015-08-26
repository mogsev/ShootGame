package com.mogsev.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by zhenya on 26.08.2015.
 */
public interface LoadTexture {
    public static final Texture PLAYER = new Texture(Gdx.files.internal("player.png"));
    public static final Texture BULLET = new Texture(Gdx.files.internal("bullet.png"));
}
