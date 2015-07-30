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

        this.game = new GameMain();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas = null;
        Log.d(TAG, "Begining Game Loop");

        while (running) {
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            game.update(1f);
            // render state to the screen
        }
        Log.d(TAG, "Ended Game Loop");
    }
}
