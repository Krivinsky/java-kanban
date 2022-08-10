package tasks;
import managers.*;
import org.junit.jupiter.api.BeforeEach;
import tasks.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    static Epic epic;
    static TaskManager inMemoryTaskManager = Managers.taskManager;
    @BeforeEach
    public void beforeEach() {
        epic = new Epic("Epic name", "Edic description", Type.EPIC);
        inMemoryTaskManager.creationEpic(epic);
    }


    @Test
    void shouldBeZeroSubtaskIdList() {  //todo
        ArrayList<Integer> subtasksId = epic.getSubtasksId();
        Integer[] test = new Integer[0];
        Integer[] subtaskArray = new Integer[subtasksId.size()];
        for (int i = 0; i < subtaskArray.length; i++) {
            subtaskArray[i] = subtasksId.get(i);
        }

        assertArrayEquals(test, subtaskArray, " Пустой список подзадач - не пройден");
    }

    @Test
    void shouldBeStatusNew() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());

        assertEquals(Status.NEW, epic.getStatus(), "Все подзадачи со статусом NEW - не пройден");
    }

    @Test
    void shouldBeStatusDone() {
        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.DONE, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask2, Status.DONE, subtask1.getIdEpic());


        assertEquals(Status.DONE, epic.getStatus(), "Все подзадачи со статусом DONE - не пройден");
    }
}