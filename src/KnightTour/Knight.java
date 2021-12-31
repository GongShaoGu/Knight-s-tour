package KnightTour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Knight {
    private Coordinate coordinate;
    private int counter;
    private ArrayList<Coordinate> trace = new ArrayList<>();
    private int[][] entrance = {
            {2, 3, 4, 4, 4, 4, 3, 2},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {2, 3, 4, 4, 4, 4, 3, 2},
    };
    public static final Random RANDOM = new Random();
    public enum KnightMove {
        E6, F5, F3, E2, C2, B3, B5, C6//用騎士在D4時所有可以移動到的格子,為移動方式命名
    }

    public Knight(Coordinate start) {
        coordinate = start;
        trace.add(coordinate);
        this.counter = 1;
    }

    private int[] way(KnightMove m) {
        int horizontalMove;
        int verticalMove;
        switch (m) {
            case E6 -> {
                horizontalMove = 1;
                verticalMove = 2;
            }
            case F5 -> {
                horizontalMove = 2;
                verticalMove = 1;
            }
            case F3 -> {
                horizontalMove = 2;
                verticalMove = -1;
            }
            case E2 -> {
                horizontalMove = 1;
                verticalMove = -2;
            }
            case C2 -> {
                horizontalMove = -1;
                verticalMove = -2;
            }
            case B3 -> {
                horizontalMove = -2;
                verticalMove = -1;
            }
            case B5 -> {
                horizontalMove = -2;
                verticalMove = 1;
            }
            case C6 -> {
                horizontalMove = -1;
                verticalMove = 2;
            }
            default -> throw new IllegalStateException("Unexpected value: " + m);
        }
        int[] re = new int[2];
        re[0] = horizontalMove;
        re[1] = verticalMove;
        return re;
    }

    public boolean move() {
        int[] move;
        int min = Integer.MAX_VALUE;
        KnightMove way = null;
        List<KnightMove> list = hasWay();
        if(list.size() == 0) {
            return false;
        }
        int x = coordinate.getX();
        int y = coordinate.getY();
        for(KnightMove k : list) {
            move = way(k);
            int e = this.entrance[x + move[0] - 1][y + move[1] - 1];
            if(e < min) {
                min = e;
                way = k;
            } else if(e == min) {
                if(way == null) {
                    way = k;
                } else if(RANDOM.nextInt(2) % 2 == 0) {
                    way = k;
                }
            }
        }
        move = way(way);
        coordinate = new Coordinate(x + move[0], y + move[1]);
        trace.add(coordinate);
        this.counter++;
        for(KnightMove k : KnightMove.values()) {
            move = way(k);
            try {
                this.entrance[coordinate.getX() + move[0] - 1][coordinate.getY() + move[1] - 1] -= 1;
            } catch(ArrayIndexOutOfBoundsException ignored) {}
        }
        return true;
    }

    public List<KnightMove> hasWay() {
        int[] move;
        List<KnightMove> list = new ArrayList<>();
        for(KnightMove k : KnightMove.values()) {
            move = way(k);
            int x = coordinate.getX() + move[0];
            int y = coordinate.getY() + move[1];
            if(x < 9 && x > 0 && y < 9 && y > 0) {
                Coordinate next = new Coordinate(coordinate.getX() + move[0], coordinate.getY() + move[1]);
                if(! trace.contains(next)) {
                    list.add(k);
                }
            }
        }
        return list;
    }

    public int getCounter() {
        return counter;
    }

    public ArrayList<Coordinate> getTrace() {
        return trace;
    }
}
