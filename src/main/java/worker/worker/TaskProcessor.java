package worker.worker;

public interface TaskProcessor {
    void process(WorkerTask<?> task);
}
