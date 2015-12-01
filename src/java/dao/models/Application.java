package dao.models;

import java.sql.Date;
import java.util.Objects;

public class Application implements Validatable {

    private int id;
    private String war;
    private String contextRoot;
    private String deployerName;
    private Date deployDate;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWar() {
        return war;
    }

    public void setWar(String war) {
        this.war = war;
    }

    public String getContextRoot() {
        return contextRoot;
    }

    public void setContextRoot(String contextRoot) {
        this.contextRoot = contextRoot;
    }

    public String getDeployerName() {
        return deployerName;
    }

    public void setDeployerName(String deployerName) {
        this.deployerName = deployerName;
    }

    public Date getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(Date deployDate) {
        this.deployDate = deployDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean isValid() {
        return isValidString(war)
                && isValidString(contextRoot)
                && isValidString(deployerName)
                && deployDate != null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.war);
        hash = 37 * hash + Objects.hashCode(this.contextRoot);
        hash = 37 * hash + Objects.hashCode(this.deployerName);
        hash = 37 * hash + Objects.hashCode(this.deployDate);
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
        final Application other = (Application) obj;
        if (!Objects.equals(this.war, other.war)) {
            return false;
        }
        if (!Objects.equals(this.contextRoot, other.contextRoot)) {
            return false;
        }
        if (!Objects.equals(this.deployerName, other.deployerName)) {
            return false;
        }
        return Objects.equals(this.deployDate, other.deployDate);
    }
    
    
}