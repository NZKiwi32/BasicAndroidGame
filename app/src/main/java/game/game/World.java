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

        int[] xPoints = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[] yPoints = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        try {
            player.add(new ShapeComponent(new PolygonPath(xPoints, yPoints)))
                    .add(new DrawableComponent())
                    .add(new PositionComponent(40, 30))
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }

    /**
     * Create the base entities in the world.
     */
    private void createEntities() {
        generatePlayerShape();

    }
}
