package main;

import java.util.ArrayList;
import java.util.List;

import angels.Angel;
import common.AngelFactory;
import fileio.FileSystem;

public final class GameInputLoader {
    private final String mInputPath;
    private final String mOutputPath;

    public GameInputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    public GameInput load() {
        int n = 0;
        int m = 0;
        int p = 0;
        Character[][] map = new Character[1][1];
        List<Character> heroTypes = new ArrayList<>();
        List<Integer> heroRows = new ArrayList<>();
        List<Integer> heroCols = new ArrayList<>();
        List<String> moves = new ArrayList<>();
        String row;
        List<List<Angel>> angels = new ArrayList<>();

        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);

            n = fs.nextInt();
            m = fs.nextInt();

            map = new Character[n][m];

            for (int i = 0; i < n; ++i) {
                row = fs.nextWord();
                for (int j = 0; j < m; j++) {
                    map[i][j] = row.charAt(j);
                }
            }

            p = fs.nextInt();

            for (int i = 0; i < p; ++i) {
                heroTypes.add(fs.nextWord().charAt(0));
                heroRows.add(fs.nextInt());
                heroCols.add(fs.nextInt());
            }

            p = fs.nextInt();

            for (int i = 0; i < p; ++i) {
                moves.add(fs.nextWord());
            }

            for (int i = 0; i < p; ++i) {
                List<Angel> roundAngels = new ArrayList<>();
                int angelsThisRound = fs.nextInt();
                for (int j = 0; j < angelsThisRound; j++) {
                    String[] tokens = fs.nextWord().split(",");
                    roundAngels.add(AngelFactory.getInstance().spawnAngel(tokens[0],
                            Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                }
                angels.add(roundAngels);
            }

            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(n, m, map, heroTypes, heroRows, heroCols, moves, angels);
    }
}
