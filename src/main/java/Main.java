import com.google.gson.Gson;
import exeptions.ManagerLoadException;
import exeptions.ManagerSaveException;
import managers.HistoryManager;
import managers.Managers;
import managers.client.KVTaskClient;
import managers.http.HttpTaskManager;
import managers.memory.InMemoryTaskManager;
import server.KVServer;
import tasks.Task;
import tasks.TypeTask;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws IOException, ManagerSaveException, ManagerLoadException {
//        File file = new File("csv/file.csv");
//        FileBackedTasksManager.loadFromFile(file);


        KVServer kvServer = Managers.getDefaultKVServer();
        kvServer.start();
        HttpTaskManager httpTaskManager = new HttpTaskManager(KVServer.PORT);
        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку", TypeTask.TASK, LocalDateTime.of(2022,9,1,10, 0), 90);
        httpTaskManager.creationTask(task1);
        httpTaskManager.save();

        httpTaskManager.load();


//        new KVServer().start();   //запустить сервер правильно
//        KVTaskClient kvTaskClient = new KVTaskClient(8078);
//        kvTaskClient.put("1", "{\\\"name\\\":\\\"Забрать посылку\\\",\\\"description\\\":\\\"Сходить на почту и забрать посылку\\\",\\\"id\\\":1,\\\"status\\\":\\\"NEW\\\",\\\"typeTask\\\":\\\"TASK\\\",\\\"startTime\\\":{\\\"date\\\":{\\\"year\\\":2022,\\\"month\\\":9,\\\"day\\\":1},\\\"time\\\":{\\\"hour\\\":10,\\\"minute\\\":0,\\\"second\\\":0,\\\"nano\\\":0}},\\\"duration\\\":90}");
//        String str = kvTaskClient.load("1");




//проверка работы json - он работает, но ругается
//        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
//        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
//        Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку", TypeTask.TASK, LocalDateTime.of(2022,9,1,10, 0), 90);
//        inMemoryTaskManager.creationTask(task1);
//
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(task1);
//
//        System.out.println(jsonString);
//
//        String str = "{\"name\":\"Забрать посылку\",\"description\":\"Сходить на почту и забрать посылку\",\"id\":1,\"status\":\"NEW\",\"typeTask\":\"TASK\",\"startTime\":{\"date\":{\"year\":2022,\"month\":9,\"day\":1},\"time\":{\"hour\":10,\"minute\":0,\"second\":0,\"nano\":0}},\"duration\":90}";
//        //Dog dog = gson.fromJson(jsonString, Dog.class);
//        Task taskFromJson = gson.fromJson(str, Task.class);
//        System.out.println(taskFromJson);
    }
}
