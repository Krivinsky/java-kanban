package managers;

import managers.memory.InMemoryHistoryManager;
import managers.memory.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.Type;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {

    HistoryManager historyManager;
    InMemoryTaskManager inMemoryTaskManager;
    private Task task;
    private Epic epic;
    private Subtask subtask;

    @BeforeEach
    void stUp() {
        historyManager = new InMemoryHistoryManager();
        inMemoryTaskManager = new InMemoryTaskManager();

        task = new Task("Task name", "Task description", Type.TASK);
        inMemoryTaskManager.creationTask(task);

        epic = new Epic("Epic name", "Epic description", Type.EPIC);
        inMemoryTaskManager.creationEpic(epic);

        subtask = new Subtask("Subtask name", "Subtask description",epic.getId(), Type.SUBTASK);
        inMemoryTaskManager.creationSubtask(subtask, epic.getId());
    }

    @Test
    public void getHistoryWhenEmptyHistory() {
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertTrue(history.isEmpty(), "История не пустая.");
    }

    @Test
    public void shouldBeOneWhenAddTwice() {
        historyManager.addTask(task);
        historyManager.addTask(task);
        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "Добавили две одинаковых задачи, а в истории должна быть одна");
        assertEquals(task, history.get(0),"та задача которую добавили");
    }

    @Test
    public void add() {
        historyManager.addTask(task);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void removeFirst() {
        historyManager.addTask(task);
        historyManager.addTask(subtask);
        historyManager.addTask(epic);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(3, history.size(), "Содержит правильное количество элементов");

        historyManager.remove(task.getId());
        history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(2, history.size(), "Содержит правильное количество элементов");
        assertEquals(subtask, history.get(0), "Правильный порядок элементов");
        assertEquals(epic, history.get(1), "Правильный порядок элементов");
    }

    @Test
    public void removeMiddle() {
        historyManager.addTask(task);
        historyManager.addTask(subtask);
        historyManager.addTask(epic);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(3, history.size(), "Содержит правильное количество элементов");

        historyManager.remove(subtask.getId());
        history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(2, history.size(), "Содержит правильное количество элементов");
        assertEquals(task, history.get(0), "Правильный порядок элементов");
        assertEquals(epic, history.get(1), "Правильный порядок элементов");
    }

    @Test
    public void removeLast() {
        historyManager.addTask(task);
        historyManager.addTask(subtask);
        historyManager.addTask(epic);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(3, history.size(), "Содержит правильное количество элементов");

        historyManager.remove(epic.getId());
        history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(2, history.size(), "Содержит правильное количество элементов");
        assertEquals(task, history.get(0), "Правильный порядок элементов");
        assertEquals(subtask, history.get(1), "Правильный порядок элементов");
    }

    @Test
    public void removeSingle() {
        historyManager.addTask(task);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(1, history.size(), "Содержит правильное количество элементов");

        historyManager.remove(task.getId());
        history = historyManager.getHistory();
        assertNotNull(history, "История не пустая");
        assertEquals(0, history.size(), "Содержит правильное количество элементов");
    }

    @Test
    public void getHistory() {
    }
}