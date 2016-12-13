package pizzeria;

public class Simulation extends Thread {

    public Simulation(){
        run();
    }
    private int threadCount = 0;

    private void printErgebnis(){
        System.out.println(threadCount);
    }

    public void run() {
        System.out.println("Berechne...");
        for (int i = 0; i < 25; i++) {
            threadCount++;
        }
        printErgebnis();
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Ich starte nun einen Thread");
    }

}