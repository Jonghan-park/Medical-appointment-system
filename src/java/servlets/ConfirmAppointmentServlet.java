package servlets;

import java.io.IOException;
import java.time.LocalDate;
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
public class ConfirmAppointmentServlet extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String)session.getAttribute("user_name");
        
        if (user_name == null || user_name.equals("")) {
            response.sendRedirect("welcome");
            return;
        } else {
            AccountService accountService = new AccountService();
            
            try {
                Account account = accountService.get(user_name);
                request.setAttribute("account", account);                
                
                if (account.getProfile().equals("ADMIN")) {
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
        }
        
        getTodayAppointments(request, response);
        getServletContext().getRequestDispatcher("/WEB-INF/confirmAppointment.jsp").forward(request, response);
        return;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String patient_attended[] = request.getParameterValues("patient_attended");
        
        AccountService accountService = new AccountService();
        AdministratorService administratorService = new AdministratorService();
        AppointmentService appointmentService = new AppointmentService();
        LocalDate today = LocalDate.now();      
        
        try {
            Account account = accountService.get(user_name);
            Administrator administrator = administratorService.get(account.getAccount_id());
            request.setAttribute("account", account);
            request.setAttribute("user", administrator);
            
            List<Appointment> appointments = appointmentService.getAllByDate(today + "");
            
            if (patient_attended != null && patient_attended.length != 0) {                
                int idx = 0;

                for (int i = 0; i < appointments.size(); i++) {
                    Appointment appointment = appointments.get(i);                
                    
                    if (idx < patient_attended.length) {                        
                        if (Integer.parseInt(patient_attended[idx]) == i) {
                            appointmentService.update(appointment.getDoctor_id(), appointment.getStart_date_time(),
                                    appointment.getPatient_id(), appointment.getDuration(), appointment.getType(),
                                    appointment.getReason(), true);
                            idx++;                            
                        } else {                            
                            appointmentService.update(appointment.getDoctor_id(), appointment.getStart_date_time(),
                                    appointment.getPatient_id(), appointment.getDuration(), appointment.getType(),
                                    appointment.getReason(), false);
                        }                    
                    } else {
                        appointmentService.update(appointment.getDoctor_id(), appointment.getStart_date_time(),
                                    appointment.getPatient_id(), appointment.getDuration(), appointment.getType(),
                                    appointment.getReason(), false);
                    }                    
                }
            } else {
                for (int i = 0; i < appointments.size(); i++) {
                    Appointment appointment = appointments.get(i);
                    
                    appointmentService.update(appointment.getDoctor_id(), appointment.getStart_date_time(),
                                appointment.getPatient_id(), appointment.getDuration(), appointment.getType(),
                                appointment.getReason(), false);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(WelcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getTodayAppointments(request, response);
        getServletContext().getRequestDispatcher("/WEB-INF/confirmAppointment.jsp").forward(request, response);
        return;
    }

    private void getTodayAppointments(HttpServletRequest request, HttpServletResponse response) {
        LocalDate today = LocalDate.now();     
        
        try {
            AppointmentService appointmentService = new AppointmentService();
            List<Appointment> appointments = appointmentService.getAllByDate(today + "");
            
            if (appointments != null && !appointments.isEmpty()) {
                DoctorService doctorService = new DoctorService();
                PatientService patientService = new PatientService();
                Doctor doctor = null;
                Patient patient = null;
                List<String> doctors = new ArrayList<>();
                List<String> patients = new ArrayList<>();
                
                for (int i = 0; i < appointments.size(); i++) {
                    doctor = doctorService.getByDoctorID(appointments.get(i).getDoctor_id());
                    patient = patientService.getByPatientId(appointments.get(i).getPatient_id());
                    doctors.add(doctor.getFirst_name() + " " + doctor.getLast_name());
                    patients.add(patient.getFirst_name() + " " + patient.getLast_name());
                }
                
                request.setAttribute("doctors", doctors);
                request.setAttribute("patients", patients);
            }
            
            request.setAttribute("appointments", appointments);
        } catch (Exception ex) {
            Logger.getLogger(ConfirmAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
