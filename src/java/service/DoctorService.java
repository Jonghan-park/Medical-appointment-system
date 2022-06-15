<<<<<<< HEAD
package service;

import dataaccess.AES;
import dataaccess.DoctorDB;
import java.util.ArrayList;
import java.util.List;
import models.Doctor;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class DoctorService {

    public List<Doctor> getAll() throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        List<Doctor> doctors = doctorDB.getAll();
        List<Doctor> decryptedDoctors = new ArrayList<Doctor>();
        for (int i = 0; i < doctors.size(); i++) {
            decryptedDoctors.add(decodingDoctor(doctors.get(i)));
        }
        return decryptedDoctors;
    }
    
    public void setAllDataEncrypt() throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        List<Doctor> doctors = doctorDB.getAll();
        for (int i = 0; i < doctors.size(); i++) {
            this.update(doctors.get(i).getDoctor_id(), doctors.get(i).getFirst_name(), 
                    doctors.get(i).getLast_name(), doctors.get(i).getEmail(), doctors.get(i).getMobile_phone(), doctors.get(i).getAlt_phone(), 
                    doctors.get(i).getPref_contact_type(), doctors.get(i).getAccount_id(), doctors.get(i).getGender(), 
                    doctors.get(i).getBirth_date(), doctors.get(i).getStreet_address(), doctors.get(i).getCity(), doctors.get(i).getProvince(), 
                    doctors.get(i).getPostal_code());
            
        }
    }

    public List<Doctor> getAllByName(String name) throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        List<Doctor> doctors = doctorDB.getAllByName(name);
        List<Doctor> decryptedDoctors = new ArrayList<Doctor>();
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = decodingDoctor(doctors.get(i));
            if(doctor.getFirst_name().toLowerCase().contains(name.toLowerCase()) || doctor.getLast_name().toLowerCase().contains(name.toLowerCase())){
                decryptedDoctors.add(doctor);
            }
        }
        return decryptedDoctors;
    }

    public Doctor get(int account_id) throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        Doctor doctor = doctorDB.get(account_id);
        Doctor decryptedDoctor = decodingDoctor(doctor);
        return decryptedDoctor;
    }

    public Doctor getByDoctorID(int doctor_id) throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        Doctor doctor = doctorDB.getByDoctorID(doctor_id);
        Doctor decryptedDoctor = decodingDoctor(doctor);
        return decryptedDoctor;
    }

    public void insert(int doctor_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        DoctorDB doctorDB = new DoctorDB();
        Doctor doctor = new Doctor(doctor_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone),
                pref_contact_type, account_id, gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        doctorDB.insert(doctor);
    }

    public void update(int doctor_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        DoctorDB doctorDB = new DoctorDB();
        Doctor doctor = new Doctor(doctor_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone),
                pref_contact_type, account_id, gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        doctorDB.update(doctor);
    }

    public void delete(int account_id) throws Exception {
        Doctor doctor = get(account_id);
        DoctorDB doctorDB = new DoctorDB();
        doctorDB.delete(doctor);
    }

    public Doctor decodingDoctor(Doctor doctor) {
        AES aes = new AES();
        Doctor decodedDoctor = null;

        decodedDoctor = new Doctor(doctor.getDoctor_id(), aes.decrypt(doctor.getFirst_name()), aes.decrypt(doctor.getLast_name()),
                aes.decrypt(doctor.getEmail()), aes.decrypt(doctor.getMobile_phone()), aes.decrypt(doctor.getAlt_phone()), doctor.getPref_contact_type(),
                doctor.getAccount_id(), doctor.getGender(), doctor.getBirth_date(), aes.decrypt(doctor.getStreet_address()), aes.decrypt(doctor.getCity()),
                aes.decrypt(doctor.getProvince()), aes.decrypt(doctor.getPostal_code()));

        return decodedDoctor;
    }
    
     public Doctor encodingDoctor(Doctor doctor) {
        AES aes = new AES();
        Doctor encodedDoctor = null;

        encodedDoctor = new Doctor(doctor.getDoctor_id(), aes.encrypt(doctor.getFirst_name()), aes.encrypt(doctor.getLast_name()),
                aes.encrypt(doctor.getEmail()), aes.encrypt(doctor.getMobile_phone()), aes.encrypt(doctor.getAlt_phone()), doctor.getPref_contact_type(),
                doctor.getAccount_id(), doctor.getGender(), doctor.getBirth_date(), aes.encrypt(doctor.getStreet_address()), aes.encrypt(doctor.getCity()),
                aes.encrypt(doctor.getProvince()), aes.encrypt(doctor.getPostal_code()));

        return encodedDoctor;
    }

}
=======
package service;

