package managers;

import org.junit.jupiter.api.Test;
import tasks.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TaskManagerTest <T extends TaskManager>{

    protected T taskManager;
    protected Task task;
    protected Epic epic;
    protected Subtask subtask;


    protected void taskManagerSetUp() {
        task = new Task(1,"Task name", "Task description", Type.TASK, Status.NEW, LocalDateTime.of(2022,9,1,10, 0), 90);
        taskManager.creationTask(task);

        epic = new Epic(2,"Epic name", "Epic description", Type.EPIC, Status.NEW, LocalDateTime.of(2022,9,2,10, 0), 90);
        taskManager.creationEpic(epic);

        subtask = new Subtask(3,"Subtask name", "Subtask description",epic.getId(), Type.SUBTASK, Status.NEW, LocalDateTime.of(2022,9,3,10, 0), 90);
        taskManager.creationSubtask(subtask, epic.getId());

    }
    @Test
    public void getTaskListTest() {
        ArrayList<Task> taskList = new ArrayList<> (taskManager.getTaskList());
        assertEquals(task, taskList.get(0), "getTaskListTest - не пройден");
    }

    @Test
    public void getTaskListTestWhenListIsEmptyTest() {
        ArrayList<Task> taskList = new ArrayList<> (taskManager.getTaskList());
        assertEquals(task, taskList.get(0), "getTaskListTest - не пройден");
        taskManager.cleanTaskList();
        taskList = new ArrayList<> (taskManager.getTaskList());
        assertTrue(taskList.isEmpty(), "getTaskListTestWhenListIsEmpty - не пройден");
    }

    @Test
    public void getSubtasksListTest() {
        ArrayList<Task> subtaskList = new ArrayList<> (taskManager.getSubtasksList());
        assertEquals(subtask, subtaskList.get(0), "getSubtasksListTest - не пройден");
    }

    @Test
    public void getSubtasksListTestWhenListIsEmptyTest() {
        ArrayList<Task> subtaskList = new ArrayList<> (taskManager.getSubtasksList());
        assertEquals(subtask, subtaskList.get(0), "getSubtasksListTest - не пройден");
        taskManager.cleanSubtaskList();
        subtaskList = new ArrayList<> (taskManager.getSubtasksList());
        assertTrue(subtaskList.isEmpty(), "getSubtasksListTestWhenListIsEmpty - не пройден");
    }

    @Test
    public void getEpicLisTest() {
        ArrayList<Task> epicList = new ArrayList<> (taskManager.getEpicList());
        assertEquals(epic, epicList.get(0), "getSubtasksListTest - не пройден");
    }

    @Test
    public void getEpicListTestWhenListIsEmptyTest() {
        ArrayList<Task> epicList = new ArrayList<> (taskManager.getEpicList());
        assertEquals(epic, epicList.get(0), "getSubtasksListTest - не пройден");
        taskManager.cleanEpicList();
        epicList = new ArrayList<> (taskManager.getEpicList());
        assertTrue(epicList.isEmpty(), "getSubtasksListTestWhenListIsEmpty - не пройден");
    }

    @Test
    public void cleanTaskListTest() {
        ArrayList<Task> taskList = new ArrayList<> (taskManager.getTaskList());
        assertEquals(task, taskList.get(0), "getTaskListTest - не пройден");
        taskManager.cleanTaskList();
        taskList = new ArrayList<> (taskManager.getTaskList());
        assertTrue(taskList.isEmpty(), "cleanTaskListTest - не пройден");
    }

    @Test
    public void cleanSubtaskListTest() {
        ArrayList<Task> subtaskList = new ArrayList<> (taskManager.getSubtasksList());
        assertEquals(subtask, subtaskList.get(0), "getSubtasksListTest - не пройден");
        taskManager.cleanSubtaskList();
        subtaskList = new ArrayList<> (taskManager.getSubtasksList());
        assertTrue(subtaskList.isEmpty(), "cleanSubtaskListTest - не пройден");
    }

    @Test
    public void cleanEpicListTest() {
        ArrayList<Task> epicList = new ArrayList<> (taskManager.getEpicList());
        assertEquals(epic, epicList.get(0), "getSubtasksListTest - не пройден");
        taskManager.cleanEpicList();
        epicList = new ArrayList<> (taskManager.getEpicList());
        assertTrue(epicList.isEmpty(), "cleanEpicListTest - не пройден");
    }

    @Test
    public void getTaskFromIdTest() {
        assertEquals(task, taskManager.getTaskFromId(task.getId()), "getTaskFromIdTest - не пройден");
    }

    @Test
    public void getTaskFromIdWhenWrongIdTest() {
        assertNull(taskManager.getTaskFromId(0), "getTaskFromIdWhenWrongIdTest - не пройден");
    }

    @Test
    public void getSubtaskFromIdTest() {
        assertEquals(subtask, taskManager.getSubtaskFromId(subtask.getId()), "getSubtaskFromIdTest - не пройден");
    }

    @Test
    public void getSubtaskFromIdWhenWrongIdTest() {
        assertNull(taskManager.getSubtaskFromId(0), "getSubtaskFromIdWhenWrongIdTest - не пройден");
    }

    @Test
    public void getEpicFromIdTest() {
        assertEquals(epic, taskManager.getEpicFromId(epic.getId()), "getEpicFromIdTest - не пройден");
    }

    @Test
    public void creationTaskTest() {
        assertNotNull(task, "task не null - не пройден");
        assertTrue(task.getId() > 0 , "Присвоен некорректный Id");
        assertEquals(Status.NEW, task.getStatus(), "Присвоен некорректный статус");
    }

    @Test
    public void creationTaskWhenWrongInputData() {
        assertNull(taskManager.creationTask(null), "creationTaskWhenWrongInputData - не пройден");
    }

    @Test
    public void creationSubtaskTest() {
        assertNotNull(subtask, "subtask не null - не пройден");
        assertTrue(subtask.getId() > 0 , "Присвоен некорректный Id");
        assertEquals(Status.NEW, subtask.getStatus(), "Присвоен некорректный статус");

    }

    @Test
    public void creationSubtaskWhenWrongInputData() {
        assertNull(taskManager.creationSubtask(null, epic.getId()), "creationTaskWhenWrongInputData - не пройден");
    }

    @Test
    public void creationSubtaskWhenWrongEpic() {
        assertNull(taskManager.creationSubtask(subtask, 0), "creationSubtaskWhenWrongEpic - не пройден");
    }

    @Test
    public void creationEpicTest() {
        assertNotNull(epic, "epic не null - не пройден");
        assertTrue(epic.getId() > 0 , "Присвоен некорректный Id");
        assertEquals(Status.NEW, epic.getStatus(), "Присвоен некорректный статус");
    }

    @Test
    public void updateTaskTest() {
        assertEquals(Status.NEW, task.getStatus(), "начальный статус неверный");
        taskManager.updateTask(task, Status.DONE);
        assertEquals(Status.DONE, task.getStatus(), "updateTaskTest - не пройден");
    }

    @Test
    public void updateTaskWhenWrongInputDataTest() {
        assertEquals(Status.NEW, task.getStatus(), "начальный статус неверный");
        taskManager.updateTask(null, Status.DONE);
        assertEquals(Status.NEW, task.getStatus(), "конечный статус неверный");
    }

    @Test
    public void updateSubtaskTest() {
        assertEquals(Status.NEW, subtask.getStatus(), "начальный статус неверный");
        taskManager.updateSubtask(subtask, Status.DONE, epic.getId());
        assertEquals(Status.DONE, subtask.getStatus(), "updateSubtaskTest - не пройден");
    }

    @Test
    public void updateSubtaskWhenWrongInputDataTest() {
        assertEquals(Status.NEW, subtask.getStatus(), "начальный статус неверный");
        taskManager.updateSubtask(null, Status.DONE, epic.getId());
        assertEquals(Status.NEW, subtask.getStatus(), "конечный статус неверный");
    }

    @Test
    public void updateEpicTest() {
        assertEquals(Status.NEW, epic.getStatus(), "начальный статус неверный");
        taskManager.updateSubtask(subtask, Status.DONE, epic.getId());
        assertEquals(Status.DONE, subtask.getStatus(), "updateEpicTest - не пройден");
    }

    @Test
    public void updateEpicTestWhenNoSubtask() {
        assertEquals(Status.NEW, epic.getStatus(), "начальный статус неверный");
        taskManager.cleanSubtaskList();
        ArrayList<Subtask> list = taskManager.getSubtasksList();
        assertTrue(list.isEmpty(), "Удаление всех подзадач, проверка Эпика - не пройден");
        assertEquals(Status.NEW, epic.getStatus(), "updateEpicTestWhenNoSubtask - не пройден");
    }

    @Test
    public void updateEpicTestWhenAllSubtaskDONE() {
        Subtask subtask2 = new Subtask("Subtask2 name", "Subtask2 description",epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,3,10, 0), 90);
        taskManager.creationSubtask(subtask2, epic.getId());
        taskManager.updateSubtask(subtask, Status.DONE, epic.getId());
        taskManager.updateSubtask(subtask2, Status.DONE, epic.getId());
        assertEquals(Status.DONE, epic.getStatus(), "updateSubtaskTest - не пройден");
    }

    @Test
    public void updateEpicTestWhenSubtaskDONEAndNEW() {
        Subtask subtask2 = new Subtask("Subtask2 name", "Subtask2 description",epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,4,10, 0), 90);
        taskManager.creationSubtask(subtask2, epic.getId());
        taskManager.updateSubtask(subtask2, Status.DONE, epic.getId());
        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "updateEpicTestWhenSubtaskDONEAndNEW - не пройден");
    }

    @Test
    public void deleteTaskOfIdTest() {
        assertEquals(task, taskManager.getTaskFromId(task.getId()), "getTaskListTest в deleteTaskOfIdTest - не пройден");
        taskManager.deleteTaskOfId(task.getId());
        assertNull(taskManager.getTaskFromId(task.getId()), "deleteTaskOfIdTest - не пройден");
    }

    @Test
    public void deleteTaskOfIdWhenWrongIdTest() {
        assertEquals(task, taskManager.getTaskFromId(task.getId()), "getTaskFromIdTest в deleteTaskOfIdWhenWrongIdTest - не пройден");
        taskManager.deleteTaskOfId(0);
        assertEquals(task, taskManager.getTaskFromId(task.getId()), "deleteTaskOfIdWhenWrongIdTest - не пройден");
    }

    @Test
    public void deleteSubtaskOfIdTest() {
        assertEquals(subtask, taskManager.getSubtaskFromId(subtask.getId()), "getSubtaskOfIdTest в deleteSubtaskOfIdTest - не пройден");
        taskManager.deleteSubtaskOfId(subtask.getId());
        assertNull(taskManager.getSubtaskFromId(subtask.getId()), "deleteSubtaskOfIdTest - не пройден");
    }

    @Test
    public void deleteSubtaskOfIdWhenWrongIdTest() {
        assertEquals(subtask, taskManager.getSubtaskFromId(subtask.getId()), "getSubtaskOfIdTest в deleteSubtaskOfIdWhenWrongIdTest - не пройден");
        taskManager.deleteSubtaskOfId(0);
        assertEquals(subtask,taskManager.getSubtaskFromId(subtask.getId()), "deleteSubtaskOfIdWhenWrongIdTest - не пройден");
    }

    @Test
    public void deleteEpicOfIdTest() {
        assertEquals(epic, taskManager.getEpicFromId(epic.getId()), "getEpicOfIdTest в deleteEpicOfIdTest - не пройден");
        taskManager.deleteEpicOfId(epic.getId());
        assertNull(taskManager.getEpicFromId(epic.getId()), "deleteSubtaskOfIdTest - не пройден");
    }

    @Test
    public void deleteEpicOfIdWhenWrongIdTest() {
        assertEquals(epic, taskManager.getEpicFromId(epic.getId()), "getEpicOfIdTest в deleteEpicOfIdWhenWrongIdTest - не пройден");
        taskManager.deleteEpicOfId(0);
        assertEquals(epic, taskManager.getEpicFromId(epic.getId()), "deleteEpicOfIdWhenWrongIdTest - не пройден");
    }

    @Test
    public void getEpicSubtasksListTest() {
        ArrayList<Subtask> subtasks = new ArrayList<> (taskManager.getEpicSubtasksList(epic));
        assertEquals(subtask, subtasks.get(0), "getEpicSubtasksList - не пройден");
    }

    @Test
    void getEpicSubtasksListWhenWrongInputDataTest() {
        assertNull(taskManager.getEpicSubtasksList(null));
    }
}