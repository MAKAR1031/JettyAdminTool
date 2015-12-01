package servlet;

import com.jcraft.jsch.JSchException;
import dao.JettyAdminToolDAO;
import dao.models.Application;
import dao.models.Computer;
import dao.models.Server;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import search.ApplicationSearcher;
import search.ComputerSearcher;
import search.ServerSearcher;

public class Controller extends HttpServlet {

    private final JettyAdminToolDAO dao = new JettyAdminToolDAO();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action") == null) {
            request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
        }
        switch (request.getParameter("action")) {
            case "login":
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                break;

            case "add_comp":
                request.getRequestDispatcher("/jsp/comp/add_comp.jsp").forward(request, response);
                break;

            case "to_comp":
                request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
                break;

            case "show_serv":
                int id = Integer.parseInt(request.getParameter("id_comp"));
                request.getSession().setAttribute("idComputer", id);
                request.getSession().setAttribute("ipComputer", dao.getComputer(id).getIp());
                request.getRequestDispatcher("/jsp/serv/servers.jsp").forward(request, response);
                break;

            case "comp_search":
                ComputerSearcher searcher = new ComputerSearcher();
                ArrayList<Computer> searchedComputers = searcher.searchComputers();
                ArrayList<Computer> currentComputers = dao.getAllComputers();
                searchedComputers.stream().forEach(computer -> {
                    if (!currentComputers.contains(computer)) {
                        dao.addComputer(computer);
                    }
                });
                request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
                break;

            case "rem_comp":
                id = Integer.parseInt(request.getParameter("id_comp"));
                request.getSession().setAttribute("idRemovedComputer", id);
                request.getRequestDispatcher("/jsp/comp/rem_comp.jsp").forward(request, response);
                break;

            case "add_serv":
                request.getRequestDispatcher("/jsp/serv/add_serv.jsp").forward(request, response);
                break;

            case "to_serv":
                request.getRequestDispatcher("/jsp/serv/servers.jsp").forward(request, response);
                break;

            case "show_apps":
                id = Integer.parseInt(request.getParameter("id_serv"));
                request.getSession().setAttribute("idServer", id);
                request.getSession().setAttribute("serverDir", dao.getServer(id).getDirectory());
                request.getRequestDispatcher("/jsp/app/applications.jsp").forward(request, response);
                break;

            case "serv_search":
                request.getRequestDispatcher("/jsp/serv/connect_to_comp.jsp").forward(request, response);
                break;

            case "rem_serv":
                id = Integer.parseInt(request.getParameter("id_serv"));
                request.getSession().setAttribute("idRemovedServer", id);
                request.getRequestDispatcher("/jsp/serv/rem_serv.jsp").forward(request, response);
                break;

            case "add_app":
                request.getRequestDispatcher("/jsp/app/add_app.jsp").forward(request, response);
                break;

            case "to_app":
                request.getRequestDispatcher("/jsp/app/applications.jsp").forward(request, response);
                break;

            case "rem_app":
                id = Integer.parseInt(request.getParameter("id_app"));
                request.getSession().setAttribute("idRemovedApplication", id);
                request.getRequestDispatcher("/jsp/app/rem_app.jsp").forward(request, response);
                break;

            default:
                request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("action")) {
            case "add_comp_post":
                String ip = request.getParameter("ip");
                String hostName = request.getParameter("host_name");
                Computer computer = new Computer();
                computer.setIp(ip);
                computer.setName(hostName);
                if (computer.isValid() && InetAddress.getByName(computer.getIp()).isReachable(1000)) {
                    if (dao.addComputer(computer) != -1) {
                        request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
                    }
                    request.setAttribute("errorMessage", "Произошла ошибка при добавлении компьютера в базу данных");
                    request.getRequestDispatcher("/jsp/error_page.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Вы ввели неверные данные");
                    request.getRequestDispatcher("/jsp/error_page.jsp").forward(request, response);
                }

                break;

            case "rem_com_post":
                int id = (int) request.getSession().getAttribute("idRemovedComputer");
                dao.removeComputer(id);
                request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
                break;

            case "add_serv_post":
                int idComputer = (int) request.getSession().getAttribute("idComputer");
                String directory = request.getParameter("directory");
                int port = Integer.parseInt(request.getParameter("port"));
                String comment = request.getParameter("comment");
                Server server = new Server();
                server.setDirectory(directory);
                server.setPort(port);
                server.setComment(comment);
                dao.addServer(server, idComputer);
                request.getRequestDispatcher("/jsp/serv/servers.jsp").forward(request, response);
                break;

            case "serv_search_post":
                ServerSearcher serverSearcher = new ServerSearcher();
                String userName = request.getParameter("user_name");
                String password = request.getParameter("password");
                idComputer = (int) request.getSession().getAttribute("idComputer");
                try {
                    serverSearcher.initSession(userName, password, idComputer);
                } catch (JSchException ex) {
                    request.setAttribute("errorMessage", "Ошибка при подключении...");
                    request.getRequestDispatcher("/jsp/error_page.jsp");
                }
                ArrayList<Server> searchedServers = serverSearcher.searchServers();
                ArrayList<Server> currentServers = dao.getServersByComputerID(idComputer);
                searchedServers.stream().forEach(serv -> {
                    if (!currentServers.contains(serv)) {
                        dao.addServer(serv, idComputer);
                    }
                });
                ArrayList<Server> allServers = dao.getServersByComputerID(idComputer);
                allServers.stream().forEach(serv -> {
                    if (searchedServers.contains(serv)) {
                        ApplicationSearcher applicationSearcher = new ApplicationSearcher(serv.getId());
                        try {
                            applicationSearcher.initSession(userName, password, idComputer);
                        } catch (JSchException ex) {
                            //ignore
                        }
                        ArrayList<Application> searchedApplications = applicationSearcher.searchApplications();
                        ArrayList<Application> currentApplications = dao.getApplicationsByServerID(serv.getId());
                        searchedApplications.stream().forEach(app -> {
                            if (!currentApplications.contains(app)) {
                                dao.addApplication(app, serv.getId());
                            }
                        });
                    }
                });
                request.getRequestDispatcher("/jsp/serv/servers.jsp").forward(request, response);
                break;

            case "rem_serv_post":
                id = (int) request.getSession().getAttribute("idRemovedServer");
                dao.removeServer(id);
                request.getRequestDispatcher("/jsp/serv/servers.jsp").forward(request, response);
                break;

            case "add_app_post":
                try {
                    int idServer = (int) request.getSession().getAttribute("idServer");
                    String war = request.getParameter("war");
                    String contextRoot = request.getParameter("context_root");
                    String deployerName = request.getParameter("deployer_name");
                    Date deployDate = new Date(dateFormat.parse(request.getParameter("deploy_date")).getTime());
                    comment = request.getParameter("comment");
                    Application app = new Application();
                    app.setWar(war);
                    app.setContextRoot(contextRoot);
                    app.setDeployerName(deployerName);
                    app.setDeployDate(deployDate);
                    app.setComment(comment);
                    dao.addApplication(app, idServer);
                    request.getRequestDispatcher("/jsp/app/applications.jsp").forward(request, response);
                } catch (ParseException e) {
                    throw new ServletException("Неверная дата...", e.getCause());
                }
                break;

            case "rem_app_post":
                id = (int) request.getSession().getAttribute("idRemovedApplication");
                dao.removeApplication(id);
                request.getRequestDispatcher("/jsp/comp/applications.jsp").forward(request, response);
                break;
                
            default:
                request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
                break;
        }
    }
}
