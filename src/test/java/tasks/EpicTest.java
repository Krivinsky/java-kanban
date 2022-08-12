package tasks;
import managers.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    static Epic epic;
    static TaskManager inMemoryTaskManager = Managers.taskManager;

    @BeforeEach
    public void beforeEach() {
        epic = new Epic("Epic name", "Epic description", Type.EPIC);
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
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());

        assertEquals(Status.NEW, epic.getStatus(), "Все подзадачи со статусом NEW - не пройден");
    }

    @Test
    public void shouldBeStatusDoneWhenAllSubtaskDone() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.DONE, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask2, Status.DONE, subtask1.getIdEpic());

        assertEquals(Status.DONE, epic.getStatus(), "Все подзадачи со статусом DONE - не пройден");
    }

    @Test
    public void shouldBeStatusIN_PROGRESSWhenSubtaskDoneAndNew() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());

        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());

        inMemoryTaskManager.updateSubtask(subtask1, Status.DONE, subtask1.getIdEpic());

        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Подзадачи со статусами NEW и DONE - не пройден");

    }

    @Test
    public void shouldBeStatusIN_PROGRESSWhenSubtaskIN_PROGRESS() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.IN_PROGRESS, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask2, Status.IN_PROGRESS, subtask1.getIdEpic());

        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Подзадачи со статусом IN_PROGRESS - не пройден");
    }

}