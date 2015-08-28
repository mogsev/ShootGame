package com.mogsev.game.util;

/**
 * Created by zhenya on 28.08.2015.
 */
public interface GameStatus {
    public enum Status {
        GAME_RUNNING,
        GAME_RESUME,
        GAME_PAUSED,
        GAME_OVER
    }
}
