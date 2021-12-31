package KnightTour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ChoosePanel extends JPanel {
    private final JButton tour = new JButton("Start");
    private final JLabel find = new JLabel("Find : 0");
    private final JLabel test = new JLabel("Test : 0");
    private final JComboBox<String> xCoordinate = new JComboBox<>(new String[]{"a", "b", "c", "d", "e", "f", "g", "h"});
    private final JComboBox<String> yCoordinate = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});

    private int findInt = 0;

    public enum Coordinate {
        X, Y
    };

    public ChoosePanel() {
        setVisible(true);
        setLayout(null);
        Font font = new Font("times new roman", Font.BOLD, 20);
        setBackground(new Color(49, 46, 43));

        tour.setFont(font);
        tour.setBounds(50, 370, 200, 40);
        tour.setBackground(new Color(135, 206, 235));
        add(tour);

        find.setFont(font);
        find.setForeground(new Color(135, 206, 235));
        find.setBounds(50, 30, 200, 30);
        add(find);

        test.setFont(font);
        test.setForeground(new Color(135, 206, 235));
        test.setBounds(50, 100, 200, 30);
        add(test);

        xCoordinate.setFont(font);
        xCoordinate.setBounds(70, 300, 75, 30);
        add(xCoordinate);

        yCoordinate.setFont(font);
        yCoordinate.setBounds(155, 300, 75, 30);
        add(yCoordinate);

        setSize(300, 726);
        setLocation(1020, 0);
    }

    public void setActionListener(ActionListener l) {
        tour.addActionListener(l);
        xCoordinate.addActionListener(l);
        yCoordinate.addActionListener(l);
    }


    public synchronized void setFind() {
        findInt += 1;
        String string = "Find : " + findInt;
        find.setText(string);
    }

    public synchronized void resetFind() {
        findInt = 0;
        String string = "Find : " + findInt;
        find.setText(string);
    }

    public void setTest(int test) {
        String string = "Test : " + test;
        this.test.setText(string);
    }

    public HashMap<String, Object> getElement() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tour", tour);
        map.put("x", xCoordinate);
        map.put("y", yCoordinate);
        return map;
    }

    public int getStartCoordinate(Coordinate coordinate) {
        if(coordinate == Coordinate.X) {
            return xCoordinate.getSelectedIndex() + 1;
        } else {
            return yCoordinate.getSelectedIndex() + 1;
        }
    }
}
