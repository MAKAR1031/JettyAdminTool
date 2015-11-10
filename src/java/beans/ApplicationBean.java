package beans;

import dao.JettyAdminToolDAO;
import dao.models.Application;
import java.util.ArrayList;

public class ApplicationBean {

    private final JettyAdminToolDAO dao;

    private int idServer;
    private ArrayList<Application> applications;

    public ApplicationBean() {
        applications = new ArrayList<>();
        dao = new JettyAdminToolDAO();
    }

    public int getIdServer() {
        return idServer;
    }

    public void setIdServer(int idServer) {
        this.idServer = idServer;
        applications = dao.getApplicationsByServerID(idServer);
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }
}
