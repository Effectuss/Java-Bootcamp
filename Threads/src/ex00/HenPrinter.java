package ex00;

public class HenPrinter implements Runnable {
    private final int COUNT_HENS;

    public HenPrinter(int countHens) {
        this.COUNT_HENS = countHens;
    }

    @Override
    public void run() {
        for (int i = 0; i < COUNT_HENS; ++i) {
            System.out.println("Hen");
        }
    }
}
