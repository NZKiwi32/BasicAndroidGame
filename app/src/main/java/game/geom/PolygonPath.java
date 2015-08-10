package game.geom;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * Path of a polygon
 * Can get the Path to draw with android
 * Can also obtain if a point is within the polygon.
 * <p/>
 * Created by Steven on 8/4/2015.
 */
public class PolygonPath implements Shape {
    // Path of the Polygon.
    protected Path p;

    // Polygon coordinates.
    protected float[] polyY, polyX;

    // Number of sides in the polygon.
    protected int polySides;

    public PolygonPath(float[] xPoints, float[] yPoints) {
        this.polyX = xPoints;
        this.polyY = yPoints;
        this.polySides = xPoints.length;

        p = this.formPath();
    }

    protected Path formPath() {
        p = new Path();

        // First draw point is the initial polygon point.
        p.moveTo(this.polyX[0], this.polyY[0]);

        // Protect from bad polygon position counts.
//        if (this.polyX.length != this.polyY.length) {
//            throw new Exception("Created " + PolygonPath.class.getSimpleName() + " with unequal number of x and y coordinates.");
//        }

        // Loop the polygon points adding lines between them.
        for (int i = 1; i < this.polyX.length && i < this.polyY.length; i++) {
            p.lineTo(this.polyX[i], this.polyY[i]);
        }

        // Connect it back to the original point.
        p.lineTo(this.polyX[0], this.polyY[0]);

        return p;
    }

    /**
     * Obtain the path
     *
     * @return Path of the polygon
     */
    public Path getPath() {
        return this.p;
    }

    /**
     * Center the shape
     * Displaces the shape by half its width and  height so the middle of the shape is the origin point.
     */
    public Shape centerPath() {
        RectF bounds = new RectF();
        this.p.computeBounds(bounds, false);
        this.p.offset(-1 * bounds.width() / 2, -1 * bounds.height() / 2);
        return this;
    }

    public Shape rotateAroundPoint(float cx, float cy, float angle) {
        float s = (float) Math.sin(Math.toRadians(angle));
        float c = (float) Math.cos(Math.toRadians(angle));

        for (int i = 0; i < this.polyX.length; i++) {
            float px = this.polyX[i];
            float py = this.polyY[i];

            // place point at origin
            //  px -= cx;
            //  py -= cy;

            float xnew = px * c - py * s;
            float ynew = px * s + py * c;

            this.polyX[i] = xnew; //+ cx;
            this.polyY[i] = ynew; //+ cy;
        }
        this.formPath();

        return this;
    }
    /**
     * Checks if the PolygonPath contains a point.
     *
     * @param x Point horizontal pos.
     * @param y Point vertical pos.
     * @return Point is in Poly flag.
     * @see "http://alienryderflex.com/polygon/"
     */
    @Override
    public boolean contains(int x, int y) {
        boolean c = false;
        //noinspection UnusedAssignment
        int i, j = 0;
        for (i = 0, j = polySides - 1; i < polySides; j = i++) {
            if (((polyY[i] > y) != (polyY[j] > y))
                    && (x < (polyX[j] - polyX[i]) * (y - polyY[i]) / (polyY[j] - polyY[i]) + polyX[i]))
                c = !c;
        }
        return c;
    }
}
