<%-- 
    Document   : Book appointment page
    Created on : Feb 10, 2022
    Author     : Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous">
        </script>
        <script src="https://kit.fontawesome.com/ed40b1b6b3.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
        <style><%@ include file="/css/styleCommon.css" %></style>  
        <style><%@ include file="/css/styleBookAppointmentPage.css" %></style>   
        <style><%@ include file="/css/styleFooter.css" %></style>  
        <title>Book Appointment Page</title>
    </head>
    <body>
        <div>
            <div class="headers">
                <div class="fixed_position_header">
                    <div class="top_blue_div"></div>
                    <div class="topnav_wrapper">
                        <div class="topnav">
                            <div class="topnav_left">
                                <a href="welcome"><img src="img/logo.png" height="88px"></a>
                            </div>
                            <div class="topnav_right">
                                <nav>
                                    <ul>
                                        <li><a href="welcome">Home</a></li>

                                        <c:choose>
                                            <c:when test="${account.profile == 'DOCTOR'}">
                                                <li>
                                                    <a class="drop-down-tab" href="">
                                                        Appointment <i class="bi bi-caret-down-fill"></i>
                                                    </a>
                                                    <ul class="subnav">
                                                        <li><a href="book_appointment">Book Appointment</a></li>
                                                        <li><a href="view_appointment">View Appointment</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="doctor_schedule">Doctor schedule</a></li>
                                                <li><a href="view_patient">Patient</a></li>
                                                <li><a href="view_staff">Staff</a></li>
                                            </c:when>
                                            <c:when test="${account.profile == 'ADMIN'}">
                                                <li>
                                                    <a class="drop-down-tab" href="">
                                                        Appointment <i class="bi bi-caret-down-fill"></i>
                                                    </a>
                                                    <ul class="subnav">
                                                        <li><a href="book_appointment">Book Appointment</a></li>
                                                        <li><a href="view_appointment">View Appointment</a></li>
                                                        <li><a href="confirm_appointment">Confirm Appointment</a></li>
                                                    </ul>
                                                </li>
                                                <li>
                                                    <a class="drop-down-tab" href="">
                                                        Patient <i class="bi bi-caret-down-fill"></i>
                                                    </a>
                                                    <ul class="subnav">
                                                        <li><a href="view_patient">View Patient</a></li>
                                                        <li><a href="signup">Register Patient</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="view_staff">Staff</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li>
                                                    <a class="drop-down-tab" href="">
                                                        Appointment <i class="bi bi-caret-down-fill"></i>
                                                    </a>
                                                    <ul class="subnav">
                                                        <li><a href="book_appointment">Book Appointment</a></li>
                                                        <li><a href="view_appointment">View Appointment</a></li>
                                                    </ul>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="p-0 mobile_nav">
                    <div class="pe-2 mobile_header">
                        <a href="welcome"><img src="img/logo.png" width="105px"></a>
                        <div><span class="hello">Hello ${user.first_name}</span>
                            <img
                                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACMAAAAjCAYAAAAe2bNZAAAABmJLR0QA/wD/
                                AP+gvaeTAAAD40lEQVRYhe2XS2xUZRTHf+eb6YOqpYD0laghdkAw7HwUEeLCqKnR0qJ1Y2JcWE3YqtWdS+
                                tKl8pCFy5IX9MRKFvAxMYmLtQgEdEGA22nY63UCNNO5/5dzEyZzsydudMUVpzVPd95/e693/
                                3OuXBXbqOcuqJtm5HHqg2IXdMez9Et0wsYEUQzUAusAHHgMrIzhIn17rRLtwVmPK6nPE+DGE8Hzi6+
                                xdlAb4tNbgpMdFFNWtFxxCuBIYqphlZrXX/fdru+YZiReUWcp2+ARypUWwIaK/hcDDl7ubvZLvs5uAogk2VA4sLeqq+
                                xrb2tbutqrTWB9QPzPv57054mY/Pq8KtZ8slEF9Wk5TIgxtXVtB3sa7c/
                                C02jM3rInL4D2n1qXqyvsc6uHbZUaCj5ZLSi474ggGTHSoEAHG23K2Z2zC8W2JtMeZ+XMhTBjMZ1sMJmTfzcwqkydlLNnAT+
                                8vew10bn1FkRxjx9XK4QMP2RmVfOoc8sDUyXcTEzDZaFiV3TngDnSNDTdntZqzg8Mq+IL4zn6A5QJDIyo7Kf+
                                vis9gEPV0rktL7eOhiZugLAEDJ9OiSFStmGpJBMnwXJg/
                                SiL4zBrkA5jOfDcY2OJtSWvx5LqD0UV1TwbCAY2F9QPwcpi8aVJNP0gkoSOA+
                                6CvYAcAioryKe6zdty5u7LAkQzi0OL9IYrg6EbOHnNtD81+SeMA1kburWa3p1G0tkxoA7Kvcm+
                                S93vQZjZjKYu8Msf3dFbDmnhPMtgj+ABwMkWUZMYfoFEcfcP8hrwmhB9ijG40BdgDw/
                                5ivrYJBNYHrGJ3AB9LU8N7a0zFRu05WSL6dV31jHE+a8o2Cv43MAyux0vr5u540ltJu0fi2IWQUNhkJusHun/
                                esH4CcTC2pMprwBsPcpuPmQs0j+
                                fFP0GYzNeucxDmXVlMm6e9rsTLUQhTIyqy5nGgdqskvnelvdurdQ3CixdwHldM9RNHdsRMLGQl5eYfZhoU8RTE+
                                bTYGGs2qNScOFDa1aGUtot4eirJ1jOlFqSC85XK3Wun7gYlZtc57Ojs/pyQ2BxHWAtM4CudZxob7GvVPK1/fojM2rI52Zge/
                                PLqVAnwTdyLGE7kunvQ/A3uPWPkmY2YGeFvu9Kpg8oBiwLy9iEekr57loCH54qd1u5EwnZ9SwAo855/
                                XI7A20bva5YGbdfiAVYSDzaS6nvC+E9ZXwT5P5i7wJbAFagMLRQqAToZB7u9ITDdzhRufUaaZBxOGgMcA5hw0cabXvgzhX/
                                689r460OJIdjPYDO/LMC8BPMjsdNmLlftg2BaZQJn5TnVdLg1vhRn7TuyubKf8DPbd2zmO//zkAAAAASUVORK5CYII=">
                        </div>
                    </div>

                    <nav class="navbar navbar-expand-md navbar-light m-3">
                        <div class="container-fluid justify-content-start">
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                    aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <a class="navbar-brand ms-3 fs-6">Menu</a>
                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                    <li class="nav-item">
                                        <a class="nav-link" href="welcome">Home</a>
                                    </li>

                                    <c:choose>
                                        <c:when test="${account.profile == 'DOCTOR'}">
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                   role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    Appointment
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                    <li class="ms-3 p-1"><a href="book_appointment">Book Appointment</a></li>
                                                    <li class="ms-3 p-1"><a href="view_appointment">View Appointment</a></li>
                                                </ul>
                                            </li>
                                            <li class="nav-item"><a class="nav-link" href="doctor_schedule">Doctor schedule</a></li>
                                            <li class="nav-item"><a class="nav-link" href="view_patient">Patient</a></li>
                                            <li class="nav-item"><a class="nav-link" href="view_staff">Staff</a></li>
                                        </c:when>
                                        <c:when test="${account.profile == 'ADMIN'}">
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                   role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                   Appointment
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                  <li class="ms-3 p-1"><a href="book_appointment">Book Appointment</a></li>
                                                  <li class="ms-3 p-1"><a href="view_appointment">View Appointment</a></li>
                                                  <li class="ms-3 p-1"><a href="confirm_appointment">Confirm Appointment</a></li>
                                                </ul>
                                            </li>
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                   role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                   Patient
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                  <li class="ms-3 p-1"><a href="view_patient">View Patient</a></li>
                                                  <li class="ms-3 p-1"><a href="signup">Register Patient</a></li>
                                                </ul>
                                            </li>
                                            <li class="nav-item"><a class="nav-link" href="view_staff">Staff</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                   role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    Appointment
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                    <li class="ms-3 p-1"><a href="book_appointment">Book Appointment</a></li>
                                                    <li class="ms-3 p-1"><a href="view_appointment">View Appointment</a></li>
                                                </ul>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>

                <div class="functional_links">
                    <a href="profile">Manage Account</a>
                    <a href="welcome?logout">Logout</a>
                    <span>Hello, ${user.first_name} ${user.last_name}</span>                            
                    <div class="clear"></div>
                </div>
            </div>

            <div>
                <div class="banner_field">
                    <div class="banner">
                        <p>Book Appointment</p>
                    </div>
                </div>

                <div class="steps">
                    <c:choose>
                        <c:when test="${account.profile == 'DOCTOR' || account.profile == 'PATIENT'}">
                            <c:if test="${step == '1'}">
                                <span>Select Date</span> >> 
                                <span class="non_active other_step">Book Appointment</span>
                            </c:if>
                            <c:if test="${step == '2'}">
                                <a href="book_appointment" class="non_active">Select Date</a> >> 
                                <span>Book Appointment</span>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${step == '0'}">
                                <span>Search Patient / Doctor</span> >> 
                                <span class="non_active other_step">Select Date</span> >> 
                                <span class="non_active other_step">Book Appointment</span>
                            </c:if>
                            <c:if test="${step == '1'}">
                                <a href="book_appointment?step=0" class="non_active">Search Patient / Doctor</a> >> 
                                <span>Select Date</span> >> 
                                <span class="non_active other_step">Book Appointment</span>
                            </c:if>
                            <c:if test="${step == '2'}">
                                <a href="book_appointment?step=0" class="non_active other_step">Search Patient / Doctor</a> >> 
                                <span class="non_active other_step">Select Date</span> >> 
                                <span>Book Appointment</span>
                            </c:if>   
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="message">
                    <br>
                    <h3>${message}</h3>
                </div>

                <div class="book_wrapper"> 
                    <c:choose>
                        <c:when test="${step == '0'}">
                            <div class="book">
                                <div class="form_heading">
                                    <h2>Search Patient / Doctor</h2>
                                    <p>Please search by the name</p>
                                </div>

                                <form class="book_form" action="book_appointment" method="post">
                                    <label for="name">Name</label>
                                    <input type="text" class="search_patient" name="name" value="${name}">
                                    
                                    <div class="btn_wrapper">
                                        <input type="submit" class="submit_in_form" value="Submit">
                                        <input type="hidden" name ="action" value="search_name">
                                    </div>                                    
                                </form>

                                <c:if test="${searched}">
                                    <form action="book_appointment" method="post">
                                        <div class="table-responsive">
                                            <table class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th scope="col"></th>                                                        
                                                        <th scope="col">PROFILE</th>
                                                        <th scope="col" >FIRST NAME</th>
                                                        <th scope="col">LAST NAME</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${searched_patients}" var="searched_patient">
                                                        <tr>
                                                            <th><input type="radio" name="searched"
                                                                       value="${searched_patient.account_id}"></th>
                                                            <td>PATIENT</td>
                                                            <td>${searched_patient.first_name}</td>
                                                            <td>${searched_patient.last_name}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    <c:forEach items="${searched_doctors}" var="searched_doctor">
                                                        <tr>
                                                            <th><input type="radio" name="searched"
                                                                       value="${searched_doctor.account_id}"></th>
                                                            <td>DOCTOR</td>
                                                            <td>${searched_doctor.first_name}</td>
                                                            <td>${searched_doctor.last_name}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="btn_wrapper">
                                            <input type="submit" class="selected_submit" value="Submit Selected Name">
                                            <input type="hidden" name="name" value=${name}>
                                            <input type="hidden" name ="action" value="select_name">
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </c:when>
                        <c:when test="${step == '1'}">
                            <div class="book">
                                <div class="form_heading">
                                    <h2>Select Date</h2>
                                    <p>Please select the appointment date</p>
                                </div>

                                <form class="book_form" action="book_appointment" method="post">
                                    <div>
                                        <label for="appointment_date">Date</label>
                                        <select class="form-select" name="appointment_date">
                                            <option value="0">-------- Choose Date --------</option>
                                            <c:forEach items="${available_dates}" var="available_date">
                                                <option value="${available_date}">
                                                    ${available_date}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="btn_wrapper">
                                        <input type="submit" class="submit_in_form" value="Submit">
                                        <input type="hidden" name="account_id" value=${account_id}>
                                        <input type="hidden" name ="action" value="select_date">
                                    </div>
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="book">
                                <div class="form_heading">
                                    <h2>Book Appointment</h2>
                                    <p>Please fill in the form below</p>
                                </div>

                                <form class="book_form" action="book_appointment" method="post">
                                    <div class="book_form_flex">
                                        <div>
                                            <label for="type_selection">Type</label>
                                            <select class="form-select" name="type_selection">
                                                <option value="0">-------- Choose type --------</option>
                                                <c:forEach items="${types}" var="type">
                                                    <option value="${type.type}"
                                                            <c:if test="${type_selection == type.type}">selected</c:if>>
                                                        ${type.description}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div>
                                            <c:choose>
                                                <c:when test="${account.profile == 'DOCTOR' || selected_doctor}">
                                                    <label for="patient_selection">Assigned Patient</label>
                                                    <select class="form-select" name="patient_selection">
                                                        <option value="0">-------- Choose patient --------</option>
                                                        <c:forEach items="${patients}" var="patient">
                                                            <option value="${patient.patient_id}"
                                                                    <c:if test="${patient_selection == patient.patient_id}">selected</c:if>>
                                                                ${patient.first_name}, ${patient.last_name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </c:when>
                                                <c:otherwise>
                                                    <label for="assigned_doctor">Assigned Doctor</label>
                                                    <input type="text" name="assigned_doctor" value="Dr. ${doctor.last_name}" readonly>
                                                </c:otherwise>
                                            </c:choose>                                            
                                        </div>
                                        <div>
                                            <label for="appointment_date">Appointment Date</label>
                                            <input type="text" name="appointment_date" value="${appointment_date}" readonly>
                                        </div>
                                        <div>
                                            <label for="time_selection">Appointment Time</label>
                                            <select class="form-select" name="time_selection">
                                                <option value="0">-------- Choose time --------</option>
                                                <c:forEach items="${available_times}" var="available_time">
                                                    <option value="${available_time}"
                                                            <c:if test="${time_selection == available_time}">selected</c:if>>
                                                        ${available_time}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div>
                                            <label for="book_app_reason">Appointment Reason</label>
                                            <textarea name="book_app_reason" 
                                                      placeholder="Please write down the reason for your appointment."></textarea>
                                            <br>
                                        </div>
                                        <div class="btn_wrapper">
                                            <input type="submit" class="submit_in_form" value="Done">
                                            <input type="hidden" name="account_id" value=${account_id}>
                                            <input type="hidden" name ="action" value="book_appointment">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        
        <!----------------Footer------------------------>
        <div class="container"></div>
        <footer>
            <div class="top">
                <div class="top-left">
                    <img src="img/logo.png">
                    <div class="details">
                        <h3>About Us</h3>
                        <p>Surpass health clinic prides itself on integrity, accountability, respect, excellence. 
                            Surpass health clinic believes in helping people feel better through quality family 
                            healthcare available to everyone. </p>
                    </div>
                </div>
                <div class="top-right">
                    <h3>Contact Us</h3>
                    <div class="links">
                        <a href="#"><i class="fa-solid fa-square-phone"></i> +1 420 1245 6456</a>
                        <a href="#"><i class="fa-solid fa-envelope"></i> SurpassClinic@gmail.com</a>
                        <a href="#"><i class="fa-solid fa-location-dot"></i> 436 40th Street Calgary Alberta T2M 0G6</a>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <p> © 2022 The Surpass Clinic </p>
            </div>
        </footer>
    </body>
</html>
