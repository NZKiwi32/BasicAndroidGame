package game.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import game.components.DrawableComponent;
import game.components.PositionComponent;
import game.components.ShapeComponent;
import game.geom.PolygonPath;

/**
 * World
 * This class which constructs entities in the world.
 * Created by Steven on 8/2/2015.
 */
public class World {
    public static final String TAG = World.class.getSimpleName();
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

    private Entity generatePlayerShape() {
        Entity player = this.engine.createEntity();

        float xPos = this.screenWidth / 2;
        float yPos = this.screenHeight / 2;
        int[] xPoints = {0, 0, 20, 60, 70};
        int[] yPoints = {0, 20, 40, 40, 30};

        player.add(new ShapeComponent(new PolygonPath(xPoints, yPoints).centerPath()))
                .add(new DrawableComponent())
                .add(new PositionComponent(xPos, yPos))
        ;

        engine.addEntity(player);
        return player;
    }

    /**
     * Create the base entities in the world.
     */
    private void createEntities() {
        generatePlayerShape();

    }
}
