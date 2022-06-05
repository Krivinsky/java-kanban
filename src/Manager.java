import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    int idTask;  // генерация идентификаторов +1, когда нужно получить новое значение.
    int idSubtask;
    int idEpic;

    HashMap<Integer, Task> taskMap = new HashMap<>();  //хранить задачи
    HashMap<Integer, Subtask> subtaskMap = new HashMap<>();    //хранить подзадачи
    HashMap<Integer, Epic> epicMap = new HashMap<>();  // хранить эпики

    void proverkaMetod(){ //прост метод для проверки всякого

    }
    //todo попробовать переделать методы (Получение, Удаление, Получение по id) на универ-й через
    //получение класса
    public void getTaskClass(){}


    public ArrayList<Task> getTaskList(){  //Получение списка задач
        ArrayList<Task> taskList = new ArrayList<>();
        for (Integer key: taskMap.keySet()) {
            taskList.add(taskMap.get(key));
        }
        return taskList;
    }
    public ArrayList<Subtask> getSubtasksList(){  //Получение списка подзадач
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Integer key: subtaskMap.keySet()) {
            subtasksList.add(subtaskMap.get(key));
        }
        return subtasksList;
    }
    public ArrayList<Epic> getEpicList(){  //Получение списка эпиков
        ArrayList<Epic> epicList = new ArrayList<>();
        for (Integer key: epicMap.keySet()) {
            epicList.add(epicMap.get(key));
        }
        return epicList;
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
        task.status = "new";
        task.ID = idTask;
        taskMap.put(idTask, task);
        return task;
    }
    public Subtask creationSubtask(Subtask subtask, int idEpic){   //Создание subtask.
        idSubtask++;
        subtask.status = "new";
        subtask.ID = idSubtask;
        subtaskMap.put(idSubtask, subtask);
        subtask.nameOfEpic = epicMap.get(idEpic).name;
        epicMap.get(idEpic).amountOfSubtask.add(subtask);
        return subtask;
    }
    public Epic creationEpic(Epic epic){   //Создание epic.
        idEpic++;
        epic.status = "new";
        epic.ID = idEpic;
        epicMap.put(idEpic, epic);
        return epic;
    }

    public void updateTask(Task task, int id, String status){  //Обновление даных задачи
        task.status = status;
        taskMap.put(id, task);
    }
    public void updateSubtask(Subtask subtask, int id, String status){  //Обновление даных подзадачи
        subtask.status = status;
        subtaskMap.put(id, subtask);
    }
    public void updateEpic(Epic epic, int id){  //Обновление даных эпика
        if (epic.amountOfSubtask.isEmpty()) epic.status = "new";

        for (Subtask subtask : epic.amountOfSubtask) {

        }
        epicMap.put(id, epic);
    }

    public void deleteTaskOfId(int id){    //Удаление задачи по идентификатору
        taskMap.remove(id);
    }
    public void deleteSubtaskOfId(int id){    //Удаление подзадачи по идентификатору
        subtaskMap.remove(id);
    }
    public void deleteEpicOfId(int id){    //Удаление эпика по идентификатору
        epicMap.remove(id);
    }

    public ArrayList<Subtask> getEpicSubtasksList(Epic epic){  //Получение списка всех подзадач определённого эпика
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Subtask subtask : subtaskMap.values()){
            if (subtask.nameOfEpic.equals(epic.name)){
                subtasksList.add(subtask);
            }
        }
        return subtasksList;
    }

    public String statusTask(Task task){   //Управление статусами
        String status = task.status;
        //todo написать этот метод
        return status;
    }
}