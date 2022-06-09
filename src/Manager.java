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


    public ArrayList<Task> getTaskList(){  //Получение списка задач
        return new ArrayList<>(taskMap.values());
    }
    public ArrayList<Subtask> getSubtasksList(){  //Получение списка подзадач
        return new ArrayList<>(subtaskMap.values());
    }
    public ArrayList<Epic> getEpicList(){  //Получение списка эпиков
        return new ArrayList<>(epicMap.values());
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
        task.ID = idTask;
        task.status = statusNew.name;
        taskMap.put(idTask, task);
        return task;
    }
    public Subtask creationSubtask(Subtask subtask, int idEpic){   //Создание subtask.
        idSubtask++;
        subtask.ID = idSubtask;
        subtask.status = statusNew.name;
        subtaskMap.put(idSubtask, subtask);
        epicMap.get(idEpic).subtasksid.add(subtask.ID);
        return subtask;
    }
    public Epic creationEpic(Epic epic){   //Создание epic.
        idEpic++;
        epic.status = statusNew.name;
        epic.ID = idEpic;
        epicMap.put(idEpic, epic);
        return epic;
    }

    public void updateTask(Task task, int id, Status status){  //Обновление даных задачи
        task.status = status.name;
        taskMap.put(id, task);
    }
    public void updateSubtask(Subtask subtask, int idSubtask, Status status, int idEpic){  //Обновление даных подзадачи
        subtask.status = status.name;
        subtaskMap.put(idSubtask, subtask);
        subtask.idEpic = idEpic;
        epicMap.get(idEpic).subtasksid.add(idSubtask);
    }
    public void updateEpic(Epic epic, int id){  //Обновление даных эпика
        int countNew = 0;
        int countDone = 0;
        if (epic.subtasksid.isEmpty()) {
            epic.status = "NEW";
            epicMap.put(id, epic);
            return;
        }
        for (Integer integer : epic.subtasksid) {
            if (subtaskMap.get(integer).status.equals("DONE")) {
                countDone++;
            } else if (subtaskMap.get(integer).status.equals("NEW")) {
                countNew++;
            }
        }
        if (countNew == epic.subtasksid.size()) {
            epic.status = statusNew.name;
        }else if (countDone == epic.subtasksid.size()) {
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