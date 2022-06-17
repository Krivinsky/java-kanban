public class Main {

    public static void main(String[] args) {
//        InMemoryTaskManager taskManager = new InMemoryTaskManager();
//
//        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку");
//        taskManager.creationTask(task1);
//        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине");
//        taskManager.creationTask(task2);
//
//        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
//        taskManager.creationEpic(epic1);
//        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики",1);
//        taskManager.creationSubtask(subtask1, epic1.ID);
//        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы",1);
//        taskManager.creationSubtask(subtask2, epic1.ID);
//
//        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
//        taskManager.creationEpic(epic2);
//        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить",2);
//        taskManager.creationSubtask(subtask3, epic2.ID);
//
//        System.out.println("Список задач:");
//        for (Task task : taskManager.getTaskList()) {
//            System.out.println(task.name + ". " + task.description + ". " + task.status);
//        }
//        System.out.println("\nСписок подзадач:");
//        for (Subtask subtask : taskManager.getSubtasksList()) {
//            System.out.println(subtask.name + ". " + subtask.description + ". " + subtask.status);
//        }
//        System.out.println("\nСписок эпиков:");
//        for (Epic epic : taskManager.getEpicList()) {
//            System.out.println(epic.name + ". " + epic.description + ". " + epic.status);
//        }
//
//        taskManager.updateTask(task1, task1.ID, Status.IN_PROGRESS);
//        taskManager.updateTask(task2, task2.ID, Status.DONE);
//
//        taskManager.updateSubtask(subtask1, subtask1.ID, Status.DONE, epic1.ID);
//        taskManager.updateSubtask(subtask2, subtask2.ID, Status.IN_PROGRESS, epic1.ID);
//        taskManager.updateSubtask(subtask3, subtask3.ID, Status.DONE, epic2.ID);
//
//
//        taskManager.updateEpic(epic1, epic1.ID);
//        taskManager.updateEpic(epic2, epic2.ID);
//
//        System.out.println("\nСписок задач:");
//        for (Task task : taskManager.getTaskList()) {
//            System.out.println(task.name + ". " + task.description + ". " + task.status);
//        }
//        System.out.println("\nСписок подзадач:");
//        for (Subtask subtask : taskManager.getSubtasksList()) {
//            System.out.println(subtask.name + ". " + subtask.description + ". " + subtask.status);
//        }
//        System.out.println("\nСписок эпиков:");
//        for (Epic epic : taskManager.getEpicList()) {
//            System.out.println(epic.name + ". " + epic.description + " " + epic.status);
//        }
//
//        taskManager.deleteTaskOfId(1);
//        taskManager.deleteEpicOfId(2);
//
//        System.out.println("\nСписок задач");
//        for (Task task : taskManager.getTaskList()) {
//            System.out.println(task.name + ". " + task.description + ". " + task.status);
//        }
//        System.out.println("\nСписок подзадач");
//        for (Subtask subtask : taskManager.getSubtasksList()) {
//            System.out.println(subtask.name + ". " + subtask.description + ". " + subtask.status);
//        }
//        System.out.println("\nСписок эпиков");
//        for (Epic epic : taskManager.getEpicList()) {
//            System.out.println(epic.name + ". " + epic.description + " " + epic.status);
//        }

        CheckingWork checkingWork = new CheckingWork();
        checkingWork.check();

    }
}
