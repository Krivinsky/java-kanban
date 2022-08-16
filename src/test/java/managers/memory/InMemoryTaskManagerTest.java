package managers.memory;

import managers.TaskManagerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        //todo
        ArrayList<Task> list = new ArrayList<> (taskManager.getPrioritizedTasks());
        assertEquals(task, list.get(0));
        assertEquals(epic, list.get(1));
        assertEquals(subtask, list.get(2));
    }

    @Test
    void validateTest() {

    }
}