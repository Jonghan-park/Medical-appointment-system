package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class Availability {
    private int doctor_id;
    private String start_date_time;
    private int duration;
  
    public Availability() {
    }
    
    public Availability(int doctor_id, String start_date_time) {
        this.doctor_id = doctor_id; 
        this.start_date_time = start_date_time;
    }
    
    public Availability(int doctor_id, String start_date_time, int duration) {
        this.doctor_id = doctor_id; 
        this.start_date_time = start_date_time; 
        this.duration = duration; 
    }
   
    public int getDoctor_id(){
        return doctor_id;
    }  

     public String getStart_date_time() {
        return start_date_time;
    }
    
    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return "Availability{" +
                "doctor_id=" + doctor_id +
                ", start_date_time='" + start_date_time + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
