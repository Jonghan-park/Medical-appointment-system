<<<<<<< HEAD
package dataaccess;

import java.sql.*;
import java.util.*;
import models.Doctor;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class DoctorDB {
    public List<Doctor> getAll() throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM doctor";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);            
                
                Doctor doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
                doctors.add(doctor);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctors;
    }
    
    public List<Doctor> getAllByName(String name) throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM doctor WHERE first_name LIKE ? OR last_name LIKE ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);            
                
                Doctor doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
                doctors.add(doctor);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctors;
    }
    
    public Doctor get(int account_id) throws Exception {
        Doctor doctor = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM doctor WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);   
                
                doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctor;
    }
    
    public Doctor getByDoctorID(int doctor_id) throws Exception {
        Doctor doctor = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM doctor WHERE doctor_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);   
                
                doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctor;
    }
    
    public Doctor get(String email) throws Exception {
        Doctor doctor = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM doctor WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);   
                
                doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctor;
    }
    
    public void insert(Doctor doctor) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO doctor "
                + "(doctor_id, first_name, last_name, email, mobile_phone, alt_phone, pref_contact_type, "
                + "account_id, gender, birth_date, street_address, city, province, postal_code)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor.getDoctor_id());
            ps.setString(2, doctor.getFirst_name());
            ps.setString(3, doctor.getLast_name());
            ps.setString(4, doctor.getEmail());  
            ps.setString(5, doctor.getMobile_phone());
            ps.setString(6, doctor.getAlt_phone());
            ps.setString(7, doctor.getPref_contact_type());
            ps.setInt(8, doctor.getAccount_id());
            ps.setString(9, doctor.getGender());
            ps.setString(10, doctor.getBirth_date());
            ps.setString(11, doctor.getStreet_address());
            ps.setString(12, doctor.getCity());
            ps.setString(13, doctor.getProvince());
            ps.setString(14, doctor.getPostal_code());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Doctor doctor) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE doctor SET doctor_id = ?, first_name = ?, last_name = ?, email = ?, " +
                "mobile_phone = ?, alt_phone = ?, pref_contact_type = ?, account_id = ?, gender = ?, " +
                "birth_date = ?, street_address = ?, city = ?, province = ?, postal_code = ? WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor.getDoctor_id());
            ps.setString(2, doctor.getFirst_name());
            ps.setString(3, doctor.getLast_name());
            ps.setString(4, doctor.getEmail());  
            ps.setString(5, doctor.getMobile_phone());
            ps.setString(6, doctor.getAlt_phone());
            ps.setString(7, doctor.getPref_contact_type());
            ps.setInt(8, doctor.getAccount_id());
            ps.setString(9, doctor.getGender());
            ps.setString(10, doctor.getBirth_date());
            ps.setString(11, doctor.getStreet_address());
            ps.setString(12, doctor.getCity());
            ps.setString(13, doctor.getProvince());
            ps.setString(14, doctor.getPostal_code());
            ps.setInt(15, doctor.getAccount_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(Doctor doctor) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM doctor WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor.getAccount_id());
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
import models.Doctor;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class DoctorDB {
    public List<Doctor> getAll() throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM doctor";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);            
                
                Doctor doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
                doctors.add(doctor);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctors;
    }
    
    public List<Doctor> getAllByName(String name) throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM doctor WHERE first_name LIKE ? OR last_name LIKE ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);            
                
                Doctor doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
                doctors.add(doctor);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctors;
    }
    
    public Doctor get(int account_id) throws Exception {
        Doctor doctor = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM doctor WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);   
                
                doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctor;
    }
    
    public Doctor getByDoctorID(int doctor_id) throws Exception {
        Doctor doctor = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM doctor WHERE doctor_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4); 
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);   
                
                doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctor;
    }
    
    public Doctor get(String email) throws Exception {
        Doctor doctor = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM doctor WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int doctor_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String mobile_phone = rs.getString(5);
                String alt_phone = rs.getString(6);    
                String pref_contact_type = rs.getString(7); 
                int account_id = rs.getInt(8);
                String gender = rs.getString(9);  
                String birth_date = rs.getString(10);
                String street_address = rs.getString(11);
                String city = rs.getString(12); 
                String province = rs.getString(13);
                String postal_code = rs.getString(14);   
                
                doctor = new Doctor(doctor_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return doctor;
    }
    
    public void insert(Doctor doctor) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO doctor "
                + "(doctor_id, first_name, last_name, email, mobile_phone, alt_phone, pref_contact_type, "
                + "account_id, gender, birth_date, street_address, city, province, postal_code)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor.getDoctor_id());
            ps.setString(2, doctor.getFirst_name());
            ps.setString(3, doctor.getLast_name());
            ps.setString(4, doctor.getEmail());  
            ps.setString(5, doctor.getMobile_phone());
            ps.setString(6, doctor.getAlt_phone());
            ps.setString(7, doctor.getPref_contact_type());
            ps.setInt(8, doctor.getAccount_id());
            ps.setString(9, doctor.getGender());
            ps.setString(10, doctor.getBirth_date());
            ps.setString(11, doctor.getStreet_address());
            ps.setString(12, doctor.getCity());
            ps.setString(13, doctor.getProvince());
            ps.setString(14, doctor.getPostal_code());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Doctor doctor) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE doctor SET first_name = ?, last_name = ?, email = ?, " +
                "mobile_phone = ?, alt_phone = ?, pref_contact_type = ?, account_id = ?, gender = ?, " +
                "birth_date = ?, street_address = ?, city = ?, province = ?, postal_code = ? WHERE doctor_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, doctor.getFirst_name());
            ps.setString(2, doctor.getLast_name());
            ps.setString(3, doctor.getEmail());  
            ps.setString(4, doctor.getMobile_phone());
            ps.setString(5, doctor.getAlt_phone());
            ps.setString(6, doctor.getPref_contact_type());
            ps.setInt(7, doctor.getAccount_id());
            ps.setString(8, doctor.getGender());
            ps.setString(9, doctor.getBirth_date());
            ps.setString(10, doctor.getStreet_address());
            ps.setString(11, doctor.getCity());
            ps.setString(12, doctor.getProvince());
            ps.setString(13, doctor.getPostal_code());
            ps.setInt(14, doctor.getDoctor_id());
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
        String sql = "DELETE FROM doctor WHERE account_id = ?";
        
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
