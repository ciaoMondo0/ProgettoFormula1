package it.unicam.cs.mp.formula1.controller;

import it.unicam.cs.mp.formula1.interfaces.IBotLoader;
import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.modelli.Car;
import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Responsabile di configurare i bot sul tracciato leggendo il file
 */
public class BotLoader implements IBotLoader {

    private Coordinate startPoint;

    public BotLoader(Coordinate startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public List<ICar> loadBots(String filePath) {
        List<ICar> bots = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String botName = line;
                    bots.add(new Car(startPoint, botName));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bots;
    }
}
