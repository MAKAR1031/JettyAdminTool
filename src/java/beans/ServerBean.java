package beans;

import dao.JettyAdminToolDAO;
import dao.models.Server;
import java.util.ArrayList;

public class ServerBean {

    private final JettyAdminToolDAO dao;

    private int idComputer;
    private ArrayList<Server> servers;

    public ServerBean() {
        servers = new ArrayList<>();
        dao = new JettyAdminToolDAO();
    }

    public int getIdComputer() {
        return idComputer;
    }

    public void setIdComputer(int idComputer) {
        servers = dao.getServersByComputerID(idComputer);
        this.idComputer = idComputer;
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    public void setServers(ArrayList<Server> servers) {
        this.servers = servers;
    }
}
