<<<<<<< HEAD
package dataaccess;

import java.sql.*;
import java.util.*;
import models.AppointmentType;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentTypeDB {
    public List<AppointmentType> getAll() throws Exception {
        List<AppointmentType> appointmentTypes = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM appointment_type";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int type = rs.getInt(1);
                String description = rs.getString(2);
                int std_duration = rs.getInt(3);               
                
                AppointmentType appointmentType = new AppointmentType(type , description, std_duration);
                appointmentTypes.add(appointmentType);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointmentTypes;
    }
    
    public AppointmentType get(int type) throws Exception {
        AppointmentType appointmentType = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM appointment_type WHERE type = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String description = rs.getString(2);
                int std_duration = rs.getInt(3);
                
                appointmentType = new AppointmentType(type, description, std_duration);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointmentType;
    }
    
    public void insert(AppointmentType appointmentType) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO appointment_type "
                + "(type, description, std_duration) VALUES (?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, appointmentType.getType());
            ps.setString(2, appointmentType.getDescription());
            ps.setInt(3, appointmentType.getStd_duration());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(AppointmentType appointmentType) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE appointment_type SET type = ?, description = ?, " +
                "std_duration = ? WHERE type = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, appointmentType.getType());
            ps.setString(2, appointmentType.getDescription());
            ps.setInt(3, appointmentType.getStd_duration());
            ps.setInt(4, appointmentType.getType());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(AppointmentType appointmentType) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM appointment_type WHERE type = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, appointmentType.getType());
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
import models.AppointmentType;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentTypeDB {
    public List<AppointmentType> getAll() throws Exception {
        List<AppointmentType> appointmentTypes = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM appointment_type";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int type = rs.getInt(1);
                String description = rs.getString(2);
                int std_duration = rs.getInt(3);               
                
                AppointmentType appointmentType = new AppointmentType(type , description, std_duration);
                appointmentTypes.add(appointmentType);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointmentTypes;
    }
    
    public AppointmentType get(int type) throws Exception {
        AppointmentType appointmentType = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM appointment_type WHERE type = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String description = rs.getString(2);
                int std_duration = rs.getInt(3);
                
                appointmentType = new AppointmentType(type, description, std_duration);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointmentType;
    }
    
    public void insert(AppointmentType appointmentType) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO appointment_type "
                + "(type, description, std_duration) VALUES (?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, appointmentType.getType());
            ps.setString(2, appointmentType.getDescription());
            ps.setInt(3, appointmentType.getStd_duration());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(AppointmentType appointmentType) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE appointment_type SET description = ?, " +
                "std_duration = ? WHERE type = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, appointmentType.getDescription());
            ps.setInt(2, appointmentType.getStd_duration());
            ps.setInt(3, appointmentType.getType());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(int type) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM appointment_type WHERE type = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, type);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
