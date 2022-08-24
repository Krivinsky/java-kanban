package managers;

import tasks.Task;
import tasks.User;

import java.util.List;

public interface UserManager {

    TaskManager getTaskManager();



    Integer add(User user);

    void  update(User user);

    User getById(int id);

    List<User> getAll();

    List<Task> getUserTasks(int id);

    void delete(int id);


}