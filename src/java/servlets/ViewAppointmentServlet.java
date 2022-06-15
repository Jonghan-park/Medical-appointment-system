package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class ViewAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String edited = (String) session.getAttribute("edited");
        
        AccountService accountService = new AccountService();
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService as = new AppointmentService();           
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        if (request.getParameter("delete") != null) {
            String date = request.getParameter("delete");           
            
            try {
                Appointment appointment = as.getByDate(date.substring(0, 16));
                as.delete(date, appointment.getDoctor_id(), appointment.getPatient_id());
            } catch (Exception ex) {
                Logger.getLogger(ViewAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("message", "Appointment is deleted successfully.");
        }
        
        if (edited != null && !edited.equals("")) {
            request.setAttribute("message", "Appointment has been updated successfully.");
            session.setAttribute("edited", null);
        }

        if (user_name != null && !user_name.equals("")) {
            try {
                Account account = accountService.get(user_name);
                request.setAttribute("account", account);
                
                List<Appointment> appointments = as.getAllFutures(tomorrow + "");
                
                if (account.getProfile().equals("DOCTOR")) {                    
                    Doctor doctor = doctorService.get(account.getAccount_id());
                    request.setAttribute("user", doctor);
                    
                    ArrayList<Appointment> arrlAppointments = new ArrayList();
                    ArrayList<Patient> arrlPatients = new ArrayList();
                    
                    for (int i = 0; i < appointments.size(); i++) {
                        if (appointments.get(i).getDoctor_id() == doctor.getDoctor_id()) {
                            arrlAppointments.add(appointments.get(i));
                        }
                    }

                    for (int i = 0; i < arrlAppointments.size(); i++) {
                        Patient patient = patientService.getByPatientId(arrlAppointments.get(i).getPatient_id());
                        arrlPatients.add(patient);
                    }
                    
                    request.setAttribute("arrlAppointments", arrlAppointments);
                    request.setAttribute("arrlPatients", arrlPatients);
                } else if (account.getProfile().equals("ADMIN")) {
                    AdministratorService administratorService = new AdministratorService();
                    Administrator administrator = administratorService.get(account.getAccount_id());
                    request.setAttribute("user", administrator);
                   
                    ArrayList<Appointment> arrlAppointments = (ArrayList<Appointment>) appointments;
                    ArrayList<Patient> arrlPatients = new ArrayList();
                    ArrayList<Doctor> arrlDoctors = new ArrayList();
                        
                    for (int i = 0; i < arrlAppointments.size(); i++) {
                        Patient patient = patientService.getByPatientId(arrlAppointments.get(i).getPatient_id());
                        Doctor doctor = doctorService.getByDoctorID(arrlAppointments.get(i).getDoctor_id());
                        arrlPatients.add(patient);
                        arrlDoctors.add(doctor);
                    }
                       
                    request.setAttribute("arrlAppointments", arrlAppointments);
                    request.setAttribute("arrlPatients", arrlPatients);
                    request.setAttribute("arrlDoctors", arrlDoctors);
                } else if (account.getProfile().equals("PATIENT")) {
                    Patient patient = patientService.get(account.getAccount_id());
                    request.setAttribute("user", patient);
                    request.setAttribute("step", 1);
                    
                    List<Appointment> allAppointments = as.getByPatientID(patient.getPatient_id());
                    ArrayList<Appointment> pastAppointments = new ArrayList();
                    ArrayList<Appointment> futuerAppointments = new ArrayList();

                    for (int i = 0; i < allAppointments.size(); i++) {
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate beginDate = LocalDate.parse(allAppointments.get(i).getStart_date_time().substring(0, 10), df);
                         
                        if (beginDate.isBefore(tomorrow)) {
                            pastAppointments.add(allAppointments.get(i));
                        } else {
                            futuerAppointments.add(allAppointments.get(i));
                        }
                    }
                    
                    request.setAttribute("pastAppointments", pastAppointments);
                    request.setAttribute("futuerAppointments", futuerAppointments);

                    Doctor doctor = doctorService.getByDoctorID(patient.getDoctor_id());
                    request.setAttribute("doctorName", doctor.getFirst_name() + " " + doctor.getLast_name());
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
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewAppointment.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        
        AccountService as = new AccountService();
        PatientService ps = new PatientService();
        DoctorService ds = new DoctorService();
        AppointmentService aps = new AppointmentService();
        
        try {
            String action = request.getParameter("action");          
            boolean active = false;
            
            switch (action) {
                case "history_appointment":
                    Account account = as.get(user_name);
                    request.setAttribute("account", account);
                    request.setAttribute("step", 0);                    
                    
                    Patient patient = ps.get(account.getAccount_id());
                    request.setAttribute("user", patient);
                    
                    List<Appointment> allAppointments = aps.getByPatientID(patient.getPatient_id());
                    ArrayList<Appointment> pastAppointments = new ArrayList();
                    ArrayList<Appointment> futuerAppointments = new ArrayList();
                    LocalDate tomorrow = LocalDate.now().plusDays(1);
                    
                    for (int i = 0; i < allAppointments.size(); i++) {
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate beginDate = LocalDate.parse(allAppointments.get(i).getStart_date_time().substring(0, 10), df);
                         
                        if (beginDate.isBefore(tomorrow)) {
                            pastAppointments.add(allAppointments.get(i));
                        } else {
                            futuerAppointments.add(allAppointments.get(i));
                        }
                    }
                    
                    request.setAttribute("pastAppointments", pastAppointments);
                    request.setAttribute("futuerAppointments", futuerAppointments);
                    
                    Doctor doctor = ds.getByDoctorID(patient.getDoctor_id());
                    request.setAttribute("doctorName", doctor.getFirst_name() + " " + doctor.getLast_name());
                    active = true;  
                    
                    break;            
                case "edit":
                    String date = request.getParameter("date");  
                    String time = request.getParameter("time");  
                    Appointment appointment = aps.getByDate(date + " " + time);  
                    session.setAttribute("appointmentSessionObj", appointment);
                    
                    break;
            }
            
            if (active){   
                getServletContext().getRequestDispatcher("/WEB-INF/viewAppointment.jsp").forward(request, response);
                return; 
            }
           
            response.sendRedirect("edit_appointment");
            return;
        } catch (Exception ex) {
            Logger.getLogger(EditAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
