<<<<<<< HEAD
package service;

import dataaccess.AES;
import dataaccess.AdministratorDB;
import dataaccess.PatientDB;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import models.Administrator;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AdministratorService {
    public List<Administrator> getAll() throws Exception {
        AdministratorDB administratorDB = new AdministratorDB();
        List<Administrator> administrators = administratorDB.getAll();
        List<Administrator> decryptedAdmins = new ArrayList<Administrator>();
        for (int i = 0; i < administrators.size(); i++) {
            decryptedAdmins.add(decodingAdmin(administrators.get(i)));
        }
        return decryptedAdmins;
    }
    
     public void setAllDataEncrypt() throws Exception {
        AdministratorDB doctorDB = new AdministratorDB();
        List<Administrator> admins = doctorDB.getAll();
        for (int i = 0; i < admins.size(); i++) {
            this.update(admins.get(i).getAdmin_id(), admins.get(i).getFirst_name(), 
                    admins.get(i).getLast_name(), admins.get(i).getEmail(), admins.get(i).getMobile_phone(), admins.get(i).getAlt_phone(), 
                    admins.get(i).getPref_contact_type(), admins.get(i).getAccount_id(), admins.get(i).getGender(), 
                    admins.get(i).getBirth_date(), admins.get(i).getStreet_address(), admins.get(i).getCity(), admins.get(i).getProvince(), 
                    admins.get(i).getPostal_code());
            
        }
    }
    
    public Administrator get(int account_id) throws Exception {
        AdministratorDB administratorDB = new AdministratorDB();
        Administrator administrator = administratorDB.get(account_id);
        Administrator decryptedAdmin = decodingAdmin(administrator);
        return decryptedAdmin;
    }
    
    public void insert(int doctor_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        AdministratorDB administratorDB = new AdministratorDB();
        
        Administrator administrator = new Administrator(doctor_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone),
                pref_contact_type, account_id, gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        administratorDB.insert(administrator);
    }
    
    public void update(int doctor_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        AdministratorDB administratorDB = new AdministratorDB();
        
        Administrator administrator = new Administrator(doctor_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone),
                pref_contact_type, account_id, gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        administratorDB.update(administrator);
    }
    
    public void delete(int account_id) throws Exception {
        Administrator administrator = get(account_id);
        AdministratorDB administratorDB = new AdministratorDB();
        administratorDB.delete(administrator);
    }

    public Administrator decodingAdmin(Administrator admin) {
        AES aes = new AES();
        Administrator decodedAdmin = null;

        decodedAdmin = new Administrator(admin.getAdmin_id(), aes.decrypt(admin.getFirst_name()), aes.decrypt(admin.getLast_name()),
                aes.decrypt(admin.getEmail()), aes.decrypt(admin.getMobile_phone()), aes.decrypt(admin.getAlt_phone()), admin.getPref_contact_type(),
                admin.getAccount_id(), admin.getGender(), admin.getBirth_date(), aes.decrypt(admin.getStreet_address()), aes.decrypt(admin.getCity()),
                aes.decrypt(admin.getProvince()), aes.decrypt(admin.getPostal_code()));

        return decodedAdmin;
    }
    
     public Administrator encodingDoctor(Administrator admin) {
        AES aes = new AES();
        Administrator encodedAdmin = null;

        encodedAdmin = new Administrator(admin.getAdmin_id(), aes.encrypt(admin.getFirst_name()), aes.encrypt(admin.getLast_name()),
                aes.encrypt(admin.getEmail()), aes.encrypt(admin.getMobile_phone()), aes.encrypt(admin.getAlt_phone()), admin.getPref_contact_type(),
                admin.getAccount_id(), admin.getGender(), admin.getBirth_date(), aes.encrypt(admin.getStreet_address()), aes.encrypt(admin.getCity()),
                aes.encrypt(admin.getProvince()), aes.encrypt(admin.getPostal_code()));

        return encodedAdmin;
    }
}
=======
package service;

