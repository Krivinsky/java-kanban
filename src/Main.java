import Managers.Managers;
import Managers.TaskManager;

import java.awt.*;
import java.sql.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

       // TaskManager taskManager = Managers.getDefault();

        CheckingWork checkingWork = new CheckingWork();
        checkingWork.check();

    }
}
