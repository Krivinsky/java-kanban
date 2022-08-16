package managers.memory;

import exeptions.ValidationException;
import managers.TaskManagerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;
import tasks.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    void setup() {
        taskManager = new InMemoryTaskManager();
        taskManagerSetUp();
    }
    @Test
    void generateId() {
    }

    @Test
    void deleteTaskOfId() {
    }

    @Test
    void deleteSubtaskOfId() {
    }

    @Test
    void deleteEpicOfId() {
    }

    @Test
    void getEpicSubtasksList() {
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
        Task task1 = new Task(1,"Task name", "Task description", Type.TASK, Status.NEW, LocalDateTime.of(2022,9,1,10, 0), 90);
        taskManager.creationTask(task1);
        list = new ArrayList<> (taskManager.getPrioritizedTasks());
        assertEquals(2, list.size());
    }

    @Test
    void ValidationExceptionTest() {
        final ValidationException ex = assertThrows(
                ValidationException.class,
                () -> {
                    Task task1 = new Task(1,"Task name", "Task description", Type.TASK, Status.NEW, LocalDateTime.of(2022,9,1,9, 0), 600);
                    taskManager.validate(task1);
                });
        assertEquals("Задача не может начинаться раньше и заканчиваться позже чем другие задачи", ex.getMessage());
    }
}