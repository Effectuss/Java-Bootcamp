package ex01;

public class EggThread extends Thread {
    private final ArgumentOrchestrator orchestrator;

    public EggThread(ArgumentOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < Program.count; i++) {
                orchestrator.egg();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
