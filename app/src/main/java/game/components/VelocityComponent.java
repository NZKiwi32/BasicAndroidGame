package game.components;

import com.badlogic.ashley.core.Component;

/**
 * Velocity Component
 * Controls a entities direction of movement.
 * Created by Steven on 7/29/2015.
 */
public class VelocityComponent extends Component {
    public float x;
    public float y;

    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
