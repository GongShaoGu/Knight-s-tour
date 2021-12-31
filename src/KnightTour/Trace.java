package KnightTour;

import java.util.ArrayList;

public class Trace extends ArrayList<Coordinate> {
    public Trace(ArrayList<Coordinate> e) {
        addAll(e);
    }

    public Trace(Coordinate coordinate) {
        add(coordinate);
    }
}
