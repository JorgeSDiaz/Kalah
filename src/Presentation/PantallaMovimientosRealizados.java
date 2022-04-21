package Presentation;
import javax.swing.*;
import java.awt.*;

public class PantallaMovimientosRealizados extends JPanel{
    private final KalahGUI gui;
    private String movimientosRealizados;

    public PantallaMovimientosRealizados(KalahGUI gui) {
        this.gui = gui;
        setLayout(new FlowLayout(FlowLayout.CENTER, 155, 60));
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
    }
    public void actualizarMovimientosRealizados(){
        this.movimientosRealizados = String.valueOf(gui.getMovimientosRealizados());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        actualizarMovimientosRealizados();
        g.drawString("Kalah!", 625, 60);
        g.drawString("Número de movimientos realizados: " + this.movimientosRealizados, 970, 110);
    }
}

