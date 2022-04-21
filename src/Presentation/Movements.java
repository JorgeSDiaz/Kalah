package Presentation;

import Domain.Kalah;

import java.awt.*;

public class Movements extends ScreenData{
    private String movements;

    public Movements(Kalah kalah, char align){
        super(kalah, align, 155, 60, 22);
    }


    @Override
    public void refresh() {
        movements = String.valueOf(kalah.getMovements());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        refresh();
        g.drawString("¡Kalah!", 400, 60);
        g.drawString("Number of movements realized: " + movements, 970, 110);
    }
}
