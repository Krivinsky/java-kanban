package tasks;
import managers.*;
import managers.memory.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import tasks.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    static Epic epic;
    static InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    @BeforeEach
    public void beforeEach() {
        epic = new Epic("Epic name", "Epic description", Type.EPIC, LocalDateTime.of(2022,9,1,10, 0), 90);
        inMemoryTaskManager.creationEpic(epic);
    }


    @Test
    public void shouldBeZeroSubtaskIdListWhenNoSubtask() {
        ArrayList<Integer> subtasksId = epic.getSubtasksId();
        Integer[] test = new Integer[0];
        Integer[] subtaskArray = new Integer[subtasksId.size()];
        for (int i = 0; i < subtaskArray.length; i++) {
            subtaskArray[i] = subtasksId.get(i);
        }

        assertArrayEquals(test, subtaskArray, " Пустой список подзадач - не пройден");
    }

    @Test
    public void shouldBeStatusNewWhenAllSubtaskNew() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());

        assertEquals(Status.NEW, epic.getStatus(), "Все подзадачи со статусом NEW - не пройден");
    }

    @Test
    public void shouldBeStatusDoneWhenAllSubtaskDone() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.DONE, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask2, Status.DONE, subtask1.getIdEpic());

        assertEquals(Status.DONE, epic.getStatus(), "Все подзадачи со статусом DONE - не пройден");
    }

    @Test
    public void shouldBeStatusIN_PROGRESSWhenSubtaskDoneAndNew() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());

        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());

        inMemoryTaskManager.updateSubtask(subtask1, Status.DONE, subtask1.getIdEpic());

        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Подзадачи со статусами NEW и DONE - не пройден");

    }

    @Test
    public void shouldBeStatusIN_PROGRESSWhenSubtaskIN_PROGRESS() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.IN_PROGRESS, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask2, Status.IN_PROGRESS, subtask1.getIdEpic());

        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Подзадачи со статусом IN_PROGRESS - не пройден");
    }


    @Test
    void getSubtasksIdTest() {
        assertTrue(epic.getSubtasksId().isEmpty());

        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        ArrayList<Integer> test = new ArrayList<>();
        test.add(subtask1.getId());

        assertEquals(test, epic.getSubtasksId(),"getSubtasksIdTest - не прошел тест");
    }

    @Test
    void addSubtasksIdTest() {
        assertTrue(epic.getSubtasksId().isEmpty());

        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10,00), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        ArrayList<Integer> test = new ArrayList<>();
        test.add(subtask1.getId());

        assertEquals(test, epic.getSubtasksId(),"addSubtasksIdTest - не прошел тест");
    }
}