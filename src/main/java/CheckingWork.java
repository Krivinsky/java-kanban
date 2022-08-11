
import managers.*;
import tasks.*;


public class CheckingWork {
    public void check(){

        TaskManager inMemoryTaskManager = Managers.taskManager;

        HistoryManager inMemoryHistoryManager = Managers.historyManager;

        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку", Type.TASK);
        inMemoryTaskManager.creationTask(task1);

        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине", Type.TASK);
        inMemoryTaskManager.creationTask(task2);

        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире", Type.EPIC);
        inMemoryTaskManager.creationEpic(epic1);

        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои-клей-валики", epic1.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask1, epic1.getId());

        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор и принять работы",epic1.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask2, epic1.getId());

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан", Type.EPIC);
        inMemoryTaskManager.creationEpic(epic2);

        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти нужные запчасти и закупить",epic2.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask3, epic2.getId());

        inMemoryTaskManager.updateSubtask(subtask1, Status.IN_PROGRESS, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask1, Status.IN_PROGRESS, subtask1.getIdEpic());

        System.out.println(epic1.getStatus());

    }
}
