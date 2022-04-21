package Presentation;
import Domain.Kalah;
import javax.swing.*;
import java.awt.*;

/*
 * Clase para dibujar el tablero de juego
 */
public class PantallaJuego extends JPanel {
    private final KalahGUI gui;

    public PantallaJuego(KalahGUI gui){
        this.gui = gui;
    }

    public void paintComponent(Graphics g) {
        Kalah juego = gui.kalah;
        super.paintComponent(g);
        setBackground(gui.MAJOR_COLOR);
        g.setColor(gui.SECONDARY_COLOR);

    }
}