<<<<<<< HEAD
package servlets;

import dataaccess.AES;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class ViewStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String complete = (String) session.getAttribute("complete");

        getAllStaff(request, response);

        if (complete != null) {
            request.setAttribute("message", "User information has been updated successfully.");
            session.setAttribute("complete", null);
        }

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
                } else {
                    response.sendRedirect("welcome");
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("welcome");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/viewStaff.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        DoctorService ds = new DoctorService();
        PatientService ps = new PatientService();
        AdministratorService ads = new AdministratorService();
        AppointmentService aps = new AppointmentService();
        AvailabilityService avs = new AvailabilityService();

        String user_name = (String) session.getAttribute("user_name");
        String action = request.getParameter("action");
        String account_id = request.getParameter("account_id");
        int accountID = Integer.parseInt(account_id);

        if (account_id != null) {
            Account account = null;

            try {
                account = as.get(accountID);
            } catch (Exception ex) {
                Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (action.equals("edit")) {
                session.setAttribute("selectedUser", account.getUser_name());
                session.setAttribute("editCheck", "editCheck");
                response.sendRedirect("edit_staff");
                return;
            } else if (action.equals("delete")) {
                if (account.getProfile().equals("DOCTOR")) {
                    List<Appointment> doctorsAppointment = new ArrayList<>();
                    List<Availability> doctorsAvailability = new ArrayList<>();
                    List<Patient> patients = new ArrayList<>();

                    try {
                        Doctor doctor = ds.get(account.getAccount_id());
                        doctorsAppointment = aps.getByDoctorID(doctor.getDoctor_id());
                        
                        //----------------------------   Delete Appointment ---------------------------------    
                        
                        for (int i = 0; i < doctorsAppointment.size(); i++) {                            
                            aps.delete(doctorsAppointment.get(i).getStart_date_time(), 
                                    doctorsAppointment.get(i).getDoctor_id(),
                                    doctorsAppointment.get(i).getPatient_id());
                        }
                        
                        //----------------------------   Delete Availability ---------------------------------                        
                        doctorsAvailability = avs.getAllByDoctorId(doctor.getDoctor_id());

                        for (int i = 0; i < doctorsAvailability.size(); i++) {
                            avs.deleteBySchedule(doctorsAvailability.get(i).getDoctor_id(),doctorsAvailability.get(i).getStart_date_time());
                        }
                        
                        //-------------------   Change the doctor for all patients ---------------------------    
                        patients = ps.getAllByDoctor(doctor.getDoctor_id());
                        for(int i = 0; i < patients.size();i++){
                            ps.update(patients.get(i).getPatient_id(), patients.get(i).getHealthcare_id(), 
                                    patients.get(i).getFirst_name(), patients.get(i).getLast_name(),
                                    patients.get(i).getEmail(), patients.get(i).getMobile_phone(), 
                                    patients.get(i).getAlt_phone(),patients.get(i).getPref_contact_type(), 1234567, 
                                    patients.get(i).getAccount_id(),patients.get(i).getGender(), patients.get(i).getBirth_date(), 
                                    patients.get(i).getStreet_address(), patients.get(i).getCity(), 
                                    patients.get(i).getProvince(), patients.get(i).getPostal_code());
                        } 
                        
                        ds.delete(account.getAccount_id());
                        as.delete(account.getUser_name());
                    } catch (Exception ex) {
                        Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                    try {
                        ads.delete(account.getAccount_id());
                        as.delete(account.getUser_name());
                    } catch (Exception ex) {
                        Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                request.setAttribute("message", "User is deleted successfully.");
            }
        }

        try {
            Account account = as.get(user_name);
            request.setAttribute("account", account);

            if (account.getProfile().equals("DOCTOR")) {
                DoctorService doctorService = new DoctorService();
                Doctor doctor = doctorService.get(account.getAccount_id());
                request.setAttribute("user", doctor);
            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorService administratorService = new AdministratorService();
                Administrator administrator = administratorService.get(account.getAccount_id());
                request.setAttribute("user", administrator);
            }
        } catch (Exception ex) {
            Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getAllStaff(request, response);
        getServletContext().getRequestDispatcher("/WEB-INF/viewStaff.jsp").forward(request, response);
        return;
    }

    public void getAllStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdministratorService as = new AdministratorService();
        DoctorService ds = new DoctorService();

        List<Administrator> admins = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();

        try {
            admins = as.getAll();
            doctors = ds.getAll();
        } catch (Exception ex) {
            Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("admins", admins);
        request.setAttribute("doctors", doctors);
    }
}
=======
package servlets;

import dataaccess.AES;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class ViewStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String complete = (String) session.getAttribute("complete");

        getAllStaff(request, response);

        if (complete != null) {
            request.setAttribute("message", "User information has been updated successfully.");
            session.setAttribute("complete", null);
        }

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
                } else {
                    response.sendRedirect("welcome");
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("welcome");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/viewStaff.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        DoctorService ds = new DoctorService();
        PatientService ps = new PatientService();
        AdministratorService ads = new AdministratorService();
        AppointmentService aps = new AppointmentService();
        AvailabilityService avs = new AvailabilityService();

        String user_name = (String) session.getAttribute("user_name");
        String action = request.getParameter("action");
        String account_id = request.getParameter("account_id");
        int accountID = Integer.parseInt(account_id);

        if (account_id != null) {
            Account account = null;

            try {
                account = as.get(accountID);
            } catch (Exception ex) {
                Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (action.equals("edit")) {
                session.setAttribute("selectedUser", account.getUser_name());
                session.setAttribute("editCheck", "editCheck");
                response.sendRedirect("edit_staff");
                return;
            } else if (action.equals("delete")) {
                if (account.getProfile().equals("DOCTOR")) {
                    List<Appointment> doctorsAppointment = new ArrayList<>();
                    List<Availability> doctorsAvailability = new ArrayList<>();
                    List<Patient> patients = new ArrayList<>();

                    try {
                        Doctor doctor = ds.get(account.getAccount_id());
                        doctorsAppointment = aps.getByDoctorID(doctor.getDoctor_id());
                        
                        //----------------------------   Delete Appointment ---------------------------------    
                        
                        for (int i = 0; i < doctorsAppointment.size(); i++) {                            
                            aps.delete(doctorsAppointment.get(i).getStart_date_time(), 
                                    doctorsAppointment.get(i).getDoctor_id(),
                                    doctorsAppointment.get(i).getPatient_id());
                        }
                        
                        //----------------------------   Delete Availability ---------------------------------                        
                        doctorsAvailability = avs.getAllByDoctorId(doctor.getDoctor_id());

                        for (int i = 0; i < doctorsAvailability.size(); i++) {
                            avs.deleteBySchedule(doctorsAvailability.get(i).getDoctor_id(),doctorsAvailability.get(i).getStart_date_time());
                        }
                        
                        //-------------------   Change the doctor for all patients ---------------------------    
                        patients = ps.getAllByDoctor(doctor.getDoctor_id());
                        for(int i = 0; i < patients.size();i++){
                            ps.update(patients.get(i).getPatient_id(), patients.get(i).getHealthcare_id(), 
                                    patients.get(i).getFirst_name(), patients.get(i).getLast_name(),
                                    patients.get(i).getEmail(), patients.get(i).getMobile_phone(), 
                                    patients.get(i).getAlt_phone(),patients.get(i).getPref_contact_type(), 1234567, 
                                    patients.get(i).getAccount_id(),patients.get(i).getGender(), patients.get(i).getBirth_date(), 
                                    patients.get(i).getStreet_address(), patients.get(i).getCity(), 
                                    patients.get(i).getProvince(), patients.get(i).getPostal_code());
                        } 
                        
                        ds.delete(account.getAccount_id());
                        as.delete(account.getUser_name());
                    } catch (Exception ex) {
                        Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                    try {
                        ads.delete(account.getAccount_id());
                        as.delete(account.getUser_name());
                    } catch (Exception ex) {
                        Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                request.setAttribute("message", "User is deleted successfully.");
            }
        }

        try {
            Account account = as.get(user_name);
            request.setAttribute("account", account);

            if (account.getProfile().equals("DOCTOR")) {
                DoctorService doctorService = new DoctorService();
                Doctor doctor = doctorService.get(account.getAccount_id());
                request.setAttribute("user", doctor);
            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorService administratorService = new AdministratorService();
                Administrator administrator = administratorService.get(account.getAccount_id());
                request.setAttribute("user", administrator);
            }
        } catch (Exception ex) {
            Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getAllStaff(request, response);
        getServletContext().getRequestDispatcher("/WEB-INF/viewStaff.jsp").forward(request, response);
        return;
    }

    public void getAllStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdministratorService as = new AdministratorService();
        DoctorService ds = new DoctorService();

        List<Administrator> admins = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();

        try {
            admins = as.getAll();
            doctors = ds.getAll();
        } catch (Exception ex) {
            Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("admins", admins);
        request.setAttribute("doctors", doctors);
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
