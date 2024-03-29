package managers.file;

import exeptions.ManagerSaveException;
import managers.TaskManagerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {
    File file;
    @BeforeEach
    void setUp() {
      file = new File ("csv/file.csv");
      taskManager = new FileBackedTasksManager();
      taskManagerSetUp();
    }

    @Test
    void main() {
    }

    @Test
    void saveTest()  {
        //работа метода проверяется в loadFromFileTest
    }

    @Test
    public void loadFromFileTest() throws FileNotFoundException {
        taskManager.getTaskFromId(task.getId());
        FileBackedTasksManager fileBackedTasksManager = FileBackedTasksManager.loadFromFile(file);

        ArrayList<Task> list = fileBackedTasksManager.getTaskList();
        assertNotNull(list,"лист не null");
        assertEquals(task.getId(), list.get(0).getId(), "loadFromFileTest - не пройден");
        assertEquals(task.getName(), list.get(0).getName(), "loadFromFileTest - не пройден");
        assertEquals(task.getStatus(), list.get(0).getStatus(), "loadFromFileTest - не пройден");
        assertEquals(task.getDescription(), list.get(0).getDescription(), "loadFromFileTest - не пройден");
        assertEquals(task.getStartTime(), list.get(0).getStartTime(), "loadFromFileTest - не пройден");
        assertEquals(task.getEndTime(), list.get(0).getEndTime(), "loadFromFileTest - не пройден");

    }
    @Disabled
    @Test
    void ManagerSaveExceptionTest() {
        final ManagerSaveException ex = assertThrows(
                ManagerSaveException.class,
                () -> {
                    FileBackedTasksManager fbt = new FileBackedTasksManager();
                    fbt.saveToFile();
                });
        assertEquals("", ex.getMessage());
    }

}