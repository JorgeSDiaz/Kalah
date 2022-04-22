package Domain;

public class ExceptionKalah extends Exception{
    public final static String MINIMUM_HOUSES = "Minimum 6 houses";
    public final static String MINIMUM_SEEDS = "Minimum 3 seeds";
    public final static String NOT_A_PLAYER = "Player 1 or 2";
    public final static String OUT_OF_RANGE_HOUSE = "The house is not in range";
    public final static String SEEDLESS = "This house has no seeds";

    public ExceptionKalah(String message){
        super(message);
    }
}
