package dao.models;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Computer implements Validatable {

    private int id;
    private String name;
    private String ip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return name != null && ip != null && matcher.matches();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.ip);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Computer other = (Computer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.ip, other.ip);
    }
}
