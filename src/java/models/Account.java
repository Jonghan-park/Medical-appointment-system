<<<<<<< HEAD
package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class Account {
    private int account_id;
    private String user_name;
    private String password;
    private String profile;
    private String reset_password_uuid;
    private String salt;
    
    public Account() {
    }
    
    public Account(int account_id, String user_name, String password, String profile, String reset_password_uuid, String salt) {
        this.account_id = account_id;
        this.user_name = user_name;
        this.password = password;
        this.profile = profile;
        this.reset_password_uuid = reset_password_uuid;
        this.salt = salt;
    }
    
    public Account(int account_id, String user_name, String password) {
           this.account_id = account_id;
        this.user_name = user_name;
        this.password = password;
    }
    
    public int getAccount_id() {
        return account_id;
    }
    
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    public String getReset_password_uuid() {
        return reset_password_uuid;
    }

    public void setReset_password_uuid(String reset_password_uuid) {
        this.reset_password_uuid = reset_password_uuid;
    }
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
=======
package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class Account {
    private int account_id;
    private String user_name;
    private String password;
    private String profile;
    private String reset_password_uuid;
    private String salt;
    
    public Account() {
    }
    
    public Account(int account_id, String user_name, String password,
            String profile, String reset_password_uuid, String salt) {
        this.account_id = account_id;
        this.user_name = user_name;
        this.password = password;
        this.profile = profile;
        this.reset_password_uuid = reset_password_uuid;
        this.salt = salt;
    }
    
    public Account(int account_id, String user_name, String password) {
        this.account_id = account_id;
        this.user_name = user_name;
        this.password = password;
    }
    
    public int getAccount_id() {
        return account_id;
    }
    
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    public String getReset_password_uuid() {
        return reset_password_uuid;
    }

    public void setReset_password_uuid(String reset_password_uuid) {
        this.reset_password_uuid = reset_password_uuid;
    }
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
