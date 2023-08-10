package ex01;

import java.util.concurrent.ArrayBlockingQueue;

public class ConsumerEggHen implements Runnable {
    private final int COUNT_EGGS;
    private final ArrayBlockingQueue<String> buffer;

    public ConsumerEggHen(int countEggs, ArrayBlockingQueue<String> buffer) {
        this.COUNT_EGGS = countEggs;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < COUNT_EGGS; ++i) {
                consumeEggs();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void consumeEggs() throws InterruptedException {
        String value = buffer.take();
        System.out.println(value);
    }
}
