package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Must use once to encrypt all sensitive data       
        //allDataEncrypted(request, response);
        //allPasswordEncrypted(request, response);
        HttpSession session = request.getSession();

        String user_name = (String) session.getAttribute("user_name");
        String url = request.getRequestURL().toString();
        session.setAttribute("home_url", url);

        if (request.getParameter("logout") != null) {
            session.invalidate();
            session = request.getSession();
            session.setAttribute("home_url", url);
        } else {
            if (user_name != null && !user_name.equals("")) {
                AccountService accountService = new AccountService();

                try {
                    Account account = accountService.get(user_name);
                    request.setAttribute("account", account);

                    if (account.getProfile().equals("DOCTOR")) {
                        DoctorService doctorService = new DoctorService();
                        Doctor doctor = doctorService.get(account.getAccount_id());
                        request.setAttribute("user", doctor);
                    } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                        AdministratorService administratorService = new AdministratorService();
                        Administrator administrator = administratorService.get(account.getAccount_id());
                        request.setAttribute("user", administrator);
                    } else if (account.getProfile().equals("PATIENT")) {
                        PatientService patientService = new PatientService();
                        Patient patient = patientService.get(account.getAccount_id());
                        request.setAttribute("user", patient);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void allPasswordEncrypted(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService accountService = new AccountService();

        try {
            List<Account> accounts = accountService.getAll();

            for (int i = 0; i < accounts.size(); i++) {
                Account account = accounts.get(i);

                if (account.getSalt() == null) {
                    accountService.update(account.getAccount_id(), account.getUser_name(),
                            account.getPassword(), account.getProfile());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void allDataEncrypted(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PatientService ps = new PatientService();
        DoctorService dc = new DoctorService();
        AdministratorService ads = new AdministratorService();

        try {

            ps.setAllDataEncrypt();
            dc.setAllDataEncrypt();
            ads.setAllDataEncrypt();

        } catch (Exception ex) {
            Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
