import java.awt.*;
import java.sql.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку");
        manager.creationTask(task1);
        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине");
        manager.creationTask(task2);

        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
        manager.creationEpic(epic1);
        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики","Ремонт в квартире");
        manager.creationSubtask(subtask1, epic1.ID);
        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы","Ремонт в квартире");
        manager.creationSubtask(subtask2, epic1.ID);

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
        manager.creationEpic(epic2);
        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить","Ремонт машины");
        manager.creationSubtask(subtask3, epic2.ID);

        System.out.println("Список задач:");
        for (Task task : manager.getTaskList()) {
            System.out.println(task.name + ". " + task.description + ". " + task.status);
        }
        System.out.println("\nСписок подзадач:");
        for (Subtask subtask : manager.getSubtasksList()) {
            System.out.println(subtask.name + ". " + subtask.description + ". " + subtask.status);
        }
        System.out.println("\nСписок эпиков:");
        for (Epic epic : manager.getEpicList()) {
            System.out.println(epic.name + ". " + epic.description + ". " + epic.status);
        }

        manager.updateTask(task1, task1.ID, "IN_PROGRESS");
        manager.updateTask(task2, task2.ID, "DONE");

        manager.updateSubtask(subtask1, subtask1.ID, "DONE", epic1.ID);
        manager.updateSubtask(subtask2, subtask2.ID, "IN_PROGRESS", epic1.ID);
        manager.updateSubtask(subtask3, subtask3.ID, "DONE", epic2.ID);


        manager.updateEpic(epic1, epic1.ID);
        manager.updateEpic(epic2, epic2.ID);

        System.out.println("\nСписок задач:");
        for (Task task : manager.getTaskList()) {
            System.out.println(task.name + ". " + task.description + ". " + task.status);
        }
        System.out.println("\nСписок подзадач:");
        for (Subtask subtask : manager.getSubtasksList()) {
            System.out.println(subtask.name + ". " + subtask.description + ". " + subtask.status);
        }
        System.out.println("\nСписок эпиков:");
        for (Epic epic : manager.getEpicList()) {
            System.out.println(epic.name + ". " + epic.description + " " + epic.status);
        }

        manager.deleteTaskOfId(1);
        manager.deleteEpicOfId(2);

        System.out.println("\nСписок задач");
        for (Task task : manager.getTaskList()) {
            System.out.println(task.name + ". " + task.description + ". " + task.status);
        }
        System.out.println("\nСписок подзадач");
        for (Subtask subtask : manager.getSubtasksList()) {
            System.out.println(subtask.name + ". " + subtask.description + ". " + subtask.status);
        }
        System.out.println("\nСписок эпиков");
        for (Epic epic : manager.getEpicList()) {
            System.out.println(epic.name + ". " + epic.description + " " + epic.status);
        }
    }
}
