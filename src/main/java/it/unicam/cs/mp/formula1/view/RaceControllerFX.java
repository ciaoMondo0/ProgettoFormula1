package it.unicam.cs.mp.formula1.view;

import it.unicam.cs.mp.formula1.controller.MotoreDiGioco;
import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.IMotoreDiGioco;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import it.unicam.cs.mp.formula1.modelli.Coordinate;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Responsabile della logica della gara per l’interfaccia grafica
 */
public class RaceControllerFX {
    private final Canvas canvas;
    private final ITrack track;
    private final BufferedImage trackBufferedImage;

    private static final int RED_COLOR = 0xFFFF0000;
    private final Image trackImage;
    private List<ICar> cars;
    private IMotoreDiGioco motoreDiGioco;
    private int currentPlayerIndex = 0;
    private AnimationTimer timer;

    public RaceControllerFX(Canvas canvas, ITrack track, BufferedImage trackBufferedImage, Image trackImage) {
        this.canvas = canvas;
        this.track = track;
        this.trackBufferedImage = trackBufferedImage;
        this.trackImage = trackImage;
    }

    public void startRace(List<ICar> cars) {
        this.cars = cars;
        motoreDiGioco = new MotoreDiGioco(cars, track);

        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 500_000_000) {
                    updateRace();
                    drawRace();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    private void updateRace() {
        ICar currentPlayer = cars.get(currentPlayerIndex);
        if (!currentPlayer.getFinished()) {
            motoreDiGioco.moveCar(currentPlayer);
            if (hasReachedFinishLine(currentPlayer.getCurrentPosition())) {
                currentPlayer.setFinished(true);

            }
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % cars.size();

        if (cars.stream().anyMatch(ICar::getFinished)) {
            timer.stop();
        }
    }

    private boolean hasReachedFinishLine(Coordinate position) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x < trackBufferedImage.getWidth() && y >= 0 && y < trackBufferedImage.getHeight()
                && trackBufferedImage.getRGB(x, y) == RED_COLOR;
    }

    private void drawRace() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(trackImage, 0, 0, canvas.getWidth(), canvas.getHeight());

        for (ICar car : cars) {
            if (car.getFinished()) continue;
            drawCarMovementHistory(gc, car);
            drawAvailableMoves(gc, car);
            drawCurrentCarPosition(gc, car);
        }
    }

    private void drawCarMovementHistory(GraphicsContext gc, ICar car) {
        List<Coordinate> positionHistory = car.getPositionHistory();
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        for (int i = 1; i < positionHistory.size(); i++) {
            Coordinate prev = positionHistory.get(i - 1);
            Coordinate curr = positionHistory.get(i);
            double prevX = scaleX(prev.getX());
            double prevY = scaleY(prev.getY());
            double currX = scaleX(curr.getX());
            double currY = scaleY(curr.getY());
            gc.strokeLine(prevX, prevY, currX, currY);
        }
        gc.setFill(Color.BLUE);
        for (Coordinate pos : positionHistory) {
            gc.fillOval(scaleX(pos.getX()), scaleY(pos.getY()), 5, 5);
        }
    }

    private void drawAvailableMoves(GraphicsContext gc, ICar car) {
        List<Coordinate> possibleMoves = track.getPossibleMoves(car);
        gc.setFill(Color.GREEN);
        for (Coordinate move : possibleMoves) {
            gc.fillOval(scaleX(move.getX()), scaleY(move.getY()), 5, 5);
        }
    }

    private void drawCurrentCarPosition(GraphicsContext gc, ICar car) {
        Coordinate currentPosition = car.getCurrentPosition();
        gc.setFill(Color.BLACK);
        gc.fillOval(scaleX(currentPosition.getX()), scaleY(currentPosition.getY()), 10, 10);
    }

    private double scaleX(int x) {
        return x * (canvas.getWidth() / trackBufferedImage.getWidth());
    }

    private double scaleY(int y) {
        return y * (canvas.getHeight() / trackBufferedImage.getHeight());
    }
}
