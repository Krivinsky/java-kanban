package tasks;

import managers.TaskManager;
import managers.memory.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    Subtask subtask;
    Epic epic;
    TaskManager taskManager = new InMemoryTaskManager();

    @BeforeEach
    public void BeforeEach() {
        epic = new Epic("Epic name", "Epic description", Type.EPIC);
        taskManager.creationEpic(epic);
        subtask = new Subtask("Subtask name", "Subtask description",1, Type.SUBTASK);
        taskManager.creationSubtask(subtask, 1);
    }

    @Test
    void setIdEpicTest() {
        subtask.setIdEpic(2);
        assertEquals(2, subtask.getIdEpic(), "setIdEpicTest -  - не пройден");
    }

    @Test
    void getIdEpicTest() {
        subtask.setIdEpic(2);
        assertEquals(2, subtask.getIdEpic(), "getIdEpicTest -  - не пройден");
    }

    @Test
    void testToString() {
        subtask.setId(1);
        assertEquals("1,SUBTASK,Subtask name,NEW,Subtask description,1",subtask.toString());
    }
}