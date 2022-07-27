package managers;

import managers.memory.InMemoryHistoryManager;
import managers.memory.InMemoryTaskManager;

public class Managers {

    public static final HistoryManager historyManager = new InMemoryHistoryManager();

    public static final TaskManager taskManager = new InMemoryTaskManager();

    public static  HistoryManager getDefaultHistory() {
        return historyManager;
    }

    public static TaskManager getDefault() {
        return taskManager;
    }
}
