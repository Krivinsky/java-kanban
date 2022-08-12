package tasks;

import managers.TaskManager;
import managers.memory.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task;
    TaskManager taskManager = new InMemoryTaskManager();

    @BeforeEach
    public void BeforeEach() {
        task = new Task("Task name", "Task description", Type.TASK);
        taskManager.creationTask(task);
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
        task.setId(1);
        assertEquals(1, task.getId(), "setIdTest - не пройден");
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
        assertEquals("1,TASK,Task name,NEW,Task description", task.toString(), "testToStringTest- не пройден");
    }
}