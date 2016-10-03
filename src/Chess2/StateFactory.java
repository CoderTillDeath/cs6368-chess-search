package Chess2;


import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by bchu on 10/1/16.
 */
public class StateFactory
{
    public static ArrayList<State> getNeighbors(State s)
    {
        ArrayList<State> neighbors = new ArrayList<State>();

        ArrayList<Piece> kingMoves = ChessRules.getMoves(s.whiteKing);
        ArrayList<Piece> rookMoves = ChessRules.getMoves(s.whiteRook);

        ChessRules.removeBadKingMoves(kingMoves,s.blackKing);
        ChessRules.removeBadRookMoves(rookMoves,s);

        for(Piece p : kingMoves)
        {
            neighbors.add(new State((King) p,s.blackKing,s.whiteRook));
        }

        for(Piece p : rookMoves)
        {
            neighbors.add(new State(s.whiteKing,s.blackKing,(Rook)p));
        }

        for(State news : neighbors)
        {
            news.blackKing = (King) ChessRules.BlackKingMove(news);
        }

        return neighbors;
    }

    public static int Heuristic(State s)
    {
        return ChessRules.spacesLeft(s);
    }

    public static void prune()
    {

    }
}