import dataaccess.*;
import java.util.*;
import models.Doctor;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class DoctorService {
    public List<Doctor> getAll() throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        List<Doctor> doctors = doctorDB.getAll();
        List<Doctor> decryptedDoctors = new ArrayList<Doctor>();
        
        for (int i = 0; i < doctors.size(); i++) {
            decryptedDoctors.add(decodingDoctor(doctors.get(i)));
        }
        
        return decryptedDoctors;
    }
    
    public void setAllDataEncrypt() throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        List<Doctor> doctors = doctorDB.getAll();
        
        for (int i = 0; i < doctors.size(); i++) {
            this.update(doctors.get(i).getDoctor_id(), doctors.get(i).getFirst_name(), 
                    doctors.get(i).getLast_name(), doctors.get(i).getEmail(), doctors.get(i).getMobile_phone(), doctors.get(i).getAlt_phone(), 
                    doctors.get(i).getPref_contact_type(), doctors.get(i).getAccount_id(), doctors.get(i).getGender(), 
                    doctors.get(i).getBirth_date(), doctors.get(i).getStreet_address(), doctors.get(i).getCity(), doctors.get(i).getProvince(), 
                    doctors.get(i).getPostal_code());            
        }
    }

    public List<Doctor> getAllByName(String name) throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        List<Doctor> doctors = doctorDB.getAll();
        List<Doctor> decryptedDoctors = new ArrayList<Doctor>();
        
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = decodingDoctor(doctors.get(i));
            
            if(doctor.getFirst_name().toLowerCase().contains(name.toLowerCase()) ||
                    doctor.getLast_name().toLowerCase().contains(name.toLowerCase())){
                decryptedDoctors.add(doctor);
            }
        }
        
        return decryptedDoctors;
    }

    public Doctor get(int account_id) throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        Doctor doctor = doctorDB.get(account_id);
        Doctor decryptedDoctor = decodingDoctor(doctor);
        
        return decryptedDoctor;
    }

    public Doctor getByDoctorID(int doctor_id) throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        Doctor doctor = doctorDB.getByDoctorID(doctor_id);
        Doctor decryptedDoctor = decodingDoctor(doctor);
        
        return decryptedDoctor;
    }

    public void insert(int doctor_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        DoctorDB doctorDB = new DoctorDB();
        
        Doctor doctor = new Doctor(doctor_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email),
                aes.encrypt(mobile_phone), aes.encrypt(alt_phone), pref_contact_type, account_id, gender, birth_date,
                aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        doctorDB.insert(doctor);
    }

    public void update(int doctor_id, String first_name, String last_name, String email, String mobile_phone,
            String alt_phone, String pref_contact_type, int account_id, String gender, String birth_date,
            String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        DoctorDB doctorDB = new DoctorDB();
        
        Doctor doctor = new Doctor(doctor_id, aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email),
                aes.encrypt(mobile_phone), aes.encrypt(alt_phone), pref_contact_type, account_id, gender, birth_date,
                aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        doctorDB.update(doctor);
    }

    public void delete(int account_id) throws Exception {
        DoctorDB doctorDB = new DoctorDB();
        doctorDB.delete(account_id);
    }

    public Doctor decodingDoctor(Doctor doctor) {
        AES aes = new AES();
        Doctor decodedDoctor = null;

        decodedDoctor = new Doctor(doctor.getDoctor_id(), aes.decrypt(doctor.getFirst_name()), aes.decrypt(doctor.getLast_name()),
                aes.decrypt(doctor.getEmail()), aes.decrypt(doctor.getMobile_phone()), aes.decrypt(doctor.getAlt_phone()), doctor.getPref_contact_type(),
                doctor.getAccount_id(), doctor.getGender(), doctor.getBirth_date(), aes.decrypt(doctor.getStreet_address()), aes.decrypt(doctor.getCity()),
                aes.decrypt(doctor.getProvince()), aes.decrypt(doctor.getPostal_code()));

        return decodedDoctor;
    }
    
     public Doctor encodingDoctor(Doctor doctor) {
        AES aes = new AES();
        Doctor encodedDoctor = null;

        encodedDoctor = new Doctor(doctor.getDoctor_id(), aes.encrypt(doctor.getFirst_name()), aes.encrypt(doctor.getLast_name()),
                aes.encrypt(doctor.getEmail()), aes.encrypt(doctor.getMobile_phone()), aes.encrypt(doctor.getAlt_phone()), doctor.getPref_contact_type(),
                doctor.getAccount_id(), doctor.getGender(), doctor.getBirth_date(), aes.encrypt(doctor.getStreet_address()), aes.encrypt(doctor.getCity()),
                aes.encrypt(doctor.getProvince()), aes.encrypt(doctor.getPostal_code()));

        return encodedDoctor;
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
