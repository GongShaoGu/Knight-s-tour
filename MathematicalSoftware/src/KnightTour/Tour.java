package KnightTour;

import java.util.Random;

public class Tour {
    private int[][] trace = new int[64][2];
    private int counter;
    public enum Type {
        NORMAL, RANDOM;
    }

    public synchronized void start(Type type) {
        if(type == Type.NORMAL) {
            Knight knight = new Knight();
            this.trace[knight.getCounter() - 1][0] = knight.getX();
            this.trace[knight.getCounter() - 1][1] = knight.getY();
            while(knight.getCounter() < 64) {
                if(knight.move()) {
                    this.trace[knight.getCounter() - 1][0] = knight.getX();
                    this.trace[knight.getCounter() - 1][1] = knight.getY();
                } else {
                    break;
                }
            }
            this.counter = knight.getCounter();
        } else if(type == Type.RANDOM){
            Random random = new Random();
            int x = random.nextInt(8) + 1;
            int y = random.nextInt(8) + 1;
            Knight knight = new Knight(x, y);
            this.trace[knight.getCounter() - 1][0] = knight.getX();
            this.trace[knight.getCounter() - 1][1] = knight.getY();
            while(knight.getCounter() < 64) {
                if(knight.randomMove()) {
                    this.trace[knight.getCounter() - 1][0] = knight.getX();
                    this.trace[knight.getCounter() - 1][1] = knight.getY();
                } else {
                    break;
                }
            }
            this.counter = knight.getCounter();
        }
        run();
    }

    public void run() {

    }

    public int[][] getTrace() {
        return this.trace;
    }

    public int getCounter() {
        return this.counter;
    }
}
