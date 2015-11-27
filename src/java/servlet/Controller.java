package servlet;

import dao.JettyAdminToolDAO;
import dao.models.Application;
import dao.models.Computer;
import dao.models.Server;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

    private final JettyAdminToolDAO dao = new JettyAdminToolDAO();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action") == null) {
            request.getRequestDispatcher("/jsp/computers.jsp").forward(request, response);
        }
        switch (request.getParameter("action")) {
            case "login":
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                break;
                
            case "add_comp":
                request.getRequestDispatcher("/jsp/add_comp.jsp").forward(request, response);
                break;
                
            case "to_comp":
                request.getRequestDispatcher("/jsp/computers.jsp").forward(request, response);
                break;
                
            case "show_serv":
                int id = Integer.parseInt(request.getParameter("id_comp"));
                request.getSession().setAttribute("idComputer", id);
                request.getRequestDispatcher("/jsp/servers.jsp").forward(request, response);
                break;
                
            case "rem_comp":
                id = Integer.parseInt(request.getParameter("id_comp"));
                request.getSession().setAttribute("idRemovedComputer", id);
                request.getRequestDispatcher("/jsp/rem_comp.jsp").forward(request, response);
                break;
                
            case "add_serv":
                request.getRequestDispatcher("/jsp/add_serv.jsp").forward(request, response);
                break;
                
            case "to_serv":
                request.getRequestDispatcher("/jsp/servers.jsp").forward(request, response);
                break;
                
            case "show_apps":
                id = Integer.parseInt(request.getParameter("id_serv"));
                request.getSession().setAttribute("idServer", id);
                request.getRequestDispatcher("/jsp/applications.jsp").forward(request, response);
                break;
                
            case "rem_serv":
                id = Integer.parseInt(request.getParameter("id_serv"));
                request.getSession().setAttribute("idRemovedServer", id);
                request.getRequestDispatcher("/jsp/rem_serv.jsp").forward(request, response);
                break;
                
            case "add_app":
                request.getRequestDispatcher("/jsp/add_app.jsp").forward(request, response);
                break;
                
            case "to_app":
                request.getRequestDispatcher("/jsp/applications.jsp").forward(request, response);
                break;
                
            case "rem_app":
                id = Integer.parseInt(request.getParameter("id_app"));
                request.getSession().setAttribute("idRemovedApplication", id);
                request.getRequestDispatcher("/jsp/rem_app.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/jsp/computers.jsp").forward(request, response);
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
                computer.setHostName(hostName);
                dao.addComputer(computer);
                request.getRequestDispatcher("/jsp/computers.jsp").forward(request, response);
                break;
                
            case "rem_com_post":
                int id = (int) request.getSession().getAttribute("idRemovedComputer");
                dao.removeComputer(id);
                request.getRequestDispatcher("/jsp/computers.jsp").forward(request, response);
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
                request.getRequestDispatcher("/jsp/servers.jsp").forward(request, response);
                break;

            case "rem_serv_post":
                id = (int) request.getSession().getAttribute("idRemovedServer");
                dao.removeServer(id);
                request.getRequestDispatcher("/jsp/servers.jsp").forward(request, response);
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
                    request.getRequestDispatcher("/jsp/applications.jsp").forward(request, response);
                } catch (ParseException e) {
                    throw new ServletException("Неверная дата...", e.getCause());
                }
                break;

            case "rem_app_post":
                id = (int) request.getSession().getAttribute("idRemovedApplication");
                dao.removeApplication(id);
                request.getRequestDispatcher("/jsp/applications.jsp").forward(request, response);
                break;
        }
    }
}