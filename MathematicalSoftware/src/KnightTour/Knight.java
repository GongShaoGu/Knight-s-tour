package KnightTour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Knight {
    //騎士所在的位置
    private int x;
    private int y;
    private int counter;
    private boolean[][] visited = new boolean[8][8];
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
    public static Random RANDOM = new Random();
    public enum KnightMove {
        //用騎士在D4時所有可以移動到的格子,為移動方式命名
        E6, F5, F3, E2, C2, B3, B5, C6
    }

    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                visited[i][j] = false;
            }
        }
        visited[this.x - 1][this.y - 1] = true;
        this.counter = 1;
    }

    public Knight() {
        var coordinate = RANDOM.nextInt(4);
        int x, y;
        switch(coordinate) {
            case 0 -> {
                x = 1;
                y = 1;
            }
            case 1 -> {
                x = 8;
                y = 1;
            }
            case 2 -> {
                x = 8;
                y = 8;
            }
            default -> {
                x = 1;
                y = 8;
            }
        }
        this.x = x;
        this.y = y;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                visited[i][j] = false;
            }
        }
        visited[this.x - 1][this.y - 1] = true;
        this.counter = 1;
    }

    //移動騎士
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
        List<KnightMove> list = hasWay(this.x, this.y);
        if(list.size() == 0) {
            return false;
        }
        for(KnightMove k : list) {
            move = way(k);
            int e = this.entrance[this.x + move[0] - 1][this.y + move[1] - 1];
            if(e < min) {
                min = e;
                way = k;
            }
            if(e == min) {
                if(RANDOM.nextInt() % 2 == 0) {
                    way = k;
                }
            }
        }
        move = way(way);
        this.x += move[0];
        this.y += move[1];
        this.visited[this.x - 1][this.y - 1] = true;
        this.counter++;
        for(KnightMove k : KnightMove.values()) {
            move = way(k);
            try {
                this.entrance[this.x + move[0] - 1][this.y + move[1] - 1] -= 1;
            } catch(ArrayIndexOutOfBoundsException ignored) {}
        }
        return true;
    }

    public boolean randomMove() {
        List<KnightMove> list = hasWay(this.x, this.y);
        if(list.size() == 0) {
            return false;
        }
        int[] move = way(list.get(RANDOM.nextInt(list.size())));
        this.x += move[0];
        this.y += move[1];
        this.visited[this.x - 1][this.y - 1] = true;
        this.counter++;
        return true;
    }

    public List<KnightMove> hasWay(int x, int y) {
        int[] move;
        List<KnightMove> list = new ArrayList<>();
        for(KnightMove k : KnightMove.values()) {
            move = way(k);
            try {
                if(! this.visited[x + move[0] - 1][y + move[1] - 1]) {
                    list.add(k);
                }
            } catch(ArrayIndexOutOfBoundsException ignored) {

            }
        }
        return list;
    }

    //回傳橫坐標
    public int getX() {
        return this.x;
    }

    //回傳緃坐標
    public int getY() {
        return this.y;
    }

    public int getCounter() {
        return this.counter;
    }
}
