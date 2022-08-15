package managers.memory;

import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class InMemoryTaskManager implements TaskManager {

    protected static int generateId = 0;

    protected HashMap<Integer, Task> taskMap = new HashMap<>();  //хранить задачи
    protected HashMap<Integer, Subtask> subtaskMap = new HashMap<>();    //хранить подзадачи
    protected HashMap<Integer, Epic> epicMap = new HashMap<>();  // хранить эпики
    protected TreeSet<Task> prioritizedTasks = new TreeSet<>(Comparator.comparing(Task::getStartTime));

    protected HashMap<Integer, Task> AllTypesOfTasksMap = new HashMap<>(); //хранит все типы задач

    public HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

    public static int generateId() {                                                                                    //добавил статик
        return ++generateId;
    }

    public ArrayList<Task> getTaskList(){  //Получение списка задач
        return new ArrayList<>(taskMap.values());
    }
    public ArrayList<Subtask> getSubtasksList(){  //Получение списка подзадач
        return new ArrayList<>(subtaskMap.values());
    }
    public ArrayList<Epic> getEpicList(){  //Получение списка эпиков
        return new ArrayList<>(epicMap.values());
    }

    @Override
    public ArrayList<Task> getAllTypesOfTasks() {
        ArrayList<Task> list = new ArrayList<>();
        return null;
    }

    @Override
    public ArrayList<Task> getPrioritizedTasks() {
        return new ArrayList<>(prioritizedTasks);
    }

    public void validate(Task task) {
        if (prioritizedTasks.isEmpty()) {
            return;
        }
        for (Task taskValidate: prioritizedTasks) {

        }
    }

    public void cleanTaskList() {    //Удаление всех задач
        for (Task task: taskMap.values()) {
            this.inMemoryHistoryManager.remove(task.getId());
        }
        taskMap.clear();
    }
    public void cleanSubtaskList(){    //Удаление всех подзадач
        for (Subtask subtask:subtaskMap.values()){
            this.inMemoryHistoryManager.remove(subtask.getId());
        }
        subtaskMap.clear();
    }
    public void cleanEpicList(){    //Удаление всех эпиков
        cleanSubtaskList();
        for (Epic epic: epicMap.values()) {
            this.inMemoryHistoryManager.remove(epic.getId());
        }
        epicMap.clear();
    }

    @Override
    public void cleanAllTypesOfTasksList() {

    }

    public Task getTaskFromId(int id) {    //Получение задачи по идентификатор
        if (checkId(id)) {
            inMemoryHistoryManager.addTask(taskMap.get(id));
            return taskMap.get(id);
        }
        return null;
    }
    public Subtask getSubtaskFromId(int id) {    //Получение подзадачи по идентификатор
        if (checkId(id)) {
            inMemoryHistoryManager.addTask(subtaskMap.get(id));
            return subtaskMap.get(id);
        }
        return null;
    }
    public Epic getEpicFromId(int id) {    //Получение подзадачи по идентификатор
        if (checkId(id)) {
            inMemoryHistoryManager.addTask(epicMap.get(id));
            return epicMap.get(id);
        }
        return null;
    }

    @Override
    public Task getAllTypesOfTasks(int id) {
        return null;
    }

    public Task creationTask(Task task){   //Создание task
        if (task != null) {
            task.setId(generateId());
            task.setStatus(Status.NEW);
            //validation
            taskMap.put(task.getId(), task);
            prioritizedTasks.add(task);
            return task;
        }
        return null;
    }
    public Subtask creationSubtask(Subtask subtask, int epicId){   //Создание subtask.
        if (subtask != null && checkId(epicId)) {
            subtask.setId(generateId());
            subtask.setStatus(Status.NEW);
            //validation
            subtaskMap.put(subtask.getId(), subtask);
            epicMap.get(epicId).addSubtasksId(subtask.getId());
            updateEpicDurationAndStarTime(epicMap.get(epicId));   // ---------?????
            prioritizedTasks.add(subtask);
            return subtask;
        }
        return null;
    }
    public Epic creationEpic(Epic epic){   //Создание epic.
        if (epic != null) {
            epic.setId(generateId());
            epic.setStatus(Status.NEW);
            updateEpicDurationAndStarTime(epic);
            //validation -- ??
            epicMap.put(epic.getId(), epic);
            return epic;
        }
        return null;
    }

    @Override
    public Task creationAllTypesOfTasks() {
        return null;
    }

    public void updateTask(Task task, Status status){  //Обновление данных задачи
        if (task != null) {
            task.setStatus(status);
            taskMap.put(task.getId(), task);
        }
    }
    public void updateSubtask(Subtask subtask, Status status, int idEpic){  //Обновление данных подзадачи
        if (subtask != null) {
            int id = subtask.getId();
            subtask.setStatus(status);
            subtaskMap.put(id, subtask);
            subtask.setIdEpic(idEpic);
  //          epicMap.get(idEpic).getSubtasksId().add(id);
            updateEpic(epicMap.get(idEpic));
            updateEpicDurationAndStarTime(epicMap.get(idEpic));
        }
    }
    public void updateEpic(Epic epic){  //Обновление данных эпика
        if (epic != null) {
            int countNew = 0;
            int countDone = 0;
            if (epic.getSubtasksId().isEmpty()) {
                epic.setStatus(Status.NEW);
                epicMap.put(epic.getId(), epic);
                return;
            }
            for (Integer integer : epic.getSubtasksId()) {
                if (subtaskMap.get(integer).getStatus() == Status.DONE) {
                    countDone++;
                } else if (subtaskMap.get(integer).getStatus() == Status.NEW) {
                    countNew++;
                }
            }
            if (countNew == epic.getSubtasksId().size()) {
                epic.setStatus(Status.NEW);
            } else if (countDone == epic.getSubtasksId().size()) {
                epic.setStatus(Status.DONE);
            } else epic.setStatus(Status.IN_PROGRESS);
            epicMap.put(epic.getId(), epic);
        }
    }

    public void updateEpicDurationAndStarTime(Epic epic) {
        //todo продолжительность Эпика
        LocalDateTime startEpic = LocalDateTime.MAX;
        LocalDateTime endEpic = LocalDateTime.MIN;
        long durationEpic = 0L;
        ArrayList<Integer> subtasksId = epic.getSubtasksId();
        if (subtasksId.isEmpty()) {
            epic.setDuration(0L);
            return;
        }

        for (int i : subtasksId) {
            Subtask subtask = subtaskMap.get(i);
            LocalDateTime startTime = subtask.getStartTime();
            LocalDateTime endTime = subtask.getEndTime();
            if (startTime.isBefore(startEpic)) {
                startEpic = startTime;
            }
            if (endEpic.isAfter(endTime)) {
                endEpic = endTime;
            }
            durationEpic = durationEpic + subtask.getDuration();
        }
        epic.setStartTime(startEpic);
        epic.setEndTime(endEpic);
        epic.setDuration(durationEpic);
    }

    @Override
    public void updateAllTypesOfTasks() {

    }

    public void deleteTaskOfId(int id) {    //Удаление задачи по идентификатору
        if (checkId(id)) {
            taskMap.remove(id);
            inMemoryHistoryManager.remove(id);
        }
    }
    public void deleteSubtaskOfId(int id) {    //Удаление подзадачи по идентификатору
        if (checkId(id)) {
        Subtask subtask = subtaskMap.get(id);
        Epic epic = epicMap.get(subtask.getIdEpic());
        epic.removeIdFromSubtasksIdList(id);
        subtaskMap.remove(id);
        }
    }
    public void deleteEpicOfId(int id) {    //Удаление эпика по идентификатору
        if (checkId(id)) {
            for (Integer integer : epicMap.get(id).getSubtasksId()) {
                subtaskMap.remove(integer);
            }
            epicMap.remove(id);
        }
    }

    @Override
    public void deleteAllTypesOfTasks() {

    }

/*
*   Из технического задания спринт 3:
*   3. Дополнительные методы:
*      3.1 Получение списка всех подзадач определённого эпика.
 */
    public ArrayList<Subtask> getEpicSubtasksList(Epic epic){  //Получение списка всех подзадач определённого эпика
        if (epic != null) {
            ArrayList<Subtask> subtasksList = new ArrayList<>();
            for (Subtask subtask : subtaskMap.values()) {
                if (subtask.getIdEpic() == epic.getId()) {
                    subtasksList.add(subtask);
                }
            }
            return subtasksList;
        }
        return null;
    }

    protected boolean checkId (int id) {
       return 0 < id && id <= generateId;
    }
}