public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку");
        manager.creationTask(task1);

        Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине");
        manager.creationTask(task2);

        System.out.println(task1.name + "  " + task1.description  + "  " +  task1.ID + "  " + task1.status);
        System.out.println(task2.name + "  " + task2.description  + "  " +  task2.ID + "  " + task2.status);


        Epic epic1 = new Epic("Ремонт в квартире", "Ремонт в своей квартире");
        manager.creationEpic(epic1);
        System.out.println(epic1.name + "  " + epic1.description  + "  " +  epic1.ID + "  " + epic1.status);

        Subtask subtask1 = new Subtask("Закупить стройматериалы", "Закупить обои, клей, валики","Ремонт в квартире");
        manager.creationSubtask(subtask1, epic1.ID);
        System.out.println(subtask1.name + "  " + subtask1.description  + "  " +  subtask1.ID + "  " + subtask1.status+ "  " + subtask1.nameOfEpic);

        Subtask subtask2 = new Subtask("Нанять рабочих", "Заключить договор, Принять работы","Ремонт в квартире");
        manager.creationSubtask(subtask2, epic1.ID);
        System.out.println(subtask2.name + "  " + subtask2.description  + "  " +  subtask2.ID + "  " + subtask2.status+ "  " + subtask2.nameOfEpic);

        Epic epic2 = new Epic("Ремонт машины", "Ремонт Ниссан");
        manager.creationEpic(epic2);
        System.out.println(epic2.name + "  " + epic2.description  + "  " +  epic2.ID + "  " + epic2.status);

        Subtask subtask3 = new Subtask("Закупить запчасти", "Найти ужные запчасти и закупить","Ремонт машины");
        manager.creationSubtask(subtask3, epic1.ID);
        System.out.println(subtask3.name + "  " + subtask3.description  + "  " +  subtask3.ID + "  " + subtask3.status+ "  " + subtask3.nameOfEpic);

        //todo проверить работу методов по ТЗ
    }
}
