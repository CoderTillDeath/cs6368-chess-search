package Chess2;

import java.util.ArrayList;

/**
 * Created by bchu on 10/1/16.
 */
public abstract class Piece
{
    Point position;

    public Piece(Point p) {
        this.position = p;
    }

    public Piece(int x, int y)
    {
        this.position = new Point(x,y);
    }

    public boolean equals(Object o)
    {
        Piece p = (Piece) o;

        return this.getClass().getName().equals(p.getClass().getName()) && p.position.x == this.position.x && p.position.y == this.position.y;
    }

    public String toString()
    {
        return this.getClass().getName() + ": " + position.toString();
    }
}

class King extends Piece
{
    public King(Point p)
    {
        super(p);
    }

    public King(int x, int y)
    {
        super(x,y);
    }
}

class Rook extends Piece
{
    public Rook(Point p)
    {
        super(p);
    }

    public Rook(int x, int y)
    {
        super(x,y);
    }
}
