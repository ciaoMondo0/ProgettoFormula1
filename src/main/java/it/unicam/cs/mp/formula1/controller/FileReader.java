package it.unicam.cs.mp.formula1.controller;

import it.unicam.cs.mp.formula1.interfaces.IFileReader;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import it.unicam.cs.mp.formula1.modelli.Coordinate;
import it.unicam.cs.mp.formula1.modelli.Track;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.*;

/**
 * Responsabile della creazione del tracciato di gara con i giocatori bot presenti attraverso la
 * lettura dei file passati in input dall’utente
 *
 */
public class FileReader implements IFileReader {


    private int[][] trackGrid;
    private final int red = RED.getRGB();
    private final int blue = BLUE.getRGB();
    private final int green = GREEN.getRGB();
    private final int gray = GRAY.getRGB();
    private Coordinate startPoint;
    private Coordinate endPoint;

    public FileReader() {}

    @Override
    public void loadTrack(String imagePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int width = image.getWidth();
        int height = image.getHeight();
        trackGrid = new int[width][height];
        setPositions(image, width, height);
    }

    @Override
    public void setPositions(BufferedImage image, int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                processPixel(x, y, pixel);
            }
        }
    }

    private void processPixel(int x, int y, int pixel) {
        if (pixel == gray) {
            trackGrid[x][y] = 1;
        } else if (pixel == red) {
            trackGrid[x][y] = 2;
            endPoint = new Coordinate(x, y);
        } else if (pixel == blue) {
            trackGrid[x][y] = 3;
            startPoint = new Coordinate(x, y);
        } else if (pixel == green) {
            trackGrid[x][y] = 0;
        }
    }

    @Override
    public ITrack createTrack() {
        return new Track(trackGrid, startPoint, endPoint);
    }
}
