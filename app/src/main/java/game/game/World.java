package game.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
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

    public World(PooledEngine engine) {
        this.engine = engine;
    }

    /**
     * Create all the objects in the world.
     */
    public void create() {
        createEntities();

    }

    /**
     * Create the base entities in the world.
     */
    private void createEntities () {
        // Add all entities to the engine
        Entity entity = this.engine.createEntity()
                .add(new PositionComponent(29, 100))
                .add(new VelocityComponent(2,2))
                .add(new DrawableComponent(R.drawable.droid_1))
                ;
        this.engine.addEntity(entity);

        Entity entity2 = this.engine.createEntity()
                .add(new PositionComponent(79, 220))
                .add(new VelocityComponent(3,2))
                .add(new DrawableComponent(R.drawable.droid_1))
                ;

        engine.addEntity(entity2);
    }
}
