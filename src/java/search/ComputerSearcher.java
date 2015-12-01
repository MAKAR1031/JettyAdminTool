package search;

import dao.models.Computer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//На сервере, который будет размещать данное приложение должна стоять ОС семейства Linux
//И установленные приложение nmap
public class ComputerSearcher {

    public ArrayList<Computer> searchComputers() {
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
                    Computer computer = new Computer();
                    computer.setIp(ip);
                    computers.add(computer);
                } catch (IndexOutOfBoundsException e) {
                    //ingnore
                }
            }
        });
        return computers;
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
        } catch (IOException ex) {
            return null;
        }
    }

    public void scanNetwork() {
        runCommand("nmap -sn 192.168.1.0/24");
    }
}
