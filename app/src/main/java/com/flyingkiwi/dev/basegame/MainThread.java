package com.flyingkiwi.dev.basegame;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

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


        Display display = ((WindowManager) gamePanel.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);

        this.game = new GameMain(this.surfaceHolder, this.gamePanel, screenSize.x, screenSize.y);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            game.update(1f);
        }
    }
}
