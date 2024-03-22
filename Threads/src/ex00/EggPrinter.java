package ex00;

public class EggPrinter implements Runnable {
    private final int COUNT_EGGS;

    public EggPrinter(int countEggs) {
        this.COUNT_EGGS = countEggs;
    }

    @Override
    public void run() {
        for (int i = 0; i < COUNT_EGGS; ++i) {
            System.out.println("Egg");
        }
    }
}
