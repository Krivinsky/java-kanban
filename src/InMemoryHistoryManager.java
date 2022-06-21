import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> history = new ArrayList<>();
    @Override
    public void addTask(Task task){
        if (history.size() <= 10) {
            history.add(task);
        } else {
            history.remove(0);
            history.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        int historyListSize = history.size();
        System.out.println("история просмотров:");  //убрать после проверки метод не должен выводит на экран
        for (int i = 0; i < historyListSize; i++) {
            System.out.println(history.get(i).id);
        }
        return history;
    }
}
