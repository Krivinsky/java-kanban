
public class Managers {

    public static HistoryManager getDefaultHistory(){

        return null; //  должен возвращать объект InMemoryHistoryManager
    }

    public TaskManager getDefault(){
        TaskManager taskManager = new InMemoryTaskManager();
        return taskManager;
    }
}
