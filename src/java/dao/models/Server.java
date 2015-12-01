package dao.models;

import java.util.Objects;

public class Server implements Validatable{
    private int id;
    private String directory;
    private int port;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean isValid() {
        return directory != null && port > 0 && port <= 65536;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.directory);
        hash = 83 * hash + this.port;
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
        final Server other = (Server) obj;
        if (!Objects.equals(this.directory, other.directory)) {
            return false;
        }
        return this.port == other.port;
    }
    
    
}
