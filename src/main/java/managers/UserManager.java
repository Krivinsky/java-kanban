package managers;

import tasks.User;

import java.util.List;

public interface UserManager {                      /* Делали для тренировки на вебинаре */
                                                            /** к ТЗ не относится **/
    TaskManager getTaskManager();

    Integer add(User user);

    void  update(User user);

    User getById(int id);

    List<User> getAll();

//    List<Task> getUserTasks(int id);

    void delete(int id);

}
