package worker.worker;

import java.util.List;

public interface WorkerListener {
    List<WorkerEvent> intrests();

    void onEvent(WorkerEvent event, Object... args);
}
