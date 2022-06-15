package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class Calendar {
    private String date_time;
    private String clinic_open;

    public Calendar() {
    }
    
    public Calendar(String date_time) {
        this.date_time = date_time;
    }
    
    public Calendar(String date_time, String clinic_open) {
        this.date_time = date_time;
        this.clinic_open = clinic_open;
    }

    public String getDate_time() {
        return date_time;
    }
    
    public String getClinic_open() {
        return clinic_open;
    }
    
    public void setClinic_open(String clinic_open) {
        this.clinic_open = clinic_open;
    }
    
    @Override
    public String toString() {
        return "Calendar{" +
                "date_time=" + date_time +
                ", clinic_open='" + clinic_open + '\'' +
                '}';
    }
}
