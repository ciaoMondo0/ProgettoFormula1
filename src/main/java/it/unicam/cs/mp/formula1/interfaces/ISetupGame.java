package it.unicam.cs.mp.formula1.interfaces;

import it.unicam.cs.mp.formula1.controller.GestoreGara;

import java.util.List;

public interface ISetupGame {


    void setupRace(String imagePath, String botsFilePath);
    IMotoreDiGioco getMotoreDiGioco();
    ITrack getTrack();
    List<ICar> getBots();

    IGestoreGara getRaceManager();
}
