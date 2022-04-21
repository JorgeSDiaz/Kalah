package Presentation;

import Domain.Kalah;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {
    protected final Kalah kalah;

    public Screen(Kalah kalah, char align, int horizontalGap, int verticalGap, int sizeFont){
        this.kalah = kalah;
        prepareScreen(align, horizontalGap, verticalGap);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, sizeFont));
    }

    private void prepareScreen(char align, int horizontal, int vertical){
        if (align == 'c'){
            setLayout(new FlowLayout(FlowLayout.CENTER, horizontal, vertical));
        } else if (align == 'r'){
            setLayout(new FlowLayout(FlowLayout.RIGHT, horizontal, vertical));
        } else if (align == 'l'){
            setLayout(new FlowLayout(FlowLayout.LEFT, horizontal, vertical));
        }
    }

    public abstract void refresh();
}
