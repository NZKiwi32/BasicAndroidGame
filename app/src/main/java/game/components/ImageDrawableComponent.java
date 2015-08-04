package game.components;

import com.badlogic.ashley.core.Component;

/** Image Drawable Component
 * Holds a android resource for a bitmap image to be drawn.
 * Created by Steven on 8/4/2015.
 */
public class ImageDrawableComponent extends Component {
    public int resourceID;

    public ImageDrawableComponent(int resourceID) {
        this.resourceID = resourceID;
    }
    // Draw bitmap like: canvas.drawBitmap(BitmapFactory.decodeResource(this.resources, drawableMapper.get(e).resourceID), positionMapper.get(e).x, positionMapper.get(e).y, null);
}
