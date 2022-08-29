package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import managers.Managers;
import managers.TaskManager;
import managers.file.FileBackedTasksManager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static jdk.internal.util.xml.XMLStreamWriter.DEFAULT_CHARSET;

public class HttpTaskServer {

    public static  final int PORT = 8080;
    private final HttpServer server;
    private final Gson gson;
    private final FileBackedTasksManager taskManager;

    public HttpTaskServer() throws IOException {
        this(Managers.getDefault());
    }
    public HttpTaskServer(TaskManager taskManager) throws IOException {
        this.taskManager = Managers.getDefaultFileBackedTasks();
        gson = Managers.getGson();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/tasks", this::handler);
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
                    Epic epic = taskManager.getEpicFromId(id);  //для отладки заменить на inMemoryTaskManager для работы вернуть строчку выше
                    String response = gson.toJson(epic);
                    sendText(exchange, response);
                    return;
                } else {
                    String response = gson.toJson(taskManager.getEpicList());    //для отладки заменить на inMemoryTaskManager для работы вернуть строчку выше
                    sendText(exchange, response);
                    return;
                }
            }
            case "POST" : {
                try {
                    Epic epic = gson.fromJson(body, Epic.class);
                    System.out.println(epic);  //для проверки
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return;
            }
            case "DELETE" : {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
                    taskManager.deleteEpicOfId(id);  //для отладки заменить на inMemoryTaskManager для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили эпик" + id);
                } else {
                    taskManager.getEpicList();    //для отладки заменить на inMemoryTaskManager для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили все эпики");
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
                    Subtask subtask = taskManager.getSubtaskFromId(id);
                    String response = gson.toJson(subtask);
                    sendText(exchange, response);
                    return;
                } else {
                    String response = gson.toJson(taskManager.getSubtasksList());
                    sendText(exchange, response);
                    return;
                }
            }
            case "POST" : {
                try {
                    Subtask subtask = gson.fromJson(body, Subtask.class);  //не работает gson
                    System.out.println(subtask);  //для проверки
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return;
            }
            case "DELETE" : {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
                    taskManager.deleteSubtaskOfId(id);  //для работы вернуть строчку выше для отладки заменить на inMemoryTaskManager
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили подзадачу" + id);
                } else {
                    taskManager.cleanSubtaskList();    //для работы вернуть строчку выше для отладки заменить на inMemoryTaskManager
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили все подзадачи");
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
                    Task task = taskManager.getTaskFromId(id);   //// для отладки заменить на inMemoryTaskManager для работы вернуть строчку выше
                    String response = gson.toJson(task);
                    sendText(exchange, response);
                    return;
                } else {
                    String response = gson.toJson(taskManager.getTaskList());   //для отладки заменить на inMemoryTaskManager для работы вернуть строчку выше
                    sendText(exchange, response);
                    return;
                }
            }
            case "POST" : {
                try {
                    Task task = gson.fromJson(body, Task.class);
                    taskManager.updateTask(task, Status.NEW);
                    exchange.sendResponseHeaders(200,0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return;
            }
            case "DELETE" : {
                if (Objects.nonNull(query)) {
                    String idString = query.substring(3);
                    int id = Integer.parseInt(idString);
                    taskManager.deleteTaskOfId(id);
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили задачу" + id);
                } else {
                    taskManager.cleanTaskList();    //для работы вернуть строчку выше
                    exchange.sendResponseHeaders(200,0);
                    System.out.println("удалили вск задачи");
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
