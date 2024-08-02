package it.unicam.cs.mp.formula1.controller;

import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.IGestoreGara;
import it.unicam.cs.mp.formula1.interfaces.IMotoreDiGioco;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.util.List;

/**
 * Responsabile di gestire il flusso della gara
 */

public class GestoreGara implements IGestoreGara {
    private IMotoreDiGioco motoreDiGioco;
    private List<ICar> bots;

    private ITrack track;
    private int turno;
    private boolean gameOver;
    private ICar winner;

    public GestoreGara(IMotoreDiGioco motoreDiGioco, List<ICar> bots, ITrack track) {
        this.motoreDiGioco = motoreDiGioco;
        this.bots = bots;
        this.track = track;
        this.turno = 0;
        this.gameOver = false;
        this.winner = null;
    }


    @Override
    public void gestisciGara() {
        if (!gameOver) {
            ICar currentCar = bots.get(turno);
            motoreDiGioco.moveCar(currentCar);
                if (isFinishLine(currentCar.getCurrentPosition())) {
                    winner = currentCar;
                    gameOver = true;
                }

                nextTurno();
        }
    }

    private boolean isFinishLine(Coordinate coordinate) {
        return coordinate.equals(track.getEndingPosition());
    }

    private void nextTurno() {
        turno = (turno + 1) % bots.size();
    }


    @Override
    public boolean isGameOver() {
        return gameOver;
    }


    @Override
    public ICar getWinner() {
        return winner;
    }

    @Override
    public List<ICar> getBots() {
        return bots;
    }

    @Override
    public ICar getCurrentCar() {
        return bots.get(turno);
    }

    @Override
    public List<Coordinate> getPossibleMoves() {
        return track.getPossibleMoves(getCurrentCar());
    }
}
