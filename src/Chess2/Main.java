package Chess2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by bchu on 10/2/16.
 */
public class Main {
    public static void main(String args[]) throws IOException
    {
        Scanner scan = new Scanner(new File("input"));

        int times = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < times; i++)
        {
            String trialNumber = scan.nextLine();

            Piece pieces[] = new Piece[3];

            for (int j = 0; j < 8; j++)
            {
                String line = scan.nextLine();

                String split[] = line.split(" ");

                for (int k = 0; k < split.length; k++)
                {
                    if(split[k].equals("WK"))
                    {
                        pieces[0] =new King(k,7-j);
                    }
                    else if (split[k].equals("BK"))
                    {
                        pieces[1] = new King(k,7-j);
                    }
                    else if (split[k].equals("WR"))
                    {
                        pieces[2] = new Rook(k,7-j);
                    }
                }
            }

            if(scan.hasNext())
            {
                scan.nextLine();
            }

            State s = new State((King)pieces[0], (King)pieces[1], (Rook)pieces[2]);

            System.out.println(trialNumber);
            new StateSearch().searchAndOutput(s);
        }
    }
}

// Test comment
