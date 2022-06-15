<<<<<<< HEAD
package service;

import dataaccess.AES;
import dataaccess.PatientDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import models.Account;
import models.Patient;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class PatientService {

    public List<Patient> getAll() throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAll();
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        for (int i = 0; i < patients.size(); i++) {
            decryptedPatients.add(decodingPatient(patients.get(i)));
        }
        return decryptedPatients;
    }

    public void setAllDataEncrypt() throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAll();
        
        for (int i = 0; i < patients.size(); i++) {
            this.update(patients.get(i).getPatient_id(), patients.get(i).getHealthcare_id(), patients.get(i).getFirst_name(),
                    patients.get(i).getLast_name(), patients.get(i).getEmail(), patients.get(i).getMobile_phone(), patients.get(i).getAlt_phone(),
                    patients.get(i).getPref_contact_type(), patients.get(i).getDoctor_id(), patients.get(i).getAccount_id(), patients.get(i).getGender(),
                    patients.get(i).getBirth_date(), patients.get(i).getStreet_address(), patients.get(i).getCity(), patients.get(i).getProvince(),
                    patients.get(i).getPostal_code());

        }
    }

    public List<Patient> getAllByDoctor(int doctor_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAllByDoctor(doctor_id);
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        for (int i = 0; i < patients.size(); i++) {
            decryptedPatients.add(decodingPatient(patients.get(i)));
        }
        return decryptedPatients;
    }

    public List<Patient> getAllByName(String name) throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAll();
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = decodingPatient(patients.get(i));
            if(patient.getFirst_name().toLowerCase().contains(name.toLowerCase()) || patient.getLast_name().toLowerCase().contains(name.toLowerCase())){
                decryptedPatients.add(patient);
            }
        }
        return decryptedPatients;
    }
    
    public List<Patient> getAllAssignedByName(String name, int doctor_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAllByDoctor(doctor_id);
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = decodingPatient(patients.get(i));
            if(patient.getFirst_name().toLowerCase().contains(name.toLowerCase()) || patient.getLast_name().toLowerCase().contains(name.toLowerCase())){
                decryptedPatients.add(patient);
            }
        }
        return decryptedPatients;
    }

    public Patient get(int account_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        Patient patient = patientDB.get(account_id);
        Patient decryptedPatient = decodingPatient(patient);
        return decryptedPatient;
    }

    public Patient getByPatientId(int patient_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        Patient patient = patientDB.getByPatientId(patient_id);
        Patient decryptedPatient = decodingPatient(patient);
        return decryptedPatient;
    }

    public void insert(int patient_id, String healthcare_id, String first_name, String last_name, String email,
            String mobile_phone, String alt_phone, String pref_contact_type, int doctor_id, int account_id, String gender,
            String birth_date, String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        PatientDB patientDB = new PatientDB();

        Patient patient = new Patient(patient_id, aes.encrypt(healthcare_id), aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone),
                pref_contact_type, doctor_id, account_id, gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        patientDB.insert(patient);
    }

    public void update(int patient_id, String healthcare_id, String first_name, String last_name, String email,
            String mobile_phone, String alt_phone, String pref_contact_type, int doctor_id, int account_id, String gender,
            String birth_date, String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();

        PatientDB patientDB = new PatientDB();
        Patient patient = new Patient(patient_id, aes.encrypt(healthcare_id), aes.encrypt(first_name), aes.encrypt(last_name), aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone),
                pref_contact_type, doctor_id, account_id, gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        patientDB.update(patient);
    }

    public void delete(int account_id) throws Exception {
        Patient patient = get(account_id);
        PatientDB patientDB = new PatientDB();
        patientDB.delete(patient);
    }

    public Patient decodingPatient(Patient patient) {
        AES aes = new AES();
        Patient decodedPatient = null;
        try {

            decodedPatient = new Patient(patient.getPatient_id(), aes.decrypt(patient.getHealthcare_id()), aes.decrypt(patient.getFirst_name()), aes.decrypt(patient.getLast_name()), aes.decrypt(patient.getEmail()), aes.decrypt(patient.getMobile_phone()), aes.decrypt(patient.getAlt_phone()),
                    patient.getPref_contact_type(), patient.getDoctor_id(), patient.getAccount_id(), patient.getGender(), patient.getBirth_date(), aes.decrypt(patient.getStreet_address()), aes.decrypt(patient.getCity()), aes.decrypt(patient.getProvince()), aes.decrypt(patient.getPostal_code()));
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decodedPatient;
    }

    public Patient encodingPatient(Patient patient) {
        AES aes = new AES();
        Patient encodedPatient = null;
        try {
            encodedPatient = new Patient(patient.getPatient_id(), aes.encrypt(patient.getHealthcare_id()), aes.encrypt(patient.getFirst_name()), aes.encrypt(patient.getLast_name()), aes.encrypt(patient.getEmail()), aes.encrypt(patient.getMobile_phone()), aes.encrypt(patient.getAlt_phone()),
                    patient.getPref_contact_type(), patient.getDoctor_id(), patient.getAccount_id(), patient.getGender(), patient.getBirth_date(), aes.encrypt(patient.getStreet_address()), aes.encrypt(patient.getCity()), aes.encrypt(patient.getProvince()), aes.encrypt(patient.getPostal_code()));
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodedPatient;
    }
}
=======
package service;

import dataaccess.*;
import java.util.*;
import java.util.logging.*;
import models.Patient;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class PatientService {
    public List<Patient> getAll() throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAll();
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        
        for (int i = 0; i < patients.size(); i++) {
            decryptedPatients.add(decodingPatient(patients.get(i)));
        }
        
        return decryptedPatients;
    }

    public void setAllDataEncrypt() throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAll();
        
        for (int i = 0; i < patients.size(); i++) {
            this.update(patients.get(i).getPatient_id(), patients.get(i).getHealthcare_id(), patients.get(i).getFirst_name(),
                    patients.get(i).getLast_name(), patients.get(i).getEmail(), patients.get(i).getMobile_phone(),
                    patients.get(i).getAlt_phone(), patients.get(i).getPref_contact_type(), patients.get(i).getDoctor_id(),
                    patients.get(i).getAccount_id(), patients.get(i).getGender(), patients.get(i).getBirth_date(),
                    patients.get(i).getStreet_address(), patients.get(i).getCity(), patients.get(i).getProvince(),
                    patients.get(i).getPostal_code());
        }
    }

    public List<Patient> getAllByDoctor(int doctor_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAllByDoctor(doctor_id);
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        
        for (int i = 0; i < patients.size(); i++) {
            decryptedPatients.add(decodingPatient(patients.get(i)));
        }
        
        return decryptedPatients;
    }

    public List<Patient> getAllByName(String name) throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAll();
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = decodingPatient(patients.get(i));
            
            if(patient.getFirst_name().toLowerCase().contains(name.toLowerCase()) ||
                    patient.getLast_name().toLowerCase().contains(name.toLowerCase())){
                decryptedPatients.add(patient);
            }
        }
        
        return decryptedPatients;
    }
    
    public List<Patient> getAllAssignedByName(String name, int doctor_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        List<Patient> patients = patientDB.getAllByDoctor(doctor_id);
        List<Patient> decryptedPatients = new ArrayList<Patient>();
        
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = decodingPatient(patients.get(i));
            
            if(patient.getFirst_name().toLowerCase().contains(name.toLowerCase()) ||
                    patient.getLast_name().toLowerCase().contains(name.toLowerCase())){
                decryptedPatients.add(patient);
            }
        }
        
        return decryptedPatients;
    }

    public Patient get(int account_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        Patient patient = patientDB.get(account_id);
        Patient decryptedPatient = decodingPatient(patient);
        
        return decryptedPatient;
    }

    public Patient getByPatientId(int patient_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        Patient patient = patientDB.getByPatientId(patient_id);
        Patient decryptedPatient = decodingPatient(patient);
        
        return decryptedPatient;
    }

    public void insert(int patient_id, String healthcare_id, String first_name, String last_name, String email,
            String mobile_phone, String alt_phone, String pref_contact_type, int doctor_id, int account_id, String gender,
            String birth_date, String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();
        PatientDB patientDB = new PatientDB();

        Patient patient = new Patient(patient_id, aes.encrypt(healthcare_id), aes.encrypt(first_name), aes.encrypt(last_name),
                aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone), pref_contact_type, doctor_id, account_id,
                gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        patientDB.insert(patient);
    }

    public void update(int patient_id, String healthcare_id, String first_name, String last_name, String email,
            String mobile_phone, String alt_phone, String pref_contact_type, int doctor_id, int account_id, String gender,
            String birth_date, String street_address, String city, String province, String postal_code) throws Exception {
        AES aes = new AES();

        PatientDB patientDB = new PatientDB();
        Patient patient = new Patient(patient_id, aes.encrypt(healthcare_id), aes.encrypt(first_name), aes.encrypt(last_name),
                aes.encrypt(email), aes.encrypt(mobile_phone), aes.encrypt(alt_phone), pref_contact_type, doctor_id, account_id,
                gender, birth_date, aes.encrypt(street_address), aes.encrypt(city), aes.encrypt(province), aes.encrypt(postal_code));
        patientDB.update(patient);
    }

    public void delete(int account_id) throws Exception {
        PatientDB patientDB = new PatientDB();
        patientDB.delete(account_id);
    }

    public Patient decodingPatient(Patient patient) {
        AES aes = new AES();
        Patient decodedPatient = null;
        
        try {
            decodedPatient = new Patient(patient.getPatient_id(), aes.decrypt(patient.getHealthcare_id()), aes.decrypt(patient.getFirst_name()),
                    aes.decrypt(patient.getLast_name()), aes.decrypt(patient.getEmail()), aes.decrypt(patient.getMobile_phone()),
                    aes.decrypt(patient.getAlt_phone()), patient.getPref_contact_type(), patient.getDoctor_id(), patient.getAccount_id(),
                    patient.getGender(), patient.getBirth_date(), aes.decrypt(patient.getStreet_address()), aes.decrypt(patient.getCity()),
                    aes.decrypt(patient.getProvince()), aes.decrypt(patient.getPostal_code()));
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return decodedPatient;
    }

    public Patient encodingPatient(Patient patient) {
        AES aes = new AES();
        Patient encodedPatient = null;
        
        try {
            encodedPatient = new Patient(patient.getPatient_id(), aes.encrypt(patient.getHealthcare_id()), aes.encrypt(patient.getFirst_name()),
                    aes.encrypt(patient.getLast_name()), aes.encrypt(patient.getEmail()), aes.encrypt(patient.getMobile_phone()),
                    aes.encrypt(patient.getAlt_phone()), patient.getPref_contact_type(), patient.getDoctor_id(), patient.getAccount_id(),
                    patient.getGender(), patient.getBirth_date(), aes.encrypt(patient.getStreet_address()), aes.encrypt(patient.getCity()),
                    aes.encrypt(patient.getProvince()), aes.encrypt(patient.getPostal_code()));
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encodedPatient;
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
