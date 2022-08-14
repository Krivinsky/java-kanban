package managers;

import managers.file.FileBackedTasksManager;
import managers.memory.InMemoryHistoryManager;
import managers.memory.InMemoryTaskManager;

public final class Managers {
//    public static final HistoryManager historyManager = new InMemoryHistoryManager();
    public static final TaskManager taskManager = new InMemoryTaskManager();
//    public static  HistoryManager getDefaultHistory() {
//        return historyManager;
//    }
//    public static TaskManager getDefault() {
//        return taskManager;
//    }

    public static TaskManager getDefault() {
    //    return new FileBackedTasksManager();
        return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
