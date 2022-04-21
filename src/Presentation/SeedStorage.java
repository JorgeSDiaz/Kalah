package Presentation;

import Domain.Kalah;

import java.awt.*;

public class SeedStorage extends Screen{
    private String cantSeeds;
    private int playerOwner;

    public SeedStorage(Kalah kalah, char align, int horizontalGap, int owner){
        super(kalah, align, horizontalGap, 200, 16);
        playerOwner = owner;
    }

    public void refresh(){
        cantSeeds = String.valueOf(this.kalah.getWareHouseSeeds(playerOwner - 1));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        refresh();
        g.drawString("Seed storage " + String.valueOf(playerOwner) + ": " +
                this.kalah.getWareHouseSeeds(playerOwner - 1), 20, 20);
    }
}

