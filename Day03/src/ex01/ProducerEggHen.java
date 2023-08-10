package ex01;

import java.util.concurrent.ArrayBlockingQueue;

public class ProducerEggHen implements Runnable {
    private final int COUNT_EGG_HENS;
    private final ArrayBlockingQueue<String> buffer;
    private final String[] values = {"Egg", "Hen"};

    public ProducerEggHen(int countEggHens, ArrayBlockingQueue<String> buffer) {
        this.COUNT_EGG_HENS = countEggHens;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            int index = 0;
            for (int i = 0; i < COUNT_EGG_HENS; ++i) {
                produce(index);
                ++index;
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void produce(int index) throws InterruptedException {
        String value = values[index % 2];
        buffer.put(value);
    }
}
