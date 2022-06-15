<<<<<<< HEAD
package servlets;

import java.io.IOException;
import java.util.logging.*;
import java.util.regex.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class SignupStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();        
        String user_name = (String) session.getAttribute("user_name");

        if (user_name == null || user_name.equals("")) {
            response.sendRedirect("welcome");
            return;
        } else {
            AccountService accountService = new AccountService();

            try {
                Account account = accountService.get(user_name);
                
                if (account.getProfile().equals("SYSADMIN")) {
                    AdministratorService administratorService = new AdministratorService();
                    Administrator administrator = administratorService.get(account.getAccount_id());
                    request.setAttribute("user", administrator);
                } else {
                    response.sendRedirect("welcome");
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(SignupStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
          
        getServletContext().getRequestDispatcher("/WEB-INF/signupStaff.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();        
        String user_name = (String) session.getAttribute("user_name");
        
        String usernameEntered = request.getParameter("signup_username");
        String passEntered = request.getParameter("signup_password");
        String reenterPassEntered = request.getParameter("signup_re_enter_password");
        String firstEntered = request.getParameter("signup_firstname");
        String lastEntered = request.getParameter("signup_lastname");
        String phoneEntered = request.getParameter("signup_phonenum");
        String emailEntered = request.getParameter("signup_email");
        String phoneAltEntered = request.getParameter("signup_phonenum_alt");        
        String birthEntered = request.getParameter("signup_birth_date");
        String staffTypeEntered = request.getParameter("staff_type_radio");
        String genderEntered = request.getParameter("gender_radio");
        String prefferedEntered = request.getParameter("prefered_notification_radio");
        String streetEntered = request.getParameter("signup_address");
        String cityEntered = request.getParameter("signup_city");
        String provinceEntered = request.getParameter("signup_state_province");
        String postalEntered = request.getParameter("signup_postal");

        request.setAttribute("signup_username", usernameEntered);
        request.setAttribute("signup_firstname", firstEntered);
        request.setAttribute("signup_lastname", lastEntered);
        request.setAttribute("signup_phonenum", phoneEntered);
        request.setAttribute("signup_email", emailEntered);
        request.setAttribute("signup_phonenum_alt", phoneAltEntered);
        request.setAttribute("signup_birth_date", birthEntered); 
        request.setAttribute("staff_type_radio", staffTypeEntered);
        request.setAttribute("gender_radio", genderEntered);
        request.setAttribute("prefered_notification_radio", prefferedEntered);
        request.setAttribute("signup_address", streetEntered);   
        request.setAttribute("signup_city", cityEntered); 
        request.setAttribute("signup_state_province", provinceEntered); 
        request.setAttribute("signup_postal", postalEntered);        
                    
        boolean checkNum = true;
        boolean checkNumAlt = true;
        boolean checkPass = true;

        for (char c : phoneEntered.toCharArray()) {
            if (phoneEntered.length() != 10) {
                checkNum = false;
            } else if (!Character.isDigit(c)) {
                checkNum = false;
            }
        }

        for (char c : phoneAltEntered.toCharArray()) {
            if (phoneAltEntered.length() != 10) {
                checkNumAlt = false;
            } else if (!Character.isDigit(c)) {
                checkNumAlt = false;
            }
        }

        if (!passEntered.equals(reenterPassEntered)) {
            checkPass = false;
        }

        String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n"
                + ")*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        String regexPostal = "^(?![DFIOQUWZ])[A-Z]{1}[0-9]{1}(?![DFIOQU])[A-Z]{1}[ ]{1}[0-9]{1}(?![DFIOQU])[A-Z]{1}[0-9]{1}$";
        
        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,}$";
        boolean checkEmail = true;
        boolean checkPostal = true;
        boolean checkPassword = true;
      
        

        if (emailEntered == null || emailEntered.equals("")) {
            request.setAttribute("emailErrorMessage", "*Email is required");
        } else {
            Pattern p = Pattern.compile(regexEmail);
            Matcher m = p.matcher(emailEntered);
            checkEmail = m.matches();
        }

        if (postalEntered == null || postalEntered.equals("")) {
            request.setAttribute("postalErrorMessage", "*Postal Code is required");
        } else {
            Pattern p2 = Pattern.compile(regexPostal);
            Matcher m2 = p2.matcher(postalEntered);
            checkPostal = m2.matches();
        }

        boolean insertInfo = true;

        if (usernameEntered == null || usernameEntered.equals("")) {
            request.setAttribute("userNameErrorMessage", "*Username is required");
            insertInfo = false;
        } else if (usernameEntered.contains("&") || usernameEntered.contains("=")
                || usernameEntered.contains("_") || usernameEntered.contains("'") || usernameEntered.contains("-")
                || usernameEntered.contains("+") || usernameEntered.contains(",") || usernameEntered.contains("<")
                || usernameEntered.contains(">") || usernameEntered.contains("..")) {
            request.setAttribute("userNameErrorMessage", "*Invalid username");
            insertInfo = false;
        }

        if (phoneEntered == null || phoneEntered.equals("")) {
            request.setAttribute("phoneErrorMessage", "*Phone number is required");
            insertInfo = false;
        } else if (checkNum == false) {
            request.setAttribute("phoneErrorMessage", "*Invalid phone number ex. 4035431090");
            insertInfo = false;
        }

        if (checkNumAlt == false) {
            request.setAttribute("phoneAltErrorMessage", "*Invalid phone alt number ex. 4035431090");
            insertInfo = false;
        }

           if (passEntered == null || passEntered.equals("") || reenterPassEntered == null || reenterPassEntered.equals("")) {
            request.setAttribute("passErrorMessage", "*Password is required");
            insertInfo = false;
        } else {
            Pattern p3 = Pattern.compile(regexPassword);
            Matcher m3 = p3.matcher(passEntered);
            checkPassword = m3.matches();
        }
        
         if (passEntered == null || passEntered.equals("") || reenterPassEntered == null || reenterPassEntered.equals("")) {
            request.setAttribute("passErrorMessage", "*Password is required");
            insertInfo = false;
        } else if (checkPass == false) {
            request.setAttribute("passErrorMessage", "*Password does not match ");
            insertInfo = false;
        }
        
        if (checkPassword == false) {
            request.setAttribute("passPatternErrorMessage", "*Must contain one number, one uppercase and lowercase letter, one special character and be 6 or more characters");
            insertInfo = false;
        }
        
        if (checkEmail == false) {
            request.setAttribute("emailErrorMessage", "*Invalid Email ex. example@domain.com");
            insertInfo = false;
        }

        if (checkPostal == false) {
            request.setAttribute("postalErrorMessage", "*Invalid Postal Code ex. T2L 4T7");
            insertInfo = false;
        }

        if (firstEntered == null || firstEntered.equals("")) {
            request.setAttribute("firstErrorMessage", "*First Name is required");
            insertInfo = false;
        }

        if (lastEntered == null || lastEntered.equals("")) {
            request.setAttribute("lastErrorMessage", "*Last Name is required");
            insertInfo = false;
        }

        if (birthEntered == null || birthEntered.equals("")) {
            request.setAttribute("birthErrorMessage", "*Birth date is required");
            insertInfo = false;
        }

        if (genderEntered == null || genderEntered.equals("")) {
            request.setAttribute("genderErrorMessage", "*Gender is required");
            insertInfo = false;
        }

        if (staffTypeEntered == null || staffTypeEntered.equals("")) {
            request.setAttribute("typeErrorMessage", "*Staff type is required");
            insertInfo = false;
        }

        if (prefferedEntered == null || prefferedEntered.equals("")) {
            request.setAttribute("notiErrorMessage", "*Prefered Notification is required");
            insertInfo = false;
        }

        if (streetEntered == null || streetEntered.equals("")) {
            request.setAttribute("addressErrorMessage", "*Address is required");
            insertInfo = false;
        }

        if (cityEntered == null || cityEntered.equals("")) {
            request.setAttribute("cityErrorMessage", "*City is required");
            insertInfo = false;
        }

        if (provinceEntered == null || provinceEntered.equals("")) {
            request.setAttribute("provErrorMessage", "*Province is required");
            insertInfo = false;
        }

        DoctorService ds = new DoctorService();
        AdministratorService ads = new AdministratorService();
        AccountService as = new AccountService();

        try {
            Account account = as.get(user_name);
            Administrator administrator = ads.get(account.getAccount_id());
            request.setAttribute("user", administrator);
            
            if (insertInfo == true) {
                if (staffTypeEntered.equals("DOCTOR")) {
                    as.insert(0, usernameEntered, passEntered, "DOCTOR");
                    Account newAccount = as.getAll().get(as.getAll().size() - 1);
                    ds.insert(0, firstEntered, lastEntered, emailEntered, phoneEntered,
                            phoneAltEntered, prefferedEntered, newAccount.getAccount_id(), genderEntered,
                            birthEntered, streetEntered, cityEntered, provinceEntered, postalEntered);
                } else if (staffTypeEntered.equals("ADMIN")) {
                    as.insert(0, usernameEntered, passEntered, "ADMIN");
                    Account newAccount = as.getAll().get(as.getAll().size() - 1);
                    ads.insert(0, firstEntered, lastEntered, emailEntered, phoneEntered,
                            phoneAltEntered, prefferedEntered, newAccount.getAccount_id(), genderEntered,
                            birthEntered, streetEntered, cityEntered, provinceEntered, postalEntered);
                } 
                
                request.setAttribute("signup_username", "");
                request.setAttribute("signup_firstname", "");
                request.setAttribute("signup_lastname", "");
                request.setAttribute("signup_phonenum", "");
                request.setAttribute("signup_email", "");
                request.setAttribute("signup_phonenum_alt", "");
                request.setAttribute("signup_birth_date", ""); 
                request.setAttribute("staff_type_radio", "");
                request.setAttribute("gender_radio", "");
                request.setAttribute("prefered_notification_radio", "");
                request.setAttribute("signup_address", "");   
                request.setAttribute("signup_city", ""); 
                request.setAttribute("signup_state_province", ""); 
                request.setAttribute("signup_postal", "");
                request.setAttribute("message", "New staff has beed updated successfully.");   
            }
        } catch (Exception ex) {
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/signupStaff.jsp").forward(request, response);
        return;
    }
}
=======
package servlets;

import java.io.IOException;
import java.util.logging.*;
import java.util.regex.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import service.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class SignupStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();        
        String user_name = (String) session.getAttribute("user_name");

        if (user_name == null || user_name.equals("")) {
            response.sendRedirect("welcome");
            return;
        } else {
            AccountService accountService = new AccountService();

            try {
                Account account = accountService.get(user_name);
                
                if (account.getProfile().equals("SYSADMIN")) {
                    AdministratorService administratorService = new AdministratorService();
                    Administrator administrator = administratorService.get(account.getAccount_id());
                    request.setAttribute("user", administrator);
                } else {
                    response.sendRedirect("welcome");
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(SignupStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
          
        getServletContext().getRequestDispatcher("/WEB-INF/signupStaff.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();        
        String user_name = (String) session.getAttribute("user_name");
        
        String usernameEntered = request.getParameter("signup_username");
        String passEntered = request.getParameter("signup_password");
        String reenterPassEntered = request.getParameter("signup_re_enter_password");
        String firstEntered = request.getParameter("signup_firstname");
        String lastEntered = request.getParameter("signup_lastname");
        String phoneEntered = request.getParameter("signup_phonenum");
        String emailEntered = request.getParameter("signup_email");
        String phoneAltEntered = request.getParameter("signup_phonenum_alt");        
        String birthEntered = request.getParameter("signup_birth_date");
        String staffTypeEntered = request.getParameter("staff_type_radio");
        String genderEntered = request.getParameter("gender_radio");
        String prefferedEntered = request.getParameter("prefered_notification_radio");
        String streetEntered = request.getParameter("signup_address");
        String cityEntered = request.getParameter("signup_city");
        String provinceEntered = request.getParameter("signup_state_province");
        String postalEntered = request.getParameter("signup_postal");

        request.setAttribute("signup_username", usernameEntered);
        request.setAttribute("signup_firstname", firstEntered);
        request.setAttribute("signup_lastname", lastEntered);
        request.setAttribute("signup_phonenum", phoneEntered);
        request.setAttribute("signup_email", emailEntered);
        request.setAttribute("signup_phonenum_alt", phoneAltEntered);
        request.setAttribute("signup_birth_date", birthEntered); 
        request.setAttribute("staff_type_radio", staffTypeEntered);
        request.setAttribute("gender_radio", genderEntered);
        request.setAttribute("prefered_notification_radio", prefferedEntered);
        request.setAttribute("signup_address", streetEntered);   
        request.setAttribute("signup_city", cityEntered); 
        request.setAttribute("signup_state_province", provinceEntered); 
        request.setAttribute("signup_postal", postalEntered);        
                    
        boolean checkNum = true;
        boolean checkNumAlt = true;
        boolean checkPass = true;

        for (char c : phoneEntered.toCharArray()) {
            if (phoneEntered.length() != 10) {
                checkNum = false;
            } else if (!Character.isDigit(c)) {
                checkNum = false;
            }
        }

        for (char c : phoneAltEntered.toCharArray()) {
            if (phoneAltEntered.length() != 10) {
                checkNumAlt = false;
            } else if (!Character.isDigit(c)) {
                checkNumAlt = false;
            }
        }

        if (!passEntered.equals(reenterPassEntered)) {
            checkPass = false;
        }

        String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n"
                + ")*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regexPostal = "^(?![DFIOQUWZ])[A-Z]{1}[0-9]{1}(?![DFIOQU])[A-Z]{1}[ ]{1}[0-9]{1}(?![DFIOQU])[A-Z]{1}[0-9]{1}$";        
        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,}$";

        boolean checkEmail = true;
        boolean checkPostal = true;
        boolean checkPassword = true;

        if (emailEntered == null || emailEntered.equals("")) {
            request.setAttribute("emailErrorMessage", "*Email is required");
        } else {
            Pattern p = Pattern.compile(regexEmail);
            Matcher m = p.matcher(emailEntered);
            checkEmail = m.matches();
        }

        if (postalEntered == null || postalEntered.equals("")) {
            request.setAttribute("postalErrorMessage", "*Postal Code is required");
        } else {
            Pattern p2 = Pattern.compile(regexPostal);
            Matcher m2 = p2.matcher(postalEntered);
            checkPostal = m2.matches();
        }

        boolean insertInfo = true;

        if (usernameEntered == null || usernameEntered.equals("")) {
            request.setAttribute("userNameErrorMessage", "*Username is required");
            insertInfo = false;
        } else if (usernameEntered.contains("&") || usernameEntered.contains("=")
                || usernameEntered.contains("_") || usernameEntered.contains("'") || usernameEntered.contains("-")
                || usernameEntered.contains("+") || usernameEntered.contains(",") || usernameEntered.contains("<")
                || usernameEntered.contains(">") || usernameEntered.contains("..")) {
            request.setAttribute("userNameErrorMessage", "*Invalid username");
            insertInfo = false;
        }

        if (phoneEntered == null || phoneEntered.equals("")) {
            request.setAttribute("phoneErrorMessage", "*Phone number is required");
            insertInfo = false;
        } else if (checkNum == false) {
            request.setAttribute("phoneErrorMessage", "*Invalid phone number ex. 4035431090");
            insertInfo = false;
        }

        if (checkNumAlt == false) {
            request.setAttribute("phoneAltErrorMessage", "*Invalid phone alt number ex. 4035431090");
            insertInfo = false;
        }

        if (passEntered == null || passEntered.equals("") || reenterPassEntered == null || reenterPassEntered.equals("")) {
            request.setAttribute("passErrorMessage", "*Password is required");
            insertInfo = false;
        } else {
            Pattern p3 = Pattern.compile(regexPassword);
            Matcher m3 = p3.matcher(passEntered);
            checkPassword = m3.matches();
        }
        
        if (passEntered == null || passEntered.equals("") || reenterPassEntered == null || reenterPassEntered.equals("")) {
            request.setAttribute("passErrorMessage", "*Password is required");
            insertInfo = false;
        } else if (checkPass == false) {
            request.setAttribute("passErrorMessage", "*Password does not match ");
            insertInfo = false;
        }
        
        if (checkPassword == false) {
            request.setAttribute("passPatternErrorMessage", "*Must contain one number, one uppercase and lowercase letter, one special character and be 6 or more characters");
            insertInfo = false;
        }
        
        if (checkEmail == false) {
            request.setAttribute("emailErrorMessage", "*Invalid Email ex. example@domain.com");
            insertInfo = false;
        }

        if (checkPostal == false) {
            request.setAttribute("postalErrorMessage", "*Invalid Postal Code ex. T2L 4T7");
            insertInfo = false;
        }

        if (firstEntered == null || firstEntered.equals("")) {
            request.setAttribute("firstErrorMessage", "*First Name is required");
            insertInfo = false;
        }

        if (lastEntered == null || lastEntered.equals("")) {
            request.setAttribute("lastErrorMessage", "*Last Name is required");
            insertInfo = false;
        }

        if (birthEntered == null || birthEntered.equals("")) {
            request.setAttribute("birthErrorMessage", "*Birth date is required");
            insertInfo = false;
        }

        if (genderEntered == null || genderEntered.equals("")) {
            request.setAttribute("genderErrorMessage", "*Gender is required");
            insertInfo = false;
        }

        if (staffTypeEntered == null || staffTypeEntered.equals("")) {
            request.setAttribute("typeErrorMessage", "*Staff type is required");
            insertInfo = false;
        }

        if (prefferedEntered == null || prefferedEntered.equals("")) {
            request.setAttribute("notiErrorMessage", "*Prefered Notification is required");
            insertInfo = false;
        }

        if (streetEntered == null || streetEntered.equals("")) {
            request.setAttribute("addressErrorMessage", "*Address is required");
            insertInfo = false;
        }

        if (cityEntered == null || cityEntered.equals("")) {
            request.setAttribute("cityErrorMessage", "*City is required");
            insertInfo = false;
        }

        if (provinceEntered == null || provinceEntered.equals("")) {
            request.setAttribute("provErrorMessage", "*Province is required");
            insertInfo = false;
        }

        DoctorService ds = new DoctorService();
        AdministratorService ads = new AdministratorService();
        AccountService as = new AccountService();

        try {
            Account account = as.get(user_name);
            Administrator administrator = ads.get(account.getAccount_id());
            request.setAttribute("user", administrator);
            
            if (insertInfo == true) {
                Account checkAccount = as.get(usernameEntered);
                
                if (checkAccount != null) {
                    request.setAttribute("message", "This username already exists. Please choose the other.");
                } else {
                    if (staffTypeEntered.equals("DOCTOR")) {
                        as.insert(0, usernameEntered, passEntered, "DOCTOR");
                        Account newAccount = as.getAll().get(as.getAll().size() - 1);
                        ds.insert(0, firstEntered, lastEntered, emailEntered, phoneEntered,
                                phoneAltEntered, prefferedEntered, newAccount.getAccount_id(), genderEntered,
                                birthEntered, streetEntered, cityEntered, provinceEntered, postalEntered);
                    } else if (staffTypeEntered.equals("ADMIN")) {
                        as.insert(0, usernameEntered, passEntered, "ADMIN");
                        Account newAccount = as.getAll().get(as.getAll().size() - 1);
                        ads.insert(0, firstEntered, lastEntered, emailEntered, phoneEntered,
                                phoneAltEntered, prefferedEntered, newAccount.getAccount_id(), genderEntered,
                                birthEntered, streetEntered, cityEntered, provinceEntered, postalEntered);
                    } 

                    request.setAttribute("signup_username", "");
                    request.setAttribute("signup_firstname", "");
                    request.setAttribute("signup_lastname", "");
                    request.setAttribute("signup_phonenum", "");
                    request.setAttribute("signup_email", "");
                    request.setAttribute("signup_phonenum_alt", "");
                    request.setAttribute("signup_birth_date", ""); 
                    request.setAttribute("staff_type_radio", "");
                    request.setAttribute("gender_radio", "");
                    request.setAttribute("prefered_notification_radio", "");
                    request.setAttribute("signup_address", "");   
                    request.setAttribute("signup_city", ""); 
                    request.setAttribute("signup_state_province", ""); 
                    request.setAttribute("signup_postal", "");
                    request.setAttribute("message", "New staff has beed updated successfully.");   
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/signupStaff.jsp").forward(request, response);
        return;
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
