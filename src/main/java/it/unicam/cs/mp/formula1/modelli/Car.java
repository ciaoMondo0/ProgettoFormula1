package it.unicam.cs.mp.formula1.modelli;

import it.unicam.cs.mp.formula1.interfaces.ICar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * E’ responsabile di fornire informazioni sui movimenti precedenti e correnti dell’auto
 * durante la gara
 */
public class Car implements ICar {
    private Coordinate currentPosition;

    private Coordinate previousPosition;


    private List<Coordinate> positionHistory;

    private boolean finished;

    private String name;

    private Coordinate lastMove;
    public Car(Coordinate currentPosition, String name){
        this.currentPosition = currentPosition;
        this.previousPosition = currentPosition;
        this.lastMove = new Coordinate(0, 0);
        this.positionHistory = new ArrayList<>();
        this.name = name;
    }


    @Override
    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public Coordinate getLastMove() {
        return lastMove;
    }


    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean getFinished() {
        return finished;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCurrentPosition(Coordinate coordinate) {
        this.previousPosition = this.currentPosition;
        this.positionHistory.add(previousPosition);
        this.currentPosition = coordinate;
    }


    @Override
    public List<Coordinate> getPositionHistory() {
        return positionHistory;
    }

    @Override
    public void setLastMove(Coordinate lastMove) {
        this.lastMove = lastMove;
    }

    @Override
    public Coordinate getPreviousPosition() {
        return this.previousPosition;
    }


    /**
    Calcola il punto principale su cui sono basati i movimenti successivi delle auto
     */
    @Override
    public Coordinate getPrincipalPoint(){
        return new Coordinate(
                currentPosition.getX() + lastMove.getX(),
                currentPosition.getY() + lastMove.getY());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return finished == car.finished && Objects.equals(currentPosition, car.currentPosition) && Objects.equals(lastMove, car.lastMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPosition, finished, lastMove);
    }
}
