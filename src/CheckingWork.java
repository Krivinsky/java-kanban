
import Managers.*;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;


public class CheckingWork {
    public void check(){

        TaskManager inMemoryTaskManager = Managers.taskManager;

        HistoryManager inMemoryHistoryManager = Managers.historyManager;

        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку");
        inMemoryTaskManager.creationTask(task1);


        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине");
        inMemoryTaskManager.creationTask(task2);

        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
        inMemoryTaskManager.creationEpic(epic1);

        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики",1);
        inMemoryTaskManager.creationSubtask(subtask1, epic1.getId());

        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы",1);
        inMemoryTaskManager.creationSubtask(subtask2, epic1.getId());

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
        inMemoryTaskManager.creationEpic(epic2);

        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить",2);
        inMemoryTaskManager.creationSubtask(subtask3, epic2.getId());

        System.out.println("Список Задач:");
        for (Task task : inMemoryTaskManager.getTaskList()) {
            System.out.println(task.getName());
        }
        System.out.println();

        System.out.println("Список Эпиков:");
        for (Epic epic : inMemoryTaskManager.getEpicList()) {
            System.out.println(epic.getName());
        }
        System.out.println();

        System.out.println("Список Подзадач:");
        for (Subtask subtask : inMemoryTaskManager.getSubtasksList()) {
            System.out.println(subtask.getName());
        }
        System.out.println();

//   п. 2.3   Получение по идентификатору
        inMemoryTaskManager.getTaskFromId(task1.getId());
        inMemoryTaskManager.getTaskFromId(task2.getId());
        System.out.println("task list = " + inMemoryHistoryManager.getHistory());


        inMemoryTaskManager.getSubtaskFromId(subtask1.getId());
        inMemoryTaskManager.getSubtaskFromId(subtask2.getId());
        inMemoryTaskManager.getSubtaskFromId(subtask3.getId());
        System.out.println("task list = " + inMemoryHistoryManager.getHistory());



        inMemoryTaskManager.getEpicFromId(epic1.getId());
        inMemoryTaskManager.getEpicFromId(epic2.getId());
        System.out.println("task list = " + inMemoryHistoryManager.getHistory());

        inMemoryTaskManager.updateSubtask(subtask1, subtask1.getId(), Status.DONE, epic1.getId());
        inMemoryTaskManager.updateSubtask(subtask2, subtask2.getId(), Status.DONE, epic1.getId());

        inMemoryTaskManager.updateEpic(epic1, epic1.getId());
        System.out.println(epic1.getName() + "  " + epic1.getDescription()  + "  " +  epic1.getId() + "  " + epic1.getStatus());

//        Managers.InMemoryTaskManager inMemoryTaskManager = new Managers.InMemoryTaskManager();
//
//        Tasks.Epic epic1 = new Tasks.Epic("Ремонт в квартире", "Ремонт в своей квартире");
//        inMemoryTaskManager.creationEpic(epic1);
//        Tasks.Subtask subtask1 = new Tasks.Subtask("Закупить стройматериалы", "Закупить обои, клей, валики",1);
//        inMemoryTaskManager.creationSubtask(subtask1, epic1.id);
//        Tasks.Subtask subtask2 = new Tasks.Subtask("Нанять рабочих", "Заключить договор, Принять работы",1);
//        inMemoryTaskManager.creationSubtask(subtask2, epic1.id);
//
//        Tasks.Epic epic2 = new Tasks.Epic("Ремонт машины", "Ремонт Ниссан");
//        inMemoryTaskManager.creationEpic(epic2);
//        Tasks.Subtask subtask3 = new Tasks.Subtask("Закупить запчасти", "Найти ужные запчасти и закупить",2);
//        inMemoryTaskManager.creationSubtask(subtask3, epic2.id);
//
//        System.out.println("Список подзадач:");
//        for (Tasks.Task task : inMemoryTaskManager.getSubtasksList()) {
//            System.out.println(task.id+". "+task.name + ". " + task.description + ". " + task.status);
//        }
//
//        inMemoryTaskManager.deleteEpicOfId(1);
//
//        System.out.println("Список подзадач :");
//        for (Tasks.Task task : inMemoryTaskManager.getSubtasksList()) {
//            System.out.println(task.id+". "+task.name + ". " + task.description + ". " + task.status);
//        }

        System.out.println("task list = " + inMemoryHistoryManager.getHistory());


    }
}
