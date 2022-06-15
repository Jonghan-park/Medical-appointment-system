package servlets;

import java.io.IOException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.Account;
import service.AccountService;
<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;
=======
import java.util.regex.*;
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class ForgotServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account forgotAccount = (Account) session.getAttribute("forgotAccount");
        String user_name = (String) session.getAttribute("user_name");
        String query = null;

        if (user_name != null && !user_name.equals("")) {
            response.sendRedirect("welcome");
            return;
        } else {
            try {
                query = request.getQueryString().toString();
            } catch (Exception ex) {
                getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                return;
            }

            if (forgotAccount == null) {
                request.setAttribute("resetMessage", "You should re-send email to find password.");
                getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                return;
            } else if (forgotAccount.getReset_password_uuid() != null) {
                if (query.contains(forgotAccount.getReset_password_uuid())) {
                    getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("resetMessage", "You should re-send email to find password.");
                    getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                    return;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account forgotAccount = (Account) session.getAttribute("forgotAccount");
        String sessionUrl = (String) session.getAttribute("home_url");
        AccountService accountService = new AccountService();
        String url = request.getRequestURL().toString();
        String path = getServletContext().getRealPath("/WEB-INF");
        String action = request.getParameter("action");

        if (action.equals("findPwd")) {
            String email = request.getParameter("resetEmail");
            if (email == null || email.equals("")) {
                request.setAttribute("resetMessage", "You should fill in the blank.");

                getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                return;
            } else {
<<<<<<< HEAD
=======

>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
                Account account2 = accountService.resetPassword(email, path, url);
                if (account2 != null) {
                    session.setAttribute("forgotAccount", account2);
                    request.setAttribute("resetMessage", "We sent an email to your email address.");
                    request.setAttribute("resetMessage2", "(If you haven't got the email, please check your junk mail.)");
                    getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("resetMessage", "Check account information again.");
                    getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                    return;
                }

            }
        } else if (action.equals("newPassword")) {
            String newPassword = request.getParameter("resetPassword");
            String resetConfirmPassword = request.getParameter("resetConfirmPassword");
            String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,}$";
            boolean checkPassword = true;

            Pattern p3 = Pattern.compile(regexPassword);
            Matcher m3 = p3.matcher(newPassword);
            checkPassword = m3.matches();

            if (newPassword != null && !newPassword.equals("") && resetConfirmPassword != null && !resetConfirmPassword.equals("")
                    && newPassword.equals(resetConfirmPassword)) {
                accountService.changePassword(forgotAccount.getReset_password_uuid(), newPassword);
                request.setAttribute("message", "You have successfully changed your password.");
                session.setAttribute("forgotAccount", null);
<<<<<<< HEAD
=======

>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else if (newPassword != null && !newPassword.equals("") && resetConfirmPassword != null && !resetConfirmPassword.equals("")
                    && !newPassword.equals(resetConfirmPassword)) {
                request.setAttribute("newPswMessage", "Password confirmation does not match.");

                getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
                return;
            } else if (checkPassword == false) {
                request.setAttribute("passPatternErrorMessage", "*Must contain one number, one uppercase and lowercase letter, one special character and be 6 or more characters");
            } else {
                request.setAttribute("newPswMessage", "You should fill in the blank.");

                getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("findAcc")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailFind = request.getParameter("email_accountForm");

            if (firstName == null || firstName.equals("")
                    && lastName == null || lastName.equals("")
                    && emailFind == null || emailFind.equals("")) {
                request.setAttribute("resetPwd", "You should fill in the blank.");

                getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                return;
            } else {
                try {
                    Account checkAccount = accountService.sendAccount(emailFind, firstName, lastName, path, sessionUrl);

                    if (checkAccount != null) {
                        request.setAttribute("resetPwd", "We sent an email to your email address.");
                        request.setAttribute("resetMessage2", "(If you haven't got the email, please check your junk mail.)");
<<<<<<< HEAD
=======
                        
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
                        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                        return;
                    } else {
                        request.setAttribute("resetPwd", "Check account information again.");

                        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
                        return;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
