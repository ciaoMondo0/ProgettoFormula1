package it.unicam.cs.mp.formula1.interfaces;

import java.util.List;

public interface IBotLoader {
    List<ICar> loadBots(String filePath);
}
