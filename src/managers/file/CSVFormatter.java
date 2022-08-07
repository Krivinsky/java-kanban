package managers.file;

import managers.HistoryManager;
import tasks.*;

import java.util.ArrayList;
import java.util.List;

public class CSVFormatter {
    public static List<Integer> historyFromString(String value) {  //восстановления менеджера истории из CSV
        List<Integer> result = new ArrayList<>();
        String[] lines = value.split(",");
        for (String line : lines) {
            result.add(Integer.parseInt(line));
        }
        return result;
    }

    public static Task taskFromString(String value) {  //метод создания задачи из строки
        String[] lines = value.split(",");
        int id = Integer.parseInt(lines[0]);
        Type type =  typeFromString(lines[1]);
        String name = lines[2];
        Status status = statusFromString(lines[3]);
        String description = lines[4];

        Task task = new Task(name, description, type);
        task.setId(id);
        task.setStatus(status);
        return task;
    }

    public static Epic epicFromString (String value) {
        String[] lines = value.split(",");
        int id = Integer.parseInt(lines[0]);
        Type type =  typeFromString(lines[1]);
        String name = lines[2];
        Status status = statusFromString(lines[3]);
        String description = lines[4];

        Epic epic = new Epic(name, description, type);
        epic.setId(id);
        epic.setStatus(status);
        return epic;
    }

    public static Subtask subtaskFromString (String value) {
        String[] lines = value.split(",");
        int id = Integer.parseInt(lines[0]);
        Type type =  typeFromString(lines[1]);
        String name = lines[2];
        Status status = statusFromString(lines[3]);
        String description = lines[4];
        int epic =Integer.parseInt(lines[5]);

        Subtask subtask = new Subtask(name, description, epic, type);
        subtask.setId(id);
        subtask.setStatus(status);
        return subtask;
    }

    static String toString(HistoryManager manager) {
        List<Task> tasks = manager.getHistory();
        List<String> ids = new ArrayList<>();
        for (Task task: tasks) {
            int id = task.getId();
            ids.add(String.valueOf(id));
        }
        return String.join(",", ids);
    }

    public static Type typeFromString (String string) {
        switch (string) {
            case "TASK":
                return Type.TASK;
            case "EPIC":
                return Type.EPIC;
            case "SUBTASK":
                return Type.SUBTASK;
        }
        return null;
    }

    public static Status statusFromString (String string) {
        switch (string) {
            case "NEW":
                return Status.NEW;
            case "IN_PROGRESS":
                return Status.IN_PROGRESS;
            case "DONE":
                return Status.DONE;
        }
        return null;
    }
}
