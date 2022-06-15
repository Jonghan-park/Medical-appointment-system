package dataaccess;

import java.sql.*;
import java.util.*;
import models.Appointment;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AppointmentDB {
    public List<Appointment> getAll() throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM appointment";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
                        
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String start_date_time = rs.getString(2);
                int patient_id = rs.getInt(3);
                int duration = rs.getInt(4); 
                int type = rs.getInt(5); 
                String reason = rs.getString(6);
                boolean patient_attended = rs.getBoolean(7);
                
                Appointment appointment =
                        new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
                appointments.add(appointment);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointments;
    }
    
    public List<Appointment> getAllByDate(String date) throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM appointment WHERE start_date_time LIKE ? ORDER BY 2";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, date + "%");
            rs = ps.executeQuery();
                        
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String start_date_time = rs.getString(2);
                int patient_id = rs.getInt(3);
                int duration = rs.getInt(4); 
                int type = rs.getInt(5); 
                String reason = rs.getString(6);
                boolean patient_attended = rs.getBoolean(7);
                
                Appointment appointment =
                        new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
                appointments.add(appointment);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointments;
    }
    
    public List<Appointment> getAllFutures(String date) throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;        
        String sql = "SELECT * FROM appointment WHERE start_date_time > ? ORDER BY 2";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, date);
            rs = ps.executeQuery();
                        
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String start_date_time = rs.getString(2);
                int patient_id = rs.getInt(3);
                int duration = rs.getInt(4); 
                int type = rs.getInt(5); 
                String reason = rs.getString(6);
                boolean patient_attended = rs.getBoolean(7);
                
                Appointment appointment =
                        new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
                appointments.add(appointment);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointments;
    }
    
    public List<Appointment> getByDoctorID(int doctor_id) throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM appointment WHERE doctor_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String start_date_time = rs.getString(2);
                int patient_id = rs.getInt(3);
                int duration = rs.getInt(4); 
                int type = rs.getInt(5); 
                String reason = rs.getString(6);
                boolean patient_attended = rs.getBoolean(7);   
                
                appointment =
                        new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
                appointments.add(appointment);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointments;
    }
    
    public List<Appointment> getByPatientID(int patient_id) throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM appointment WHERE patient_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String start_date_time = rs.getString(2);
                int duration = rs.getInt(4); 
                int type = rs.getInt(5); 
                String reason = rs.getString(6);
                boolean patient_attended = rs.getBoolean(7);   
                
                appointment =
                        new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
                appointments.add(appointment);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointments;
    }
    
    public Appointment getByDate(String start_date_time) throws Exception {
        Appointment appointment = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM appointment WHERE start_date_time LIKE ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, start_date_time + "%");
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int doctor_id = rs.getInt(1);
                int patient_id = rs.getInt(3);
                int duration = rs.getInt(4); 
                int type = rs.getInt(5); 
                String reason = rs.getString(6);
                boolean patient_attended = rs.getBoolean(7);
                
                appointment =
                        new Appointment(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return appointment;
    }
    
    public void insert(Appointment appointment) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO appointment "
                + "(doctor_id, start_date_time, patient_id, duration, type, reason, patient_attended)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, appointment.getDoctor_id());
            ps.setString(2, appointment.getStart_date_time());
            ps.setInt(3, appointment.getPatient_id());
            ps.setInt(4, appointment.getDuration());  
            ps.setInt(5, appointment.getType());
            ps.setString(6, appointment.getReason());
            ps.setBoolean(7, appointment.getPatient_attended());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void update(Appointment appointment) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE appointment SET patient_id = ?, duration = ?, type = ?, reason = ?, patient_attended = ?"
                + " WHERE doctor_id = ? AND start_date_time = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, appointment.getPatient_id());
            ps.setInt(2, appointment.getDuration());  
            ps.setInt(3, appointment.getType());
            ps.setString(4, appointment.getReason());
            ps.setBoolean(5, appointment.getPatient_attended());
            ps.setInt(6, appointment.getDoctor_id());
            ps.setString(7, appointment.getStart_date_time());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void assignDoctor(int doctor_id, int patient_id, String start_date_time) throws Exception{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE appointment SET doctor_id = ?"
                + " WHERE patient_id = ? AND start_date_time = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            ps.setInt(2, patient_id);  
            ps.setString(3, start_date_time);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(String time, int doctor_id, int patient_id) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM appointment WHERE start_date_time = ? and doctor_id = ? and patient_id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, time);
            ps.setInt(2,doctor_id);
            ps.setInt(3,patient_id);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
