package KnightTour;

import java.util.ArrayList;

public class Tour {
    private ArrayList<Coordinate> trace = new ArrayList<>();
    private int counter;
    private Coordinate start;

    public Tour(Coordinate start) {
        this.start = start;
    }

    public void start() {
        Knight knight = new Knight(start);
        while(knight.getCounter() < 64) {
            if(! knight.move()) {
                break;
            }
        }
        trace = knight.getTrace();
        this.counter = knight.getCounter();
    }

    public ArrayList<Coordinate> getTrace() {
        return this.trace;
    }

    public int getCounter() {
        return this.counter;
    }
}
