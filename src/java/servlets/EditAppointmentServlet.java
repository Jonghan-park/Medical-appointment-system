package servlets;

import java.io.IOException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class EditAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        Appointment appointSession = (Appointment) session.getAttribute("appointmentSessionObj");
        
        AccountService as = new AccountService();
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        
        if (appointSession == null) {
            response.sendRedirect("welcome");
            return;
        } else {
            try {
                Account account = as.get(user_name);
                request.setAttribute("account", account);
                
                if (account.getProfile().equals("DOCTOR")) {                    
                    Doctor doctor = doctorService.get(account.getAccount_id());
                    request.setAttribute("user", doctor);
                } else if (account.getProfile().equals("ADMIN")) {
                    AdministratorService administratorService = new AdministratorService();
                    Administrator administrator = administratorService.get(account.getAccount_id());
                    request.setAttribute("user", administrator);
                } else {
                    Patient patient = patientService.get(account.getAccount_id());
                    request.setAttribute("user", patient);
                }
                
                String doctor = doctorService.getByDoctorID(appointSession.getDoctor_id()).getLast_name();
                String patient = patientService.getByPatientId(appointSession.getPatient_id()).getFirst_name() +
                        " " + patientService.getByPatientId(appointSession.getPatient_id()).getLast_name();
                
                request.setAttribute("appointment", appointSession);
                request.setAttribute("doctor", doctor);
                request.setAttribute("patient", patient);
            } catch (Exception ex) {
                Logger.getLogger(EditAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/editAppointment.jsp").forward(request, response);
            return;
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        Appointment appointSession = (Appointment) session.getAttribute("appointmentSessionObj");
        String type = request.getParameter("type");
        String reason = request.getParameter("reason");
        
        AccountService as = new AccountService();
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService aps = new AppointmentService();
        AppointmentTypeService apts = new AppointmentTypeService();
                        
        try {
            Account account = as.get(user_name);
            request.setAttribute("account", account);
                
            if (account.getProfile().equals("DOCTOR")) {                    
                Doctor doctor = doctorService.get(account.getAccount_id());
                request.setAttribute("user", doctor);
            } else if (account.getProfile().equals("ADMIN")) {
                AdministratorService administratorService = new AdministratorService();
                Administrator administrator = administratorService.get(account.getAccount_id());
                request.setAttribute("user", administrator);
            } else {
                Patient patient = patientService.get(account.getAccount_id());
                request.setAttribute("user", patient);
            }
                
            String doctor = doctorService.getByDoctorID(appointSession.getDoctor_id()).getLast_name();
            String patient = patientService.getByPatientId(appointSession.getPatient_id()).getFirst_name() +
                    " " + patientService.getByPatientId(appointSession.getPatient_id()).getLast_name();
              
            request.setAttribute("appointment", appointSession);
            request.setAttribute("doctor", doctor);
            request.setAttribute("patient", patient);
            
            if (reason == null || reason.equals("")) {
                request.setAttribute("message", "Please fill out the reason.");
                getServletContext().getRequestDispatcher("/WEB-INF/editAppointment.jsp").forward(request, response);
                return;
            } else {
                aps.update(appointSession.getDoctor_id(), appointSession.getStart_date_time(), appointSession.getPatient_id(),
                        apts.get(Integer.parseInt(type)).getStd_duration(), Integer.parseInt(type), reason, false);
                
                session.setAttribute("appointmentSessionObj", null);
                session.setAttribute("edited", "edited");
                response.sendRedirect("view_appointment");
                return;
            }            
        } catch (Exception ex) {
            Logger.getLogger(EditAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
