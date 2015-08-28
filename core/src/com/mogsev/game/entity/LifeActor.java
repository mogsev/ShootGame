package com.mogsev.game.entity;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by zhenya on 28.08.2015.
 */
public class LifeActor extends Actor {
    private static final String TAG = "LifeActor";
    private int count;

    public enum Action {
        RAISE,
        REDUCE
    }

    public void setLifeCount (int count) {
        this.count = count;
    }

    public int getLifeCount() {
        return this.count;
    }

    public void changeLifeCount(int count, Action action) {
        switch (action) {
            case RAISE:
                this.count += count;
                break;
            case REDUCE:
                this.count -= count;
                break;
        }
    }
}
