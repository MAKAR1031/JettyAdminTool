package dao.models;

import java.sql.Date;

public class Application {

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
}
