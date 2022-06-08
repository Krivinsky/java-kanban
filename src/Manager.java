import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Manager {
    int idTask;  // генерация идентификаторов +1, когда нужно получить новое значение.
    int idSubtask;
    int idEpic;

    HashMap<Integer, Task> taskMap = new HashMap<>();  //хранить задачи
    HashMap<Integer, Subtask> subtaskMap = new HashMap<>();    //хранить подзадачи
    HashMap<Integer, Epic> epicMap = new HashMap<>();  // хранить эпики

    Status statusNew = Status.NEW;
    Status statusInProgress = Status.IN_PROGRESS;
    Status statusDone = Status.DONE;


    public Collection<Task> getTaskList(){  //Получение списка задач
        return taskMap.values();
    }
    public Collection<Subtask> getSubtasksList(){  //Получение списка подзадач
        return subtaskMap.values();
    }
    public Collection<Epic> getEpicList(){  //Получение списка эпиков
        return epicMap.values();
    }

    public void cleanTaskList(){    //Удаление всех задач
        taskMap.clear();
    }
    public void cleanSubtaskList(){    //Удаление всех подзадач
        subtaskMap.clear();
    }
    public void cleanEpicList(){    //Удаление всех эпиков
        epicMap.clear();
    }

    public Task getTaskFromId(int id) {    //Получение задачи по идентификатор
        return taskMap.get(id);
    }
    public Task getSubtaskFromId(int id) {    //Получение подзадачи по идентификатор
        return subtaskMap.get(id);
    }
    public Task getEpicFromId(int id) {    //Получение подзадачи по идентификатор
        return epicMap.get(id);
    }

    public Task creationTask(Task task){   //Создание task.
        idTask++;
        Task taskNew = new Task(task.name, task.description);
        taskNew.ID = idTask;
        taskNew.status = statusNew.name;
        taskMap.put(idTask, taskNew);
        return taskNew;
    }
    public Subtask creationSubtask(Subtask subtask, int idEpic){   //Создание subtask.
        idSubtask++;
        Subtask subtaskNew = new Subtask(subtask.name, subtask.description, subtask.idEpic);
        subtaskNew.ID = idSubtask;
        subtaskNew.status = statusNew.name;
        subtaskMap.put(idSubtask, subtaskNew);
        epicMap.get(idEpic).subtasksid.add(subtaskNew.ID);
        return subtaskNew;
    }
    public Epic creationEpic(Epic epic){   //Создание epic.
        idEpic++;
        Epic epicNew = new Epic(epic.name, epic.description);
        epicNew.status = statusNew.name;
        epicNew.ID = idEpic;
        epicMap.put(idEpic, epicNew);
        return epicNew;
    }

    public void updateTask(Task task, int id, Status status){  //Обновление даных задачи
        task.status = status.name;
        taskMap.put(id, task);
    }
    public void updateSubtask(Subtask subtask, int idSubtask, Status status, int idEpic){  //Обновление даных подзадачи
        subtask.status = status.name;
        subtaskMap.put(idSubtask, subtask);
        subtask.idEpic = idEpic;
        epicMap.get(idEpic).amountOfSubtask.add(subtask);
        epicMap.get(idEpic).subtasksid.add(idSubtask);

    }
    public void updateEpic(Epic epic, int id){  //Обновление даных эпика
        int countNew = 0;
        int countDone = 0;
        if (epic.amountOfSubtask.isEmpty()) {
            epic.status = "NEW";
            epicMap.put(id, epic);
            return;
        }
        for (Subtask subtask : epic.amountOfSubtask) {
            if (subtask.status.equals("DONE")) {
                countDone++;
            } else if (subtask.status.equals("NEW")) {
                countNew++;
            }
        }

        if (countNew == epic.amountOfSubtask.size()) {
            epic.status = statusNew.name;
        }else if (countDone == epic.amountOfSubtask.size()) {
            epic.status = statusDone.name;
        } else epic.status = statusInProgress.name;
        epicMap.put(id, epic);
    }

    public void deleteTaskOfId(int id){    //Удаление задачи по идентификатору
        taskMap.remove(id);
    }
    public void deleteSubtaskOfId(int id){    //Удаление подзадачи по идентификатору
        subtaskMap.remove(id);
        for (Epic value : epicMap.values()) {
            value.subtasksid.remove(id);
        }
    }
    public void deleteEpicOfId(int id){    //Удаление эпика по идентификатору
        for (Integer integer : epicMap.get(id).subtasksid) {
            subtaskMap.remove(integer);
        }
        epicMap.remove(id);
    }
    public ArrayList<Subtask> getEpicSubtasksList(Epic epic){  //Получение списка всех подзадач определённого эпика
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Subtask subtask : subtaskMap.values()){
            if (subtask.idEpic == epic.ID){
                subtasksList.add(subtask);
            }
        }
        return subtasksList;
    }
}