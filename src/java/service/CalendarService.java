package service;

import dataaccess.CalendarDB;
import java.util.List;
import models.Calendar;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class CalendarService {
    public List<Calendar> getAll() throws Exception {
        CalendarDB calendarDB = new CalendarDB();
        List<Calendar> calendars = calendarDB.getAll();
        return calendars;
    }
    
    public List<Calendar> getAllAvailable(String start_date_time, String end_date_time) throws Exception {
        CalendarDB calendarDB = new CalendarDB();
        List<Calendar> calendars = calendarDB.getAllAvailable(start_date_time, end_date_time);
        return calendars;
    }
    
    public Calendar get(String date_time) throws Exception {
        CalendarDB calendarDB = new CalendarDB();
        Calendar calendar = calendarDB.get(date_time);
        return calendar;
    }
    
    public void insert(String date_time, String clinic_open) throws Exception {
        Calendar calendar = new Calendar(date_time , clinic_open);
        CalendarDB calendarDB = new CalendarDB();
        calendarDB.insert(calendar);
    }
    
    public void update(String date_time, String clinic_open) throws Exception {
        Calendar calendar = new Calendar(date_time , clinic_open);
        CalendarDB calendarDB = new CalendarDB();
        calendarDB.update(calendar);
    }
    
    public void delete(String date_time) throws Exception {
        Calendar calendar = get(date_time);
        CalendarDB calendarDB = new CalendarDB();
        calendarDB.delete(calendar);
    }
}
