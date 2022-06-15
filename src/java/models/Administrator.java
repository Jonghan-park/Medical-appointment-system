package models;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class Administrator {
    private int admin_id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_phone;
    private String alt_phone;
    private String pref_contact_type;
    private int account_id;
    private String gender;
    private String birth_date;
    private String street_address;
    private String city;
    private String province;
    private String postal_code;

    public Administrator() {
    }
    
    public Administrator(int admin_id, String first_name, String last_name, String email,
            String mobile_phone, String alt_phone, String pref_contact_type, int account_id, String gender,
            String birth_date, String street_address, String city, String province, String postal_code) {
        this.admin_id = admin_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_phone = mobile_phone;
        this.alt_phone = alt_phone;
        this.pref_contact_type = pref_contact_type;
        this.account_id = account_id;
        this.gender = gender;
        this.birth_date = birth_date;
        this.street_address = street_address;
        this.city = city;
        this.province = province;
        this.postal_code = postal_code;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getAlt_phone() {
        return alt_phone;
    }

    public void setAlt_phone(String alt_phone) {
        this.alt_phone = alt_phone;
    }

    public String getPref_contact_type() {
        return pref_contact_type;
    }

    public void setPref_contact_type(String pref_contact_type) {
        this.pref_contact_type = pref_contact_type;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "admin_id=" + admin_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", alt_phone='" + alt_phone + '\'' +
                ", pref_contact_type='" + pref_contact_type + '\'' +
                ", account_id=" + account_id +
                ", gender='" + gender + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", street_address='" + street_address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postal_code='" + postal_code + '\'' +
                '}';
    }
}
