package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exeptions.ManagerSaveException;
import managers.adapters.LocalDateTimeAdapter;
import managers.file.FileBackedTasksManager;
import managers.http.HttpTaskManager;
import managers.memory.InMemoryHistoryManager;
import managers.memory.InMemoryTaskManager;
//import managers.memory.InMemoryUserManager;
import server.HttpTaskServer;
import server.KVServer;

import java.io.IOException;
import java.time.LocalDateTime;

public final class Managers {

    public static final TaskManager taskManager = new InMemoryTaskManager();

    public static final FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager();

    public static TaskManager getDefault() throws ManagerSaveException {
        return taskManager;
    }

    public static HttpTaskServer getDefaultHttpTaskServer() throws ManagerSaveException, IOException {
        return new HttpTaskServer();
    }

    public static InMemoryTaskManager getDefaultInMemoryTas() {
        return new InMemoryTaskManager();
    }

    public static FileBackedTasksManager getDefaultFileBackedTasks() {
        return fileBackedTasksManager;
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

//    public static UserManager getDefaultUser() throws ManagerSaveException {
//        return new InMemoryUserManager();
//    }

    public static KVServer getDefaultKVServer() throws IOException {
        return new KVServer();
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        return gsonBuilder.create();
    }
}
