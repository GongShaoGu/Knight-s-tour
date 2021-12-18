package KnightTour;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Controller implements ActionListener {
    private View view;
    private Map<String, Object> map;
    private Map<String, JMenuItem> itemMap;
    private ThreadTour threadTour;
    private int goal = -1;

    public Controller() {
        view = new View();
        map = view.getChoose().getButton();
        itemMap = view.getItem();
        view.getChoose().setActionListener(this);
        view.setListener(this);
    }

    public void die() {
        try {
            threadTour.die();
        } catch (Exception ignored) {}
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == map.get("set")) {
            goal = view.getChoose().getGoal();
        } else if(source == map.get("tour")) {
            die();
            threadTour = new ThreadTour(view, goal, Tour.Type.NORMAL);
            threadTour.start();
        } else if(source == map.get("random")) {
            die();
            threadTour = new ThreadTour(view, goal, Tour.Type.RANDOM);
            threadTour.start();
        } else if(source == map.get("exit")) {
            System.exit(0);
        } else if(source == itemMap.get("stop")) {
            try {
                threadTour.setStop(true);
            } catch (Exception ignored) {}
        } else if(source == itemMap.get("play")) {
            try {
                threadTour.setStop(false);
            } catch (Exception ignored) {}
        }
    }
}

class ThreadTour extends Thread {
    private boolean canRun = true;
    private View view;
    private int goal;
    private Tour.Type type;
    private boolean isStop = false;


    public ThreadTour(View view, int goal, Tour.Type type) {
        this.view = view;
        this.goal = goal;
        this.type = type;
    }

    @Override
    public void run() {
        int counter;
        do {
            view.reset();
            Tour tour = new Tour();
            tour.start(type);
            int[][] trace = tour.getTrace();
            counter = tour.getCounter();
            for(int i = 0; i < counter; i++) {
                if(!canRun) {
                    break;
                }
                synchronized(this) {
                    if (isStop) {
                        try {
                            wait();
                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }
                view.setKnight(trace[i][0], trace[i][1], i);
                view.getChoose().setNow(i + 1);
            }
            if(!canRun) {
                break;
            }
            synchronized(this) {
                if(isStop) {
                    try {
                        wait();
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                }
            }
        } while(counter < goal);
    }

    public synchronized void setStop(boolean stop) {
       this.isStop = stop;
       if(!stop) {
           notifyAll();
       }
    }

    public void die() {
        canRun = false;
    }
}

