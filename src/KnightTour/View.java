package KnightTour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class View {
    private final Panel[] panel = new Panel[6];
    private final ChoosePanel rightPanel = new ChoosePanel();
    private final JFrame frame = new JFrame("Knight tour");
    private final JMenuItem stopOrPlay = new JMenuItem("Stop");

    public View() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(1335, 783);
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setLayout(null);

        Container container = frame.getContentPane();
        container.setBackground(new Color(49, 46, 43));

        for(int i = 0; i < 6; i++) {
            panel[i] = new Panel();
        }
        panel[0].setLocation(0, 0);
        container.add(panel[0]);
        panel[1].setLocation(340, 0);
        container.add(panel[1]);
        panel[2].setLocation(0, 360);
        container.add(panel[2]);
        panel[3].setLocation(340, 360);
        container.add(panel[3]);
        panel[4].setLocation(680, 0);
        container.add(panel[4]);
        panel[5].setLocation(680, 360);
        container.add(panel[5]);

        container.add(rightPanel);

        JMenu m1 = new JMenu("Help");
        JMenuItem m1_1 = new JMenuItem("About");
        m1_1.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Author : 龔少谷"));
        m1.add(m1_1);

        JMenu m2 = new JMenu("Tool");
        m2.add(stopOrPlay);

        JMenuBar mb = new JMenuBar();
        mb.add(m2);
        mb.add(m1);
        frame.setJMenuBar(mb);

        frame.setSize(frameSize);
        frame.setVisible(true);
    }

    public ChoosePanel getChoose() {
        return this.rightPanel;
    }

    public HashMap<String, JMenuItem> getItem() {
        HashMap<String, JMenuItem> map = new HashMap<>();
        map.put("stopOrPlay", stopOrPlay);
        return map;
    }

    public Panel getPanel(int index) {
        return this.panel[index];
    }

    public void setListener(ActionListener l) {
        stopOrPlay.addActionListener(l);
    }
}
