package game.geom;

import android.graphics.Path;

/**
 * Interface for a Shape
 * Shapes are made of a series of points defined by android Path, they must know whats inside the shape using Contains.
 * Created by Steven on 8/4/2015.
 */
public interface Shape {
    boolean contains( int x, int y );

    Path getPath();

    Shape centerPath();
}
