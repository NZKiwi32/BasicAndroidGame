package game.geom;

/**
 * This class generates a bunch of polygon shapes for use within games.
 * Created by Steven on 8/10/2015.
 */
public abstract class PolygonShapeGenerator {
    public static PolygonPath generateHexagon(float radius) {
        float apothem = (float) (radius * Math.cos(Math.toRadians(30))); // http://www.mathopenref.com/apothem.html

        float[] xPoints = {radius, (float) 0.5 * radius, (float) -0.5 * radius, (float) -1.0 * radius, (float) -0.5 * radius, (float) 0.5 * radius,};
        float[] yPoints = {0, apothem, apothem, 0, -1 * apothem, -1 * apothem};

        return new PolygonPath(xPoints, yPoints);
    }

    public static PolygonPath generateSquare(float size) {
        float halfsize = size / 2;

        float[] xPoints = {halfsize, halfsize, -1 * halfsize, -1 * halfsize};
        float[] yPoints = {halfsize, -1 * halfsize, -1 * halfsize, halfsize};

        return new PolygonPath(xPoints, yPoints);
    }
}
