package dao;

import dao.models.Application;
import dao.models.Computer;
import dao.models.Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JettyAdminToolDAO {

    private Connection connection;
    private boolean isServerContext;

    public JettyAdminToolDAO() {
        this(true);
    }

    public JettyAdminToolDAO(boolean isTesting) {
        this.isServerContext = isTesting;
    }

    // <editor-fold desc="Служебные методы">
    private void connect() throws Exception {
        try {
            if (isServerContext) {
                InitialContext initialContext = new InitialContext();
                DataSource dataSource = (DataSource) initialContext.lookup("jdbc/JettyAdminToolDataSource");
                connection = dataSource.getConnection();
            } else {
                connection = DriverManager
                        .getConnection(
                                "jdbc:postgresql://localhost:5432/jetty_admin_tool_data",
                                "postgres",
                                "root");
            }
        } catch (NamingException | SQLException e) {
            throw new Exception("Connection error");
        }
    }

    private void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }

    // </editor-fold>
    // <editor-fold desc="Computers CRUD">
    public int addComputer(Computer computer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO computers (ip, name) VALUES (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, computer.getIp());
            statement.setString(2, computer.getName());
            statement.execute();
            ResultSet set = statement.getGeneratedKeys();
            if (set.next()) {
                return set.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            disconnect();
        }
        return -1;
    }

    public Computer getComputer(int idComputer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT ip, name FROM computers WHERE id_computer=?");
            statement.setInt(1, idComputer);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                Computer computer = new Computer();
                computer.setId(idComputer);
                computer.setIp(set.getString(1));
                computer.setName(set.getString(2));
                return computer;
            }
        } catch (Exception e) {
        } finally {
            disconnect();
        }
        return null;
    }

    public ArrayList<Computer> getAllComputers() {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT id_computer, ip, name FROM computers");
            ResultSet set = statement.executeQuery();
            ArrayList<Computer> computers = new ArrayList<>();
            while (set.next()) {
                Computer computer = new Computer();
                computer.setId(set.getInt(1));
                computer.setIp(set.getString(2));
                computer.setName(set.getString(3));
                computers.add(computer);
            }
            return computers;
        } catch (Exception ex) {

        } finally {
            disconnect();
        }
        return new ArrayList();
    }

    public boolean editComputer(int id, Computer computer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE computers SET(ip, name) = (?,?) WHERE id_computer=?");
            statement.setString(1, computer.getIp());
            statement.setString(2, computer.getName());
            statement.setInt(3, id);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }

    public boolean removeComputer(int idComputer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM computers WHERE id_computer=?");
            statement.setInt(1, idComputer);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }

    // </editor-fold>
    // <editor-fold desc="Servers CRUD">
    public int addServer(Server server, int idComputer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO "
                    + "servers (directory, port, server_comment, id_computer) "
                    + "VALUES(?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, server.getDirectory());
            statement.setInt(2, server.getPort());
            statement.setString(3, server.getComment());
            statement.setInt(4, idComputer);
            statement.executeQuery();
            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                return key.getInt(1);
            }
        } catch (Exception e) {

        } finally {
            disconnect();
        }
        return -1;
    }

    public Server getServer(int idServer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT directory, port, server_comment"
                    + " FROM servers"
                    + " WHERE id_server=?");
            statement.setInt(1, idServer);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                Server server = new Server();
                server.setId(idServer);
                server.setDirectory(set.getString(1));
                server.setPort(set.getInt(2));
                server.setComment(set.getString(3));
                return server;
            }
        } catch (Exception e) {
        } finally {
            disconnect();
        }
        return null;
    }

    public ArrayList<Server> getServersByComputerID(int idComputer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT id_server, directory, port, server_comment "
                    + "FROM servers "
                    + "WHERE id_computer=?");
            statement.setInt(1, idComputer);
            ResultSet set = statement.executeQuery();
            ArrayList<Server> servers = new ArrayList<>();
            while (set.next()) {
                Server server = new Server();
                server.setId(set.getInt(1));
                server.setDirectory(set.getString(2));
                server.setPort(set.getInt(3));
                server.setComment(set.getString(4) == null ? "" : set.getString(4));
                servers.add(server);
            }
            return servers;
        } catch (Exception e) {

        } finally {
            disconnect();
        }
        return new ArrayList<>();
    }

    public boolean editServer(int idServer, Server server) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE servers "
                    + "SET (directory, port, server_comment) = (?,?,?) "
                    + "WHERE id_server=?");
            statement.setString(1, server.getDirectory());
            statement.setInt(2, server.getPort());
            statement.setString(3, server.getComment());
            statement.setInt(4, idServer);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }

    public boolean removeServer(int idServer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM servers WHERE id_server=?");
            statement.setInt(1, idServer);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }

    // </editor-fold>
    // <editor-fold desc="Applications CRUD">
    public int addApplication(Application application, int idServer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO applications "
                    + "(war, context_root, deployer_name, deploy_date, application_comment, id_server) "
                    + "VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, application.getWar());
            statement.setString(2, application.getContextRoot());
            statement.setString(3, application.getDeployerName());
            statement.setDate(4, application.getDeployDate());
            statement.setString(5, application.getComment());
            statement.setInt(6, idServer);
            statement.execute();
            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                return key.getInt(1);
            }
        } catch (Exception e) {

        } finally {
            disconnect();
        }
        return -1;
    }

    public ArrayList<Application> getApplicationsByServerID(int idServer) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT "
                    + "id_application, war, context_root, deployer_name, deploy_date, application_comment "
                    + "FROM applications "
                    + "WHERE id_server=?");
            statement.setInt(1, idServer);
            ResultSet set = statement.executeQuery();
            ArrayList<Application> applications = new ArrayList<>();
            while (set.next()) {
                Application application = new Application();
                application.setId(set.getInt(1));
                application.setWar(set.getString(2));
                application.setContextRoot(set.getString(3));
                application.setDeployerName(set.getString(4));
                application.setDeployDate(set.getDate(5));
                application.setComment(set.getString(6) == null ? "" : set.getString(6));
                applications.add(application);
            }
            return applications;
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            disconnect();
        }
    }

    public boolean editApplication(int idApplication, Application application) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE applications "
                    + "SET(war, context_root, deployer_name, deploy_date, application_comment) = "
                    + "(?,?,?,?,?) "
                    + "WHERE id_application=?");
            statement.setString(1, application.getWar());
            statement.setString(2, application.getContextRoot());
            statement.setString(3, application.getDeployerName());
            statement.setDate(4, application.getDeployDate());
            statement.setString(5, application.getComment());
            statement.setInt(6, idApplication);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }

    public boolean removeApplication(int idApplication) {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM applications WHERE id_application=?");
            statement.setInt(1, idApplication);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }

    // </editor-fold>   
    //<editor-fold desc="Users and Roles CRUD">
    //</editor-fold>
}
