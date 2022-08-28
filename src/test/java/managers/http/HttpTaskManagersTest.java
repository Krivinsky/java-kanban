package managers.http;

import exeptions.ManagerSaveException;
import managers.Managers;
import managers.TaskManagerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import server.KVServer;

import java.io.IOException;

class HttpTaskManagersTest extends TaskManagerTest<HttpTaskManager> {

    private KVServer kvServer;

    @BeforeEach
    void setUp() throws ManagerSaveException, IOException {
        taskManager = new HttpTaskManager(KVServer.PORT);
        kvServer = Managers.getDefaultKVServer();
        taskManagerSetUp();
        kvServer.start();
    }

    @AfterEach
    void tearDown() {
        kvServer.stop();
    }

    @Test
    void load() {
    }

    @Disabled
    @Test
    void save() {
    }
}