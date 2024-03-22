package ex01;

public class ArgumentOrchestrator {
    private boolean isEggTurn = true;

    public synchronized void egg() throws InterruptedException {
        while (!isEggTurn) {
            wait();
        }

        System.out.println("Egg");
        isEggTurn = false;
        notify();
    }

    public synchronized void hen() throws InterruptedException {
        while (isEggTurn) {
            wait();
        }

        System.out.println("Hen");
        isEggTurn = true;
        notify();
    }
}