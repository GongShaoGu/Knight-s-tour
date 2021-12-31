package KnightTour;

import javax.swing.*;
import java.awt.*;

public class Panel extends JLayeredPane {
    private final JLabel knight = new JLabel();
    private final JLabel time = new JLabel("0");
    private final ImageIcon boardIcon = new ImageIcon("icon/chessboard.png");
    private final JLabel runTime = new JLabel("ms : " + "0");

    private static final Font FONT = new Font("times new roman", Font.BOLD, 20);
    private static final int SQUARE_SIZE = 41;

    public Panel() {
        setLayout(null);
        setSize(340, 360);
        setBackground(new Color(49, 46, 43));

        boardIcon.setImage(boardIcon.getImage().getScaledInstance(boardIcon.getIconWidth() / 4, boardIcon.getIconHeight() / 4, Image.SCALE_SMOOTH));

        runTime.setFont(FONT);
        runTime.setBackground(new Color(49, 46, 43));
        runTime.setForeground(new Color(255, 255, 255));
        runTime.setBounds(0, 340, 200, 20);

        time.setFont(new Font("times new roman", Font.BOLD, 40));
        time.setBackground(new Color(0, 0, 0, 0));
        time.setForeground(new Color(150, 25, 25, 100));
        time.setBounds(21, 0, 50, 50);

        set();
    }

    public void reset() {
        try {
            Thread.sleep(250);
        } catch(Exception ignored) {}
        removeAll();
        set();
        repaint();
    }

    public void set() {
        knight.setVisible(false);
        ImageIcon knightIcon = new ImageIcon("icon/knight.png");
        knightIcon.setImage(knightIcon.getImage().getScaledInstance(knightIcon.getIconWidth() / 750 * 50, knightIcon.getIconHeight() / 750 * 50, Image.SCALE_SMOOTH));
        knight.setIcon(knightIcon);
        knight.setSize(50, 50);
        add(knight);
        setLayer(knight,2);

        JLabel chessBoard = new JLabel();
        chessBoard.setIcon(boardIcon);
        chessBoard.setBounds(0, 0, 340, 340);
        add(chessBoard);
        setLayer(chessBoard, 0);

        add(runTime);
        setLayer(runTime, 3);

        add(time);
        setLayer(time, 3);
    }

    public void setRunTime(long time) {
        String string = "ms : " + time;
        runTime.setText(string);
    }

    public void setTime(int time) {
        this.time.setText(Integer.toString(time));
    }

    public void setKnight(int x, int y, int z) {
        knight.setVisible(true);
        if(z > 0) {
            JLabel label = new JLabel(Integer.toString(z));
            int a;
            if(z < 10) {
                a = this.knight.getX() - 9 + 26;
            } else {
                a = this.knight.getX() - 9 + 21;
            }
            label.setBounds(a, this.knight.getY(), SQUARE_SIZE, SQUARE_SIZE);
            label.setFont(FONT);
            if(z % 3 == 1) {
                label.setForeground(new Color(0, 0, 200));
            } else if(z % 3 == 2){
                label.setForeground(new Color(47, 79, 79));
            } else {
                label.setForeground(new Color(200, 0, 0));
            }
            add(label);
            setLayer(label, 1);
        }
        knight.setLocation(9 + (x - 1) * SQUARE_SIZE, 295 - (y - 1) * SQUARE_SIZE);
    }
}
