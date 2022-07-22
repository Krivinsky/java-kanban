
import managers.*;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;


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


        inMemoryTaskManager.getTaskFromId(task1.getId());
        inMemoryTaskManager.getTaskFromId(task2.getId());
        inMemoryTaskManager.getTaskFromId(task2.getId());
        inMemoryTaskManager.getTaskFromId(task2.getId());
        inMemoryTaskManager.getTaskFromId(task2.getId());
        System.out.println("task list = " + inMemoryHistoryManager.getHistory());

    }
}
