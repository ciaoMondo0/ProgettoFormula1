package it.unicam.cs.mp.formula1.view;

import it.unicam.cs.mp.formula1.controller.BotLoader;
import it.unicam.cs.mp.formula1.controller.FileReader;
import it.unicam.cs.mp.formula1.interfaces.IBotLoader;
import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.IFileReader;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Responsabile di inizializzare la gara per l'interfaccia grafica di JavaFX
 */
public class JavaFXController {

    @FXML
    private Canvas canvas;
    @FXML
    private Label statusLabel;
    @FXML
    private Spinner<Integer> botCountSpinner;
    @FXML
    private TextField trackFilePath;
    @FXML
    private TextField botsFilePath;

    private List<ICar> cars;
    private Image trackImage;
    private BufferedImage trackBufferedImage;
    private RaceControllerFX raceController;
    private ITrack track;
    private IFileReader fileReader;

    private IBotLoader botLoader;

    @FXML
    public void initialize() {
        initializeBotCountSpinner();
    }

    private void initializeBotCountSpinner() {
        botCountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2));
    }

    @FXML
    public void startRace() {
        if (areFilePathsValid()) {
            setupRace();
            int botCount = botCountSpinner.getValue();
            loadBots(botCount);
            startRaceController();
        }
    }

    private boolean areFilePathsValid() {
        if (trackFilePath.getText().isEmpty() || botsFilePath.getText().isEmpty()) {
            statusLabel.setText("Inserire file per il tracciato e per i bot.");
            return false;
        }
        return true;
    }

    private void setupRace() {
        String trackImagePath = trackFilePath.getText();
        initializeFileReader(trackImagePath);
        initializeTrackImage(trackImagePath);
        initializeBotLoader();
    }

    private void initializeFileReader(String trackImagePath) {
        fileReader = new FileReader();
        fileReader.loadTrack(trackImagePath);
        track = fileReader.createTrack();
    }

    private void initializeBotLoader(){
        botLoader = new BotLoader(track.getStartingPosition());
    }

    private void initializeTrackImage(String trackImagePath) {
        try {
            trackBufferedImage = ImageIO.read(new File(trackImagePath));
            trackImage = new Image(new File(trackImagePath).toURI().toString(), canvas.getWidth(), canvas.getHeight(), false, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBots(int botCount) {
        BotConfiguration botConfiguration = new BotConfiguration(botLoader, track, botsFilePath.getText());
        try {
            cars = botConfiguration.loadBots(botCount);
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Errore nel caricare i bot.");
        }
    }

    private void startRaceController() {
        raceController = new RaceControllerFX(canvas, track, trackBufferedImage, trackImage);
        statusLabel.setText("La gara è iniziata con " + cars.size() + " bot!");
        raceController.startRace(cars);
    }
}
