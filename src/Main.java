
import managers.file.FileBackedTasksManager;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

      //  CheckingWork checkingWork = new CheckingWork();
      //  checkingWork.check();

        File file = new File("csv/file.csv");
        FileBackedTasksManager.loadFromFile(file);

    }
}
