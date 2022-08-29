package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.adapters.LocalDateTimeAdapter;
import managers.file.FileBackedTasksManager;
import managers.http.HttpTaskManager;
import managers.memory.InMemoryHistoryManager;
import managers.memory.InMemoryTaskManager;
import server.KVServer;

import java.io.IOException;
import java.time.LocalDateTime;

public final class Managers {

    public static final TaskManager taskManager = new InMemoryTaskManager();

    public static final FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager();

    public static HttpTaskManager getDefaultHttpTaskManager() {
        return new HttpTaskManager(KVServer.PORT);
    }

    public static TaskManager getDefault() {
        return taskManager;
    }

    public static FileBackedTasksManager getDefaultFileBackedTasks() {
        return fileBackedTasksManager;
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static KVServer getDefaultKVServer() throws IOException {
        return new KVServer();
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        return gsonBuilder.create();
    }
}
