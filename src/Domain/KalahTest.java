package Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class KalahTest {
    Kalah kalahTest;

    @Before
    public void setUp(){
        try {
            kalahTest =new Kalah();
        } catch (ExceptionKalah t){
            fail("Exception: " + t.getMessage());
        }
    }

    @Test
    public void shouldCreateBasicKalah(){
        int[][] board = kalahTest.board();
        assertArrayEquals(new int[]{0, 3, 3, 3, 3, 3, 3}, board[0]);
        assertArrayEquals(new int[]{0, 3, 3, 3, 3, 3, 3}, board[1]);
    }

    @Test
    public void shouldCreateCustomKalah(){
        try {
            kalahTest = new Kalah(9, 5);
            assertArrayEquals(new int[]{0, 5, 5, 5, 5, 5, 5, 5, 5, 5}, kalahTest.board()[0]);
            assertArrayEquals(new int[]{0, 5, 5, 5, 5, 5, 5, 5, 5, 5}, kalahTest.board()[1]);
        } catch (ExceptionKalah t){
            fail("Exception: " + t.getMessage());
        }
    }

    @Test
    public void shouldThrowCustomKalahHousesException(){
        try {
            kalahTest = new Kalah(4, 5);
        } catch (ExceptionKalah t){
            assertEquals(ExceptionKalah.MINIMUM_HOUSES, t.getMessage());
        }
    }

    @Test
    public void shouldThrowCustomKalahSeedsException(){
        try {
            kalahTest = new Kalah(9, 2);
        } catch (ExceptionKalah t){
            assertEquals(ExceptionKalah.MINIMUM_SEEDS, t.getMessage());
        }
    }

    @Test
    public void shouldPlayKalah(){
        try {
            kalahTest.play(0, 1);
            assertArrayEquals(new int[]{0, 0, 4, 4, 4, 3, 3}, kalahTest.board()[0]);
            kalahTest.play(0, 6);
            assertArrayEquals(new int[][]{{1, 0, 4, 4, 4, 3, 0}, {0, 4, 4, 3, 3, 3, 3}}, kalahTest.board());
        } catch (ExceptionKalah t){
            fail("Exception: " + t.getMessage());
        }
    }

    @Test
    public void shouldPlayKalahNotPlayerException(){
        try {
            kalahTest.play(3, 1);
        } catch (ExceptionKalah t){
            assertEquals(ExceptionKalah.NOT_A_PLAYER, t.getMessage());
        }
    }

    @Test
    public void shouldPlayKalahOutRangeHouseException(){
        try {
            kalahTest.play(0, 9);
        } catch (ExceptionKalah t){
            assertEquals(ExceptionKalah.OUT_OF_RANGE_HOUSE, t.getMessage());
        }
    }

    @Test
    public void shouldPlayKalahWareHouseOutRangeHouseException(){
        try {
            kalahTest.play(0, 0);
        } catch (ExceptionKalah t){
            assertEquals(ExceptionKalah.OUT_OF_RANGE_HOUSE, t.getMessage());
        }
    }

    @Test
    public void shouldPlayKalahSeedlessHouseException(){
        try {
            kalahTest.play(0, 1);
            kalahTest.play(0, 1);
        } catch (ExceptionKalah t){
            assertEquals(ExceptionKalah.SEEDLESS, t.getMessage());
        }
    }

    @Test
    public void shouldEndGame(){
        kalahTest.setBoard(new int[][]{{16, 2, 0, 0, 0, 0, 0}, {18, 0, 0, 0, 0, 0, 0}});
        assertTrue(kalahTest.endGame());
    }

    @Test
    public void shouldWhoWin(){
        kalahTest.setBoard(new int[][]{{18, 0, 0, 0, 0, 0, 0}, {16, 2, 0, 0, 0, 0, 0}});
        assertEquals(1, kalahTest.whoWin());
    }

    @Test
    public void shouldPlayer(){
        try {
            assertEquals(1, kalahTest.player());
            kalahTest.play(0, 1);
            assertEquals(2, kalahTest.player());
        } catch (ExceptionKalah t){
            fail("Exception: " + t.getMessage());
        }
    }

    @Test
    public void shouldBoard(){
        assertArrayEquals(new int[][]{{0, 3, 3, 3, 3, 3, 3}, {0, 3, 3, 3, 3, 3, 3}}, kalahTest.board());
    }

    @Test
    public void shoulsSetBoard(){
        kalahTest.setBoard(new int[][]{{0, 3, 3, 3, 3, 3, 3}, {0, 3, 3, 4, 3, 3, 3}});
        assertArrayEquals(new int[][]{{0, 3, 3, 3, 3, 3, 3}, {0, 3, 3, 4, 3, 3, 3}}, kalahTest.board());
    }

    @Test
    public void shouldGetWareHouseSeeds(){
        assertEquals(0, kalahTest.getWareHouseSeeds(0));
        kalahTest.setBoard(new int[][]{{0, 3, 3, 3, 3, 3, 3}, {6, 3, 0, 0, 3, 3, 3}});
        assertEquals(6, kalahTest.getWareHouseSeeds(1));
    }

    @Test
    public void shouldGetMovements(){
        try {
            assertEquals(0, kalahTest.getMovements());
            kalahTest.play(0, 1);
            kalahTest.play(0, 6);
            assertEquals(2, kalahTest.getMovements());
        } catch (ExceptionKalah t){
            fail("Exception: " + t.getMessage());
        }
    }

    @After
    public void tearDown(){}
}
