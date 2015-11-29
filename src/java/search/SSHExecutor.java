package search;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dao.JettyAdminToolDAO;
import dao.models.Computer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SSHExecutor {

    protected Session session;
    protected JettyAdminToolDAO dao;
    protected Computer computer;

    public SSHExecutor(boolean isServerContext) {
        dao = new JettyAdminToolDAO(isServerContext);
    }

    public void initSession(String userName, String password, int idComputer) throws JSchException {
        JSch jSch = new JSch();
        computer = dao.getComputer(idComputer);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session = jSch.getSession(userName, computer.getIp(), 22);
        session.setConfig(config);
        session.setPassword(password);
    }

    protected ArrayList<String> executeCommand(String command) {
        ArrayList<String> resuls = new ArrayList<>();
        if (session.isConnected()) {
            ChannelExec channel = null;
            try {
                channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
                channel.connect();
                String result;
                while ((result = reader.readLine()) != null) {
                    resuls.add(result);
                }
            } catch (JSchException | IOException ex) {
                Logger.getLogger(ServerSearcher.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (channel != null) {
                    channel.disconnect();
                }
            }
        }
        return resuls;
    }
}
