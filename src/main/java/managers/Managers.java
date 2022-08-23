package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.memory.InMemoryHistoryManager;
import managers.memory.InMemoryTaskManager;
import managers.memory.InMemoryUserManager;

import java.time.LocalDateTime;

public final class Managers {

    public static final TaskManager taskManager = new InMemoryTaskManager();

    public static TaskManager getDefault() {
          return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static UserManager getDefaultUser() {
        return new InMemoryUserManager();
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        return gsonBuilder.create();
    }
}
