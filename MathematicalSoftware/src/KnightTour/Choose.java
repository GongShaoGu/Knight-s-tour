package KnightTour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Choose extends JPanel {
    private JButton tour = new JButton("Warnsdorff's rule");
    private JButton random = new JButton("random");
    private JButton exit = new JButton("exit");
    private JTextField goal = new JTextField(3);
    private JButton set = new JButton("set");
    private JLabel now = new JLabel("Now : 0");
    private JLabel maximum = new JLabel("Maximum : 0");
    private int maxInt = 0;

    public Choose() {
        setVisible(true);
        setLayout(null);
        Font font = new Font("times new roman", Font.BOLD, 20);
        setBackground(new Color(49, 46, 43));

        tour.setFont(font);
        tour.setBounds(50, 370, 200, 40);
        tour.setBackground(new Color(135, 206, 235));
        add(tour);

        random.setFont(font);
        random.setBounds(50, 230, 200, 40);
        random.setBackground(new Color(135, 206, 235));
        add(random);

        goal.setFont(font);
        goal.setBounds(50, 90, 120, 40);
        goal.setText("Goal");
        set.setFont(font);
        set.setBounds(170, 90, 80, 40);
        add(goal);
        add(set);

        exit.setFont(font);
        exit.setBounds(50, 600, 200, 40);
        exit.setBackground(new Color(135, 206, 235));
        add(exit);

        now.setFont(font);
        now.setForeground(new Color(135, 206, 235));
        now.setBounds(25, 30, 100, 30);
        maximum.setFont(font);
        maximum.setForeground(new Color(135, 206, 235));
        maximum.setBounds(125, 30, 150, 30);
        add(now);
        add(maximum);

        setSize(300, 726);
        setLocation(680, 0);
    }

    public void setActionListener(ActionListener l) {
        exit.addActionListener(l);
        tour.addActionListener(l);
        random.addActionListener(l);
        set.addActionListener(l);
    }

    public int getGoal() {
        int goal;
        try {
            goal = Integer.parseInt(this.goal.getText());
        } catch(Exception e) {
            return -1;
        }
        return goal < 0 || goal > 64 ? -1 : goal;
    }

    public void setNow(int counter) {
        String string = "Now : " + counter;
        if(counter > maxInt) {
            maxInt = counter;
            String string2 = "Maximum : " + maxInt;
            maximum.setText(string2);
        }
        now.setText(string);
    }

    public HashMap<String, Object> getButton() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tour", tour);
        map.put("random", random);
        map.put("exit", exit);
        map.put("set", set);
        return map;
    }
}
