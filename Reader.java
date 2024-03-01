package peggame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public static PegGame readGameFromFile(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        int size = Integer.parseInt(br.readLine());
        SquareBoard game = new SquareBoard(size);
        for (int i = 0; i < size; i++) {
            String line = br.readLine();
            for (int j = 0; j < size; j++) {
                game.getBoard()[i][j] = line.charAt(j);
            }
        }
        br.close();
        return game;
    }
}