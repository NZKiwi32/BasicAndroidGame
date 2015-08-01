package game.components;

import android.graphics.Bitmap;

import com.badlogic.ashley.core.Component;

/** Drawable Component
 * Defines if a component can be draw to the screen, and provides the Bitmap to do so
 * Created by Steven on 7/30/2015.
 */
public class DrawableComponent extends Component {
    public int resourceID;

    public DrawableComponent (int resourceID) {
        this.resourceID = resourceID;
    }
}
