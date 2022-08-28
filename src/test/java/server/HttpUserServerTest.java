//package server;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import exeptions.ManagerSaveException;
//import managers.Managers;
//import managers.TaskManager;
//import managers.UserManager;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import tasks.Task;
//import tasks.TypeTask;
//import tasks.User;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class HttpUserServerTest {
//    private HttpUserServer server;
//    private UserManager userManager;
//    private TaskManager taskManager;
//
//    private Task task;
//    private User user;
//
//    private Gson gson = Managers.getGson();
//
//    @BeforeEach
//    void init() throws IOException, ManagerSaveException {
//        userManager = Managers.getDefaultUser();
//        taskManager = userManager.getTaskManager();
//        server = new HttpUserServer(userManager);
//
//        user = new User("Username");
//        userManager.add(user);
//        task = new Task("Task name",
//                        "Task description",
//                        TypeTask.TASK,
//                        LocalDateTime.of(2022,9,2,10, 0), 90,
//                        user);
//        taskManager.creationTask(task);
//        server.start();
//    }
//
//    @AfterEach
//    void stop() {
//        server.stop();
//    }
//
//    @Test
//    void getUsers() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        URI url = URI.create("http://localhost:8080/api/v1/users");
//        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        assertEquals(200,response.statusCode());
//
//        Type userType = new TypeToken<ArrayList<User>>() {
//        }.getType();
//
//        List<User> users = gson.fromJson(response.body(), userType);
//        assertNotNull(users, "Пользователи не возвращаются");
//        assertEquals(1, users.size(), "Не верное количество пользователей");
//        assertEquals(user, users.get(0), "Вернули не того пользователя");
//    }
//
//    @Test
//    void getUserById() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        URI url = URI.create("http://localhost:8080/api/v1/users/1");
//        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        assertEquals(200,response.statusCode());
//
//        Type userType = new TypeToken<User>() {
//        }.getType();
//
//        User received = gson.fromJson(response.body(), userType);
//        assertNotNull(received, "Пользователи не возвращаются");
//        assertEquals(user, received, "Вернули не того пользователя");
//    }
//
//    @Test
//    void getUserTasks() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        URI url = URI.create("http://localhost:8080/api/v1/users/1/tasks");
//        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        assertEquals(200,response.statusCode());
//
//        Type taskType = new TypeToken<List<Task>>() {
//        }.getType();
//
//        List<Task> tasks = gson.fromJson(response.body(), taskType);
//        assertNotNull(tasks, "Задачи не возвращаются");
//        assertEquals(1, tasks.size(), "Не верное количество задач");
//
//        assertEquals(task.getId(), tasks.get(0).getId(), "getUserTasks - не пройден");
//        assertEquals(task.getName(), tasks.get(0).getName(), "getUserTasks - не пройден");
//        assertEquals(task.getStatus(), tasks.get(0).getStatus(), "getUserTasks - не пройден");
//        assertEquals(task.getDescription(), tasks.get(0).getDescription(), "getUserTasks - не пройден");
//        assertEquals(task.getStartTime(), tasks.get(0).getStartTime(), "getUserTasks - не пройден");
//        assertEquals(task.getEndTime(), tasks.get(0).getEndTime(), "getUserTasks - не пройден");
//    }
//
//    @Test
//    void deleteUser() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        URI url = URI.create("http://localhost:8080/api/v1/users/1");
//        HttpRequest request = HttpRequest.newBuilder().uri(url).DELETE().build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        assertEquals(200,response.statusCode());
//
//
//
//        client = HttpClient.newHttpClient();
//        url = URI.create("http://localhost:8080/api/v1/users");
//        request = HttpRequest.newBuilder().uri(url).GET().build();
//
//        response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        assertEquals(200,response.statusCode());
//
//        Type userType = new TypeToken<ArrayList<User>>() {
//        }.getType();
//
//        List<User> users = gson.fromJson(response.body(), userType);
//        assertNotNull(users, "Пользователи не возвращаются");
//        assertEquals(0, users.size(), "Не верное количество пользователей");
//    }
//}