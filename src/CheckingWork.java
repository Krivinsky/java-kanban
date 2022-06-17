public class CheckingWork {
    public void check(){
//        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
//
//        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку");
//        inMemoryTaskManager.creationTask(task1);
//
//        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине");
//        inMemoryTaskManager.creationTask(task2);
//
//        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
//        inMemoryTaskManager.creationEpic(epic1);
//
//        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики","Ремонт в квартире");
//        inMemoryTaskManager.creationSubtask(subtask1, epic1.ID);
//
//        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы","Ремонт в квартире");
//        inMemoryTaskManager.creationSubtask(subtask2, epic1.ID);
//
//        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
//        inMemoryTaskManager.creationEpic(epic2);
//
//        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить","Ремонт машины");
//        inMemoryTaskManager.creationSubtask(subtask3, epic2.ID);
//
//        for (Task task : inMemoryTaskManager.getTaskList()) {
//            System.out.println(task.name);
//        }
//
//        for (Subtask subtask : inMemoryTaskManager.getSubtasksList()) {
//            System.out.println(subtask.name);
//        }
//
//        for (Epic epic : inMemoryTaskManager.getEpicList()) {
//            System.out.println(epic.name);
//        }
//
//
//
//
////Проверка работы методов класса InMemoryTaskManager п. 2 Методы для каждого из типа задач(Задача/Эпик/Подзадача):
//
////       + п. 2.1 Получение списка всех задач по типам
//        for (Task task : inMemoryTaskManager.getTaskList()) {
//            System.out.println(task.name);
//        }
//
//        for (Subtask subtask : inMemoryTaskManager.getSubtasksList()) {
//            System.out.println(subtask.name);
//        }
//
//        for (Epic epic : inMemoryTaskManager.getEpicList()) {
//            System.out.println(epic.name);
//        }
////    + п. 2.2   Удаление всех задач по типам
////        inMemoryTaskManager.cleanTaskList();     //
////        inMemoryTaskManager.cleanSubtaskList();
////        inMemoryTaskManager.cleanEpicList();
//
////   п. 2.3   Получение по идентификатору
//        System.out.println(inMemoryTaskManager.getTaskFromId(task1.ID));
//        inMemoryTaskManager.getTaskFromId(task2.ID);
//
//        inMemoryTaskManager.getSubtaskFromId(subtask1.ID);
//        inMemoryTaskManager.getSubtaskFromId(subtask2.ID);
//        inMemoryTaskManager.getSubtaskFromId(subtask3.ID);
//
//        inMemoryTaskManager.getEpicFromId(epic1.ID);
//        inMemoryTaskManager.getEpicFromId(epic2.ID);
//
//        inMemoryTaskManager.updateSubtask(subtask1, subtask1.ID, "DONE", epic1.ID);
//        inMemoryTaskManager.updateSubtask(subtask2, subtask2.ID, "DONE", epic1.ID);
//
//        inMemoryTaskManager.updateEpic(epic1, epic1.ID);
//        System.out.println(epic1.name + "  " + epic1.description  + "  " +  epic1.ID + "  " + epic1.status);
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
        inMemoryTaskManager.creationEpic(epic1);
        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики",1);
        inMemoryTaskManager.creationSubtask(subtask1, epic1.ID);
        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы",1);
        inMemoryTaskManager.creationSubtask(subtask2, epic1.ID);

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
        inMemoryTaskManager.creationEpic(epic2);
        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить",2);
        inMemoryTaskManager.creationSubtask(subtask3, epic2.ID);

        System.out.println("Список подзадач:");
        for (Task task : inMemoryTaskManager.getSubtasksList()) {
            System.out.println(task.ID+". "+task.name + ". " + task.description + ". " + task.status);
        }

        inMemoryTaskManager.deleteEpicOfId(1);

        System.out.println("Список подзадач :");
        for (Task task : inMemoryTaskManager.getSubtasksList()) {
            System.out.println(task.ID+". "+task.name + ". " + task.description + ". " + task.status);
        }
    }
}
