package ex01;

public class HenThread implements Runnable {
    private final ArgumentOrchestrator orchestrator;

    public HenThread(ArgumentOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < Program.count; i++) {
                orchestrator.hen();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}