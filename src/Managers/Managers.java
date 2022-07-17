package Managers;


public class Managers {

    private static final TaskManager taskManager = new InMemoryTaskManager();
    private static HistoryManager historyManager = new InMemoryHistoryManager();

    public static TaskManager getDefault(){
        return taskManager;
    }

    public static  HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
