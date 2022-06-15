<<<<<<< HEAD
package service;

import dataaccess.AppointmentDB;
import java.util.List;
import models.Appointment;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentService {
    public List<Appointment> getAll() throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getAll();
        return appointments;
    }
    
    public List<Appointment> getAllByDate(String date) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getAllByDate(date);
        return appointments;
    }
    
    public List<Appointment> getAllFutures(String date) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getAllFutures(date);
        return appointments;
    }
    
    public List <Appointment> getByDoctorID(int doctor_id) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getByDoctorID(doctor_id);
        return appointments;
    }
    
    public List<Appointment> getByPatientID(int patient_id) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getByPatientID(patient_id);
        return appointments;
    }
    
    public Appointment getByDate(String start_date_time) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        Appointment appointment = appointmentDB.getByDate(start_date_time);
        return appointment;
    }
    
    public void insert(int doctor_id, String start_date_time, int patient_id, int duration,
            int type, String reason, boolean patient_attended) throws Exception {
        Appointment appointment =
                new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.insert(appointment);
    }
    
    public void update(int doctor_id, String start_date_time, int patient_id, int duration,
            int type, String reason, boolean patient_attended) throws Exception {
        Appointment appointment =
                new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.update(appointment);
    }
    
    public void assignDoc(int doctor_id, int patient_id, String start_date_time) throws Exception{
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.assignDoctor(doctor_id, patient_id, start_date_time);
    }
    
    public void delete (String time, int doctor_id, int patient_id) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.delete(time, doctor_id, patient_id);
    }    

}
=======
package service;

import dataaccess.AppointmentDB;
import java.util.List;
import models.Appointment;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentService {
    public List<Appointment> getAll() throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getAll();
        return appointments;
    }
    
    public List<Appointment> getAllByDate(String date) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getAllByDate(date);
        return appointments;
    }
    
    public List<Appointment> getAllFutures(String date) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getAllFutures(date);
        return appointments;
    }
    
    public List <Appointment> getByDoctorID(int doctor_id) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getByDoctorID(doctor_id);
        return appointments;
    }
    
    public List<Appointment> getByPatientID(int patient_id) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        List<Appointment> appointments = appointmentDB.getByPatientID(patient_id);
        return appointments;
    }
    
    public Appointment getByDate(String start_date_time) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        Appointment appointment = appointmentDB.getByDate(start_date_time);
        return appointment;
    }
    
    public void insert(int doctor_id, String start_date_time, int patient_id, int duration,
            int type, String reason, boolean patient_attended) throws Exception {
        Appointment appointment =
                new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.insert(appointment);
    }
    
    public void update(int doctor_id, String start_date_time, int patient_id, int duration,
            int type, String reason, boolean patient_attended) throws Exception {
        Appointment appointment =
                new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.update(appointment);
    }
    
    public void assignDoc(int doctor_id, int patient_id, String start_date_time) throws Exception{
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.assignDoctor(doctor_id, patient_id, start_date_time);
    }
    
    public void delete (String time, int doctor_id, int patient_id) throws Exception {
        AppointmentDB appointmentDB = new AppointmentDB();
        appointmentDB.delete(time, doctor_id, patient_id);
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
