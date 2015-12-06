package search;

import dao.models.Computer;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputerSearcher {

    public ArrayList<Computer> searchComputers() {
        ArrayList<Computer> computers = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            try {
                InetAddress ip = InetAddress.getByName("192.168.1." + i);
                if (ip.isReachable(100)) {
                    Computer computer = new Computer();
                    computer.setIp(ip.getHostAddress());
                    computer.setName(ip.getHostName());
                    computers.add(computer);
                }
            } catch (IOException ex) {
                Logger.getLogger(ComputerSearcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return computers;
    }
}
