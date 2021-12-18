package KnightTour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class View {
    private Panel panel = new Panel();
    private Choose rightPanel = new Choose();
    private JLabel knight = new JLabel();
    private Container container;
    private JFrame frame = new JFrame("Knight tour");
    private JMenuItem m2_1 = new JMenuItem("Stop");
    private JMenuItem m2_2 = new JMenuItem("Play");
    private static final Font FONT = new Font("times new roman", Font.BOLD, 40);
    private static final int SQUARE_SIZE = 82;

    public View() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - 995) / 2, (screenSize.height - 743) / 2);
        frame.setLayout(null);

        container = frame.getContentPane();
        container.setBackground(new Color(49, 46, 43));

        knight();
        container.add(panel);
        container.add(rightPanel);

        JMenu m1 = new JMenu("Help");
        JMenuItem m1_1 = new JMenuItem("About");
        m1_1.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Author : 龔少谷"));
        m1.add(m1_1);

        JMenu m2 = new JMenu("Tool");
        m2.add(m2_1);
        m2.add(m2_2);

        JMenuBar mb = new JMenuBar();
        mb.add(m2);
        mb.add(m1);
        frame.setJMenuBar(mb);

        frame.setSize(995, 743);
        frame.setVisible(true);
    }

    public void reset() {
        try {
            Thread.sleep(250);
        } catch(Exception ignored) {}
        panel.removeAll();
        frame.remove(this.panel);
        this.panel = new Panel();
        knight();
        frame.repaint();
        container.add(panel);
    }

    public Choose getChoose() {
        return this.rightPanel;
    }

    public HashMap<String, JMenuItem> getItem() {
        HashMap<String, JMenuItem> map = new HashMap<>();
        map.put("stop", m2_1);
        map.put("play", m2_2);
        return map;
    }

    public void setListener(ActionListener l) {
        m2_1.addActionListener(l);
        m2_2.addActionListener(l);
    }


    public void knight() {
        knight.setVisible(false);
        ImageIcon knightIcon = new ImageIcon("icon/knight.png");
        knightIcon.setImage(knightIcon.getImage().getScaledInstance(knightIcon.getIconWidth() / 75 * 10, knightIcon.getIconHeight() / 75 * 10, Image.SCALE_SMOOTH));
        knight.setIcon(knightIcon);
        knight.setSize(100, 100);
        panel.add(knight, 0);
    }

    public void setKnight(int x, int y, int z) {
        knight.setVisible(true);
        if(z > 0) {
            JLabel label = new JLabel(Integer.toString(z));
            int a;
            if(z < 10) {
                a = this.knight.getX() - 18 + 53;
            } else {
                a = this.knight.getX() - 18 + 43;
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
            panel.add(label, 0);
        }
        if(z != 0) {
            int xDifference = 18 + (x - 1) * SQUARE_SIZE - this.knight.getX();
            int yDifference = 570 - (y - 1) * SQUARE_SIZE - this.knight.getY();
            int nowX = this.knight.getX();
            int nowY = this.knight.getY();
            for(int i = 1; i <= 41; i += 2) {
                this.knight.setLocation(nowX + xDifference / 41 * i, nowY + yDifference / 41 * i);
                try {
                    Thread.sleep(9);
                } catch(Exception ignored) {}
            }
        } else {
            knight.setLocation(18 + (x - 1) * SQUARE_SIZE, 570 - (y - 1) * SQUARE_SIZE);
            try {
                Thread.sleep(100);
            } catch(Exception ignored) {}
        }
        try {
            Thread.sleep(100);
        } catch(Exception ignored) {}
    }
}
