package managers.file;

import managers.TaskManager;
import managers.memory.InMemoryTaskManager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    File file = new File("file.csv");

    FileBackedTasksManager(File file) {
        this.file = file;
    }

    public void save() {   //сохранять: Все задачи, подзадачи, эпики и историю просмотра любых задач.
        String heading = "id,type,name,status,description,epic";  // заголовок таблицы

        CSVFormatter.toString(this.inMemoryHistoryManager);
    }

    public static FileBackedTasksManager loadFromFile(File file) throws FileNotFoundException{
        final FileBackedTasksManager tasksManager = new FileBackedTasksManager();
        int generatedId = 0;

        try {
            String csv = Files.readString(file.toPath());
            String[] lines = csv.split(System.lineSeparator());
            //читаемпервую строку
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];

                if (line.isEmpty()) {
                    line = lines[i+1];
                    List<Integer> hystory = CSVFormatter.hystoryFromString(line);
                    break;
                }

                //прочитать задачи
                Task task = CSVFormatter.taskFromString(lines[i]);
                tasksManager.creationTask(task);
                int id = task.getId();
                if (id > generatedId) {
                    generatedId = id;
                }
                //прочитать эпики
                //прочитать сабтаски


            }
        } catch (IOException e) {
            throw new FileNotFoundException("Не могу прочитать файл");
        }
        tasksManager.generateId = generatedId;
        return  tasksManager;
    }

    @Override
    public ArrayList<Task> getTaskList() {
        return null;
    }

    @Override
    public ArrayList<Subtask> getSubtasksList() {
        return null;
    }

    @Override
    public ArrayList<Epic> getEpicList() {
        return null;
    }

    @Override
    public void cleanTaskList() {

    }

    @Override
    public void cleanSubtaskList() {

    }

    @Override
    public void cleanEpicList() {

    }

    @Override
    public Task getTaskFromId(int id) {
        return null;
    }

    @Override
    public Task getSubtaskFromId(int id) {
        return null;
    }

    @Override
    public Task getEpicFromId(int id) {
        return null;
    }

    @Override
    public int creationTask(Task task) {
        Integer id = super.creationTask(task);
        save();
        return id;
    }

    @Override
    public Subtask creationSubtask(Subtask subtask, int idEpic) {
        super.creationSubtask(subtask, idEpic);
        save();
        return subtask;
    }

    @Override
    public Epic creationEpic(Epic epic) {
        super.creationEpic(epic);
        save();
        return epic;
    }

    @Override
    public void updateTask(Task task, int id, Status status) {
        super.updateTask(task, id, status);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask, int idSubtask, Status status, int idEpic) {
        super.updateSubtask(subtask, idSubtask, status, idEpic);
        save();
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        super.updateEpic(epic, id);
        save();
    }

    @Override
    public void deleteTaskOfId(int id) {
        super.deleteTaskOfId(id);
        save();
    }

    @Override
    public void deleteSubtaskOfId(int id) {
        super.deleteSubtaskOfId(id);
        save();
    }

    @Override
    public void deleteEpicOfId(int id) {
        super.deleteEpicOfId(id);
        save();
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasksList(Epic epic) {
        ArrayList<Subtask> subtasksList = super.getEpicSubtasksList(epic);
        save();
        return subtasksList;
    }
}
