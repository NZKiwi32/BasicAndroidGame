package game.game;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.flyingkiwi.dev.basegame.MainGamePanel;
import com.flyingkiwi.dev.basegame.R;

import game.components.DrawableComponent;
import game.components.PositionComponent;
import game.components.VelocityComponent;
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

    public GameMain(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        this.engine = new PooledEngine();

        // Add all systems to the engine
        engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderSystem(surfaceHolder, gamePanel.getResources()));

        World world = new World(engine);
        world.create();
    }

    @Override
    public void update(float time) {
        engine.update(time);
    }
}
