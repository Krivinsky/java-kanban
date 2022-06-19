public class CheckingWork {
    public void check(){
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку");
        inMemoryTaskManager.creationTask(task1);

        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине");
        inMemoryTaskManager.creationTask(task2);

        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
        inMemoryTaskManager.creationEpic(epic1);

        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики",1);
        inMemoryTaskManager.creationSubtask(subtask1, epic1.id);

        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы",1);
        inMemoryTaskManager.creationSubtask(subtask2, epic1.id);

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
        inMemoryTaskManager.creationEpic(epic2);

        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить",2);
        inMemoryTaskManager.creationSubtask(subtask3, epic2.id);

        System.out.println("Список Задач:");
        for (Task task : inMemoryTaskManager.getTaskList()) {
            System.out.println(task.name);
        }

        System.out.println("Список Эпиков:");
        for (Epic epic : inMemoryTaskManager.getEpicList()) {
            System.out.println(epic.name);
        }

        System.out.println("Список Подзадач:");
        for (Subtask subtask : inMemoryTaskManager.getSubtasksList()) {
            System.out.println(subtask.name);
        }

//Проверка работы методов класса InMemoryTaskManager п. 2 Методы для каждого из типа задач(Задача/Эпик/Подзадача):


//    + п. 2.2   Удаление всех задач по типам
//        inMemoryTaskManager.cleanTaskList();     //
//        inMemoryTaskManager.cleanSubtaskList();
//        inMemoryTaskManager.cleanEpicList();

//   п. 2.3   Получение по идентификатору
        inMemoryTaskManager.getTaskFromId(task1.id);
        inMemoryTaskManager.getTaskFromId(task2.id);
        inMemoryTaskManager.getHistory();


        inMemoryTaskManager.getSubtaskFromId(subtask1.id);
        inMemoryTaskManager.getSubtaskFromId(subtask2.id);
        inMemoryTaskManager.getSubtaskFromId(subtask3.id);
        inMemoryTaskManager.getHistory();

        inMemoryTaskManager.getEpicFromId(epic1.id);
        inMemoryTaskManager.getEpicFromId(epic2.id);
        inMemoryTaskManager.getHistory();

        inMemoryTaskManager.updateSubtask(subtask1, subtask1.id, Status.DONE, epic1.id);
        inMemoryTaskManager.updateSubtask(subtask2, subtask2.id, Status.DONE, epic1.id);

        inMemoryTaskManager.updateEpic(epic1, epic1.id);
        System.out.println(epic1.name + "  " + epic1.description  + "  " +  epic1.id + "  " + epic1.status);

//        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
//
//        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
//        inMemoryTaskManager.creationEpic(epic1);
//        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики",1);
//        inMemoryTaskManager.creationSubtask(subtask1, epic1.id);
//        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы",1);
//        inMemoryTaskManager.creationSubtask(subtask2, epic1.id);
//
//        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
//        inMemoryTaskManager.creationEpic(epic2);
//        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить",2);
//        inMemoryTaskManager.creationSubtask(subtask3, epic2.id);
//
//        System.out.println("Список подзадач:");
//        for (Task task : inMemoryTaskManager.getSubtasksList()) {
//            System.out.println(task.id+". "+task.name + ". " + task.description + ". " + task.status);
//        }
//
//        inMemoryTaskManager.deleteEpicOfId(1);
//
//        System.out.println("Список подзадач :");
//        for (Task task : inMemoryTaskManager.getSubtasksList()) {
//            System.out.println(task.id+". "+task.name + ". " + task.description + ". " + task.status);
//        }


    }
}
