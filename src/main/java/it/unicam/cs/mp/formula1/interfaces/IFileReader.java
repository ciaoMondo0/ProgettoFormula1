package it.unicam.cs.mp.formula1.interfaces;

import java.awt.image.BufferedImage;

public interface IFileReader {

    void loadTrack(String imagePath);
    void setPositions(BufferedImage image, int width, int height);



    ITrack createTrack();
}
