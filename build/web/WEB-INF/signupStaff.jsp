<%-- 
    Document   : Signup staff page
    Created on : Mar 24, 2022
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
        <style><%@ include file="/css/styleSignupPage.css" %></style>  
        <style><%@ include file="/css/styleFooter.css" %></style>  
        <title>Sign Up Staff Page</title>      
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
                                                &nbsp;Staff&nbsp;&nbsp; <i class="bi bi-caret-down-fill"></i>
                                            </a>
                                            <ul class="subnav">
                                                <li><a href="view_staff">View Staff</a></li>
                                                <li><a href="signup_staff">Register Staff</a></li>
                                            </ul>
                                        </li>
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
                                            Staff
                                        </a>
                                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <li class="ms-3 p-1"><a href="view_staff">View Staff</a></li>
                                            <li class="ms-3 p-1"><a href="signup_staff">Register Staff</a></li>
                                        </ul>
                                    </li>
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
                        <p>New Staff</p>
                    </div>
                </div>

                <div class="message">
                    <br>
                    <h3>${message}</h3>
                </div>
                    
                <div class="signup">
                    <div class="form_heading">
                        <h2>Register</h2>
                        <p>Please fill in the form below</p>
                    </div>

                    <form class="signup_form" action="signup_staff" method="post">                        
                        <div>
                            <label>Username</label>
                            <input type="text" name="signup_username" placeholder="Username" value="${signup_username}">
                            <p class="errormessage">${userNameErrorMessage}</p>
                        </div>
                        <div>
                            <label>Password</label>
                            <input type="password" name="signup_password" placeholder="Password">
                            <p class="errormessage">${passPatternErrorMessage}</p>
                        </div>
                        <div>
                            <label>Re-enter Password</label>
                            <input type="password" name="signup_re_enter_password" placeholder="Re-enter password">
                            <p class="errormessage">${passErrorMessage}</p>
                        </div>
                        <div>
                            <label>First name</label>
                            <input type="text" name="signup_firstname" placeholder="First Name" value="${signup_firstname}">
                            <p class="errormessage">${firstErrorMessage}</p>
                        </div>
                        <div>
                            <label>Last Name</label>
                            <input type="text" name="signup_lastname" placeholder="Last Name" value="${signup_lastname}">
                            <p class="errormessage">${lastErrorMessage}</p>
                        </div>
                        <div>
                            <label>Phone number</label>
                            <input type="tel" name="signup_phonenum" placeholder="Phone number" value="${signup_phonenum}">
                            <p class="errormessage">${phoneErrorMessage}</p>
                        </div>
                        <div>
                            <label>Email</label>
                            <input type="email" name="signup_email" placeholder="Email" value="${signup_email}">
                            <p class="errormessage">${emailErrorMessage}</p>
                        </div>
                        <div>
                            <label>Alternate Phone number</label>
                            <input type="tel" name="signup_phonenum_alt" placeholder="Alternate phone number" value="${signup_phonenum_alt}">
                            <p class="errormessage">${phoneAltErrorMessage}</p>
                        </div>
                        <div>
                            <label>Birth date</label>
                            <input type="date" name="signup_birth_date" placeholder="Birth date" value="${signup_birth_date}">
                            <p class="errormessage">${birthErrorMessage}</p>
                        </div>
                        <div>
                            <label>Staff Type</label>
                            <div> 
                                <input type="radio" name="staff_type_radio" value="DOCTOR" 
                                        <c:if test="${staff_type_radio == 'DOCTOR'}">checked</c:if>>
                                <label>&nbsp; Doctor</label>
                            </div>
                            <div>
                                <input type="radio" name="staff_type_radio" value="ADMIN"
                                        <c:if test="${staff_type_radio == 'ADMIN'}">checked</c:if>>
                                <label>&nbsp; Admin</label>
                            </div>
                            <p class="errormessage">${typeErrorMessage}</p>
                        </div>
                        <div>
                            <label>Sex</label>
                            <div> 
                                <input type="radio" name="gender_radio" value="male"
                                        <c:if test="${gender_radio == 'male'}">checked</c:if>>
                                <label>&nbsp; Male</label>
                            </div>
                            <div>
                                <input type="radio" name="gender_radio" value="female"
                                        <c:if test="${gender_radio == 'female'}">checked</c:if>>
                                <label>&nbsp; Female</label>
                            </div>
                            <p class="errormessage">${genderErrorMessage}</p>
                        </div>                            
                        <div>
                            <label>Preferred Notification</label>
                            <div> 
                                <input type="radio" name="prefered_notification_radio" value="MOBILE_PHONE"
                                        <c:if test="${prefered_notification_radio == 'MOBILE_PHONE'}">checked</c:if>>
                                <label>&nbsp; Phone</label>
                            </div>
                            <div>
                                <input type="radio" name="prefered_notification_radio" value="EMAIL"
                                        <c:if test="${prefered_notification_radio == 'EMAIL'}">checked</c:if>>
                                <label>&nbsp; Email</label>
                            </div>
                            <p class="errormessage">${notiErrorMessage}</p>
                        </div>
                        <div>
                            <label>Address</label>
                            <input type="text" name="signup_address" placeholder="Street address" value="${signup_address}">
                            <p class="errormessage">${addressErrorMessage}</p>
                        </div>
                        <div>
                            <label>City</label>
                            <input type="text" name="signup_city" placeholder="City" value="${signup_city}">
                            <p class="errormessage">${cityErrorMessage}</p>
                        </div>
                        <div>
                            <label>Province</label>
                            <select class="province" name="signup_state_province" placeholder="Province" >
                                <option value="" disabled selected>Province</option>
                                <option value="Alberta" <c:if test="${signup_state_province == 'Alberta'}">selected</c:if>>
                                    Alberta
                                </option>
                                <option value="British Columbia"
                                        <c:if test="${signup_state_province == 'British Columbia'}">selected</c:if>>
                                    British Columbia
                                </option>
                                <option value="Manitoba" <c:if test="${signup_state_province == 'Manitoba'}">selected</c:if>>
                                    Manitoba
                                </option>
                                <option value="New Brunswick" <c:if test="${signup_state_province == 'New Brunswick'}">selected</c:if>>
                                    New Brunswick
                                </option>
                                <option value="Newfoundland and Labrador"
                                        <c:if test="${signup_state_province == 'Newfoundland and Labrador'}">selected</c:if>>
                                    Newfoundland
                                </option>
                                <option value="Nova Scotia" <c:if test="${signup_state_province == 'Nova Scotia'}">selected</c:if>>
                                    Nova Scotia
                                </option>
                                <option value="Ontario" <c:if test="${signup_state_province == 'Ontario'}">selected</c:if>>
                                    Ontario
                                </option>
                                <option value="Prince Edward Island"
                                        <c:if test="${signup_state_province == 'Prince Edward Island'}">selected</c:if>>
                                    Prince Edward Island
                                </option>
                                <option value="Quebec" <c:if test="${signup_state_province == 'Quebec'}">selected</c:if>>
                                    Quebec
                                </option>
                                <option value="Saskatchewan" <c:if test="${signup_state_province == 'Saskatchewan'}">selected</c:if>>
                                    Saskatchewan
                                </option>
                                <option value="Northwest Territories"
                                        <c:if test="${signup_state_province == 'Northwest Territories'}">selected</c:if>>
                                    Northwest Territories
                                </option>
                                <option value="Nunavut" <c:if test="${signup_state_province == 'Nunavut'}">selected</c:if>>
                                    Nunavut
                                </option>
                                <option value="Yukon" <c:if test="${signup_state_province == 'Yukon'}">selected</c:if>>
                                    Yukon
                                </option>
                            </select>
                            <p class="errormessage">${provErrorMessage}</p>
                        </div>
                        <div>
                            <label>Postal Code</label>
                            <input type="text" name="signup_postal" placeholder="T2X 2X2" value="${signup_postal}">
                            <p class="errormessage">${postalErrorMessage}</p>
                        </div>

                        <div class="register_btn">
                            <input type="submit" value="Register">
                            <input type="button" value="Cancel">
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
