package managers;

import java.io.File;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    File file = new File("fie.csv");

    FileBackedTasksManager(File file) {
        this.file = file;
    }
}
