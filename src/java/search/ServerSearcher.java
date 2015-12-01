package search;

import com.jcraft.jsch.JSchException;
import dao.models.Server;
import java.io.File;
import java.util.ArrayList;

public class ServerSearcher extends SSHExecutor {

    public ServerSearcher() {
        super(true);
    }
    
    public ServerSearcher(boolean isServerContext) {
        super(isServerContext);
    }

    public ArrayList<Server> searchServers() {
        ArrayList<Server> servers = new ArrayList<>();
        try {
            session.connect();
            for (String line : executeCommand("find / -name \"start.jar\" -print")) {
                Server server = new Server();
                server.setDirectory(line.substring(0, line.lastIndexOf(File.separator) + 1));
                ArrayList<String> commandResults
                        = executeCommand("cat " + server.getDirectory() + "start.ini | grep \"port=\"");
                if (commandResults.isEmpty()) {
                    continue;
                }
                String portSettingString = commandResults.get(0);
                int port = Integer.parseInt(portSettingString.split("=")[1]);
                server.setPort(port);
                servers.add(server);
            }
        } catch (JSchException ex) {
            // ignore
        } finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return servers;
    }
}
