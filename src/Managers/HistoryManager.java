package Managers;

import Tasks.Task;

import java.util.List;

public interface HistoryManager {

    public void addTask(Task task);

    public void remove(int id);

    public List<Task> getHistory();
}
