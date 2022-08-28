package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import exeptions.ManagerSaveException;
import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import managers.file.FileBackedTasksManager;
import managers.memory.InMemoryTaskManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TypeTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static jdk.internal.util.xml.XMLStreamWriter.DEFAULT_CHARSET;

public class HttpTaskServer {

    public static  final int PORT = 8080;
    private HttpServer server;
    private Gson gson;
    private FileBackedTasksManager taskManager;

    public HttpTaskServer() throws ManagerSaveException, IOException {
        this(Managers.getDefault());
    }
    public HttpTaskServer(TaskManager taskManager) throws IOException {
//        this.taskManager = taskManager; //попробую заменить на
        this.taskManager = Managers.getDefaultFileBackedTasks();
        gson = Managers.getGson();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/tasks", this::handler);
    }


    static InMemoryTaskManager inMemoryTaskManager;
    static Task task1 = new Task("Забрать посылку","Сходить на почту и забрать посылку", TypeTask.TASK, LocalDateTime.of(2022,9,1,10, 0), 90);
    static Task task2 = new Task("Заменить масло", "Заменить моторное масло в машине", TypeTask.TASK, LocalDateTime.of(2022,9,2,10, 30), 90);



    public static void main(String[] args) throws IOException, ManagerSaveException {

        inMemoryTaskManager = new InMemoryTaskManager();
        inMemoryTaskManager.creationTask(task1);
        inMemoryTaskManager.creationTask(task2);

        final HttpTaskServer httpTaskServer = new HttpTaskServer();
        httpTaskServer.start();
//        httpTaskServer.stop();
    }

    private void handler(HttpExchange exchange) {
        try {
            System.out.println("/\ntasks" + exchange.getRequestURI());
            final String path = exchange.getRequestURI().getPath().replaceFirst("/tasks/", "");
            switch (path) {
                case "task":
                    handleTasks(exchange);
                    break;
                case "subtask":
                    handleSubtask(exchange);
                    break;
                case "epic":
                    handleEpic(exchange);
                    break;
                case "history" :
                    handleHistory(exchange);
                    break;
                default:
            }
        } catch (IOException exception) {
            System.out.println("Ошибка при обработке запроса");
        } finally {
            exchange.close();
        }
    }

    private void handleHistory(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery();
        if (requestMethod.equals("GET")) {
            List<Task> history = taskManager.inMemoryHistoryManager.getHistory();
            String response = gson.toJson(history);
            sendText(exchange, response);
        }

    }

