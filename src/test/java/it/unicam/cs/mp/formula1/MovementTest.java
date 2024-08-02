package it.unicam.cs.mp.formula1;

import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import it.unicam.cs.mp.formula1.modelli.Car;
import it.unicam.cs.mp.formula1.modelli.Coordinate;
import it.unicam.cs.mp.formula1.modelli.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovementTest {

    private ITrack track;
    private ICar car;


    @BeforeEach
    public void setUp() {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(10, 10);
        track = new Track(start, end);
        car = new Car(start, "Bot1");
    }

    @Test
    public void testPrincipalPointCalculation() {
        // Simula una mossa
        Coordinate initialPosition = new Coordinate(1, 1);
        car.setCurrentPosition(initialPosition);
        car.setLastMove(new Coordinate(1, 1));

        // Calcola il punto principale
        Coordinate principalPoint = car.getPrincipalPoint();
        Coordinate expectedPrincipalPoint = new Coordinate(2, 2);

        assertEquals(expectedPrincipalPoint, principalPoint, "Il punto principale deve essere calcolato correttamente");
    }

    @Test
    public void testPossibleMovesFromPrincipalPoint() {
        // Simula una mossa
        Coordinate initialPosition = new Coordinate(1, 1);
        car.setCurrentPosition(initialPosition);
        car.setLastMove(new Coordinate(1, 1));

        // Calcola il punto principale
        Coordinate principalPoint = car.getPrincipalPoint();
        Coordinate expectedPrincipalPoint = new Coordinate(2, 2);
        assertEquals(expectedPrincipalPoint, principalPoint, "Il punto principale deve essere calcolato correttamente");

        // Verifica che le mosse successive rispettino la regola degli otto vicini
        List<Coordinate> possibleMoves = track.getPossibleMoves(car);
        List<Coordinate> expectedMoves = Arrays.asList(
                new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(1, 3),
                new Coordinate(2, 1), new Coordinate(2, 2), new Coordinate(2, 3),
                new Coordinate(3, 1), new Coordinate(3, 2), new Coordinate(3, 3)
        );

        assertTrue(possibleMoves.containsAll(expectedMoves) && expectedMoves.containsAll(possibleMoves),
                "Le mosse possibili dovrebbero corrispondere a quelle previste");
    }

    @Test
    public void testSubsequentMoves() {
        // Simula due mosse
        Coordinate firstMove = new Coordinate(2, 1);
        car.setCurrentPosition(firstMove);
        car.setLastMove(new Coordinate(1, 0));

        Coordinate secondMove = new Coordinate(4, 2);
        car.setCurrentPosition(secondMove);
        car.setLastMove(new Coordinate(2, 1));

        // Calcola il punto principale
        Coordinate principalPoint = car.getPrincipalPoint();
        Coordinate expectedPrincipalPoint = new Coordinate(6, 3);
        assertEquals(expectedPrincipalPoint, principalPoint, "Il punto principale deve essere calcolato correttamente");

        // Verifica che le mosse successive rispettino la regola degli otto vicini
        List<Coordinate> possibleMoves = track.getPossibleMoves(car);
        List<Coordinate> expectedMoves = Arrays.asList(
                new Coordinate(5, 2), new Coordinate(5, 3), new Coordinate(5, 4),
                new Coordinate(6, 2), new Coordinate(6, 3), new Coordinate(6, 4),
                new Coordinate(7, 2), new Coordinate(7, 3), new Coordinate(7, 4)
        );

        assertTrue(possibleMoves.containsAll(expectedMoves) && expectedMoves.containsAll(possibleMoves),
                "Le mosse possibili dovrebbero corrispondere a quelle previste");
    }





}

