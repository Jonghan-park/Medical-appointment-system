<<<<<<< HEAD
package service;

import dataaccess.*;
import java.util.*;
import java.util.logging.*;
import models.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AccountService {
    public Account login(String user_name, String password) {        
        try {
            Account account = get(user_name);
            String salt = account.getSalt();
            String userPassword = account.getPassword();
            
            if ((salt == null && password.equals(userPassword)) ||
                    (salt != null && userPassword.equals(PasswordUtil.hashAndSaltPassword(password, salt)))) {
                return account;
            }
        } catch (Exception e) {}
        
        return null;
    }
    
    public List<Account> getAll() throws Exception {
        AccountDB accountDB = new AccountDB();
        List<Account> accounts = accountDB.getAll();
        return accounts;
    }
    
    public Account get(String user_name) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(user_name);
        return account;
    }
    
    public Account get(int account_id) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(account_id);
        return account;
    }
    
    public void insert(int account_id, String user_name, String password, String profile) throws Exception {
        String salt = PasswordUtil.getSalt();
        Account account =
                new Account(account_id, user_name, PasswordUtil.hashAndSaltPassword(password, salt), profile, null, salt);
        AccountDB accountDB = new AccountDB();
        accountDB.insert(account);
    }
    
    public void update(int account_id, String user_name, String password, String profile) throws Exception {
        String salt = PasswordUtil.getSalt();
        Account account =
                new Account(account_id, user_name, PasswordUtil.hashAndSaltPassword(password, salt), profile, null, salt);
        AccountDB accountDB = new AccountDB();
        accountDB.update(account);
    }
    
    public void delete(String user_name) throws Exception {
        Account account = get(user_name);
        AccountDB accountDB = new AccountDB();
        accountDB.delete(account);
    }
    
    public Account resetPassword(String email, String path, String url) {
        String uuid = UUID.randomUUID().toString();
        String to = email;
        String subject = "Surpass Health Clinic Reset Password";
        String template = path + "/email_templates/reset_password.html";
        String link = url + "?uuid=" + uuid;
        AccountDB accountDB = new AccountDB();
        Account account = null;
        AES aes = new AES();
        
        try {
            account = accountDB.getByEmail(email);

            if (account.getProfile().equals("DOCTOR")) {
                DoctorDB doctorDB = new DoctorDB();
                Doctor doctor = doctorDB.get(account.getAccount_id());
                
                account.setReset_password_uuid(uuid);
                accountDB.update(account);

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", aes.decrypt(doctor.getFirst_name()));
                tags.put("lastname", aes.decrypt(doctor.getLast_name()));
                tags.put("link", link);
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);
            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorDB administratorDB = new AdministratorDB();
                Administrator administrator = administratorDB.get(account.getAccount_id());

                account.setReset_password_uuid(uuid);
                accountDB.update(account);

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", aes.decrypt(administrator.getFirst_name()));
                tags.put("lastname", aes.decrypt(administrator.getLast_name()));
                tags.put("link", link);
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);
            } else if (account.getProfile().equals("PATIENT")) {
                PatientDB patientDB = new PatientDB();
                Patient patient = patientDB.get(account.getAccount_id());

                account.setReset_password_uuid(uuid);
                accountDB.update(account);

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", aes.decrypt(patient.getFirst_name()));
                tags.put("lastname", aes.decrypt(patient.getLast_name()));
                tags.put("link", link);
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);
            }

            //GmailService.sendMail(email, "NotesKeeper Password", "Hello, To reset your password, please click this link : ", false);
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return account;
    }
    
    public boolean changePassword(String uuid, String password) {
        AccountDB accountDB = new AccountDB();
        
        try {
            Account account = accountDB.getByUUID(uuid);
            String salt = account.getSalt();
            String encryptedPassword = PasswordUtil.hashAndSaltPassword(password, salt);
            account.setPassword(encryptedPassword);
            account.setReset_password_uuid(null);
            accountDB.update(account);
            
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public Account findAccount(String firstName, String lastName, String email) throws Exception {
        AccountDB accountDB = new AccountDB();
        DoctorDB doctorDB = new DoctorDB();
        PatientDB patientDB = new PatientDB();
        AdministratorDB administratorDB = new AdministratorDB();
        Account account = accountDB.getByEmail(email);
        AES aes = new AES();
        
        if (account.getProfile().equals("DOCTOR")) {
            Doctor doctor = doctorDB.get(aes.encrypt(email));
            
            if (doctor.getFirst_name().equals(aes.encrypt(firstName)) && doctor.getLast_name().equals(aes.encrypt(lastName))) {
                return account;
            }
        } else if (account.getProfile().equals("PATIENT")) {
            Patient patient = patientDB.get(aes.encrypt(email));
            
            if (patient.getFirst_name().equals(aes.encrypt(firstName)) && patient.getLast_name().equals(aes.encrypt(lastName))) {
                return account;
            }
        } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
            Administrator administrator = administratorDB.get(aes.encrypt(email));
            
            if (administrator.getFirst_name().equals(aes.encrypt(firstName)) && administrator.getLast_name().equals(aes.encrypt(lastName))) {
                return account;
            }
        }
        
        return null;
    }
    
    public Account sendAccount(String email, String firstName, String LastName, String path, String url) throws Exception {
        String to = email;
        String subject = "Your ID From Surpass Health Clinic";
        String template = path + "/email_templates/find_account.html";

        AccountDB accountDB = new AccountDB();        
        Account account = null;
        AES aes = new AES();
        
        try {
            account = accountDB.getByEmail(email);

            if (account.getProfile().equals("DOCTOR")) {
                DoctorDB doctorDB = new DoctorDB();
                Doctor doctor = doctorDB.get(account.getAccount_id());

                if (doctor.getFirst_name().equals(aes.encrypt(firstName)) &&
                        doctor.getLast_name().equals(aes.encrypt(LastName)) && doctor.getEmail().equals(aes.encrypt(email))) {      
                    HashMap<String, String> tags = new HashMap<>();
                    tags.put("firstname", aes.decrypt(doctor.getFirst_name()));
                    tags.put("lastname", aes.decrypt(doctor.getLast_name()));
                    tags.put("id", account.getUser_name());
                    tags.put("link", url);
                    tags.put("date", (new java.util.Date()).toString());

                    GmailService.sendMail(to, subject, template, tags);
                } else {
                    return null;
                }

            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorDB administratorDB = new AdministratorDB();
                Administrator administrator = administratorDB.get(account.getAccount_id());

                if (administrator.getFirst_name().equals(aes.encrypt(firstName)) &&
                        administrator.getLast_name().equals(aes.encrypt(LastName)) && administrator.getEmail().equals(aes.encrypt(email))) {
                    HashMap<String, String> tags = new HashMap<>();
                    tags.put("firstname", aes.decrypt(administrator.getFirst_name()));
                    tags.put("lastname", aes.decrypt(administrator.getLast_name()));
                    tags.put("id", account.getUser_name());
                    tags.put("link", url);
                    tags.put("date", (new java.util.Date()).toString());

                    GmailService.sendMail(to, subject, template, tags);
                } else {
                    return null;
                }
            } else if (account.getProfile().equals("PATIENT")) {
                PatientDB patientDB = new PatientDB();
                Patient patient = patientDB.get(account.getAccount_id());

                if (patient.getFirst_name().equals(aes.encrypt(firstName)) &&
                        patient.getLast_name().equals(aes.encrypt(LastName)) && patient.getEmail().equals(aes.encrypt(email))) {
                    HashMap<String, String> tags = new HashMap<>();
                    tags.put("firstname", aes.decrypt(patient.getFirst_name()));
                    tags.put("lastname", aes.decrypt(patient.getLast_name()));
                    tags.put("id", account.getUser_name());
                    tags.put("link", url);
                    tags.put("date", (new java.util.Date()).toString());

                    GmailService.sendMail(to, subject, template, tags);
                }else {
                    return null;
                } 
            }

            //GmailService.sendMail(email, "NotesKeeper Password", "Hello, To reset your password, please click this link : ", false);
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return account;
    }
}
=======
package service;

import dataaccess.*;
import java.util.*;
import java.util.logging.*;
import models.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AccountService {
    public Account login(String user_name, String password) {        
        try {
            Account account = get(user_name);
            String salt = account.getSalt();
            String userPassword = account.getPassword();
            
            if ((salt == null && password.equals(userPassword)) ||
                    (salt != null && userPassword.equals(PasswordUtil.hashAndSaltPassword(password, salt)))) {
                return account;
            }
        } catch (Exception e) {}
        
        return null;
    }
    
    public List<Account> getAll() throws Exception {
        AccountDB accountDB = new AccountDB();
        List<Account> accounts = accountDB.getAll();
        return accounts;
    }
    
    public Account get(String user_name) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(user_name);
        return account;
    }
    
    public Account get(int account_id) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.get(account_id);
        return account;
    }
    
    public void insert(int account_id, String user_name, String password, String profile) throws Exception {
        String salt = PasswordUtil.getSalt();
        Account account =
                new Account(account_id, user_name, PasswordUtil.hashAndSaltPassword(password, salt), profile, null, salt);
        AccountDB accountDB = new AccountDB();
        accountDB.insert(account);
    }
    
    public void update(int account_id, String user_name, String password, String profile) throws Exception {
        String salt = PasswordUtil.getSalt();
        Account account =
                new Account(account_id, user_name, PasswordUtil.hashAndSaltPassword(password, salt), profile, null, salt);
        AccountDB accountDB = new AccountDB();
        accountDB.update(account);
    }
    
    public void delete(String user_name) throws Exception {
        Account account = get(user_name);
        AccountDB accountDB = new AccountDB();
        accountDB.delete(account);
    }
    
    public Account resetPassword(String email, String path, String url) {
        String uuid = UUID.randomUUID().toString();
        String to = email;
        String subject = "Surpass Health Clinic Reset Password";
        String template = path + "/email_templates/reset_password.html";
        String link = "";
        AccountDB accountDB = new AccountDB();
        Account account = null;
        AES aes = new AES();
        
        try {
            account = accountDB.getByEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if(account.getReset_password_uuid() == null){
            link = url + "?uuid=" + uuid;
        } else {
          link = url + "?uuid=" + account.getReset_password_uuid();
        }
        
        try {
            if (account.getProfile().equals("DOCTOR")) {
                DoctorDB doctorDB = new DoctorDB();
                Doctor doctor = doctorDB.get(account.getAccount_id());
                
                if(account.getReset_password_uuid() == null){
                    account.setReset_password_uuid(uuid);
                }
                
                accountDB.update(account);

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", aes.decrypt(doctor.getFirst_name()));
                tags.put("lastname", aes.decrypt(doctor.getLast_name()));
                tags.put("link", link);
                tags.put("date", (new java.util.Date()).toString());

                JavaMailService.sendMail(to, subject, template, tags);
            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorDB administratorDB = new AdministratorDB();
                Administrator administrator = administratorDB.get(account.getAccount_id());

                if(account.getReset_password_uuid() == null){
                    account.setReset_password_uuid(uuid);
                }
                
                accountDB.update(account);

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", aes.decrypt(administrator.getFirst_name()));
                tags.put("lastname", aes.decrypt(administrator.getLast_name()));
                tags.put("link", link);
                tags.put("date", (new java.util.Date()).toString());

                JavaMailService.sendMail(to, subject, template, tags);
            } else if (account.getProfile().equals("PATIENT")) {
                PatientDB patientDB = new PatientDB();
                Patient patient = patientDB.get(account.getAccount_id());

                if(account.getReset_password_uuid() == null){
                    account.setReset_password_uuid(uuid);
                }
                
                accountDB.update(account);

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", aes.decrypt(patient.getFirst_name()));
                tags.put("lastname", aes.decrypt(patient.getLast_name()));
                tags.put("link", link);
                tags.put("date", (new java.util.Date()).toString());

                JavaMailService.sendMail(to, subject, template, tags);
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return account;
    }
    
    public boolean changePassword(String uuid, String password) {
        AccountDB accountDB = new AccountDB();
        
        try {
            Account account = accountDB.getByUUID(uuid);
            String salt = account.getSalt();
            String encryptedPassword = PasswordUtil.hashAndSaltPassword(password, salt);
            account.setPassword(encryptedPassword);
            account.setReset_password_uuid(null);
            accountDB.update(account);
            
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public Account findAccount(String firstName, String lastName, String email) throws Exception {
        AccountDB accountDB = new AccountDB();
        DoctorDB doctorDB = new DoctorDB();
        PatientDB patientDB = new PatientDB();
        AdministratorDB administratorDB = new AdministratorDB();
        Account account = accountDB.getByEmail(email);
        AES aes = new AES();
        
        if (account.getProfile().equals("DOCTOR")) {
            Doctor doctor = doctorDB.get(aes.encrypt(email));
            
            if (doctor.getFirst_name().equals(aes.encrypt(firstName)) &&
                    doctor.getLast_name().equals(aes.encrypt(lastName))) {
                return account;
            }
        } else if (account.getProfile().equals("PATIENT")) {
            Patient patient = patientDB.get(aes.encrypt(email));
            
            if (patient.getFirst_name().equals(aes.encrypt(firstName)) &&
                    patient.getLast_name().equals(aes.encrypt(lastName))) {
                return account;
            }
        } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
            Administrator administrator = administratorDB.get(aes.encrypt(email));
            
            if (administrator.getFirst_name().equals(aes.encrypt(firstName)) &&
                    administrator.getLast_name().equals(aes.encrypt(lastName))) {
                return account;
            }
        }
        
        return null;
    }
    
    public Account sendAccount(String email, String firstName, String LastName, String path, String url) throws Exception {
        String to = email;
        String subject = "Your ID From Surpass Health Clinic";
        String template = path + "/email_templates/find_account.html";

        AccountDB accountDB = new AccountDB();        
        Account account = null;
        AES aes = new AES();
        
        try {
            account = accountDB.getByEmail(email);

            if (account.getProfile().equals("DOCTOR")) {
                DoctorDB doctorDB = new DoctorDB();
                Doctor doctor = doctorDB.get(account.getAccount_id());

                if (doctor.getFirst_name().equals(aes.encrypt(firstName)) &&
                        doctor.getLast_name().equals(aes.encrypt(LastName)) &&
                        doctor.getEmail().equals(aes.encrypt(email))) {      
                    HashMap<String, String> tags = new HashMap<>();
                    tags.put("firstname", aes.decrypt(doctor.getFirst_name()));
                    tags.put("lastname", aes.decrypt(doctor.getLast_name()));
                    tags.put("id", account.getUser_name());
                    tags.put("link", url);
                    tags.put("date", (new java.util.Date()).toString());

                    JavaMailService.sendMail(to, subject, template, tags);
                } else {
                    return null;
                }
            } else if (account.getProfile().equals("ADMIN") || account.getProfile().equals("SYSADMIN")) {
                AdministratorDB administratorDB = new AdministratorDB();
                Administrator administrator = administratorDB.get(account.getAccount_id());

                if (administrator.getFirst_name().equals(aes.encrypt(firstName)) &&
                        administrator.getLast_name().equals(aes.encrypt(LastName)) &&
                        administrator.getEmail().equals(aes.encrypt(email))) {
                    HashMap<String, String> tags = new HashMap<>();
                    tags.put("firstname", aes.decrypt(administrator.getFirst_name()));
                    tags.put("lastname", aes.decrypt(administrator.getLast_name()));
                    tags.put("id", account.getUser_name());
                    tags.put("link", url);
                    tags.put("date", (new java.util.Date()).toString());

                    JavaMailService.sendMail(to, subject, template, tags);
                } else {
                    return null;
                }
            } else if (account.getProfile().equals("PATIENT")) {
                PatientDB patientDB = new PatientDB();
                Patient patient = patientDB.get(account.getAccount_id());

                if (patient.getFirst_name().equals(aes.encrypt(firstName)) &&
                        patient.getLast_name().equals(aes.encrypt(LastName)) &&
                        patient.getEmail().equals(aes.encrypt(email))) {
                    HashMap<String, String> tags = new HashMap<>();
                    tags.put("firstname", aes.decrypt(patient.getFirst_name()));
                    tags.put("lastname", aes.decrypt(patient.getLast_name()));
                    tags.put("id", account.getUser_name());
                    tags.put("link", url);
                    tags.put("date", (new java.util.Date()).toString());

                    JavaMailService.sendMail(to, subject, template, tags);
                }else {
                    return null;
                } 
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return account;
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
