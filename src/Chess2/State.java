package Chess2;

/**
 * Created by bchu on 10/1/16.
 */
public class State implements Comparable
{
    public King whiteKing;
    public King blackKing;
    public Rook whiteRook;

    public State(King whiteKing, King blackKing, Rook whiteRook) {
        this.whiteKing = whiteKing;
        this.blackKing = blackKing;
        this.whiteRook = whiteRook;
    }

    public int compareTo(Object o)
    {
        return StateFactory.Heuristic(this) - StateFactory.Heuristic((State) o);
    }

    public String toString()
    {
        String all = "";

        all += "White " + whiteKing.toString() + "\n";
        all += "Black " + blackKing.toString() + "\n";
        all += "White " + whiteRook.toString() + "\n";

        return all;
    }

    public String outputChessBoard()
    {
        String arr[][] = new String[8][8];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = "--";
            }
        }

        drawPiece(arr,whiteKing,"WK");
        drawPiece(arr,blackKing,"BK");
        drawPiece(arr,whiteRook,"WR");

        String all = "";

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                all += arr[i][j] + " ";
            }
            all += "\n";
        }

        return all;
    }

    public void drawPiece(String[][] arr, Piece p, String s)
    {
        arr[7 - p.position.y][p.position.x] = s;
    }

    public String compressedOutput()
    {
        return "WK:" + whiteKing.position.toString() + ",BK:" + blackKing.position.toString() + ",WR:" + whiteRook.position.toString();
    }
}
