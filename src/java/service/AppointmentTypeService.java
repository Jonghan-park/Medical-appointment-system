<<<<<<< HEAD
package service;

import dataaccess.AppointmentTypeDB;
import java.util.List;
import models.AppointmentType;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentTypeService {
    public List<AppointmentType> getAll() throws Exception {
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        List<AppointmentType> appointmentTypes = appointmentTypeDB.getAll();
        return appointmentTypes;
    }
    
    public AppointmentType get(int type) throws Exception {
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        AppointmentType appointmentType = appointmentTypeDB.get(type);
        return appointmentType;
    }
    
    public void insert(int type , String description, int std_duration) throws Exception {
        AppointmentType appointmentType = new AppointmentType(type , description, std_duration);
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        appointmentTypeDB.insert(appointmentType);
    }
    
    public void update(int type , String description, int std_duration) throws Exception {
        AppointmentType appointmentType = new AppointmentType(type , description, std_duration);
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        appointmentTypeDB.update(appointmentType);
    }
    
    public void delete(int type) throws Exception {
        AppointmentType appointmentType = get(type);
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        appointmentTypeDB.delete(appointmentType);
    }
}
=======
package service;

import dataaccess.AppointmentTypeDB;
import java.util.List;
import models.AppointmentType;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentTypeService {
    public List<AppointmentType> getAll() throws Exception {
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        List<AppointmentType> appointmentTypes = appointmentTypeDB.getAll();
        return appointmentTypes;
    }
    
    public AppointmentType get(int type) throws Exception {
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        AppointmentType appointmentType = appointmentTypeDB.get(type);
        return appointmentType;
    }
    
    public void insert(int type , String description, int std_duration) throws Exception {
        AppointmentType appointmentType = new AppointmentType(type , description, std_duration);
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        appointmentTypeDB.insert(appointmentType);
    }
    
    public void update(int type , String description, int std_duration) throws Exception {
        AppointmentType appointmentType = new AppointmentType(type , description, std_duration);
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        appointmentTypeDB.update(appointmentType);
    }
    
    public void delete(int type) throws Exception {
        AppointmentTypeDB appointmentTypeDB = new AppointmentTypeDB();
        appointmentTypeDB.delete(type);
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
