package tasks;

import managers.TaskManager;
import managers.memory.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    Epic epic;
    Subtask subtask;
    InMemoryTaskManager taskManager;

    @BeforeEach
    public void add() {
        taskManager = new InMemoryTaskManager();
        epic = new Epic(1,"Epic name", "Epic description", Type.EPIC, Status.NEW, LocalDateTime.of(2022,9,1,10,00), 90);
        subtask = new Subtask(2,"Subtask name", "Subtask description",epic.getId(), Type.SUBTASK, Status.NEW, LocalDateTime.of(2022,9,1,10,00), 90);

    }

    @Test
    void setIdEpicTest() {
        subtask.setIdEpic(1);
        assertEquals(1, subtask.getIdEpic(), "setIdEpicTest -  - не пройден");
    }

    @Test
    void getIdEpicTest() {
        subtask.setIdEpic(1);
        assertEquals(1, subtask.getIdEpic(), "getIdEpicTest -  - не пройден");
    }

    @Test
    void testToString() {
        assertEquals("2,SUBTASK,Subtask name,NEW,Subtask description,01_09_2022|10:00,90,1"  ,subtask.toString());
    }
}