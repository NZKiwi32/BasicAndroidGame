package game.components;

import com.badlogic.ashley.core.Component;

/**
 * A component for 2D PositionComponent
 * Created by Steven on 7/29/2015.
 */
public class PositionComponent extends Component {
    public int x;
    public int y;

    public PositionComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return PositionComponent.class.getSimpleName() + "=(" + this.x + "," + this.y + ")";
    }
}
