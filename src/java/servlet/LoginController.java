package servlet;

import dao.JettyAdminToolDAO;
import dao.models.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {

    private final JettyAdminToolDAO dao;

    public LoginController() {
        this.dao = new JettyAdminToolDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                break;
            case "login_post":
                String userName = request.getParameter("username");
                if (!dao.userExist(userName)) {
                    request.setAttribute("errorMessage", "Пользователя с таким именем не существует");
                    request.getRequestDispatcher("/jsp/error_page.jsp").forward(request, response);
                } else {
                    String password = request.getParameter("password");
                    User user = dao.getUser(userName, password);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        request.getRequestDispatcher("/jsp/comp/computers.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "Вы ввели неверный пароль");
                        request.getRequestDispatcher("/jsp/error_page.jsp").forward(request, response);
                    }
                }
                break;
            case "logoff":
                request.getSession().invalidate();
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                break;
        }
    }

}
