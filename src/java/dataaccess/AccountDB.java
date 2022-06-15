<<<<<<< HEAD
package dataaccess;

import java.sql.*;
import java.util.*;
import models.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AccountDB {
    public List<Account> getAll() throws Exception {
        List<Account> accounts = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM account";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int account_id = rs.getInt(1);
                String user_name = rs.getString(2);
                String password = rs.getString(3);
                String profile = rs.getString(4); 
                String reset_password_uuid = rs.getString(5);
                String salt = rs.getString(6);               
                
                Account account = new Account(account_id, user_name, password, profile, reset_password_uuid, salt);
                accounts.add(account);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return accounts;
    }
    
    public Account get(String user_name) throws Exception {
        Account account = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM account WHERE user_name = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user_name);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int account_id = rs.getInt(1);
                String password = rs.getString(3);
                String profile = rs.getString(4);  
                String reset_password_uuid = rs.getString(5);  
                String salt = rs.getString(6);  
                
                account = new Account(account_id, user_name, password, profile, reset_password_uuid, salt);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return account;
    }
    
    public Account get(int account_id) throws Exception {
        Account account = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM account WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String user_name = rs.getString(2);
                String password = rs.getString(3);
                String profile = rs.getString(4);  
                String reset_password_uuid = rs.getString(5);  
                String salt = rs.getString(6);  
                
                account = new Account(account_id, user_name, password, profile, reset_password_uuid, salt);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return account;
    }
    
    public Account getByEmail(String email) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        DoctorDB doctorDB = new DoctorDB();
        PatientDB patientDB = new PatientDB();
        AdministratorDB administratorDB = new AdministratorDB();
        AES aes = new AES();
        
        if (doctorDB.get(aes.encrypt(email)) != null){
           Doctor doctor = doctorDB.get(aes.encrypt(email));
            account = accountDB.get(doctor.getAccount_id());
        } else if (patientDB.get(aes.encrypt(email)) != null){
            Patient patient = patientDB.get(aes.encrypt(email));
            account = accountDB.get(patient.getAccount_id());
        } else if (administratorDB.get(aes.encrypt(email)) != null){
            Administrator admin = administratorDB.get(aes.encrypt(email));
            account = accountDB.get(admin.getAccount_id());
        } 
        
        return account;
    }
    
    public Account getByUUID(String uuid) throws Exception {
        AccountDB accountDB = new AccountDB();
        List<Account> accounts = accountDB.getAll();
        
        for (int i=0; i < accounts.size(); i++){
            if (accounts.get(i).getReset_password_uuid() != null &&
                    accounts.get(i).getReset_password_uuid().equals(uuid)) {
                return accounts.get(i);
            }
        }
        
        return null;
    }
    
    public void insert(Account account) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO account "
                + "(account_id, user_name, password, profile, reset_password_uuid, salt)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account.getAccount_id());
            ps.setString(2, account.getUser_name());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getProfile());  
            ps.setString(5, account.getReset_password_uuid());
            ps.setString(6, account.getSalt());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Account account) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE account SET account_id = ?, user_name = ?, " +
                "password = ?, profile = ?, reset_password_uuid = ?, salt = ? WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account.getAccount_id());
            ps.setString(2, account.getUser_name());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getProfile());
            ps.setString(5, account.getReset_password_uuid());
            ps.setString(6, account.getSalt());
            ps.setInt(7, account.getAccount_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(Account account) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM account WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account.getAccount_id());
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
import models.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AccountDB {
    public List<Account> getAll() throws Exception {
        List<Account> accounts = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM account";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int account_id = rs.getInt(1);
                String user_name = rs.getString(2);
                String password = rs.getString(3);
                String profile = rs.getString(4); 
                String reset_password_uuid = rs.getString(5);
                String salt = rs.getString(6);               
                
                Account account = new Account(account_id, user_name, password, profile, reset_password_uuid, salt);
                accounts.add(account);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return accounts;
    }
    
    public Account get(String user_name) throws Exception {
        Account account = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM account WHERE user_name = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user_name);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int account_id = rs.getInt(1);
                String password = rs.getString(3);
                String profile = rs.getString(4);  
                String reset_password_uuid = rs.getString(5);  
                String salt = rs.getString(6);  
                
                account = new Account(account_id, user_name, password, profile, reset_password_uuid, salt);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return account;
    }
    
    public Account get(int account_id) throws Exception {
        Account account = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM account WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String user_name = rs.getString(2);
                String password = rs.getString(3);
                String profile = rs.getString(4);  
                String reset_password_uuid = rs.getString(5);  
                String salt = rs.getString(6);  
                
                account = new Account(account_id, user_name, password, profile, reset_password_uuid, salt);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return account;
    }
    
    public Account getByEmail(String email) throws Exception {
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        DoctorDB doctorDB = new DoctorDB();
        PatientDB patientDB = new PatientDB();
        AdministratorDB administratorDB = new AdministratorDB();
        AES aes = new AES();
        
        if (doctorDB.get(aes.encrypt(email)) != null){
            Doctor doctor = doctorDB.get(aes.encrypt(email));
            account = accountDB.get(doctor.getAccount_id());
        } else if (patientDB.get(aes.encrypt(email)) != null){
            Patient patient = patientDB.get(aes.encrypt(email));
            account = accountDB.get(patient.getAccount_id());
        } else if (administratorDB.get(aes.encrypt(email)) != null){
            Administrator admin = administratorDB.get(aes.encrypt(email));
            account = accountDB.get(admin.getAccount_id());
        } 
        
        return account;
    }
    
    public Account getByUUID(String uuid) throws Exception {
        AccountDB accountDB = new AccountDB();
        List<Account> accounts = accountDB.getAll();
        
        for (int i=0; i < accounts.size(); i++){
            if (accounts.get(i).getReset_password_uuid() != null &&
                    accounts.get(i).getReset_password_uuid().equals(uuid)) {
                return accounts.get(i);
            }
        }
        
        return null;
    }
    
    public void insert(Account account) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO account "
                + "(account_id, user_name, password, profile, reset_password_uuid, salt) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account.getAccount_id());
            ps.setString(2, account.getUser_name());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getProfile());  
            ps.setString(5, account.getReset_password_uuid());
            ps.setString(6, account.getSalt());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Account account) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE account SET user_name = ?, password = ?, " +
                "profile = ?, reset_password_uuid = ?, salt = ? WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, account.getUser_name());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getProfile());
            ps.setString(4, account.getReset_password_uuid());
            ps.setString(5, account.getSalt());
            ps.setInt(6, account.getAccount_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(Account account) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM account WHERE account_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account.getAccount_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
