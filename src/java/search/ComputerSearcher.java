package search;

import dao.models.Computer;
import dao.models.Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//На сервере, который будет размещать данное приложение должна стоять ОС семейства Linux
//И установленные приложения nmap и nbtscan

public class ComputerSearcher {

    public List<Computer> searchComputers() {
        ArrayList<String> lines = runCommand("arp -a");
        ArrayList<Computer> computers = new ArrayList<>();
        String ipPart = "(2[0-5]{2}|(1|0)?\\d{1,2})";
        String patternString = String.join("\\.", ipPart, ipPart, ipPart, ipPart);
        Pattern pattern = Pattern.compile(patternString);
        lines.stream().forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                try {
                    String ip = matcher.group();
                    String hostName = runCommand("nbtscan -q " + ip).get(0).replaceAll("\\s+", " ").split(" ")[1];
                    Computer computer = new Computer();
                    computer.setIp(ip);
                    computer.setHostName(hostName);
                    computers.add(computer);
                } catch (IndexOutOfBoundsException e) {
                    //ingnore
                }
            }
        });
        return computers;
    }

    public List<Server> searchServers() {
        return null;
    }

    private ArrayList<String> runCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            ArrayList<String> result = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.add(line);
                }
            }
            return result;
        }   catch (IOException ex) {
            Logger.getLogger(ComputerSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void runCommandWithoutOutput(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(ComputerSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void scanNetwork() {
        runCommandWithoutOutput("nmap -sn 192.168.1.0/24");
    }
}
