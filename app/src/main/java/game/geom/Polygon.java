package game.geom;

/**
 * Minimum Polygon class for Android.
 */
public class Polygon implements Shape
{
    // Polygon coordinates.
    private int[] polyY, polyX;

    // Number of sides in the polygon.
    private int polySides;

    /**
     * Default constructor.
     * @param px Polygon y coods.
     * @param py Polygon x coods.
     * @param ps Polygon sides count.
     */
    public Polygon( int[] px, int[] py, int ps )
    {
        polyX = px;
        polyY = py;
        polySides = ps;
    }

    /**
     * Checks if the Polygon contains a point.
     * @see "http://alienryderflex.com/polygon/"
     * @param x Point horizontal pos.
     * @param y Point vertical pos.
     * @return Point is in Poly flag.
     */
    @Override
    public boolean contains(int x, int y) {
        boolean c = false;
        int i, j = 0;
        for (i = 0, j = polySides - 1; i < polySides; j = i++) {
            if (((polyY[i] > y) != (polyY[j] > y))
                    && (x < (polyX[j] - polyX[i]) * (y - polyY[i]) / (polyY[j] - polyY[i]) + polyX[i]))
                c = !c;
        }
        return c;
    }
}