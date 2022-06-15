package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.Account;
import service.AccountService;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        
        if (user_name != null && !user_name.equals("")) {
            response.sendRedirect("welcome");
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = request.getParameter("username_input");
        String password = request.getParameter("password_input");
        request.setAttribute("username_input", user_name);
        
        AccountService accountService = new AccountService();
        Account account = accountService.login(user_name, password);
        
        if (account == null) {
            request.setAttribute("invalidMessage", "Invalid username or password. Please login again!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            return;
        } else {
            session.setAttribute("user_name", user_name);
            response.sendRedirect("welcome");
            return;
        }
    }
}
