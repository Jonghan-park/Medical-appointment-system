package dataaccess;

import java.sql.*;
import java.util.*;
import models.Availability;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class AvailabilityDB {
    public List<Availability> getAll() throws Exception {
        List<Availability> availabilities = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM availability";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int doctor_id = rs.getInt(1);
                String start_date_time = rs.getString(2);
                int duration = rs.getInt(3);

                Availability availability = new Availability(doctor_id, start_date_time, duration);
                availabilities.add(availability);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return availabilities;
    }

    public List<Availability> getAllByDoctorDate(int doctor_id, String start_date_time) throws Exception {
        List<Availability> availabilities = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM availability where doctor_id = ? and start_date_time > ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            ps.setString(2, start_date_time);
            rs = ps.executeQuery();

            while (rs.next()) {
                start_date_time = rs.getString(2);
                int duration = rs.getInt(3);

                Availability availability = new Availability(doctor_id, start_date_time, duration);
                availabilities.add(availability);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return availabilities;
    }

    // add for doctor schedule
    public List<Availability> getAllByDoctorId(int doctor_id) throws Exception {
        List<Availability> availabilities = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM availability where doctor_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                String start_date_time = rs.getString(2);
                int duration = rs.getInt(3);
                Availability availability = new Availability(doctor_id, start_date_time, duration);
                availabilities.add(availability);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return availabilities;
    }

    public Availability get(int doctor_id) throws Exception {
        Availability availability = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM availability WHERE doctor_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String start_date_time = rs.getString(2);
                int duration = rs.getInt(3);

                availability = new Availability(doctor_id, start_date_time, duration);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return availability;
    }

    public void insert(Availability availability) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO availability "
                + "(doctor_id , start_date_time, duration) VALUES (?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, availability.getDoctor_id());
            ps.setString(2, availability.getStart_date_time());
            ps.setInt(3, availability.getDuration());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(Availability availability) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE availability SET doctor_id = ?, start_date_time = ?, "
                + "duration = ? WHERE doctor_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, availability.getDoctor_id());
            ps.setString(2, availability.getStart_date_time());
            ps.setInt(3, availability.getDuration());
            ps.setInt(4, availability.getDoctor_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(Availability availability) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM availability WHERE doctor_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, availability.getDoctor_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void deleteBySchedule(Availability availability) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM availability WHERE doctor_id = ? and start_date_time = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, availability.getDoctor_id());
            ps.setString(2, availability.getStart_date_time());           
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void updateSchedule(Availability availability, String new_start_time, int new_duration) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE availability SET doctor_id = ?, start_date_time = ?, "
                + "duration = ? WHERE doctor_id = ? and start_date_time= ?" ;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, availability.getDoctor_id());
            ps.setString(2, new_start_time);
            ps.setInt(3, new_duration);
            ps.setInt(4, availability.getDoctor_id());
            ps.setString(5, availability.getStart_date_time());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
