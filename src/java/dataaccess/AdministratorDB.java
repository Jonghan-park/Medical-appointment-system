<<<<<<< HEAD
package dataaccess;

import java.sql.*;
import java.util.*;
import models.Administrator;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AdministratorDB {
    public List<Administrator> getAll() throws Exception {
        List<Administrator> administrators = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM administrator";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int admin_id = rs.getInt(1);
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
                
                Administrator administrator = new Administrator(admin_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
                administrators.add(administrator);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return administrators;
    }
    
    public Administrator get(int account_id) throws Exception {
        Administrator administrator = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM administrator WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int admin_id = rs.getInt(1);
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
                
                administrator = new Administrator(admin_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return administrator;
    }
    
    public Administrator get(String email) throws Exception {
        Administrator administrator = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM administrator WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int admin_id = rs.getInt(1);
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
                
                administrator = new Administrator(admin_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return administrator;
    }
    
    public void insert(Administrator administrator) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO administrator "
                + "(admin_id, first_name, last_name, email, mobile_phone, alt_phone, pref_contact_type, "
                + "account_id, gender, birth_date, street_address, city, province, postal_code)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, administrator.getAdmin_id());
            ps.setString(2, administrator.getFirst_name());
            ps.setString(3, administrator.getLast_name());
            ps.setString(4, administrator.getEmail());  
            ps.setString(5, administrator.getMobile_phone());
            ps.setString(6, administrator.getAlt_phone());
            ps.setString(7, administrator.getPref_contact_type());
            ps.setInt(8, administrator.getAccount_id());
            ps.setString(9, administrator.getGender());
            ps.setString(10, administrator.getBirth_date());
            ps.setString(11, administrator.getStreet_address());
            ps.setString(12, administrator.getCity());
            ps.setString(13, administrator.getProvince());
            ps.setString(14, administrator.getPostal_code());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Administrator administrator) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE administrator SET admin_id = ?, first_name = ?, last_name = ?, email = ?, " +
                "mobile_phone = ?, alt_phone = ?, pref_contact_type = ?, account_id = ?, gender = ?, " +
                "birth_date = ?, street_address = ?, city = ?, province = ?, postal_code = ? WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, administrator.getAdmin_id());
            ps.setString(2, administrator.getFirst_name());
            ps.setString(3, administrator.getLast_name());
            ps.setString(4, administrator.getEmail());  
            ps.setString(5, administrator.getMobile_phone());
            ps.setString(6, administrator.getAlt_phone());
            ps.setString(7, administrator.getPref_contact_type());
            ps.setInt(8, administrator.getAccount_id());
            ps.setString(9, administrator.getGender());
            ps.setString(10, administrator.getBirth_date());
            ps.setString(11, administrator.getStreet_address());
            ps.setString(12, administrator.getCity());
            ps.setString(13, administrator.getProvince());
            ps.setString(14, administrator.getPostal_code());
            ps.setInt(15, administrator.getAccount_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(Administrator administrator) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM administrator WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, administrator.getAccount_id());
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
import models.Administrator;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AdministratorDB {
    public List<Administrator> getAll() throws Exception {
        List<Administrator> administrators = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM administrator";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int admin_id = rs.getInt(1);
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
                
                Administrator administrator = new Administrator(admin_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
                administrators.add(administrator);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return administrators;
    }
    
    public Administrator get(int account_id) throws Exception {
        Administrator administrator = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM administrator WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int admin_id = rs.getInt(1);
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
                
                administrator = new Administrator(admin_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return administrator;
    }
    
    public Administrator get(String email) throws Exception {
        Administrator administrator = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM administrator WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int admin_id = rs.getInt(1);
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
                
                administrator = new Administrator(admin_id, first_name, last_name, email, mobile_phone, alt_phone,
                        pref_contact_type, account_id, gender, birth_date, street_address, city, province, postal_code);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return administrator;
    }
    
    public void insert(Administrator administrator) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO administrator "
                + "(admin_id, first_name, last_name, email, mobile_phone, alt_phone, pref_contact_type, "
                + "account_id, gender, birth_date, street_address, city, province, postal_code)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, administrator.getAdmin_id());
            ps.setString(2, administrator.getFirst_name());
            ps.setString(3, administrator.getLast_name());
            ps.setString(4, administrator.getEmail());  
            ps.setString(5, administrator.getMobile_phone());
            ps.setString(6, administrator.getAlt_phone());
            ps.setString(7, administrator.getPref_contact_type());
            ps.setInt(8, administrator.getAccount_id());
            ps.setString(9, administrator.getGender());
            ps.setString(10, administrator.getBirth_date());
            ps.setString(11, administrator.getStreet_address());
            ps.setString(12, administrator.getCity());
            ps.setString(13, administrator.getProvince());
            ps.setString(14, administrator.getPostal_code());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Administrator administrator) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE administrator SET first_name = ?, last_name = ?, email = ?, " +
                "mobile_phone = ?, alt_phone = ?, pref_contact_type = ?, account_id = ?, gender = ?, " +
                "birth_date = ?, street_address = ?, city = ?, province = ?, postal_code = ? WHERE admin_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, administrator.getFirst_name());
            ps.setString(2, administrator.getLast_name());
            ps.setString(3, administrator.getEmail());  
            ps.setString(4, administrator.getMobile_phone());
            ps.setString(5, administrator.getAlt_phone());
            ps.setString(6, administrator.getPref_contact_type());
            ps.setInt(7, administrator.getAccount_id());
            ps.setString(8, administrator.getGender());
            ps.setString(9, administrator.getBirth_date());
            ps.setString(10, administrator.getStreet_address());
            ps.setString(11, administrator.getCity());
            ps.setString(12, administrator.getProvince());
            ps.setString(13, administrator.getPostal_code());
            ps.setInt(14, administrator.getAdmin_id());
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
        String sql = "DELETE FROM administrator WHERE account_id = ?";
        
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
