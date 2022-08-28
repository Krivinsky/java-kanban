package managers.file;

import exeptions.ManagerSaveException;
import managers.memory.InMemoryTaskManager;
import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {

    public static void main(String[] args) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager();
        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку", TypeTask.TASK, LocalDateTime.of(2022,9,1,10, 0), 90);
        fileBackedTasksManager.creationTask(task1);
        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине", TypeTask.TASK, LocalDateTime.of(2022,9,2,10, 0), 90);
        fileBackedTasksManager.creationTask(task2);
        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире", TypeTask.EPIC);
        fileBackedTasksManager.creationEpic(epic1);
        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить стройматериалы",1, TypeTask.SUBTASK, LocalDateTime.of(2022,9,3,10, 0), 90);
        fileBackedTasksManager.creationSubtask(subtask1, epic1.getId());
        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор",1, TypeTask.SUBTASK, LocalDateTime.of(2022,9,4,10, 0), 90);
        fileBackedTasksManager.creationSubtask(subtask2, epic1.getId());
        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан", TypeTask.EPIC);
        fileBackedTasksManager.creationEpic(epic2);
        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти нужные запчасти",2, TypeTask.SUBTASK, LocalDateTime.of(2022,9,5,10, 0), 90);
        fileBackedTasksManager.creationSubtask(subtask3, epic2.getId());
        fileBackedTasksManager.getTaskFromId(task2.getId());
        fileBackedTasksManager.getTaskFromId(task1.getId());
        fileBackedTasksManager.getEpicFromId(epic2.getId());
        fileBackedTasksManager.getEpicFromId(epic1.getId());
    }
                                                            //сохранять текущее состояние менеджера в указанный файл.
    public void saveToFile() throws ManagerSaveException {     //сохранять: Все задачи, подзадачи, эпики и историю просмотра любых задач.
        File file = new File ("csv/file.csv");
        try {
            String heading = "id,type,name,status,description,startTime,duration,endTime,epic, " + System.lineSeparator();  // заголовок таблицы
            FileWriter fr = new FileWriter(file);
            fr.write(heading);

            for (Task task : taskMap.values()) {
                fr.write(task.toString() + System.lineSeparator());
            }
            for (Epic epic : epicMap.values()) {
                fr.write(epic.toString() + System.lineSeparator());
            }
            for (Subtask subtask : subtaskMap.values()) {
                fr.write(subtask.toString() + System.lineSeparator());
            }
            fr.write(System.lineSeparator());
            fr.write(CSVFormatter.toString(this.inMemoryHistoryManager));
            fr.close();
        } catch (IOException e) {
            throw new ManagerSaveException("файл не сохранен");
            }
    }

    public static FileBackedTasksManager loadFromFile(File file) throws FileNotFoundException{
        final FileBackedTasksManager tasksManager = new FileBackedTasksManager();
        List<Integer> history = null;
        try {
            String csv = Files.readString(file.toPath());
            String[] lines = csv.split(System.lineSeparator());
            String heading = lines[0]; //читаем первую строку
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                if (line.isEmpty()) {
                    line = lines[i+1];
                    history = CSVFormatter.historyFromString(line);
                    break;
                }
                String[] type = line.split(",");
                String typeTask = type[1];
                switch (typeTask) {
                    case "TASK":
                        Task task = CSVFormatter.taskFromString(lines[i]);
                        tasksManager.taskMap.put(task.getId(), task);
                        break;
                    case "EPIC":
                        Epic epic = CSVFormatter.epicFromString(lines[i]);
                        tasksManager.epicMap.put(epic.getId(),epic);
                        break;
                    case "SUBTASK":
                        Subtask subtask = CSVFormatter.subtaskFromString(lines[i]);
                        tasksManager.subtaskMap.put(subtask.getId(), subtask);
                        break;
                }
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Не могу прочитать файл");
        }
        //вывод только для проверки работы
        System.out.println(tasksManager.inMemoryHistoryManager.getHistory());
        System.out.println(tasksManager.taskMap);
        System.out.println(tasksManager.subtaskMap);
        System.out.println(tasksManager.epicMap);
        System.out.println(history);
        return tasksManager;
    }

    @Override
    public ArrayList<Task> getTaskList() {
        return super.getTaskList();
    }

    @Override
    public ArrayList<Subtask> getSubtasksList() {
        return super.getSubtasksList();
    }

    @Override
    public ArrayList<Epic> getEpicList() {
        return super.getEpicList();
    }

    @Override
    public void cleanTaskList() {
        super.cleanTaskList();
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanSubtaskList() {
        super.cleanSubtaskList();
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanEpicList() {
        super.cleanEpicList();
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task getTaskFromId(int id) {
        Task task = super.getTaskFromId(id);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Subtask getSubtaskFromId(int id) {
        Subtask subtask = super.getSubtaskFromId(id);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return subtask;
    }

    @Override
    public Epic getEpicFromId(int id) {
        Epic epic = super.getEpicFromId(id);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return epic;
    }

    @Override
    public Task creationTask(Task task) {
        super.creationTask(task);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Subtask creationSubtask(Subtask subtask, int epicId) {
        if (subtask != null && super.checkId(epicId)) {
            super.creationSubtask(subtask, epicId);
            try {
                saveToFile();
            } catch (ManagerSaveException e) {
                e.printStackTrace();
            }
            return subtask;
        }
        return null;
    }

    @Override
    public Epic creationEpic(Epic epic) {
        super.creationEpic(epic);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return epic;
    }

    @Override
    public void updateTask(Task task, Status status) {
        super.updateTask(task, status);     // валидация реализована в InMemoryTaskManager
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubtask(Subtask subtask, Status status, int idEpic) {
        super.updateSubtask(subtask,  status, idEpic);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTaskOfId(int id) {
        super.deleteTaskOfId(id);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubtaskOfId(int id) {
        super.deleteSubtaskOfId(id);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEpicOfId(int id) {
        super.deleteEpicOfId(id);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasksList(Epic epic) {
        ArrayList<Subtask> subtasksList = super.getEpicSubtasksList(epic);
        try {
            saveToFile();
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        return subtasksList;
    }
}
