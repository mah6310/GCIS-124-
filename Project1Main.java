package peggame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Project1Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter filename (MUST BE .txt FILE): ");
        String txtfileN = reader.readLine();
        PegGame game = Reader.readGameFromFile(txtfileN);
        ControllerBoard.playGame(game);
    }
}
