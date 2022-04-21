package Presentation;
import Domain.Kalah;
import javax.swing.*;
import java.awt.*;

/*
 * Clase para dibujar el tablero de juego
 */
public class GameScreen extends JPanel {
    private final KalahGUI kalahGUI;

    public GameScreen(KalahGUI kalahGUI){
        this.kalahGUI = kalahGUI;
    }

    public void paintComponent(Graphics g) {
        Kalah Game = this.kalahGUI.kalah;
        super.paintComponent(g);
        setBackground(KalahGUI.MAJOR_COLOR);
        g.setColor(KalahGUI.SECONDARY_COLOR);

    }
}