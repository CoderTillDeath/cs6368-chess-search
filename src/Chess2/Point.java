package Chess2;

/**
 * Created by bchu on 10/1/16.
 */
public class Point
{
    public int x;
    public int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o)
    {
        Point p = (Point)o;

        return x == p.x && y == p.y;
    }

    public boolean adjacent(Point p)
    {
        return Math.abs(p.x - x) <= 1 && Math.abs(p.y - y) <= 1;
    }

    public static double distance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ')';
    }
}
