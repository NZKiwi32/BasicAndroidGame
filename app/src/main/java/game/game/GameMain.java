package game.game;

import android.view.SurfaceHolder;

import com.badlogic.ashley.core.PooledEngine;
import com.flyingkiwi.dev.basegame.MainGamePanel;

import game.systems.MovementSystem;
import game.systems.RenderSystem;

/**
 * Main Game
 * The entry point for the game once the app has launched.
 * Created by Steven on 7/29/2015.
 */
public class GameMain implements GameMainInterface {
    public static final String TAG = GameMain.class.getSimpleName();
    private PooledEngine engine;
    private World world;

    public GameMain(SurfaceHolder surfaceHolder, MainGamePanel gamePanel, int screenWidth, int screenHeight) {

        this.engine = new PooledEngine();

        // Add all systems to the engine
        engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderSystem(surfaceHolder, gamePanel.getResources()));

        World world = new World(engine, screenWidth, screenHeight);

        world.create();
    }


    @Override
    public void update(float time) {
        engine.update(time);
    }
}
