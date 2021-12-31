package KnightTour;

import java.util.ArrayList;

public class ThreadTour extends Thread {
    private final Panel panel;
    private final ChoosePanel choosePanel;
    private final Coordinate start;

    private boolean canRunFlag = true;
    private boolean isStopFlag = false;

    private static ArrayList<ArrayList<Coordinate>> ALL_TRACE = new ArrayList<>();
    private static int TRY = 0;

    public ThreadTour(Panel panel, ChoosePanel choosePanel, Coordinate start) {
        this.panel = panel;
        this.choosePanel = choosePanel;
        this.start = start;
    }

    @Override
    public void run() {
        int counter;
        Tour tour;
        long time1 = System.currentTimeMillis();
        int thisTime = 0;
        do {
            thisTime++;
            panel.reset();
            tour = new Tour(start);
            System.gc();
            tour.start();
            ArrayList<Coordinate> trace = tour.getTrace();
            counter = tour.getCounter();
            for(int i = 0; i < counter; i++) {
                if(!canRunFlag) {
                    break;
                }
                Coordinate now = trace.get(i);
                panel.setKnight(now.getX(), now.getY(), i);
            }
            if(!canRunFlag) {
                break;
            }
            synchronized(this) {
                TRY += 1;
                panel.setTime(thisTime);
                if(isStopFlag) {
                    try {
                        wait();
                    } catch (Exception ignored) {}
                }
                if(! ALL_TRACE.contains(trace) && counter == 64) {
                    choosePanel.setFind();
                    ALL_TRACE.add(trace);
                    panel.setRunTime(System.currentTimeMillis() - time1);
                    time1 = System.currentTimeMillis();
                    thisTime = 0;
                }
            }
            choosePanel.setTest(TRY);
        } while(true);
    }

    public synchronized void setStop(boolean stop) {
        isStopFlag = stop;
        if(!stop) {
            notifyAll();
        }
    }

    public void die() {
        canRunFlag = false;
    }

    public static void reset() {
        TRY = 0;
        ALL_TRACE = new ArrayList<>();
    }
}

