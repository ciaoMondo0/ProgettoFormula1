package it.unicam.cs.mp.formula1;


import it.unicam.cs.mp.formula1.controller.SetupGame;
import it.unicam.cs.mp.formula1.interfaces.ISetupGame;
import it.unicam.cs.mp.formula1.view.ConsoleView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String imagePath;
        String botsFilePath;

        if (args.length >= 2) {
            imagePath = args[0];
            botsFilePath = args[1];
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Inserisci il percorso dell'immagine del tracciato: ");
            imagePath = scanner.nextLine();
            System.out.print("Inserisci il percorso del file dei bot: ");
            botsFilePath = scanner.nextLine();
        }

        ISetupGame setupGame = new SetupGame();
        setupGame.setupRace(imagePath, botsFilePath);

        ConsoleView consoleView = new ConsoleView(setupGame.getRaceManager());
        consoleView.startRace();
    }
}
