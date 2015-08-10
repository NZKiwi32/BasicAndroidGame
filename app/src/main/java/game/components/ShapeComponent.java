package game.components;

import com.badlogic.ashley.core.Component;

import game.geom.Shape;

/** Shape Component
 * Holds a singular shape object.
 * Created by Steven on 8/4/2015.
 */
public class ShapeComponent extends Component {
    public Shape shape;

    public ShapeComponent(Shape shape) {
        this.shape = shape;
    }
}
