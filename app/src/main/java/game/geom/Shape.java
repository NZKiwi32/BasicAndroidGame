package game.geom;

/**
 * Interface for a Shape
 * Shapes are made of a series of points, they must know whats inside the shape.
 * Created by Steven on 8/4/2015.
 */
public interface Shape {
    boolean contains( int x, int y );
}
