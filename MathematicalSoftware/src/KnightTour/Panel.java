package KnightTour;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        setLayout(null);
        setSize(680, 686);
        setBackground(new Color(49, 46, 43));

        JLabel chessBoard = new JLabel();
        ImageIcon boardIcon = new ImageIcon("icon/chessboard.png");
        boardIcon.setImage(boardIcon.getImage().getScaledInstance(boardIcon.getIconWidth() / 2, boardIcon.getIconHeight() / 2, Image.SCALE_SMOOTH));
        chessBoard.setIcon(boardIcon);
        chessBoard.setBounds(0, 0, 680, 686);

        add(chessBoard);
        setLocation(0, 0);
    }

}
