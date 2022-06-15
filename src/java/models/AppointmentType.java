package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentType {
    private int type;
    private String description;
    private int std_duration;
    
    public AppointmentType() {
    }
  
    public AppointmentType(int type , String description, int std_duration){
      this.type = type; 
      this.description = description; 
      this.std_duration = std_duration; 
    }
    
    public int getType(){
        return type;
    }  

    public String getDescription() {
        return description;
    }   

    public void setDescription(String description) {
        this.description = description;
    } 
     
    public int getStd_duration(){
        return std_duration;
    }

    public void setStd_duration(int std_duration) {
        this.std_duration = std_duration;
    }
    
    @Override
    public String toString() {
        return "AppointmentType{" +
                "type=" + type +
                ", description='" + description + '\'' +
                ", std_duration='" + std_duration + '\'' +
                '}';
    }
}
