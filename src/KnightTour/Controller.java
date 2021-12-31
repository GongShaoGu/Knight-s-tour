package KnightTour;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Controller implements ActionListener {
    private final View view;
    private final Map<String, Object> map;
    private final Map<String, JMenuItem> itemMap;
    private final ChoosePanel choosePanel;

    private ThreadTour[] threadTour = new ThreadTour[6];
    private int x = 1;
    private int y = 1;
    private boolean stopFlag = true;

    public Controller(View view) {
        this.view = view;
        choosePanel = view.getChoose();
        map = choosePanel.getElement();
        itemMap = view.getItem();
        view.getChoose().setActionListener(this);
        view.setListener(this);
    }

    public void die() {
        try {
           for(int i = 0; i < 6; i++) {
               threadTour[i].die();
           }
        } catch (Exception ignored) {}
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == map.get("tour")) {
            die();
            Coordinate start = new Coordinate(x, y);
            choosePanel.resetFind();
            ThreadTour.reset();
            for(int i = 0; i < 6; i++) {
                threadTour[i] = new ThreadTour(view.getPanel(i), view.getChoose(), start);
                threadTour[i].start();
            }
        } else if(source == map.get("x")) {
            x = choosePanel.getStartCoordinate(ChoosePanel.Coordinate.X);
        } else if(source == map.get("y")) {
            y = choosePanel.getStartCoordinate(ChoosePanel.Coordinate.Y);
        } else if(source == itemMap.get("stopOrPlay")) {
            if(stopFlag) {
                itemMap.get("stopOrPlay").setText("Play");
            } else {
                itemMap.get("stopOrPlay").setText("Stop");
            }
            try {
                for(int i = 0; i < 6; i++) {
                    threadTour[i].setStop(stopFlag);
                }
            } catch (Exception ignored) {}
            stopFlag = ! stopFlag;
        }
    }
}
