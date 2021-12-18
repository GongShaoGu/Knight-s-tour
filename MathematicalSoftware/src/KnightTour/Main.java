package KnightTour;


public class Main {
    public static void main(String[] args) {
        Runnable t = Controller::new;
        Thread thread = new Thread(t);
        thread.start();
    }
}
