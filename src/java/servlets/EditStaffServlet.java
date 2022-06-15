<<<<<<< HEAD
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Administrator;
import models.Doctor;
import models.Patient;
import service.AccountService;
import service.AdministratorService;
import service.DoctorService;
import service.PatientService;

public class EditStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String selectedUser = (String) session.getAttribute("selectedUser");

        if (user_name == null || user_name.equals("") || selectedUser == null ) {
            response.sendRedirect("welcome");
            return;
        } else if (selectedUser != null) {
            displayInformation(request, selectedUser);
            //session.setAttribute("loginUser", null);
            session.setAttribute("selectedUser", null);
        } else if (user_name != null || user_name.equals("")) {
            displayInformation(request, user_name);
            session.setAttribute("editCheck", null);
            String noProfile = (String) session.getAttribute("noProfile");
            if (noProfile != null || !noProfile.equals("")) {
                response.sendRedirect("welcome");
                session.setAttribute("noProfile", null);
                return;
            }
        } 

        getServletContext().getRequestDispatcher("/WEB-INF/editStaff.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user");

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String phone_number = request.getParameter("phone_number");
        String alt_phone = request.getParameter("alt_phone");
        String email = request.getParameter("email");
        String pref_contact_type = request.getParameter("pref_contact_type");
        String gender = request.getParameter("gender");
        String birth_date = request.getParameter("birth_date");
        String street_address = request.getParameter("street_address");
        String city = request.getParameter("city");
        String postal_code = request.getParameter("postal_code");
        String province = request.getParameter("province");

        boolean regex_all_check = false;
        // 2022/03/22   add  start point
        String regex_first_name = "[a-zA-Z\\-]{1,25}";
        String regex_last_name = "[a-zA-Z\\-]{1,25}";
        String regex_phone_number = "^(\\+\\d{1}\\s)?\\(?\\d{3}\\)?\\d{3}\\d{4}$";
        String regex_email = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
        String regex_birth_date = "^\\d{4}\\)?[\\s.-]\\d{2}[\\s.-]\\d{2}$";
        String regex_city = "[a-zA-Z\\-]{1,25}";
        String regex_postal_code = "[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]\\d";

        Pattern p = Pattern.compile(regex_first_name);
        Matcher m = p.matcher(first_name);
        boolean check_first_name = m.matches();

        p = Pattern.compile(regex_last_name);
        m = p.matcher(last_name);
        boolean check_last_name = m.matches();

        p = Pattern.compile(regex_phone_number);
        m = p.matcher(phone_number);
        boolean check_phone_name = m.matches();

        p = Pattern.compile(regex_email);
        m = p.matcher(email);
        boolean check_regex_email = m.matches();

        p = Pattern.compile(regex_city);
        m = p.matcher(city);
        boolean check_regex_city = m.matches();

        p = Pattern.compile(regex_postal_code);
        m = p.matcher(postal_code);
        boolean check_postal_code = m.matches();

        p = Pattern.compile(regex_birth_date);
        m = p.matcher(birth_date);
        boolean check_birth_date = m.matches();

        if (check_first_name == true && check_last_name == true && check_phone_name == true && check_regex_email == true
                && check_regex_city == true && check_postal_code == true && check_birth_date == true) {
            regex_all_check = true;
        }

        if (regex_all_check == false || first_name == null || first_name.equals("") || last_name == null || last_name.equals("")
                || phone_number == null || phone_number.equals("") || email == null || email.equals("")
                || birth_date == null || birth_date.equals("") || street_address == null || street_address.equals("")
                || city == null || city.equals("") || postal_code == null || postal_code.equals("")
                || province == null || province.equals("")) {
            displayInformation(request, user_name);
            request.setAttribute("message", "Please fill out all the required information.");

            getServletContext().getRequestDispatcher("/WEB-INF/editStaff.jsp").forward(request, response);
            return;
        } else {
            AccountService accountService = new AccountService();

            try {
                Account account = accountService.get(user_name);

                if (account.getProfile().equals("DOCTOR")) {
                    DoctorService doctorService = new DoctorService();
                    Doctor doctor = doctorService.get(account.getAccount_id());
                    doctorService.update(doctor.getDoctor_id(), first_name, last_name, email,
                            phone_number, alt_phone, pref_contact_type, doctor.getAccount_id(), gender,
                            birth_date, street_address, city, province, postal_code);

                    Doctor updatedDoctor = doctorService.get(account.getAccount_id());
                    request.setAttribute("user", updatedDoctor);
                    session.setAttribute("loginUser", updatedDoctor);
                } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                    AdministratorService administratorService = new AdministratorService();
                    Administrator administrator = administratorService.get(account.getAccount_id());
                    administratorService.update(administrator.getAdmin_id(), first_name, last_name, email,
                            phone_number, alt_phone, pref_contact_type, administrator.getAccount_id(), gender,
                            birth_date, street_address, city, province, postal_code);

                    Administrator updatedAdministrator = administratorService.get(account.getAccount_id());
                    request.setAttribute("user", updatedAdministrator);
                    session.setAttribute("loginUser", updatedAdministrator);
                } else if (account.getProfile().equals("PATIENT")) {
                    PatientService patientService = new PatientService();
                    Patient patient = patientService.get(account.getAccount_id());
                    patientService.update(patient.getPatient_id(), patient.getHealthcare_id(), first_name, last_name, email,
                            phone_number, alt_phone, pref_contact_type, patient.getDoctor_id(), patient.getAccount_id(), gender,
                            birth_date, street_address, city, province, postal_code);

                    Patient updatedPatient = patientService.get(account.getAccount_id());
                    request.setAttribute("user", updatedPatient);
                    session.setAttribute("loginUser", updatedPatient);
                }

                Account updatedAccount = accountService.get(user_name);
                request.setAttribute("loginAccount", updatedAccount);
                request.setAttribute("message", "Your informaiotn has been updated successfully.");
            } catch (Exception ex) {
                Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String editCheck = (String) session.getAttribute("editCheck");
        String editCheckPatient = (String) session.getAttribute("editCheckPatient");

        if (editCheck != null) {
            session.setAttribute("editCheck", null);
            session.setAttribute("complete", "complete");
            response.sendRedirect("view_staff");
            return;
        } else if (editCheckPatient != null) {
            session.setAttribute("editCheckPatient", null);
            session.setAttribute("complete", "complete");
            response.sendRedirect("view_patient");
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/editStaff.jsp").forward(request, response);
            return;
        }
    }

    private void displayInformation(HttpServletRequest request, String user_name) {
        HttpSession session = request.getSession();
        AccountService accountService = new AccountService();
        String loginUser = (String) session.getAttribute("user_name");

        try {
            Account account = accountService.get(user_name);
            request.setAttribute("account", account);

            if (account.getProfile().equals("DOCTOR")) {
                DoctorService doctorService = new DoctorService();
                Doctor doctor = doctorService.get(account.getAccount_id());
                request.setAttribute("user", doctor);
                session.setAttribute("user", account.getUser_name());

            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorService administratorService = new AdministratorService();
                Administrator administrator = administratorService.get(account.getAccount_id());
                request.setAttribute("user", administrator);
                session.setAttribute("user", account.getUser_name());

            } else {
                session.setAttribute("noProfile", "noProfile");
                return;
            }

            Account loginAccount = accountService.get(loginUser);
            request.setAttribute("loginAccount", loginAccount);

            if (loginAccount.getProfile().equals("SYSADMIN")) {
                AdministratorService administratorService = new AdministratorService();
                Administrator loginAdministrator = administratorService.get(loginAccount.getAccount_id());
                request.setAttribute("loginUser", loginAdministrator);
            } else {
                session.setAttribute("noProfile", "noProfile");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
=======
package servlets;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class EditStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String selectedUser = (String) session.getAttribute("selectedUser");

        if (user_name == null || user_name.equals("") || selectedUser == null ) {
            response.sendRedirect("welcome");
            return;
        } else if (selectedUser != null) {
            displayInformation(request, selectedUser);
            session.setAttribute("selectedUser", null);
        } else if (user_name != null || user_name.equals("")) {
            displayInformation(request, user_name);
            session.setAttribute("editCheck", null);
            String noProfile = (String) session.getAttribute("noProfile");
            if (noProfile != null || !noProfile.equals("")) {
                response.sendRedirect("welcome");
                session.setAttribute("noProfile", null);
                return;
            }
        } 

        getServletContext().getRequestDispatcher("/WEB-INF/editStaff.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user");

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String phone_number = request.getParameter("phone_number");
        String alt_phone = request.getParameter("alt_phone");
        String email = request.getParameter("email");
        String pref_contact_type = request.getParameter("pref_contact_type");
        String gender = request.getParameter("gender");
        String birth_date = request.getParameter("birth_date");
        String street_address = request.getParameter("street_address");
        String city = request.getParameter("city");
        String postal_code = request.getParameter("postal_code");
        String province = request.getParameter("province");

        boolean regex_all_check = false;
        String regex_first_name = "[a-zA-Z\\-]{1,25}";
        String regex_last_name = "[a-zA-Z\\-]{1,25}";
        String regex_phone_number = "^(\\+\\d{1}\\s)?\\(?\\d{3}\\)?\\d{3}\\d{4}$";
        String regex_email = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
        String regex_birth_date = "^\\d{4}\\)?[\\s.-]\\d{2}[\\s.-]\\d{2}$";
        String regex_city = "[a-zA-Z\\-]{1,25}";
        String regex_postal_code = "[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]\\d";

        Pattern p = Pattern.compile(regex_first_name);
        Matcher m = p.matcher(first_name);
        boolean check_first_name = m.matches();

        p = Pattern.compile(regex_last_name);
        m = p.matcher(last_name);
        boolean check_last_name = m.matches();

        p = Pattern.compile(regex_phone_number);
        m = p.matcher(phone_number);
        boolean check_phone_name = m.matches();

        p = Pattern.compile(regex_email);
        m = p.matcher(email);
        boolean check_regex_email = m.matches();

        p = Pattern.compile(regex_city);
        m = p.matcher(city);
        boolean check_regex_city = m.matches();

        p = Pattern.compile(regex_postal_code);
        m = p.matcher(postal_code);
        boolean check_postal_code = m.matches();

        p = Pattern.compile(regex_birth_date);
        m = p.matcher(birth_date);
        boolean check_birth_date = m.matches();

        if (check_first_name == true && check_last_name == true && check_phone_name == true && check_regex_email == true
                && check_regex_city == true && check_postal_code == true && check_birth_date == true) {
            regex_all_check = true;
        }

        if (regex_all_check == false || first_name == null || first_name.equals("") || last_name == null || last_name.equals("")
                || phone_number == null || phone_number.equals("") || email == null || email.equals("")
                || birth_date == null || birth_date.equals("") || street_address == null || street_address.equals("")
                || city == null || city.equals("") || postal_code == null || postal_code.equals("")
                || province == null || province.equals("")) {
            displayInformation(request, user_name);
            request.setAttribute("message", "Please fill out all the required information.");

            getServletContext().getRequestDispatcher("/WEB-INF/editStaff.jsp").forward(request, response);
            return;
        } else {
            AccountService accountService = new AccountService();

            try {
                Account account = accountService.get(user_name);

                if (account.getProfile().equals("DOCTOR")) {
                    DoctorService doctorService = new DoctorService();
                    Doctor doctor = doctorService.get(account.getAccount_id());
                    doctorService.update(doctor.getDoctor_id(), first_name, last_name, email,
                            phone_number, alt_phone, pref_contact_type, doctor.getAccount_id(), gender,
                            birth_date, street_address, city, province, postal_code);

                    Doctor updatedDoctor = doctorService.get(account.getAccount_id());
                    request.setAttribute("user", updatedDoctor);
                    session.setAttribute("loginUser", updatedDoctor);
                } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                    AdministratorService administratorService = new AdministratorService();
                    Administrator administrator = administratorService.get(account.getAccount_id());
                    administratorService.update(administrator.getAdmin_id(), first_name, last_name, email,
                            phone_number, alt_phone, pref_contact_type, administrator.getAccount_id(), gender,
                            birth_date, street_address, city, province, postal_code);

                    Administrator updatedAdministrator = administratorService.get(account.getAccount_id());
                    request.setAttribute("user", updatedAdministrator);
                    session.setAttribute("loginUser", updatedAdministrator);
                } else if (account.getProfile().equals("PATIENT")) {
                    PatientService patientService = new PatientService();
                    Patient patient = patientService.get(account.getAccount_id());
                    patientService.update(patient.getPatient_id(), patient.getHealthcare_id(), first_name, last_name, email,
                            phone_number, alt_phone, pref_contact_type, patient.getDoctor_id(), patient.getAccount_id(), gender,
                            birth_date, street_address, city, province, postal_code);

                    Patient updatedPatient = patientService.get(account.getAccount_id());
                    request.setAttribute("user", updatedPatient);
                    session.setAttribute("loginUser", updatedPatient);
                }

                Account updatedAccount = accountService.get(user_name);
                request.setAttribute("loginAccount", updatedAccount);
                request.setAttribute("message", "Your informaiotn has been updated successfully.");
            } catch (Exception ex) {
                Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String editCheck = (String) session.getAttribute("editCheck");
        String editCheckPatient = (String) session.getAttribute("editCheckPatient");

        if (editCheck != null) {
            session.setAttribute("editCheck", null);
            session.setAttribute("complete", "complete");
            response.sendRedirect("view_staff");
            return;
        } else if (editCheckPatient != null) {
            session.setAttribute("editCheckPatient", null);
            session.setAttribute("complete", "complete");
            response.sendRedirect("view_patient");
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/editStaff.jsp").forward(request, response);
            return;
        }
    }

    private void displayInformation(HttpServletRequest request, String user_name) {
        HttpSession session = request.getSession();
        AccountService accountService = new AccountService();
        String loginUser = (String) session.getAttribute("user_name");

        try {
            Account account = accountService.get(user_name);
            request.setAttribute("account", account);

            if (account.getProfile().equals("DOCTOR")) {
                DoctorService doctorService = new DoctorService();
                Doctor doctor = doctorService.get(account.getAccount_id());
                request.setAttribute("user", doctor);
                session.setAttribute("user", account.getUser_name());

            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorService administratorService = new AdministratorService();
                Administrator administrator = administratorService.get(account.getAccount_id());
                request.setAttribute("user", administrator);
                session.setAttribute("user", account.getUser_name());

            } else {
                session.setAttribute("noProfile", "noProfile");
                return;
            }

            Account loginAccount = accountService.get(loginUser);
            request.setAttribute("loginAccount", loginAccount);

            if (loginAccount.getProfile().equals("SYSADMIN")) {
                AdministratorService administratorService = new AdministratorService();
                Administrator loginAdministrator = administratorService.get(loginAccount.getAccount_id());
                request.setAttribute("loginUser", loginAdministrator);
            } else {
                session.setAttribute("noProfile", "noProfile");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
