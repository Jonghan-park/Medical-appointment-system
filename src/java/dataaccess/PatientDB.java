<<<<<<< HEAD
package dataaccess;

import java.sql.*;
import java.util.*;
import models.Patient;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class PatientDB {
    public List<Patient> getAll() throws Exception {
        List<Patient> patients = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM patient";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5); 
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);    
                String pref_contact_type = rs.getString(8); 
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);  
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14); 
                String province = rs.getString(15);
                String postal_code = rs.getString(16);            
                
                Patient patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
                patients.add(patient);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return patients;
    }
    
    public List<Patient> getAllByDoctor(int doctor_id) throws Exception {
        List<Patient> patients = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM patient WHERE doctor_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5); 
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);    
                String pref_contact_type = rs.getString(8);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);  
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14); 
                String province = rs.getString(15);
                String postal_code = rs.getString(16);            
                
                Patient patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
                patients.add(patient);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return patients;
    }
    
    public List<Patient> getAllByName(String name) throws Exception {
        List<Patient> patients = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM patient WHERE first_name LIKE ? OR last_name LIKE ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5); 
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);    
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);  
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14); 
                String province = rs.getString(15);
                String postal_code = rs.getString(16);            
                
                Patient patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
                patients.add(patient);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return patients;
    }
    
    public Patient get(int account_id) throws Exception {
        Patient patient = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM patient WHERE account_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5);
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);                
                String gender = rs.getString(11);
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14);
                String province = rs.getString(15);
                String postal_code = rs.getString(16);
                patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return patient;
    }
    
    public Patient get(String email) throws Exception {
        Patient patient = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM patient WHERE email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);                
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14);
                String province = rs.getString(15);
                String postal_code = rs.getString(16);
                patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return patient;
    }
    
    public Patient getByPatientId(int patient_id) throws Exception{
        Patient patient = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {                
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5);
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14);
                String province = rs.getString(15);
                String postal_code = rs.getString(16);
                patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
            }
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
       
        return patient;
    } 
        
    public void insert(Patient patient) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO patient "
                + "(patient_id, healthcare_id, first_name, last_name, email, mobile_phone, alt_phone, pref_contact_type, "
                + "doctor_id, account_id, gender, birth_date, street_address, city, province, postal_code)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, patient.getPatient_id());
            ps.setString(2, patient.getHealthcare_id());
            ps.setString(3, patient.getFirst_name());
            ps.setString(4, patient.getLast_name());
            ps.setString(5, patient.getEmail());  
            ps.setString(6, patient.getMobile_phone());
            ps.setString(7, patient.getAlt_phone());
            ps.setString(8, patient.getPref_contact_type());
            ps.setInt(9, patient.getDoctor_id());
            ps.setInt(10, patient.getAccount_id());
            ps.setString(11, patient.getGender());
            ps.setString(12, patient.getBirth_date());
            ps.setString(13, patient.getStreet_address());
            ps.setString(14, patient.getCity());
            ps.setString(15, patient.getProvince());
            ps.setString(16, patient.getPostal_code());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Patient patient) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE patient SET patient_id = ?, healthcare_id = ?, first_name = ?, last_name = ?, email = ?, " +
                "mobile_phone = ?, alt_phone = ?, pref_contact_type = ?, doctor_id = ?, account_id = ?, gender = ?, " +
                "birth_date = ?, street_address = ?, city = ?, province = ?, postal_code = ? WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, patient.getPatient_id());
            ps.setString(2, patient.getHealthcare_id());
            ps.setString(3, patient.getFirst_name());
            ps.setString(4, patient.getLast_name());
            ps.setString(5, patient.getEmail());  
            ps.setString(6, patient.getMobile_phone());
            ps.setString(7, patient.getAlt_phone());
            ps.setString(8, patient.getPref_contact_type());
            ps.setInt(9, patient.getDoctor_id());
            ps.setInt(10, patient.getAccount_id());
            ps.setString(11, patient.getGender());
            ps.setString(12, patient.getBirth_date());
            ps.setString(13, patient.getStreet_address());
            ps.setString(14, patient.getCity());
            ps.setString(15, patient.getProvince());
            ps.setString(16, patient.getPostal_code());
            ps.setInt(17, patient.getAccount_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(Patient patient) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM patient WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, patient.getAccount_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
=======
package dataaccess;

import java.sql.*;
import java.util.*;
import models.Patient;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class PatientDB {
    public List<Patient> getAll() throws Exception {
        List<Patient> patients = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM patient";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5); 
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);    
                String pref_contact_type = rs.getString(8); 
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);  
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14); 
                String province = rs.getString(15);
                String postal_code = rs.getString(16);            
                
                Patient patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
                patients.add(patient);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return patients;
    }
    
    public List<Patient> getAllByDoctor(int doctor_id) throws Exception {
        List<Patient> patients = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM patient WHERE doctor_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5); 
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);    
                String pref_contact_type = rs.getString(8);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);  
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14); 
                String province = rs.getString(15);
                String postal_code = rs.getString(16);            
                
                Patient patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
                patients.add(patient);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return patients;
    }
    
    public List<Patient> getAllByName(String name) throws Exception {
        List<Patient> patients = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM patient WHERE first_name LIKE ? OR last_name LIKE ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5); 
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);    
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);  
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14); 
                String province = rs.getString(15);
                String postal_code = rs.getString(16);            
                
                Patient patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
                patients.add(patient);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return patients;
    }
    
    public Patient get(int account_id) throws Exception {
        Patient patient = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM patient WHERE account_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5);
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);                
                String gender = rs.getString(11);
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14);
                String province = rs.getString(15);
                String postal_code = rs.getString(16);
                patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return patient;
    }
    
    public Patient get(String email) throws Exception {
        Patient patient = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM patient WHERE email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int patient_id = rs.getInt(1);
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);                
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14);
                String province = rs.getString(15);
                String postal_code = rs.getString(16);
                patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return patient;
    }
    
    public Patient getByPatientId(int patient_id) throws Exception{
        Patient patient = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {                
                String healthcare_id = rs.getString(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String email = rs.getString(5);
                String mobile_phone = rs.getString(6);
                String alt_phone = rs.getString(7);
                String pref_contact_type = rs.getString(8);
                int doctor_id = rs.getInt(9);
                int account_id = rs.getInt(10);
                String gender = rs.getString(11);
                String birth_date = rs.getString(12);
                String street_address = rs.getString(13);
                String city = rs.getString(14);
                String province = rs.getString(15);
                String postal_code = rs.getString(16);
                patient = new Patient(patient_id, healthcare_id, first_name, last_name,
                        email, mobile_phone, alt_phone, pref_contact_type, doctor_id, account_id,
                        gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
       
        return patient;
    } 
        
    public void insert(Patient patient) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO patient "
                + "(patient_id, healthcare_id, first_name, last_name, email, mobile_phone, alt_phone, pref_contact_type, "
                + "doctor_id, account_id, gender, birth_date, street_address, city, province, postal_code)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, patient.getPatient_id());
            ps.setString(2, patient.getHealthcare_id());
            ps.setString(3, patient.getFirst_name());
            ps.setString(4, patient.getLast_name());
            ps.setString(5, patient.getEmail());  
            ps.setString(6, patient.getMobile_phone());
            ps.setString(7, patient.getAlt_phone());
            ps.setString(8, patient.getPref_contact_type());
            ps.setInt(9, patient.getDoctor_id());
            ps.setInt(10, patient.getAccount_id());
            ps.setString(11, patient.getGender());
            ps.setString(12, patient.getBirth_date());
            ps.setString(13, patient.getStreet_address());
            ps.setString(14, patient.getCity());
            ps.setString(15, patient.getProvince());
            ps.setString(16, patient.getPostal_code());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Patient patient) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE patient SET healthcare_id = ?, first_name = ?, last_name = ?, email = ?, " +
                "mobile_phone = ?, alt_phone = ?, pref_contact_type = ?, doctor_id = ?, account_id = ?, gender = ?, " +
                "birth_date = ?, street_address = ?, city = ?, province = ?, postal_code = ? WHERE patient_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, patient.getHealthcare_id());
            ps.setString(2, patient.getFirst_name());
            ps.setString(3, patient.getLast_name());
            ps.setString(4, patient.getEmail());  
            ps.setString(5, patient.getMobile_phone());
            ps.setString(6, patient.getAlt_phone());
            ps.setString(7, patient.getPref_contact_type());
            ps.setInt(8, patient.getDoctor_id());
            ps.setInt(9, patient.getAccount_id());
            ps.setString(10, patient.getGender());
            ps.setString(11, patient.getBirth_date());
            ps.setString(12, patient.getStreet_address());
            ps.setString(13, patient.getCity());
            ps.setString(14, patient.getProvince());
            ps.setString(15, patient.getPostal_code());
            ps.setInt(16, patient.getPatient_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(int account_id) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM patient WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
