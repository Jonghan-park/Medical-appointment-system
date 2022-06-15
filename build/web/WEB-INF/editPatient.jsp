<%-- 
    Document   : Profile page
    Created on : Mar 9, 2022
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
        <style><%@ include file="/css/styleProfilePage.css" %></style>
        <style><%@ include file="/css/styleFooter.css" %></style>  
        <title>Profile Page</title>
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
                                            <c:when test="${loginAccount.profile == 'DOCTOR'}">
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
                                                <c:when test="${loginAccount.profile == 'ADMIN'}">
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
                                                <c:when test="${loginAccount.profile == 'SYSADMIN'}">
                                                <li>
                                                    <a class="drop-down-tab" href="">
                                                        &nbsp;Staff&nbsp;&nbsp; <i class="bi bi-caret-down-fill"></i>
                                                    </a>
                                                    <ul class="subnav">
                                                        <li><a href="view_staff">View Staff</a></li>
                                                        <li><a href="signup_staff">Register Staff</a></li>
                                                    </ul>
                                                </li>
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
                        <div><span class="hello">Hello ${loginUser.first_name}</span>
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
                                        <c:when test="${loginAccount.profile == 'DOCTOR'}">
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
                                            <c:when test="${loginAccount.profile == 'ADMIN'}">
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
                                            <c:when test="${loginAccount.profile == 'SYSADMIN'}">
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                                   role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    Staff
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                    <li class="ms-3 p-1"><a href="view_staff">View Staff</a></li>
                                                    <li class="ms-3 p-1"><a href="signup_staff">Register Staff</a></li>
                                                </ul>
                                            </li>
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
                    <span>Hello, ${loginUser.first_name} ${loginUser.last_name}</span>
                    <div class="clear"></div>
                </div>
            </div>

            <div>
                <div class="banner_field">
                    <div class="banner">
                        <p>Edit Account</p>
                    </div>
                </div>

                <div class="message">
                    <br>
                    <h3>${message}</h3>
                </div>

                <div class="profileForm">
                    <div class="form_heading">
                        <h2>Edit your account</h2>
                        <p>Please enter your information for your account.</p>
                    </div>

                    <form action="edit_patient" method="post">
                        <div class="container">
                            <div class="row align-items-start">
                                <div class="col-lg-5">
                                    <label for="first_name">First name</label> <br>
                                    <input type="text" id="first_name" name="first_name" value="${user.first_name}">
                                </div>
                                <div class="col-lg-5">
                                    <label for="last_name">Last name</label> <br>
                                    <input type="text" id="last_name" name="last_name" value="${user.last_name}">
                                </div>
                            </div>                            
                            <div class="row align-items-third">
                                <div class="col-lg-5">
                                    <label for="phone_number">Phone number</label> <br>
                                    <input type="text" id="phone_number" name="phone_number" value="${user.mobile_phone}">
                                </div>
                                <div class="col-lg-5">
                                    <label for="alt_phone">Alter Phone number</label> <br>
                                    <input type="text" id="alt_phone" name="alt_phone" value="${user.alt_phone}">
                                </div>
                            </div>
                            <div class="row align-items-third">
                                <div class="col-lg-5">
                                    <label for="select_doctor">Assign Doctor</label><br>
                                    <select id="select_doctor" name="select_doctor">
                                        <c:forEach items="${doctors}" var="doctor">
                                            <option value="${doctor.doctor_id}" 
                                                    <c:if test="${select_doctor == doctor.doctor_id}">selected</c:if>> 
                                                Dr. ${doctor.last_name}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                            </div>
                            <div class="row align-items-fourth">
                                <div class="col-lg-5">
                                    <label for="email">Email</label> <br>
                                    <input type="email" id="email" name="email" value="${user.email}">
                                </div>
                                <div class="col-lg-5">
                                    <label for="pref_contact_type">Preferred Notification</label><br>
                                    <select id="pref_contact_type" name="pref_contact_type">
                                        <c:if test="${user.pref_contact_type == 'MOBILE_PHONE'}">
                                            <option value="MOBILE_PHONE" selected>Phone</option>
                                            <option value="EMAIL" >Email</option>
                                        </c:if>
                                        <c:if test="${user.pref_contact_type == 'EMAIL'}">
                                            <option value="EMAIL" selected>Email</option>
                                            <option value="MOBILE_PHONE" >Phone</option>
                                        </c:if>
                                    </select>  
                                </div>
                            </div>
                            <div class="row align-items-fifth">
                                <div class="col-lg-5">
                                    <label for="birth_date">Birth date</label> <br>
                                    <input type="date" id="birth_date" name="birth_date" value="${user.birth_date}">
                                </div>
                                <div class="col-lg-5">
                                    <label for="gender">Sex</label><br>
                                    <select id="gender" name="gender">
                                        <c:if test="${user.gender == 'male'}">
                                            <option value="male" selected>Male</option>
                                            <option value="female" >Female</option>
                                        </c:if>
                                        <c:if test="${user.gender == 'female'}">
                                            <option value="female" selected>Female</option>
                                            <option value="male" >Male</option>
                                        </c:if>
                                    </select>  
                                </div>
                            </div>
                            <div class="row align-items-sixth">
                                <div class="col-lg-11">
                                    <label>Address</label> <br>
                                    <input type="text" id="street_address" name="street_address" value="${user.street_address}"><br>
                                    <input type="text" id="city" name="city" value="${user.city}">
                                </div>                       
                            </div>
                            <div class="row align-items-seventh">
                                <div class="col-lg-11">
                                    <input type="text" id="postal_code" name="postal_code" value="${user.postal_code}"><br>
                                    <select name="province">                                      
                                        <option value="Alberta" ${user.province == 'Alberta' ? 'selected' : ''} >Alberta</option>
                                        <option value="British Columbia" ${user.province == 'British Columbia' ? 'selected' : ''}>British Columbia</option>
                                        <option value="Manitoba" ${user.province == 'Manitoba' ? 'selected' : ''}>Manitoba</option>
                                        <option value="New Brunswick" ${user.province == 'New Brunswick' ? 'selected' : ''}>New Brunswick</option>
                                        <option value="Newfoundland and Labrador" ${user.province == 'Newfoundland and Labrador' ? 'selected' : ''}>Newfoundland</option>
                                        <option value="Nova Scotia" ${user.province == 'Nova Scotia' ? 'selected' : ''}>Nova Scotia</option>
                                        <option value="Ontario" ${user.province == 'Ontario' ? 'selected' : ''}>Ontario</option>
                                        <option value="Prince Edward Island" ${user.province == 'Prince Edward Island' ? 'selected' : ''}>Prince Edward Island</option>
                                        <option value="Quebec" ${user.province == 'Quebec' ? 'selected' : ''}>Quebec</option>
                                        <option value="Saskatchewan" ${user.province == 'Saskatchewan' ? 'selected' : ''}>Saskatchewan</option>
                                        <option value="Northwest Territories" ${user.province == 'Northwest Territories' ? 'selected' : ''}>Northwest Territories</option>
                                        <option value="Nunavut" ${user.province == 'Nunavut' ? 'selected' : ''}>Nunavut</option>
                                        <option value="Yukon" ${user.province == 'Yukon' ? 'selected' : ''}>Yukon</option>
                                    </select>                                  
                                </div>                       
                            </div>
                        </div>

                        <div class="btn_wrapper">
                            <input type="submit" value="Submit">          
                        </div>
                    </form>
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
