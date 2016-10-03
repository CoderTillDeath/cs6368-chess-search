package Chess2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by bchu on 10/1/16.
 */
public class ChessRulesTest
{

    private King k1;
    private King k2;
    private King k3;
    private Rook r1;
    private Rook r2;
    private Rook r3;
    private King k4;

    @Before
    public void setUp() {
        k1 = makeKing(0, 0);
        k2 = makeKing(0, 0);
        k3 = makeKing(1,1);
        k4 = makeKing(2,2);
        r1 = makeRook(0, 0);
        r2 = makeRook(0,0);
        r3 = makeRook(1,1);
    }

    private Rook makeRook(int x, int y) {
        return new Rook(x, y);
    }

    private King makeKing(int x, int y) {
        return new King(x, y);
    }

    @Test
    public void KingEqualsKing() throws Exception {

        assertEquals(k1, k2);
    }

    @Test
    public void KingDoesNotEqualRook() throws Exception {

        assertNotEquals(k1,r1);
    }

    @Test
    public void RookEqualsRook() throws Exception {

        assertEquals(r1,r2);
    }

    @Test
    public void PiecesDoNotEqualPoints() throws Exception {

        assertNotEquals(r1,r3);
        assertNotEquals(k1,k3);
    }

    @Test
    public void GetKingMovesOnSideIsCorrect() throws Exception {

        ArrayList<Piece> p = ChessRules.getMoves(k1);
        ArrayList<Piece> moves = new ArrayList<Piece>(Arrays.asList(
                new King(0,1),
                new King(1,0),
                new King(1,1)
        ));

        assertEquals(p,moves);
    }

    @Test
    public void GetKingMovesInMiddleIsCorrect() throws Exception {

        ArrayList<Piece> p = ChessRules.getMoves(k3);
        ArrayList<Piece> moves = new ArrayList<Piece>(Arrays.asList(
                new King(0,0),
                new King(0,1),
                new King(0,2),
                new King(1,0),
                new King(1,2),
                new King(2,0),
                new King(2,1),
                new King(2,2)
        ));

        assertEquals(p,moves);
    }

    @Test
    public void GetRookMoves() throws Exception {

        ArrayList<Piece> p = ChessRules.getMoves(r3);
        ArrayList<Piece> moves = new ArrayList<Piece>(Arrays.asList(
                new Rook(0,1),
                new Rook(1,0),
                new Rook(2,1),
                new Rook(1,2),
                new Rook(3,1),
                new Rook(1,3),
                new Rook(4,1),
                new Rook(1,4),
                new Rook(5,1),
                new Rook(1,5),
                new Rook(6,1),
                new Rook(1,6),
                new Rook(7,1),
                new Rook(1,7)
        ));

        assertEquals(p,moves);
    }

    @Test
    public void RemoveBadKingMoves() throws Exception {

        ArrayList<Piece> p = ChessRules.getMoves(k1);
        ArrayList<Piece> moves = new ArrayList<Piece>(Arrays.asList(
                new King(0,1),
                new King(1,0)
        ));

        ChessRules.removeBadKingMoves(p,k4);

        assertEquals(p,moves);
    }

    @Test
    public void KingUnderAttack() throws Exception {

        assertTrue (ChessRules.BlackKingIsUnderAttack(new State(
                new King(7,7),
                new King(2,2),
                new Rook(2,7)
        )));

    }

    @Test
    public void StalemateDetection() throws Exception {

        State s = new State(
                makeKing(5,5),
                makeKing(7,7),
                makeRook(6,6)
        );

        assertTrue(ChessRules.staleMate(s));

    }

    @Test
    public void StaleMateWeird() throws Exception {
        State s = new State(
                new King(2,2),
                new King(5,2),
                new Rook(7,3)
        );

        assertTrue(!ChessRules.staleMate(s));
    }

    @Test
    public void CheckCheckmate() throws Exception {

        State s = new State(
                new King(6,5),
                new King(6,7),
                new Rook(1,7)
        );

        assertTrue(ChessRules.checkMate(s));
    }
}