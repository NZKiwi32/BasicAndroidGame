package com.flyingkiwi.dev.basegame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import game.game.GameMain;

/**
 * Main Application Thread
 * Created by Steven on 7/27/2015.
 */
public class MainThread extends Thread {
    public static final String TAG = MainThread.class.getSimpleName();
    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;
    private GameMain game;

    // flag to hold game state
    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

        this.game = new GameMain(surfaceHolder, gamePanel);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        game.update(1f);
    }
}
