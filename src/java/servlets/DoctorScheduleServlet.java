package servlets;

import java.io.IOException;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Calendar;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class DoctorScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("step", "1");

        String user_name = (String) session.getAttribute("user_name");
        ArrayList<String> datelist = new ArrayList<>();
        AccountService accountService = new AccountService();
        DoctorService doctorService = new DoctorService();
        
        if (user_name == null || user_name.equals("")) {
            response.sendRedirect("welcome");
            return;
        }
        
        for (int i = 1; i < 20; i++) {
            if (!(LocalDate.now().plusDays(i).getDayOfWeek().toString().equals("SATURDAY")
                    || LocalDate.now().plusDays(i).getDayOfWeek().toString().equals("SUNDAY"))) {
                datelist.add(LocalDate.now().plusDays(i).toString());
            }
        }

        request.setAttribute("dateList", datelist);

        try {
            Account account = accountService.get(user_name);
            
            if (account.getProfile().equals("DOCTOR")) {
                Doctor doctor = doctorService.get(account.getAccount_id());
                request.setAttribute("user", doctor);
            } else {
                response.sendRedirect("welcome");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(DoctorScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/doctorSchedule.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String action = request.getParameter("action");
        String schedule_date = request.getParameter("schedule_date");
        request.setAttribute("schedule_date", schedule_date);
        
        AccountService accountService = new AccountService();
        DoctorService doctorService = new DoctorService();
        AvailabilityService availabilityService = new AvailabilityService();
        Doctor doctor = null;
        Account account = null;
        String seleted_schedule_date[] = request.getParameterValues("seleted_schedule_date");
        String seleted_start_time[] = request.getParameterValues("start_time");
        String seleted_end_time[] = request.getParameterValues("end_time");
        Date beginDate;
        Date endDate;
        long du_time;
        DateFormat df = new SimpleDateFormat("HH:mm");

        List<Availability> availabilities = new ArrayList<>();

        try {
            account = accountService.get(user_name);
            doctor = doctorService.get(account.getAccount_id());
            availabilities = availabilityService.getAllByDoctorId(doctor.getDoctor_id());
            request.setAttribute("availabilities", availabilities);
            request.setAttribute("user", doctor);
        } catch (Exception ex) {
            Logger.getLogger(DoctorScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (action) {
            case "select_date":
                if (schedule_date.equals("0")) {
                 request.setAttribute("step", "1");
                } else {
                    request.setAttribute("step", "2");
                }
                
                break;

            case "doctor_schedule":
                List<String> cleand_start_time = new ArrayList<>();
                List<String> cleand_end_time = new ArrayList<>();

                try {
                    for (int i = 0; i < seleted_start_time.length; i++) {
                        if (!seleted_start_time[i].equals("0") && !seleted_end_time[i].equals("0")) {
                            cleand_start_time.add(seleted_start_time[i]);
                            cleand_end_time.add(seleted_end_time[i]);
                        }
                    }

                    int b = 0;
                    int c = 0;
                    int d = 0;

                    for (int i = 0; i < cleand_start_time.size(); i++) {
                        b++;
                    }

                    for (int i = 0; i < cleand_end_time.size(); i++) {
                        c++;
                    }

                    for (int i = 0; i < seleted_schedule_date.length; i++) {
                        d++;
                    }

                    if (b == c && c == d && d == b) {
                        for (int i = 0; i < seleted_schedule_date.length; i++) {
                            try {
                                beginDate = df.parse(cleand_start_time.get(i));
                                endDate = df.parse(cleand_end_time.get(i));
                                du_time = (endDate.getTime() - beginDate.getTime()) / (60 * 1000);
                                Long longtime = du_time;
                                int du_time_time = longtime.intValue();

                                for (Availability availability : availabilities) {
                                    if (availability.getStart_date_time().substring(0, 10).equals(seleted_schedule_date[i])) {
                                        availabilityService.updateSchedule(doctor.getDoctor_id(),
                                                availability.getStart_date_time(), availability.getDuration(),
                                                seleted_schedule_date[i] + " " + cleand_start_time.get(i) + ":00.0", du_time_time);
                                    }
                                }

                                availabilityService.insert(doctor.getDoctor_id(),
                                        seleted_schedule_date[i] + " " + cleand_start_time.get(i) + ":00.0", du_time_time);
                            } catch (Exception ex) {
                            }
                        }
                        
                        boolean deleteDateList = true;
                        int beforedate;
                        int afterdate;

                        String beforedate_cal = (String) session.getAttribute("schedule_start_date");
                        String afterdate_cal = (String) session.getAttribute("schedule_end_date");
                        beforedate = Integer.parseInt(beforedate_cal.substring(0, 4) + beforedate_cal.substring(5, 7) + beforedate_cal.substring(8, 10));
                        afterdate = Integer.parseInt(afterdate_cal.substring(0, 4) + afterdate_cal.substring(5, 7) + afterdate_cal.substring(8, 10));

                        int availDateInt;

                        for (int i = 0; i < availabilities.size(); i++) {
                            availDateInt = Integer.parseInt(availabilities.get(i).getStart_date_time().substring(0, 4)
                                    + availabilities.get(i).getStart_date_time().substring(5, 7)
                                    + availabilities.get(i).getStart_date_time().substring(8, 10));

                            if (!(beforedate > availDateInt || afterdate < availDateInt)) {
                                try {
                                    for (int j = 0; j < seleted_schedule_date.length; j++) {
                                        if (seleted_schedule_date[j].equals(availabilities.get(i).getStart_date_time().substring(0, 10))) {
                                            deleteDateList = false;
                                        }
                                    }
                                } catch (Exception ex) {
                                }

                                if (deleteDateList) {
                                    try {
                                        availabilityService.deleteBySchedule(doctor.getDoctor_id(), availabilities.get(i).getStart_date_time());
                                    } catch (Exception ex) {
                                    }
                                }

                                deleteDateList = true;
                            }
                        }
                        
                        schedule_date = LocalDate.now().plusDays(1).toString();
                        request.setAttribute("message", "Schedule has been updated successfully.");
                        request.setAttribute("step", "1");
                    } else {
                        request.setAttribute("message", "*Update false");
                        request.setAttribute("step", "2");
                    }
                } catch (Exception ex) {
                    request.setAttribute("message", "*Update false");
                    request.setAttribute("step", "2");
                }

                break;
        }

        // after select date  
        ArrayList<String> datelist = new ArrayList<>();
        ArrayList<String> timetable = new ArrayList<>();

        int k = 0;

        for (int i = 1; i < 20; i++) {
            if ((LocalDate.now().plusDays(i).toString().equals(schedule_date))) {
                k = i - 1;
            }
        }

        int a = 0;

        for (int i = 1; i < 20; i++) {
            if (!(LocalDate.now().plusDays(k + i).getDayOfWeek().toString().equals("SATURDAY")
                    || LocalDate.now().plusDays(k + i).getDayOfWeek().toString().equals("SUNDAY"))) {
                datelist.add(LocalDate.now().plusDays(k + i).toString());
                a++;
            }
        }

        session.removeAttribute("schedule_start_date");
        session.removeAttribute("schedule_end_date");
        session.setAttribute("schedule_start_date", datelist.get(0));
        session.setAttribute("schedule_end_date", datelist.get(a - 1));
        request.setAttribute("dateList", datelist);

        String dateConvert = null;
        int time = 7;

        for (int i = 0; i < 19; i++) {
            if (i % 2 == 0) {
                time = time + 1;

                try {
                    beginDate = df.parse(Integer.toString(time) + ":" + "00");
                    dateConvert = df.format(beginDate);
                } catch (ParseException ex) {
                    Logger.getLogger(DoctorScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                timetable.add(dateConvert);
            } else {
                try {
                    beginDate = df.parse(Integer.toString(time) + ":" + "30");
                    dateConvert = df.format(beginDate);
                } catch (ParseException ex) {
                    Logger.getLogger(DoctorScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                timetable.add(dateConvert);
            }
        }

        request.setAttribute("timetable", timetable);

        ArrayList<String> endTimelist = new ArrayList<>();
        ArrayList<Integer> duration_list = new ArrayList<>();

        int duration_time = 0;
        String end_time_start;
        Date duration_time_list;
        String conveted_endtime;
        Calendar calndr = Calendar.getInstance();

        for (Availability availability : availabilities) {
            end_time_start = availability.getStart_date_time().substring(11, 16);
            duration_time = availability.getDuration();
            duration_list.add(availability.getDuration());

            try {
                duration_time_list = df.parse(end_time_start);
                calndr.setTime(duration_time_list);
                calndr.add(Calendar.MINUTE, duration_time);
                conveted_endtime = df.format(calndr.getTime());
                endTimelist.add(conveted_endtime);
            } catch (ParseException ex) {
                Logger.getLogger(DoctorScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("conveted_endtime_from_availabilty", endTimelist);
            request.setAttribute("duration_list", duration_list);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/doctorSchedule.jsp").forward(request, response);
        return;
    }
}
