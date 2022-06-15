<<<<<<< HEAD
package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class Appointment {
    private int doctor_id;
    private String start_date_time;
    private int patient_id;
    private int duration;
    private int type;
    private String reason;
    private boolean patient_attended;

    public Appointment() {
    }
    
    public Appointment(int doctor_id, String start_date_time, int patient_id,
            int duration, int type, String reason, boolean patient_attended) {
        this.doctor_id = doctor_id;
        this.start_date_time = start_date_time;
        this.patient_id = patient_id;
        this.duration = duration;
        this.type = type;
        this.reason = reason;
        this.patient_attended = patient_attended;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getStart_date_time() {
        return start_date_time;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public boolean getPatient_attended() {
        return patient_attended;
    }

    public void setPatient_attended(boolean patient_attended) {
        this.patient_attended = patient_attended;
    }
    
    public String getType_toString(int id) {
        String typeString = "";
        
        if (type ==1) {
            typeString= "Regular";
        }else if(type==2){
            typeString ="Annual Physical";
        }else{
            typeString = "Urgent Care";
        }
        
        return typeString;
    }
    
    @Override
    public String toString() {
        return "Appointment{" +
                "doctor_id=" + doctor_id +
                ", start_date_time='" + start_date_time + '\'' +
                ", patient_id='" + patient_id + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", patient_attended='" + patient_attended + '\'' +
                '}';
    }
}
=======
package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class Appointment {
    private int doctor_id;
    private String start_date_time;
    private int patient_id;
    private int duration;
    private int type;
    private String reason;
    private boolean patient_attended;

    public Appointment() {
    }
    
    public Appointment(int doctor_id, String start_date_time, int patient_id,
            int duration, int type, String reason, boolean patient_attended) {
        this.doctor_id = doctor_id;
        this.start_date_time = start_date_time;
        this.patient_id = patient_id;
        this.duration = duration;
        this.type = type;
        this.reason = reason;
        this.patient_attended = patient_attended;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getStart_date_time() {
        return start_date_time;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public boolean getPatient_attended() {
        return patient_attended;
    }

    public void setPatient_attended(boolean patient_attended) {
        this.patient_attended = patient_attended;
    }
    
    @Override
    public String toString() {
        return "Appointment{" +
                "doctor_id=" + doctor_id +
                ", start_date_time='" + start_date_time + '\'' +
                ", patient_id='" + patient_id + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", patient_attended='" + patient_attended + '\'' +
                '}';
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
