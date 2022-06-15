<%-- 
    Document   : Confirm appointment page
    Created on : Feb 11, 2022
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
        <style><%@ include file="/css/styleConfirmAppointmentPage.css" %></style>
        <style><%@ include file="/css/styleFooter.css" %></style>  
        <title>Confirm Appointment Page</title>
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
                        <p>Confirm Appointment</p>
                    </div>
                </div>

                <div class="table-responsive">
                    <form action="confirm_appointment" method="POST">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">DATE AND TIME </th>
                                    <th scope="col">PATIENT NAME </th>
                                    <th scope="col">DOCTOR NAME </th>
                                    <th scope="col">TYPE </th>
                                    <th scope="col">PATIENT ATTENDED </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${appointments}" var="appointment" varStatus="loop">
                                    <c:if test="${(loop.count % 2) == 0}">
                                        <tr class="table-secondary">
                                            <th scope="row">${appointment.start_date_time.substring(0, 16)}</th>
                                            <td>${patients.get(loop.index)}</td>
                                            <td>${doctors.get(loop.index)}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${appointment.type == '1'}">
                                                        Regular
                                                    </c:when>
                                                    <c:when test="${appointment.type == '2'}">
                                                        Annual Physical
                                                    </c:when>
                                                    <c:when test="${appointment.type == '3'}">
                                                        Urgent Care
                                                    </c:when>
                                                    <c:otherwise>
                                                        New Patient Meeting
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <input type="checkbox" class="me-2" value="${loop.index}" name="patient_attended"
                                                        id="patient_attended" <c:if test="${appointment.patient_attended}">checked</c:if>>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${(loop.count % 2) == 1}">
                                        <tr>
                                            <th scope="row">${appointment.start_date_time.substring(0, 16)}</th>
                                            <td>${patients.get(loop.index)}</td>
                                            <td>${doctors.get(loop.index)}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${appointment.type == '1'}">
                                                        Regular
                                                    </c:when>
                                                    <c:when test="${appointment.type == '2'}">
                                                        Annual Physical
                                                    </c:when>
                                                    <c:when test="${appointment.type == '3'}">
                                                        Urgent Care
                                                    </c:when>
                                                    <c:otherwise>
                                                        New Patient Meeting
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <input type="checkbox" class="me-2" value="${loop.index}" name="patient_attended"
                                                        id="patient_attended" <c:if test="${appointment.patient_attended}">checked</c:if>>
                                            </td>
                                        </tr>
                                    </c:if>                                
                                </c:forEach>
                            </tbody>
                        </table>
                        
                        <div class="submit_wrapper">
                            <input type="submit" class="submit_in_form" value="Submit">
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
