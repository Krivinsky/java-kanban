package managers;

import managers.memory.InMemoryHistoryManager;
import managers.memory.InMemoryTaskManager;

public final class Managers {

    public static final TaskManager taskManager = new InMemoryTaskManager();

    public static TaskManager getDefault() {
          return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
