package search;

import com.jcraft.jsch.JSchException;
import dao.models.Application;
import dao.models.Server;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationSearcher extends SSHExecutor {

    private final Server server;

    public ApplicationSearcher(int idServer) {
        super(true);
        this.server = dao.getServer(idServer);
    }
    
    public ApplicationSearcher(boolean isServerContext, int idServer) throws JSchException {
        super(isServerContext);
        server = dao.getServer(idServer);
    }

    public ArrayList<Application> searchApplications() {
        ArrayList<Application> applications = new ArrayList<>();
        try {
            session.connect();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String command = "ls " + server.getDirectory() + File.separator + "webapps" + File.separator + "*.war";
            for (String appPath : executeCommand(command)) {
                try {
                    Application application = new Application();
                    application.setWar(appPath.substring(appPath.lastIndexOf(File.separator) + 1,
                            appPath.lastIndexOf(".")));
                    application.setContextRoot("/" + application.getWar());
                    String fileInfo = executeCommand("stat --format=\"%U:%z\" " + appPath).get(0);
                    application.setDeployerName(fileInfo.split(":")[0]);
                    application.setDeployDate(new Date(dateFormat.parse(fileInfo.split(":")[1]).getTime()));
                    applications.add(application);
                } catch (ParseException ex) {
                    Logger.getLogger(ApplicationSearcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (JSchException e) {

        } finally {
            session.disconnect();
        }
        return applications;
    }
}
