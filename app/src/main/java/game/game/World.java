package game.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Array;
import com.flyingkiwi.dev.basegame.R;

import game.components.DrawableComponent;
import game.components.PositionComponent;
import game.components.VelocityComponent;

/**
 * World
 * This class which constructs entities in the world.
 * Created by Steven on 8/2/2015.
 */
public class World {
    private PooledEngine engine;
    private int screenWidth;
    private int screenHeight;

    public World(PooledEngine engine, int screenWidth, int screenHeight) {
        this.engine = engine;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Create all the objects in the world.
     */
    public void create() {
        createEntities();

    }

    private Array<Entity> generateBalls(int count) {
        Array<Entity> balls = new Array<>();

        for (int i = 0; i < count; i++) {
            Entity ball = this.engine.createEntity();
            ball.add(new PositionComponent((int) Math.floor(Math.random() * this.screenWidth), (int) Math.floor(Math.random() * this.screenHeight)))
                    .add(new DrawableComponent(R.drawable.droid_1))
                    .add(new VelocityComponent((float) Math.random() * 4 - 2, (float) Math.random() * 4 - 2))
            ;
            balls.add(ball);
            engine.addEntity(ball);
        }

        return balls;
    }

    /**
     * Create the base entities in the world.
     */
    private void createEntities() {
        generateBalls(10);
    }
}
