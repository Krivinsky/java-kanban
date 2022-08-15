
import managers.HistoryManager;
import managers.Managers;
import managers.file.FileBackedTasksManager;
import managers.memory.InMemoryTaskManager;
import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("csv/file.csv");
  //      FileBackedTasksManager.loadFromFile(file);

      //  TaskManager inMemoryTaskManager = Managers.taskManager;
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку", Type.TASK, LocalDateTime.of(2022,9,1,10, 0), 90);
        inMemoryTaskManager.creationTask(task1);
        inMemoryTaskManager.updateTask(task1, Status.IN_PROGRESS);

        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине", Type.TASK, LocalDateTime.of(2022,9,2,10, 30), 90);
        inMemoryTaskManager.creationTask(task2);
        inMemoryTaskManager.updateTask(task2, Status.DONE);

        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире", Type.EPIC);
        inMemoryTaskManager.creationEpic(epic1);

        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои-клей-валики", epic1.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,4,10, 0), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic1.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.IN_PROGRESS, subtask1.getIdEpic());

        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор и принять работы",epic1.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,5,10, 0), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic1.getId());

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан", Type.EPIC);
        inMemoryTaskManager.creationEpic(epic2);

        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти нужные запчасти и закупить",epic2.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,7,10, 0), 90);
        inMemoryTaskManager.creationSubtask(subtask3, epic2.getId());

        ArrayList<Task> listT = inMemoryTaskManager.getTaskList();
        for (Task t: listT) {
            System.out.println(t);
        }
        System.out.println();

        ArrayList<Subtask> listS = inMemoryTaskManager.getSubtasksList();
        for (Subtask s: listS) {
            System.out.println(s);
        }
        System.out.println();

        ArrayList<Epic> listE = inMemoryTaskManager.getEpicList();
        for (Epic e: listE) {
            System.out.println(e);
        }
        System.out.println();

    }
}
