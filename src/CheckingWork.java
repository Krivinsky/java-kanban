public class CheckingWork {
    public void check(){
//        Manager manager = new Manager();
//
//        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку");
//        manager.creationTask(task1);
//
//        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине");
//        manager.creationTask(task2);
//
//        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
//        manager.creationEpic(epic1);
//
//        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики","Ремонт в квартире");
//        manager.creationSubtask(subtask1, epic1.ID);
//
//        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы","Ремонт в квартире");
//        manager.creationSubtask(subtask2, epic1.ID);
//
//        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
//        manager.creationEpic(epic2);
//
//        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить","Ремонт машины");
//        manager.creationSubtask(subtask3, epic2.ID);
//
//        for (Task task : manager.getTaskList()) {
//            System.out.println(task.name);
//        }
//
//        for (Subtask subtask : manager.getSubtasksList()) {
//            System.out.println(subtask.name);
//        }
//
//        for (Epic epic : manager.getEpicList()) {
//            System.out.println(epic.name);
//        }
//
//
//
//
////Проверка работы методов класса Manager п. 2 Методы для каждого из типа задач(Задача/Эпик/Подзадача):
//
////       + п. 2.1 Получение списка всех задач по типам
//        for (Task task : manager.getTaskList()) {
//            System.out.println(task.name);
//        }
//
//        for (Subtask subtask : manager.getSubtasksList()) {
//            System.out.println(subtask.name);
//        }
//
//        for (Epic epic : manager.getEpicList()) {
//            System.out.println(epic.name);
//        }
////    + п. 2.2   Удаление всех задач по типам
////        manager.cleanTaskList();     //
////        manager.cleanSubtaskList();
////        manager.cleanEpicList();
//
////   п. 2.3   Получение по идентификатору
//        System.out.println(manager.getTaskFromId(task1.ID));
//        manager.getTaskFromId(task2.ID);
//
//        manager.getSubtaskFromId(subtask1.ID);
//        manager.getSubtaskFromId(subtask2.ID);
//        manager.getSubtaskFromId(subtask3.ID);
//
//        manager.getEpicFromId(epic1.ID);
//        manager.getEpicFromId(epic2.ID);
//
//        manager.updateSubtask(subtask1, subtask1.ID, "DONE", epic1.ID);
//        manager.updateSubtask(subtask2, subtask2.ID, "DONE", epic1.ID);
//
//        manager.updateEpic(epic1, epic1.ID);
//        System.out.println(epic1.name + "  " + epic1.description  + "  " +  epic1.ID + "  " + epic1.status);
        Manager manager = new Manager();

        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
        manager.creationEpic(epic1);
        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики",1);
        manager.creationSubtask(subtask1, epic1.ID);
        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы",1);
        manager.creationSubtask(subtask2, epic1.ID);

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
        manager.creationEpic(epic2);
        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить",2);
        manager.creationSubtask(subtask3, epic2.ID);

        System.out.println("Список подзадач:");
        for (Task task : manager.getSubtasksList()) {
            System.out.println(task.ID+". "+task.name + ". " + task.description + ". " + task.status);
        }

        manager.deleteEpicOfId(1);

        System.out.println("Список подзадач :");
        for (Task task : manager.getSubtasksList()) {
            System.out.println(task.ID+". "+task.name + ". " + task.description + ". " + task.status);
        }
    }
}
