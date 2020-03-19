package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import engine.GameManager;

public final class Main {

    private Main() {

    }

    public static void main(final String[] args) {

        File file = new File(args[1]);
        PrintStream stream = null;
        try {
            stream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.setOut(stream);
        
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        GameManager gameManager = new GameManager(gameInput);
        gameManager.startGame();
    }
}
