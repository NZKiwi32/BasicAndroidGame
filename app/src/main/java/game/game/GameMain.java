package game.game;

import android.util.Log;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import game.components.PositionComponent;
import game.components.VelocityComponent;
import game.systems.MovementSystem;

/**
 * Main Game
 * The entry point for the game once the app has launched.
 * Created by Steven on 7/29/2015.
 */
public class GameMain implements GameMainInterface {
    public static final String TAG = GameMain.class.getSimpleName();
    private PooledEngine engine;
    private Entity entity;

    public GameMain() {
        this.engine = new PooledEngine();

        // Add all systems to the engine
        engine.addSystem(new MovementSystem());

        // Add all entities to the engine
        this.entity = engine.createEntity()
                .add(new PositionComponent(19, 10))
                .add(new VelocityComponent(2,2));
        engine.addEntity(entity);

        Log.d(TAG, "Entity ID: " + Long.toString(entity.getId()));
    }

    @Override
    public void update(float time) {
        engine.update(time);
        Log.d(TAG, entity.getComponent(PositionComponent.class).toString());
    }
}
