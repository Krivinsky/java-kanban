package managers.memory;

import exeptions.ManagerSaveException;
import managers.Managers;
import managers.TaskManager;
import managers.UserManager;
import tasks.User;

import java.util.ArrayList;             /* Делали для тренировки на вебинаре */
import java.util.HashMap;
import java.util.List;

public class InMemoryUserManager implements UserManager {

    private final HashMap<Integer, User> users = new HashMap<>();

    private final TaskManager taskManager = Managers.getDefault();

    private int generatedId = 0;

    public InMemoryUserManager() throws ManagerSaveException {
    }

    private int generateId() {
        return ++generatedId;
    }

    @Override
    public TaskManager getTaskManager() {
        return taskManager;
    }

    @Override
    public Integer add(User user) {
        int id = generateId();
        user.setId(id);
        users.put(id, user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        int id = user.getId();
        if (!users.containsKey(id)) {
            return;
        }
        users.put(id, user);
    }

    @Override
    public User  getById(int id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

//    @Override
//    public List<Task> getUserTasks(int id) {
//        return taskManager.getTaskList().stream()
//                .filter(task -> task.getUser().getId() == id)
//                .collect(Collectors.toList());
//    }

    public void delete(int id) {
        users.remove(id);
    }
}
