package dao.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Computer implements Validatable {

    private int id;
    private String hostName;
    private String ip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean isValid() {
        String patternPart = "(2[0-5]{2}|(1|0)?\\d{1,2}|)";
        String pattern = String.join("\\.", patternPart, patternPart, patternPart, patternPart);
        Matcher matcher = Pattern.compile("^" + pattern + "$").matcher(ip);
        return hostName != null && ip != null && matcher.matches();
    }
}
