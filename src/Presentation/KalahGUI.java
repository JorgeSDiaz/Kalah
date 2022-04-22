package Presentation;
import Domain.ExceptionKalah;
import Domain.Kalah;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * @Author Valentina de la Hoz - Jorge Saenz
 * @Version 2.0
 */

public class KalahGUI extends JFrame {
    public Kalah kalah;
    public static Color MAJOR_COLOR = Color.BLUE;
    public static Color SECONDARY_COLOR = Color.RED;

    /* We obtain the dimensions for the window to occupy a quarter of the screen, for all devices */
    private final static Dimension PREFERRED_DIMENSION =
            new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().width*(0.5)),
                    (int)(Toolkit.getDefaultToolkit().getScreenSize().height*(0.5)));

    /* Menu Bar */
    private JMenuBar menuBar;
    private JMenu principalMenu, openSaveMenuItem;
    private JMenuItem newMenuItem, exitMenuItem, openMenuItem, saveMenuItem, changeColorMenuItem;

    /* File Chooser */
    private JFileChooser fileChooser;

    /* Color Chooser */
    private JColorChooser colorChooser;

    /* Main Window Attributes */
    private JPanel gameContainer, containerMovementsMade, containerCantSeedsStockOne, containerCantSeedsStockTwo, gameDashboard;
    private JButton start, restart, setUp;

    /**
     * KalahGUI Constructor
     */
    public KalahGUI() {
        try{
            kalah = new Kalah(7, 2);
            prepareElements();
            prepareActions();
        } catch (ExceptionKalah kg){
            JOptionPane.showMessageDialog(null, kg.getMessage());
        }
    }

    /**
     * prepare/set graphic elements of KalahGUI
     */
    private void prepareElements(){
        /* Set Menu */
        prepareElementsMenu();
        prepareElementsBoard();

        /* Set Window Design */
        this.setTitle("Kalah");
        this.setVisible(true);
        this.setSize(PREFERRED_DIMENSION);
        this.setLocationRelativeTo(null);
    }

    /**
     * prepare/set graphic elements of KalahGUI Menu
     */
    private void prepareElementsMenu(){
        /* Create */
        /* Menu Bar */
        menuBar = new JMenuBar();
        /* Menu */
        principalMenu = new JMenu("Menu");
        /* Items Menu Items */
        /* "New" Item*/
        newMenuItem = new JMenuItem("New");
        /* subMenu to Open - Save Items */
        openSaveMenuItem = new JMenu("Open - Save");
        /* "Open" and "Save" Items */
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        /*"ChangeColor" Item*/
        changeColorMenuItem = new JMenuItem("Change color");
        /* "Exit" Item*/
        exitMenuItem = new JMenuItem("Exit");

        /* We group the "menu" items inside menu, and menu we group it inside "menuBar" */
        menuBar.add(principalMenu);
        /* "New" in "Menu"*/
        principalMenu.add(newMenuItem);
        principalMenu.addSeparator();
        /* "Open" and "Save" in "OpenSaveMenu" */
        openSaveMenuItem.add(openMenuItem);
        openSaveMenuItem.add(saveMenuItem);
        /* "OpenSaveMenu" in "Menu"*/
        principalMenu.add(openSaveMenuItem);
        principalMenu.addSeparator();
        /* "ChangeColor" in "Menu" */
        principalMenu.add(changeColorMenuItem);
        principalMenu.addSeparator();
        /* "Exit" in "Menu" */
        principalMenu.add(exitMenuItem);
        /* Set "MenuBar' as main MenuBar in the JFrame */
        setJMenuBar(menuBar);
    }
    
    /**
     * prepare/set graphic elements of Kalah Board
     */
    private void prepareElementsBoard() {
        /* Set up the main panel containing the different elements */
        gameContainer = new JPanel();
        gameContainer.setBackground(Color.RED);
        gameContainer.setLayout(new BorderLayout());
        add(gameContainer);
        /* Place the seed number panel in the two warehouses in the East zone */
        JPanel cantSeedsContainer = new JPanel();
        containerCantSeedsStockOne = new SeedStorage(this.kalah, 'l', 100, 1);
        cantSeedsContainer.add(containerCantSeedsStockOne);
        containerCantSeedsStockTwo = new SeedStorage(this.kalah, 'r', 102, 2);
        cantSeedsContainer.add(containerCantSeedsStockTwo);
        gameContainer.add(cantSeedsContainer, BorderLayout.EAST);

        /* Place the movement panel in the East zone */
        containerMovementsMade = new Movements(this.kalah, 'c');
        gameContainer.add(containerMovementsMade, BorderLayout.NORTH);

        /* Place the game panel in the Center zone */
        gameDashboard = new GameScreen(this);
        gameContainer.add(gameDashboard, BorderLayout.CENTER);

        /* Prepare the game buttons */
        JPanel buttonContainer = new JPanel();
        start = new JButton("Play");
        restart = new JButton("Restart");
        setUp = new JButton("Set Up");
        buttonContainer.add(start);
        buttonContainer.add(restart);
        buttonContainer.add(setUp);
        gameContainer.add(buttonContainer, BorderLayout.SOUTH);

        refresh();
    }

    private void refresh(){
        gameDashboard.repaint();
        containerCantSeedsStockOne.repaint();
        containerCantSeedsStockTwo.repaint();
        containerMovementsMade.repaint();
    }


    /**
     * prepare/configure KalahGUI actions/listeners
     */
    private void prepareActions(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        prepareActionsMenu();
        /* Listener that executes the function "closeAction" when the window close event occurs */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeAction();
            }
        });
    }

    /**
     * prepare/configure KalahGUI Menu actions/listeners
     */
    private void prepareActionsMenu(){
        /* Listener that when the event of pressing the MenuItem "Exit" occurs, executes "closeAction" */
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAction();
            }
        });

        /* Create JFileChooser */
        fileChooser = new JFileChooser();
        /* Listener that when the event of pressing the MenuItem "Open" occurs, executes "openAction" */
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAction();
            }
        });
        /* Listener that when the event of pressing the MenuItem "Save" occurs, executes "saveAction" */
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAction();
            }
        });
        /* Listener that when the event of pressing the MenuItem "Change color" occurs, executes "ChangeColorAction" */
        changeColorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){ changeColorAction();}
        });
    }

    /**
     * Open a file selected by the user from the file management window
     */
    private void openAction(){
        int selection = fileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION){
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, fichero.getName() + ", no se puede abrir, " +
                    "función 'Open' en construcción");
        }
    }

    /**
     * Save a file with a name given by the user, in a location given by the user, through the file manager
     */
    private void saveAction(){
        int selection = fileChooser.showSaveDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION){
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, fichero.getName() + ", no se puede guardar, " +
                    "función 'save' en construcción");
        }
    }

    /**
     * The user is given a confirmation message to close the window
     */
    private void closeAction(){
        int confirm = JOptionPane.showConfirmDialog(this, "Are U sure?", "Warning", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    /*
     * Method to change the main color and the secondary color of the board
     */
    private void changeColorAction(){
        colorChooser = new JColorChooser();
        Color newMainColor = JColorChooser.showDialog(this, "Select new main color", MAJOR_COLOR);
        Color nuevoColorS = JColorChooser.showDialog(this, "Select secondary color", SECONDARY_COLOR);
        if(newMainColor != null && nuevoColorS != null){
            MAJOR_COLOR = newMainColor;
            SECONDARY_COLOR = nuevoColorS;
        }
        refresh();
    }

    public static void main(String[] args){
        new KalahGUI();
    }
}



