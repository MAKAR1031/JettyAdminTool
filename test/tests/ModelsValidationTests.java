package tests;

import dao.models.Application;
import dao.models.Computer;
import dao.models.Server;
import java.sql.Date;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class ModelsValidationTests {
    
    public ModelsValidationTests() {
        
    }
    
    @Test 
    public void computerTest() {
        Computer computer = new Computer();
        computer.setName("localhost");
        computer.setIp("127.0.0.1");
        assertTrue(computer.isValid());
    }
    
    @Test
    public void serverTest() {
        Server server = new Server();
        server.setPort(5000);
        server.setDirectory("/usr/jetty/");
        assertTrue(server.isValid());
    }
    
    @Test
    public void applicationTest() {
        Application application = new Application();
        application.setWar("HelloWorld");
        application.setContextRoot("HelloWorld/");
        application.setDeployerName("Sergey");
        application.setDeployDate(new Date(System.currentTimeMillis()));
        assertTrue(application.isValid());
    }
}
