package server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import managers.Managers;
import managers.TaskManager;
import managers.UserManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TypeTask;
import tasks.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpUserServerTest {
    private HttpUserServer httpUserServer;
    private UserManager userManager;
    private TaskManager taskManager;

    private Task task;
    private User user;

    private Gson gson = Managers.getGson();

    @BeforeEach
    void init() throws IOException {
        userManager = Managers.getDefaultUser();
        taskManager = userManager.getTaskManager();
        httpUserServer = new HttpUserServer(userManager);

        user = new User("Username");
        userManager.add(user);
        task = new Task("Task name", "Task description", TypeTask.TASK, LocalDateTime.of(2022,9,2,10, 0), 90, user);
        taskManager.creationTask(task);

        httpUserServer.start();
    }

    @AfterEach
    void stop() {
        httpUserServer.stop();
    }

    @Test
    void getUsers() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://localhost:8080/api/v1/users");
        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200,response.statusCode());

        Type userType = new TypeToken<ArrayList<User>>() {

        }.getType();
        List<User> users = gson.fromJson(response.body(), userType);
        assertNotNull(users, "Пользователи не возвращаются");
        assertEquals(1, users.size(), "Не верное количество пользователей");
        assertEquals(user, users.get(0), "Вернули не того пользователя");
    }

    @Test
    void getUserById() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://localhost:8080/api/v1/users/1");
        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200,response.statusCode());

        Type userType = new TypeToken<User>() {

        }.getType();
        User received = gson.fromJson(response.body(), userType);
        assertNotNull(received, "Пользователи не возвращаются");
        assertEquals(user, received, "Вернули не того пользователя");
    }

    @Test
    void getUserTasks() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://localhost:8080/api/v1/users/1/tasks");
        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200,response.statusCode());

        Type taskType = new TypeToken<List<User>>() {

        }.getType();
        List<Task> tasks = gson.fromJson(response.body(), taskType);
        assertNotNull(tasks, "Задачи не возвращаются");
        assertEquals(1, tasks.size(), "Не верное количество задач");
        assertEquals(task, tasks.get(0), "Вернули не ту задачу");
    }
}