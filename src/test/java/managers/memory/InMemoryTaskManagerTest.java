package managers.memory;

import exeptions.ValidationException;
import managers.TaskManagerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;
import tasks.TypeTask;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    void setup() {
        taskManager = new InMemoryTaskManager();
        taskManagerSetUp();
    }
    @Test
    void generateId() {
        int i = InMemoryTaskManager.generateId;
        InMemoryTaskManager.generateId();
        assertEquals(i+1, InMemoryTaskManager.generateId);
    }

    @Test
    void deleteTaskOfId() {
        taskManager.deleteTaskOfId(task.getId());
        assertNull(taskManager.getTaskFromId(task.getId()));
    }

    @Test
    void deleteSubtaskOfId() {
        taskManager.deleteSubtaskOfId(subtask.getId());
        assertNull(taskManager.getSubtaskFromId(subtask.getId()));
    }

    @Test
    void deleteEpicOfId() {
        taskManager.deleteEpicOfId(epic.getId());
        assertNull(taskManager.getEpicFromId(epic.getId()));
    }

    @Test
    void getEpicSubtasksList() {
        ArrayList<Subtask> list = taskManager.getEpicSubtasksList(epic);
        assertEquals(subtask, list.get(0));
    }

    @Test
    public void getPrioritizedTasksTest() {
        ArrayList<Task> list = new ArrayList<> (taskManager.getPrioritizedTasks());
        assertEquals(task, list.get(0));
        assertEquals(subtask, list.get(1), "getPrioritizedTasksTest - не пройден");
    }

    @Test
    void validateTest() {
        ArrayList<Task> list = new ArrayList<> (taskManager.getPrioritizedTasks());
        assertEquals(2, list.size());
        Task task1 = new Task(1,"Task name", "Task description", TypeTask.TASK, Status.NEW, LocalDateTime.of(2022,9,1,10, 0), 90);
        taskManager.creationTask(task1);
        list = new ArrayList<> (taskManager.getPrioritizedTasks());
        assertEquals(2, list.size());
    }

    @Test
    void ValidationExceptionTest() {
        final ValidationException ex = assertThrows(
                ValidationException.class,
                () -> {
                    Task task1 = new Task(2,"Task name2", "Task description2", TypeTask.TASK, Status.NEW, LocalDateTime.of(2022,9,1,9, 0), 600);
                    taskManager.validate(task1);
                });
        assertEquals("Задача не может начинаться раньше и заканчиваться позже чем другие задачи", ex.getMessage());
    }
}