    private void handleEpic(HttpExchange exchange) throws IOException {

        String requestMethod = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery(); //параметры запроса
        InputStream inputStream = exchange.getRequestBody();
        String body = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);
        switch (requestMethod) {

            case "GET": {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
//                    Epic epic = taskManager.getEpicFromId(id);   //для отладки заменить на inMemoryTaskManager
                    Epic epic = inMemoryTaskManager.getEpicFromId(id);  //для работы вернуть строчку выше
                    String response = gson.toJson(epic);
                    sendText(exchange, response);
                    return;
                } else {
//                    String response = gson.toJson(taskManager.getTaskList());   //для отладки заменить на inMemoryTaskManager
                    String response = gson.toJson(inMemoryTaskManager.getEpicList());    //для работы вернуть строчку выше
                    sendText(exchange, response);
                    return;
                }
            }
            case "POST" : {
                if (Objects.nonNull(body)) {
                    try {
                        Epic epic = gson.fromJson(body, Epic.class);  //не работает gson
                        System.out.println(epic);  //для проверки
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                    return;
                } else {
                    System.out.println("Ошибка тела запроса");
                    return;
                }
            }
            case "DELETE" : {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
//                    taskManager.deleteEpicOfId(id);   //для отладки заменить на inMemoryTaskManager
                    inMemoryTaskManager.deleteEpicOfId(id);  //для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили эпик" + id);
                    return;
                } else {
//                    taskManager.getEpicList();  //для отладки заменить на inMemoryTaskManager
                    inMemoryTaskManager.getEpicList();    //для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили все эпики");
                    return;
                }
            }
        }
    }

    private void handleSubtask(HttpExchange exchange) throws IOException {

        String requestMethod = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery(); //параметры запроса
        InputStream inputStream = exchange.getRequestBody();
        String body = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);
        switch (requestMethod) {

            case "GET": {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
//                    Subtask subtask = taskManager.getSubtaskFromId(id);   //для отладки заменить на inMemoryTaskManager
                    Subtask subtask = inMemoryTaskManager.getSubtaskFromId(id);  //для работы вернуть строчку выше
                    String response = gson.toJson(subtask);
                    sendText(exchange, response);
                    return;
                } else {
//                    String response = gson.toJson(taskManager.getTaskList());   //для отладки заменить на inMemoryTaskManager
                    String response = gson.toJson(inMemoryTaskManager.getSubtasksList());    //для работы вернуть строчку выше
                    sendText(exchange, response);
                    return;
                }
            }
            case "POST" : {
                if (Objects.nonNull(body)) {
                    try {
                        Subtask subtask = gson.fromJson(body, Subtask.class);  //не работает gson
                        System.out.println(subtask);  //для проверки
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                    return;
                } else {
                    System.out.println("Ошибка тела запроса");
                    return;
                }
            }
            case "DELETE" : {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
//                    taskManager.deleteSubtaskOfId(id);   //для отладки заменить на inMemoryTaskManager
                    inMemoryTaskManager.deleteSubtaskOfId(id);  //для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили подзадачу" + id);
                    return;
                } else {
//                    taskManager.cleanSubtaskList();  //для отладки заменить на inMemoryTaskManager
                    inMemoryTaskManager.cleanSubtaskList();    //для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили все подзадачи");
                    return;
                }
            }
        }
    }

    private void handleTasks(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery(); //параметры запроса
        InputStream inputStream = exchange.getRequestBody();
        String body = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);
        switch (requestMethod) {

            case "GET": {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
                    Task task = taskManager.getTaskFromId(id);   //для отладки заменить на inMemoryTaskManager
//                    Task task = inMemoryTaskManager.getTaskFromId(id);  //для работы вернуть строчку выше
                    String response = gson.toJson(task);
                    sendText(exchange, response);
                    return;
                } else {
                    String response = gson.toJson(taskManager.getTaskList());   //для отладки заменить на inMemoryTaskManager
//                    String response = gson.toJson(inMemoryTaskManager.getTaskList());    //для работы вернуть строчку выше
                    sendText(exchange, response);
                    return;
                }
            }
            case "POST" : {
                if (Objects.nonNull(body)) {
                    try {         // try для обхода ошибки gson
                        Task task = gson.fromJson(body, Task.class);  //ошибка в библиотеке gson
                        String str = readText(exchange);
                        System.out.println(task);  //для проверки
                        System.out.println(str);  //для проверки
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                    return;
                } else {
                    System.out.println("Ошибка тела запроса");
                    return;
                }
            }
            case "DELETE" : {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
//                    taskManager.deleteTaskOfId(id);   //для отладки заменить на inMemoryTaskManager
                    inMemoryTaskManager.deleteTaskOfId(id);  //для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили задачу" + id);
                    return;

                } else {
//                    taskManager.cleanTaskList();  //для отладки заменить на inMemoryTaskManager
                    inMemoryTaskManager.cleanTaskList();    //для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили вск задачи");
                    return;
                }
            }
        }
    }

    public void start() {
        System.out.println("Запускаем сервер Задач " + PORT);
        System.out.println("http://localhost:" + PORT + "/tasks");
        server.start();
    }

    public void stop() {
        server.stop(0);
        System.out.println("Остановили сервер на порту" + PORT);
    }

    protected String readText(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), UTF_8);
    }

    protected void sendText(HttpExchange exchange, String text) throws IOException {
        byte[] resp = text.getBytes(UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, resp.length);
        exchange.getResponseBody().write(resp);
    }
}
