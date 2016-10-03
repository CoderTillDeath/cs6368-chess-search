package Chess2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by bchu on 10/1/16.
 */
public class StateSearch {

    private Stack<String> visited = new Stack<String>();
    Scanner pause = new Scanner(System.in);

    public static void searchAndOutput(State s)
    {
        Tuple<Integer, State> t = new StateSearch().search(s);

        if(t.getType2() != null)
        {
            System.out.println(t.getType1() + "\n" + t.getType2().outputChessBoard());
        }
        else
        {
            System.out.println("No Solution...");
        }
    }

    public Tuple<Integer,State> search(State inputState)
    {
        String output = inputState.compressedOutput();

        if(visited.contains(output))
        {
            return new Tuple<Integer,State>(1, null);
        }
        else
        {
            visited.push(output);
        }

        if(ChessRules.checkMate(inputState))
        {
            return new Tuple<Integer,State>(1,inputState);
        }
        else if(ChessRules.staleMate(inputState))
        {
            return new Tuple<Integer,State>(1, null);
        }

        ArrayList<State> neighbors = StateFactory.getNeighbors(inputState);
        sortBasedOnHeuristic(neighbors);
        int searches = 1;

        for(State state : neighbors)
        {
            Tuple<Integer,State> t = search(state);
            searches += t.getType1();

            State returnedState = t.getType2();

            if(returnedState != null)
            {
                if(ChessRules.checkMate(returnedState))
                {
                    return new Tuple<Integer,State>(searches,returnedState);
                }
            }
        }

        return new Tuple<Integer,State>(searches,null);
    }

    public void sortBasedOnHeuristic(ArrayList<State> states)
    {
        Collections.sort(states);
    }
}
