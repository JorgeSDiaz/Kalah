package Domain;
import java.util.ArrayList;

/**
 * Write a description of class Kalah here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kalah
{
    private int[] wareHouseSeeds;
    static private int dimension = 6;
    static private int casas = 6;
    //private Casa[][] tablero;
    static private int movements = 0;

    /**
     * Constructor for objects of class Kalah
     */
    public Kalah(int house, int seeds)
    {
        //tablero = new Casa[dimension][dimension];
        wareHouseSeeds = new int[]{3, 3};
        
    }

    public int getWareHouseSeeds(int player) {
        return wareHouseSeeds[player];
    }

    public int getMovements() {
        return movements;
    }

}