import dataaccess.*;
import java.util.*;
import models.Administrator;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AdministratorService {
    public List<Administrator> getAll() throws Exception {
        AdministratorDB administratorDB = new AdministratorDB();
        List<Administrator> administrators = administratorDB.getAll();
        List<Administrator> decryptedAdmins = new ArrayList<Administrator>();
        
        for (int i = 0; i < administrators.size(); i++) {
            decryptedAdmins.add(decodingAdmin(administrators.get(i)));
        }
        
        return decryptedAdmins;
    }
    
     public void setAllDataEncrypt() throws Exception {
        AdministratorDB administratorDB = new AdministratorDB();
        List<Administrator> admins = administratorDB.getAll();
        
        for (int i = 0; i < admins.size(); i++) {
            this.update(admins.get(i).getAdmin_id(), admins.get(i).getFirst_name(), 
                    admins.get(i).getLast_name(), admins.get(i).getEmail(), admins.get(i).getMobile_phone(), admins.get(i).getAlt_phone(), 
                    admins.get(i).getPref_contact_type(), admins.get(i).getAccount_id(), admins.get(i).getGender(), 
                    admins.get(i).getBirth_date(), admins.get(i).getStreet_address(), admins.get(i).getCity(), admins.get(i).getProvince(), 
                    admins.get(i).getPostal_code());            
        }
    }
    
    public Administrator get(int account_id) throws Exception {
        AdministratorDB administratorDB = new AdministratorDB();
        Administrator administrator = administratorDB.get(account_id);
        Administrator decryptedAdmin = decodingAdmin(administrator);
        
        return decryptedAdmin;
    }
    
    public void insert(int admin_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        AdministratorDB administratorDB = new AdministratorDB();
        
        Administrator administrator = new Administrator(admin_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email),
                aes.encrypt(mobile_phone), aes.encrypt(alt_phone), pref_contact_type, account_id, gender, birth_date,
                aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        administratorDB.insert(administrator);
    }
    
    public void update(int admin_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        AdministratorDB administratorDB = new AdministratorDB();
        
        Administrator administrator = new Administrator(admin_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email),
                aes.encrypt(mobile_phone), aes.encrypt(alt_phone), pref_contact_type, account_id, gender, birth_date,
                aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        administratorDB.update(administrator);
    }
    
    public void delete(int account_id) throws Exception {
        AdministratorDB administratorDB = new AdministratorDB();
        administratorDB.delete(account_id);
    }

    public Administrator decodingAdmin(Administrator admin) {
        AES aes = new AES();
        Administrator decodedAdmin = null;

        decodedAdmin = new Administrator(admin.getAdmin_id(), aes.decrypt(admin.getFirst_name()), aes.decrypt(admin.getLast_name()),
                aes.decrypt(admin.getEmail()), aes.decrypt(admin.getMobile_phone()), aes.decrypt(admin.getAlt_phone()), admin.getPref_contact_type(),
                admin.getAccount_id(), admin.getGender(), admin.getBirth_date(), aes.decrypt(admin.getStreet_address()), aes.decrypt(admin.getCity()),
                aes.decrypt(admin.getProvince()), aes.decrypt(admin.getPostal_code()));

        return decodedAdmin;
    }
    
     public Administrator encodingAdmin(Administrator admin) {
        AES aes = new AES();
        Administrator encodedAdmin = null;

        encodedAdmin = new Administrator(admin.getAdmin_id(), aes.encrypt(admin.getFirst_name()), aes.encrypt(admin.getLast_name()),
                aes.encrypt(admin.getEmail()), aes.encrypt(admin.getMobile_phone()), aes.encrypt(admin.getAlt_phone()), admin.getPref_contact_type(),
                admin.getAccount_id(), admin.getGender(), admin.getBirth_date(), aes.encrypt(admin.getStreet_address()), aes.encrypt(admin.getCity()),
                aes.encrypt(admin.getProvince()), aes.encrypt(admin.getPostal_code()));

        return encodedAdmin;
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
