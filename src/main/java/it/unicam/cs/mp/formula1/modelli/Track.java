package it.unicam.cs.mp.formula1.modelli;

import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.ITrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * E’ responsabile di fornire le posizioni disponibili e valide sul tracciato di gara seguendo la
 * regola degli otto vicini
 */

public class Track implements ITrack {



    private int[][] trackGrid;
    private int width;
    private int height;
    private Coordinate startingPosition;
    private Coordinate finishLine;

    public Track(int[][] trackGrid, Coordinate startingPosition, Coordinate finishLine) {
        this.trackGrid = trackGrid;
        this.width = trackGrid.length;
        this.height = trackGrid[0].length;
        this.startingPosition = startingPosition;
        this.finishLine = finishLine;
    }

    public Track(Coordinate startingPosition, Coordinate finishLine) {

        this.startingPosition = startingPosition;
        this.finishLine = finishLine;
    }



    @Override
    public Coordinate getStartingPosition() {
        return startingPosition;
    }

    @Override
    public Coordinate getEndingPosition() {
        return finishLine;
    }



    public List<Coordinate> getOttoVicini(Coordinate coordinate) {
        List<Coordinate> vicini = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();

        vicini.add(new Coordinate(x + 1, y));
        vicini.add(new Coordinate(x - 1, y));
        vicini.add(new Coordinate(x, y + 1));
        vicini.add(new Coordinate(x, y - 1));
        vicini.add(new Coordinate(x + 1, y + 1));
        vicini.add(new Coordinate(x - 1, y - 1));
        vicini.add(new Coordinate(x + 1, y - 1));
        vicini.add(new Coordinate(x - 1, y + 1));

        return vicini;
    }

    public boolean isFuoriPista(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return x < 0 || x >= width || y < 0 || y >= height || trackGrid[x][y] == 0;
    }

    @Override
    public List<Coordinate> getPossibleMoves(ICar car) {
        List<Coordinate> possibleMoves = new ArrayList<>();

        // Calcola il punto principale
        Coordinate puntoPrincipale = car.getPrincipalPoint();

        // Ottieni tutti gli otto vicini del punto principale
        List<Coordinate> vicini = this.getOttoVicini(puntoPrincipale);
        // Aggiunge il punto principale alla lista dei vicini
        vicini.add(puntoPrincipale);


        for (Coordinate vicino : vicini) {

            possibleMoves.add(vicino);
        }

        return possibleMoves;
    }

    /**
     Ottiene i movimenti che non sono fuori pista e non corrispondo alle posizioni precedenti
     */
    @Override
    public List<Coordinate> filterValidMoves(ICar car, List<Coordinate> moves) {
        return moves.stream()
                .filter(move -> !isFuoriPista(move) && !move.equals(car.getPreviousPosition()))
                .collect(Collectors.toList());
    }




}
