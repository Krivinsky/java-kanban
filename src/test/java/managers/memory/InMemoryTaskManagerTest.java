package managers.memory;

import managers.TaskManagerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void getPrioritizedTasks() {
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
}