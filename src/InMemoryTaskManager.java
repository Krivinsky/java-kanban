import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager{
    //todo переделать на сплошную нумерацию
    int idTask;  // генерация идентификаторов +1, когда нужно получить новое значение.
    int idSubtask;
    int idEpic;

    HashMap<Integer, Task> taskMap = new HashMap<>();  //хранить задачи
    HashMap<Integer, Subtask> subtaskMap = new HashMap<>();    //хранить подзадачи
    HashMap<Integer, Epic> epicMap = new HashMap<>();  // хранить эпики

    Status statusNew = Status.NEW;
    Status statusInProgress = Status.IN_PROGRESS;
    Status statusDone = Status.DONE;

    //todo переделать все методы на универсальные (1 метод на три типа задач)
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
        task.status = Status.NEW;
        taskMap.put(idTask, task);
        return task;
    }
    public Subtask creationSubtask(Subtask subtask, int idEpic){   //Создание subtask.
        idSubtask++;
        subtask.ID = idSubtask;
        subtask.status = Status.NEW;
        subtaskMap.put(idSubtask, subtask);
        epicMap.get(idEpic).subtasksid.add(subtask.ID);
        return subtask;
    }
    public Epic creationEpic(Epic epic){   //Создание epic.
        idEpic++;
        epic.status = Status.NEW;
        epic.ID = idEpic;
        epicMap.put(idEpic, epic);
        return epic;
    }

    public void updateTask(Task task, int id, Status status){  //Обновление даных задачи
        task.status = status;
        taskMap.put(id, task);
    }
    public void updateSubtask(Subtask subtask, int idSubtask, Status status, int idEpic){  //Обновление даных подзадачи
        subtask.status = status;
        subtaskMap.put(idSubtask, subtask);
        subtask.idEpic = idEpic;
        epicMap.get(idEpic).subtasksid.add(idSubtask);
    }
    public void updateEpic(Epic epic, int id){  //Обновление даных эпика
        int countNew = 0;
        int countINprogress = 0;
        int countDone = 0;
        if (epic.subtasksid.isEmpty()) {
            epic.status = Status.NEW;
            epicMap.put(id, epic);
            return;
        }
        for (Integer integer : epic.subtasksid) {
            //todo переделать строчку ниже DONE
            if (subtaskMap.get(integer).status.equals("DONE")) {
                countDone++;
                //todo переделать строчку ниже NEW
            } else if (subtaskMap.get(integer).status.equals("NEW")) {
                countNew++;
            }
        }
        if (countNew == epic.subtasksid.size()) {
            epic.status = Status.NEW;
            //todo проверить строчку ниже size
        }else if (countDone == epic.subtasksid.size()) {
            epic.status = Status.DONE;
        } else epic.status = Status.IN_PROGRESS;
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