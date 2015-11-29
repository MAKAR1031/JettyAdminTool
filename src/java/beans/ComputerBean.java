package beans;

import dao.JettyAdminToolDAO;
import dao.models.Computer;
import java.util.ArrayList;

public class ComputerBean {
    private ArrayList<Computer> computers;

    public ComputerBean() {
        JettyAdminToolDAO dao = new JettyAdminToolDAO();
        computers = dao.getAllComputers();
    }

    public ComputerBean(ArrayList<Computer> computers) {
        this.computers = computers;
    }
    
    public ArrayList<Computer> getComputers() {
        return computers;
    }

    public void setComputers(ArrayList<Computer> computers) {
        this.computers = computers;
    }
}
