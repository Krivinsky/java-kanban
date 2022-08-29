package managers.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import managers.Managers;
import managers.client.KVTaskClient;
import managers.file.FileBackedTasksManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class HttpTaskManager extends FileBackedTasksManager {

    private final KVTaskClient client;
    private final Gson gson;

    public HttpTaskManager(int port) {
        gson = Managers.getGson();
        client = new KVTaskClient(port);
    }

    public void load() {    //переопределяем метод FileBackedTasksManager loadFromFile
        Type tasksType = new TypeToken<ArrayList<Task>>() {
        }.getType();

        ArrayList<Task> tasks = gson.fromJson(client.load("tasks"), tasksType);
        if (Objects.nonNull(tasks)) {
            tasks.forEach(task -> {
                int id = task.getId();
                this.taskMap.put(id, task);
                this.prioritizedTasks.add(task);
                if (id > generateId) {
                    generateId = id;
                }
            });
        }

        Type subtasksType = new TypeToken<ArrayList<Subtask>>() {
        }.getType();
        ArrayList<Subtask> subtasks = gson.fromJson(client.load("subtasks"), subtasksType);
        if (Objects.nonNull(subtasks)) {
            subtasks.forEach(subtask -> {
                int id = subtask.getId();
                this.subtaskMap.put(id, subtask);
                this.prioritizedTasks.add(subtask);
                if (id > generateId) {
                    generateId = id;
                }
            });
        }

        Type epicsType = new TypeToken<ArrayList<Epic>>() {
        }.getType();
        ArrayList<Epic> epics = gson.fromJson(client.load("epics"), epicsType);
        if (Objects.nonNull(epics)) {
            epics.forEach(epic -> {
                int id = epic.getId();
                this.epicMap.put(id, epic);
                //this.prioritizedTasks.add(epic);
                if (id > generateId) {
                    generateId = id;
                }
            });
        }

        Type historyType = new TypeToken<ArrayList<Task>>(){
        }.getType();
        ArrayList<Task> history = gson.fromJson(client.load("history"), historyType);

        if (Objects.nonNull(history)) {
            for (Task task:history) {
                inMemoryHistoryManager.addTask(this.findTask(task.getId()));
            }
        }
    }



    public void save() {       //переопределяем метод FileBackedTasksManager saveToFile

        String tasksJson = gson.toJson(getTaskList());
        client.put("tasks", tasksJson);

        String epicsJson = gson.toJson(getEpicList());
        client.put("epics", epicsJson);

        String subtasksJson = gson.toJson(getSubtasksList());
        client.put("subtasks" , subtasksJson);

        String historyJson = gson.toJson(getSubtasksList());
        client.put("history" , historyJson);
    }

    protected Task findTask(Integer id) {
        final Task task = taskMap.get(id);
        if (task != null) {
            return task;
        }
        final Subtask subtask = subtaskMap.get(id);
        if (subtask != null) {
            return subtask;
        }
        return epicMap.get(id);
    }
}
