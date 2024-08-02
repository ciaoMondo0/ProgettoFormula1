package it.unicam.cs.mp.formula1.view;

import it.unicam.cs.mp.formula1.controller.GestoreGara;
import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.IGestoreGara;
import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.util.List;

/**
 * E’ responsabile di mostrare su console la simulazione della gara tra i bot
 */
public class ConsoleView {
    private IGestoreGara gestoreGara;

    public ConsoleView(IGestoreGara gestoreGara) {
        this.gestoreGara = gestoreGara;
    }

    public void startRace() {
        if (gestoreGara.getBots().isEmpty()) {
            System.out.println("Nessun bot caricato");
            return;
        }
        while (!gestoreGara.isGameOver()) {
            printRace();
            gestoreGara.gestisciGara();
        }
        printWinner();
    }

    private void printRace() {
        ICar currentCar = gestoreGara.getCurrentCar();
        List<Coordinate> possibleMoves = gestoreGara.getPossibleMoves();
        printPossibleMoves(possibleMoves);

        if (!possibleMoves.isEmpty()) {
            printMove(currentCar);
        } else {
            System.out.println("Non ci sono mosse disponibili per " + currentCar.getCurrentPosition());
        }
    }

    private void printPossibleMoves(List<Coordinate> possibleMoves) {
        System.out.println("Gli otto vicini:");
        for (Coordinate coordinate : possibleMoves) {
            System.out.println(coordinate);
        }
    }

    private void printMove(ICar currentCar) {
        System.out.println(currentCar.getName() + " a: " + currentCar.getCurrentPosition());
        System.out.println("Nuovo ultimo spostamento: " + currentCar.getLastMove());
        Coordinate principalPoint = currentCar.getPrincipalPoint();
        System.out.println("Punto principale finale: " + principalPoint);
    }

    private void printWinner() {
        if (gestoreGara.getWinner() != null) {
            System.out.println("Abbiamo un vincitore: " + gestoreGara.getWinner().getName() + " " + gestoreGara.getWinner().getCurrentPosition());
        }
    }
}
