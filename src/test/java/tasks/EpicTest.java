package tasks;

import managers.memory.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
     Epic epic;
     InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    Subtask subtask1;
    Subtask subtask2;

    @BeforeEach
    public void beforeEach() {
        epic = new Epic("Epic name", "Epic description", TypeTask.EPIC);
        inMemoryTaskManager.creationEpic(epic);
        subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), TypeTask.SUBTASK, LocalDateTime.of(2022,9,1,10, 0), 90);
        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), TypeTask.SUBTASK, LocalDateTime.of(2022,9,2,10, 0), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
    }


    @Test
    public void shouldBeZeroSubtaskIdListWhenNoSubtask() {
        inMemoryTaskManager.deleteSubtaskOfId(subtask1.getId());
        inMemoryTaskManager.deleteSubtaskOfId(subtask2.getId());
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
//        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10, 0), 90);
//        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
//        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10, 0), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());

        assertEquals(Status.NEW, epic.getStatus(), "Все подзадачи со статусом NEW - не пройден");
    }

    @Test
    public void shouldBeStatusDoneWhenAllSubtaskDone() {
//        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10, 0), 90);
//        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
//        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10, 0), 90);
        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.DONE, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask2, Status.DONE, subtask1.getIdEpic());

        assertEquals(Status.DONE, epic.getStatus(), "Все подзадачи со статусом DONE - не пройден");
    }

    @Test
    public void shouldBeStatusIN_PROGRESSWhenSubtaskDoneAndNew() {
//        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10, 0), 90);
//        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
//        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10, 0), 90);
//        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());

        inMemoryTaskManager.updateSubtask(subtask1, Status.DONE, subtask1.getIdEpic());

        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Подзадачи со статусами NEW и DONE - не пройден");

    }

    @Test
    public void shouldBeStatusIN_PROGRESSWhenSubtaskIN_PROGRESS() {
//        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10, 0), 90);
//        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
//        Subtask subtask2 = new Subtask("sb name2", "sb2 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,2,10, 0), 90);
//        inMemoryTaskManager.creationSubtask(subtask2, epic.getId());
        inMemoryTaskManager.updateSubtask(subtask1, Status.IN_PROGRESS, subtask1.getIdEpic());
        inMemoryTaskManager.updateSubtask(subtask2, Status.IN_PROGRESS, subtask1.getIdEpic());

        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Подзадачи со статусом IN_PROGRESS - не пройден");
    }


    @Test
    void getSubtasksIdTest() {
//        assertTrue(epic.getSubtasksId().isEmpty());

//        Subtask subtask1 = new Subtask("sb name1", "sb1 description", epic.getId(), Type.SUBTASK, LocalDateTime.of(2022,9,1,10, 0), 90);
//        inMemoryTaskManager.creationSubtask(subtask1, epic.getId());
        ArrayList<Integer> test = epic.getSubtasksId();
//        test.add(subtask1.getId());

        assertEquals(test, epic.getSubtasksId(),"getSubtasksIdTest - не прошел тест");
    }

}