package Chess2;

import java.util.ArrayList;

/**
 * Created by bchu on 10/1/16.
 */
public class ChessRules
{
    public static boolean checkMate(State s)
    {
        return BlackKingIsUnderAttack(s) && BlackKingHasNoMoves(s);
    }

    public static boolean staleMate(State s)
    {
        return !BlackKingIsUnderAttack(s) && BlackKingHasNoMoves(s);
    }

    public static boolean invalid(State s)
    {
        return s.blackKing.position.equals(s.whiteRook.position);
    }

    public static boolean isPieceInChessBoard(Piece p)
    {
        return Utilities.isBetween(p.position.x, 0, 7) && Utilities.isBetween(p.position.y,0,7);
    }

    public static ArrayList<Piece> getMoves(Piece p)
    {
        ArrayList<Piece> moves = new ArrayList<Piece>();

        if(p instanceof King)
        {
            for(int i = -1; i <= 1; i++)
            {
                for(int j = -1; j <= 1; j++)
                {
                    if(!(i == 0 && j == 0))
                    {
                        King king = new King(p.position.x + i, p.position.y + j);

                        if(isPieceInChessBoard(king)) moves.add(king);
                    }
                }
            }
        }
        else
        {
            for(int i = 0; i <= 7; i++)
            {
                if(i != p.position.x)
                {
                    moves.add(new Rook(i,p.position.y));
                }

                if(i != p.position.y)
                {
                    moves.add(new Rook(p.position.x,i));
                }
            }
        }

        return moves;
    }

    public static boolean protectedByKing(Piece r, King k)
    {
        return r.position.adjacent(k.position);
    }

    public static boolean underAttackByBlackKing(Piece r, King k)
    {
        return k.position.adjacent(r.position);
    }

    public static void removeBadRookMoves(ArrayList<Piece> p, State s)
    {
        int i = 0;

        while(i < p.size())
        {
            Piece rook = p.get(i);

            if(underAttackByBlackKing(rook,s.blackKing) && !protectedByKing(rook,s.whiteKing))
            {
                p.remove(i);
            }
            else
            {
                i++;
            }
        }
    }

    public static void removeBadKingMoves(ArrayList<Piece> p, King blackKing)
    {
        int i = 0;

        while(i < p.size())
        {
            Piece king = p.get(i);

            if(king.position.adjacent(blackKing.position))
            {
                p.remove(i);
            }
            else
            {
                i++;
            }
        }
    }

    public static void removeBadBlackKingMoves(ArrayList<Piece> moves, State s)
    {
        int i = 0;

        while(i < moves.size())
        {
            Piece king = moves.get(i);

            if(BlackKingIsUnderAttack(new State(s.whiteKing, (King) king, s.whiteRook)) || king.position.adjacent(s.whiteKing.position))
            {
                moves.remove(i);
            }
            else
            {
                i++;
            }
        }
    }

    public static boolean BlackKingIsUnderAttack(State s)
    {
        King bking = s.blackKing;
        Rook wrook = s.whiteRook;

        return bking.position.x == wrook.position.x || bking.position.y == wrook.position.y;
    }

    private static boolean BlackKingHasNoMoves(State s)
    {
        King bking = s.blackKing;
        Rook wrook = s.whiteRook;

        ArrayList<Piece> moves = getMoves(bking);

        for(Piece p : moves)
        {
            State state = new State(s.whiteKing,(King)p,wrook);

            if(!BlackKingIsUnderAttack(state) && !state.blackKing.position.adjacent(s.whiteKing.position))
            {
                return false;
            }
        }

        return true;
    }

    public static double kingScore(Piece k)
    {
        return Math.abs(k.position.x - 5) * 5 +
                Math.abs(k.position.y - 5) * 3 +
                (k.position.y + k.position.x) * .1;
    }

    public static Piece BlackKingMove(State s)
    {
        ArrayList<Piece> moves = ChessRules.getMoves(s.blackKing);
        ChessRules.removeBadBlackKingMoves(moves, s);

        if(moves.size() > 0)
        {
            ArrayList<Piece> validMoves = new ArrayList<Piece>();

            double lowest = Integer.MAX_VALUE;
            int index = 0;

            for(int i = 0; i < moves.size(); i++)
            {
                Piece k = moves.get(i);
                validMoves.add(k);

                double kingScore = kingScore(k);

                if(kingScore < lowest)
                {
                    lowest = kingScore;
                    index = i;
                }
            }

            return validMoves.get(index);
        }
        else
        {
            return s.blackKing;
        }
    }

    public static int spacesLeft(State s)
    {
        if(!ChessRules.BlackKingIsUnderAttack(s))
        {
            int x = s.whiteRook.position.x;
            int y = s.whiteRook.position.y;

            if(s.blackKing.position.x > s.whiteRook.position.x)
            {
                x = 7 - x;
            }

            if(s.blackKing.position.y > s.whiteRook.position.y)
            {
                y = 7 - y;
            }

            return x*y + (int)Point.distance(s.blackKing.position,s.whiteKing.position);
        }
        else
        {
            ArrayList<Piece> moves = getMoves(s.blackKing);
            removeBadBlackKingMoves(moves,s);

            return moves.size();
        }
    }
}
