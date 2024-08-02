package it.unicam.cs.mp.formula1.view;


import it.unicam.cs.mp.formula1.interfaces.IBotLoader;
import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import it.unicam.cs.mp.formula1.modelli.Car;

import java.io.IOException;
import java.util.List;

/**
 * Responsabile della configurazione dei bot per l'interfaccia
 */
public class BotConfiguration {

    private final IBotLoader botLoader;
    private final ITrack track;
    private final String botsFilePath;

    public BotConfiguration(IBotLoader botLoader, ITrack track, String botsFilePath) {
        this.botLoader = botLoader;
        this.track = track;
        this.botsFilePath = botsFilePath;
    }

    public List<ICar> loadBots(int botCount) throws IOException {
        List<ICar> cars = botLoader.loadBots(botsFilePath);

        String botName = "Bot " + (cars.size() + 1);
        cars.add(new Car(track.getStartingPosition(), botName));

        return cars;
    }
}
