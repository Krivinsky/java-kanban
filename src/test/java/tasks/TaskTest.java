package tasks;

import managers.TaskManager;
import managers.memory.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task;
    TaskManager taskManager = new InMemoryTaskManager();

    @BeforeEach
    public void BeforeEach() {
        task = new Task(1, "Task name", "Task description", Type.TASK, Status.NEW, LocalDateTime.of(2022,9,1,10,00), 90);
       }

    @Test
    void getNameTest() {
        assertEquals("Task name", task.getName(), "getNameTest - не пройден");
    }

    @Test
    void getDescriptionTest() {
        assertEquals("Task description", task.getDescription(), "getDescriptionTest - не пройден");
    }

    @Test
    void getStatusTest() {
        assertEquals(Status.NEW, task.getStatus(), "getStatusTest - - не пройден");
    }

    @Test
    void setIdTest() {
        task.setId(2);
        assertEquals(2, task.getId(), "setIdTest - не пройден");
    }

    @Test
    void getIdTest() {
        task.setId(1);
        assertEquals(1, task.getId(), "getIdTest");
    }

    @Test
    void setNameTest() {
        task.setName("Task new name");
        assertEquals("Task new name", task.getName(), "setNameTest - не пройден");
    }

    @Test
    void setDescriptionTest() {
        task.setDescription("Task new description");
        assertEquals("Task new description", task.getDescription(), "setDescriptionTest - не пройден");
    }

    @Test
    void setStatusTest() {
        task.setStatus(Status.DONE);
        assertEquals(Status.DONE, task.getStatus(), "setStatusTest - не пройден");
    }

    @Test
    void testToStringTest() {
        task.setId(1);
        assertEquals("1,TASK,Task name,NEW,Task description,01_09_2022|10:00,90", task.toString(), "testToStringTest- не пройден");
    }
}