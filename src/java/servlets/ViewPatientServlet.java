package servlets;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class ViewPatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String complete = (String) session.getAttribute("complete");
        String deleteCheck = (String) session.getAttribute("deleteCheck");
        String noName = (String) session.getAttribute("noName");

        if (complete != null) {
            request.setAttribute("message", "User information has been updated successfully.");
            session.setAttribute("complete", null);
        } else if (deleteCheck != null) {
            request.setAttribute("message", "User is deleted successfully.");
            session.setAttribute("deleteCheck", null);
        } else if (noName != null) {
            request.setAttribute("message", "Please enter the name");
            session.setAttribute("noName", null);
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
                    session.setAttribute("doctorsPatients", doctor);
                    getAllAssignedPatient(request, response);
                    session.setAttribute("doctorsPatients", null);
                } else if (account.getProfile().equals("ADMIN")) {
                    AdministratorService administratorService = new AdministratorService();
                    getAllPatient(request, response);
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

        getServletContext().getRequestDispatcher("/WEB-INF/viewPatient.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        PatientService ps = new PatientService();
        DoctorService ds = new DoctorService();
        AdministratorService ads = new AdministratorService();
        AppointmentService aps = new AppointmentService();

        List<Patient> searched_patients = new ArrayList<>();

        String user_name = (String) session.getAttribute("user_name");
        String name = request.getParameter("name");
        String action = request.getParameter("action");
        String account_id = request.getParameter("account_id");

        Account account = null;

        try {
            account = as.get(user_name);
        } catch (Exception ex) {
            Logger.getLogger(ViewPatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (account_id == null && action.equals("search_name")) {
            if (!name.equals("")) {
                if (account.getProfile().equals("DOCTOR")) {
                    Doctor doctor = null;

                    try {
                        doctor = ds.get(account.getAccount_id());
                        request.setAttribute("account", account);
                        searched_patients = ps.getAllAssignedByName(name, doctor.getDoctor_id());

                    } catch (Exception ex) {
                        Logger.getLogger(ViewPatientServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.setAttribute("searchedPatients", searched_patients);
                    request.setAttribute("user", doctor);
                    session.setAttribute("doctorsPatients", doctor);
                    getAllAssignedPatient(request, response);
                    session.setAttribute("doctorsPatients", null);

                    request.setAttribute("name", name);
                    request.setAttribute("searched", true);
                } else if (account.getProfile().equals("ADMIN")) {
                    Administrator admin = null;

                    try {
                        admin = ads.get(account.getAccount_id());
                        request.setAttribute("account", account);
                        searched_patients = ps.getAllByName(name);
                    } catch (Exception ex) {
                        Logger.getLogger(ViewPatientServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.setAttribute("searchedPatients", searched_patients);
                    request.setAttribute("user", admin);
                    getAllPatient(request, response);

                    request.setAttribute("name", name);
                    request.setAttribute("searched", true);
                }
            } else {
                session.setAttribute("noName", "noName");
                response.sendRedirect("view_patient");
                return;
            }
        }

        if (account_id != null) {
            int accountID = Integer.parseInt(account_id);

            try {
                account = as.get(accountID);
            } catch (Exception ex) {
                Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (action.equals("edit")) {
                session.setAttribute("selectedUser", account.getUser_name());
                session.setAttribute("editCheckPatient", "editCheckPatient");
                response.sendRedirect("edit_patient");
                return;
            } else if (action.equals("delete")) {
                List<Appointment> patientsAppointment = new ArrayList<>();

                try {
                    Patient patient = ps.get(account.getAccount_id());
                    patientsAppointment = aps.getByPatientID(patient.getPatient_id());
                    for (int i = 0; i < patientsAppointment.size(); i++) {
                        aps.delete(patientsAppointment.get(i).getStart_date_time(),
                                patientsAppointment.get(i).getDoctor_id(),
                                patientsAppointment.get(i).getPatient_id());
                    }
                    ps.delete(patient.getAccount_id());

                    as.delete(account.getUser_name());
                } catch (Exception ex) {
                    Logger.getLogger(ViewStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("deleteCheck", "deleteCheck");
                response.sendRedirect("view_patient");
                return;
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/viewPatient.jsp").forward(request, response);
        return;
    }

    public void getAllPatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PatientService ps = new PatientService();

        List<Patient> patients = new ArrayList<>();

        try {
            patients = ps.getAll();
        } catch (Exception ex) {
            Logger.getLogger(ViewPatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("patients", patients);
    }

    public void getAllAssignedPatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PatientService ps = new PatientService();

        List<Patient> patients = new ArrayList<>();

        try {
            Doctor doctor = (Doctor) session.getAttribute("doctorsPatients");
            patients = ps.getAllByDoctor(doctor.getDoctor_id());

        } catch (Exception ex) {
            Logger.getLogger(ViewPatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("patients", patients);
    }
}
