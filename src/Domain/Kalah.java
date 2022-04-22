package Domain;

/**
 * Write a description of class Kalah here.
 * 
 * @author Valentina de la Hoz
 * @version 1.0
 */
public class Kalah
{
    private int movements;
    private int player;
    private int[][] board;
    private final int[] housesSeeds;

    /**
     * Constructor for objects of class Kalah
     * @param houses How many houses to play with
     * @param seeds How many seeds each house starts with
     */
    public Kalah(int houses, int seeds){
        movements = 0;
        player = 1;
        housesSeeds = new int[]{houses, seeds};
        createBoard();
    }

    /**
     * constructor for kalah class objects with base and standard data
     */
    public Kalah() {
        this(6, 3);
    }

    /**
     * The board is built and filled
     */
    public void createBoard(){
        board = new int[2][housesSeeds[0] + 1];
        for (int i = 0; i < 2; i++){
            for (int j = 1; j < housesSeeds[0] + 1; j++){
                board[i][j] = housesSeeds[1];
            }
        }
    }

    /**
     * A move is made and the board is updated
     * @param player Player making the move
     * @param house house on which the movement is to be made
     */
    public void play(int player, int house) {
        boolean intoWareHouse = false;
        movements += 1;
        if (board[player][house] != 0) {
            for (int i = 1; i < board[player][house] + 1; i++) {
                if (house + i == housesSeeds[0]) {
                    board[player][0] += 1;
                    intoWareHouse = true;
                } else if (house + i > housesSeeds[0]){
                    board[player == 1 ? 0 : 1][(house + i) % housesSeeds[1]] += 1;
                    if ((house + i) % housesSeeds[1] == housesSeeds[0]){
                        board[player == 1 ? 0 : 1][0] += 1;
                    }
                } else {
                    board[player][house + i] += 1;
                    if (board[player][house + 1] == 1) {
                        board[player][0] += (board[player][house + i] + board[player == 1 ? 0 : 1][house + i]);
                        board[player][house + i] = 0;
                        board[player == 1 ? 0 : 1][house + i] = 0;
                    }
                }
            }
        }

        if (!intoWareHouse){
            this.player = (player % 2) + 1;
        }
    }

    /**
     * We check if the game has already ended
     * @return game over
     */
    public boolean endGame(){
        boolean veredict = true;
        for (int[] i : board){
            int j = 1;
            while (veredict && j < i.length + 1){
                if (i[j] != 0){
                    veredict = false;
                }
                j += 1;
            }
        }
        return veredict;
    }

    /**
     * It is determined who won
     * @return winning player number
     */
    public int whoWin(){
        if (board[0][0] > board[1][0]){
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Who is currently playing
     * @return
     */
    public int player(){
        return player;
    }

    /**
     * Current status of the board
     * @return board
     */
    public int[][] board(){
        return board;
    }

    /**
     * gives the quantity of seeds in the storage
     * @param player player who owns the warehouse
     * @return amount of seeds
     */
    public int getWareHouseSeeds(int player) {
        return board[player][0];
    }

    /**
     * Obtain the number of movements
     * @return total movements
     */
    public int getMovements() {
        return movements;
    }

}
