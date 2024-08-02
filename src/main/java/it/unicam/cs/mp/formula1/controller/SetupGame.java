package it.unicam.cs.mp.formula1.controller;

import it.unicam.cs.mp.formula1.interfaces.*;

import java.util.List;


/**
 * Responsabile della configurazione dell'ambiente di gara
 */
public class SetupGame implements ISetupGame {

    private IMotoreDiGioco motoreDiGioco;
    private ITrack track;
    private List<ICar> bots;
    private IGestoreGara gestoreGara;


    @Override
    public void setupRace(String imagePath, String botsFilePath) {
        IFileReader fileReader = new FileReader();
        fileReader.loadTrack(imagePath);
        this.track = fileReader.createTrack();

        IBotLoader botLoader = new BotLoader(track.getStartingPosition());
        this.bots = botLoader.loadBots(botsFilePath);

        this.motoreDiGioco = new MotoreDiGioco(bots, track);
        this.gestoreGara = new GestoreGara(motoreDiGioco, bots, track);

    }


    @Override
    public IMotoreDiGioco getMotoreDiGioco() {
        return motoreDiGioco;
    }

    @Override
    public ITrack getTrack() {
        return track;
    }

    @Override
    public List<ICar> getBots() {
        return bots;
    }

    public IGestoreGara getRaceManager() {
        return gestoreGara;
    }
}
