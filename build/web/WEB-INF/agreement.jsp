<%-- 
    Document   : Agreement page
    Created on : Mar 28, 2022
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
        <style><%@ include file="/css/styleAgreementPage.css" %></style>  
        <style><%@ include file="/css/styleFooter.css" %></style>  
        <title>Agreement Page</title>
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
                        <div><span class="hello">Hello</span>
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
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
                
                <div class="functional_links">
                    <a href="login">Login</a>
                    <a href="signup">Register</a>
                    <a href="forgot">Find Account/Password</a>
                    <div class="clear"></div>
                </div>
            </div>

            <div class="agreement">
                <h2>Health Information Act</h2>
                
                <h3>Your rights</h3>
                <p>The Health Information Act (HIA) strikes a balance between the protection of privacy and enabling
                    the appropriate amount of information sharing to provide health services and manage the health system.</p>
                <p>You have rights under the HIA –</p>
                <ul>
                    <li>
                        You have a right to access a copy of your health information held by a custodian, subject to specific and limited exemptions.
                    </li>
                    <li>
                        You have the right to request a correction or amendment of your health information held by a custodian.
                    </li>
                    <li>
                        The HIA protects your health information and governs the collection, use and disclosure of that information.
                    </li>
                    <li>
                        You have a right to know why your health information is being collected, used and disclosed.
                    </li>
                    <li>
                        You have the right to make an expressed wish regarding the disclosure of your health information.
                        A custodian is required to consider your concerns before disclosing your health information.
                    </li>
                    <li>
                        You have a right to request an independent review of decisions made by a custodian regarding access to your information or
                        a correction or amendment to your health information, within 60 days of being notified of the decision.
                    </li>
                </ul>
                
                <h3>Custodians and affiliates</h3> 
                <p>A custodian is an organization or entity defined in section 1(1)(f) of the HIA or designated in section 2 of
                    the Health Information Regulation. Examples of custodians include physicians, chiropractors, nurses,
                    Alberta Health Services and Alberta Health.</p>
                <p>An affiliate, as defined by section 1(1)(a) of the HIA, is an individual or organization employed by a custodian,
                    or a person or entity that performs a service for a custodian as an appointee, volunteer or student, or under a contract or
                    agency relationship with the custodian. Examples of affiliates include a receptionist or records clerk in a physician’s office,
                    or a nurse working for Alberta Health Services.</p>
                
                <h3>Mandatory breach reporting</h3>
                <p>The HIA requires a custodian to, as soon as practicable, give notice in accordance with the regulations of a loss of,
                    unauthorized access to, or disclosure of individually identifying health information in the custody or control of the custodian
                    if there is a risk of harm to an individual as a result of the loss or unauthorized access or disclosure.</p>
                <p>When the custodian determines that there is a risk of harm due to a breach,
                    notice is to be given in writing to the affected individual(s), the Information and Privacy Commissioner of Alberta,
                    and the Minister of Health.</p>
                <p>Section 8.2 of the Health Information Regulation sets out the factors that custodians must consider when determining
                    if the breach created any risk of harm to any individual. The assessment of harm must be done by the custodian.</p>
                
                <h3>Privacy Commissioner</h3>
                <p>Alberta’s Information and Privacy Commissioner is an independent officer of the Alberta Legislature who works to protect
                    the information access and privacy rights of all Albertans. The Office of the Information and Privacy Commissioner (OIPC)
                    is the regulator responsible for ensuring custodian and affiliate compliance with the HIA.</p>
                <p>The HIA establishes the role and responsibilities of the OIPC. Under the HIA, the OIPC may:</p>
                <ul>
                    <li>
                        provide independent review and resolution of requests for review of custodian responses to access to information or
                        correction requests and complaints related to the collection, use, disclosure or retention of health information
                    </li>
                    <li>
                        authorize a custodian to disregard a request for access or correction
                    </li>
                    <li>
                        investigate any matters relating to the application of the HIA,
                        or in regards to the destruction of records set out in an enactment of Alberta, whether requested or not
                    </li>
                    <li>
                        inform the public about the HIA
                    </li>
                    <li>
                        investigate any disclosure of information from an affiliate that the affiliate believes is being collected,
                        used or disclosed in contravention of the HIA
                    </li>
                    <li>
                        receive comments from the public concerning the administration of the HIA
                    </li>
                    <li>
                        engage in or commission a study of anything affecting the achievement of the purposes of the HIA
                    </li>
                    <li>
                        comment on the implications for access to health information or for protection of health information
                        of privacy impact assessments submitted to the Commissioner
                    </li>
                    <li>
                        comment on the implications for protection of health information of using or disclosing health information
                        for the purpose of performing data matching
                    </li>
                    <li>
                        give advice and recommendations of general application to a custodian on matters respecting the rights
                        or obligations of custodians under the HIA
                    </li>
                    <li>
                        bring to the attention of a custodian any failure by the custodian to assist applicants in making an access request
                    </li>
                    <li>
                        exchange information with an extra-provincial commissioner and enter into information sharing and other agreements
                        with extra-provincial commissioners for the purpose of coordinating activities and handling complaints involving 2
                        or more jurisdictions
                    </li>
                </ul>
                
                <h3>Access to your information</h3>
                <p>Under the HIA, you have the right of access to your health information that is held by a custodian.
                    To request a copy of your health information, you must submit an access request to the custodian who you believe has custody or
                    control of the information you are looking for. A custodian may require the request to be made in writing, using either the:
                    Request to Access Health Information form or the provider may request you use their own access request form</p>
                <p>A custodian may charge a fee of $25 for producing a copy of an individual’s health information. This fee includes the cost of
                    photocopying/printing the first 20 pages of the record, preparing the document, removing information as necessary,
                    and reviewing the record. Additional fees may be charged as outlined in the Schedule found in the Health Information Regulation
                    web site. This fee can be waived if agreed upon by the custodian.</p>
                <p>Within 30 days of receiving your request, the custodian must provide you with access to the record,
                    or notify you of the reason why the request has been refused.</p>
                <p>If the custodian refuses to provide access, or refuses to waive fees, you have the right to ask the OIPC to review the custodian’s
                    decision. You must submit your request for review within 60 days of receiving the decision. Contact the OIPC at 780-422-6860 or
                    generalinfo@oipc.ab.ca</p>
                
                <h3>Correction or amendments</h3>
                <p>Under the HIA, you have a right to request a correction or amendment to facts included in your health information.
                    To do this, you must make a request in writing to the custodian who has custody or control of the record. Within 30 days
                    of receiving your request, the custodian must make the correction or amendment, or notify you of the reason why the correction
                    or amendment has been refused.</p>
                <p>If the custodian refuses to make the correction, you have 2 options, but cannot do both:</p>
                <ul>
                    <li>
                        You have the right to ask the OIPC to review the custodian’s decision. You must submit your request for review
                        within 60 days of receiving the decision. Contact the OIPC at 780-422-6860 or generalinfo@oipc.ab.ca .
                    </li>
                    <li>
                        You can attach a statement of disagreement to the record in question. A statement of disagreement sets out,
                        in 500 words or less, the requested correction or amendment and the applicant’s reasons for disagreeing with the decision
                        of the custodian. The statement of disagreement must be submitted to the custodian within 30 days after the written notice
                        of refusal has been given to the applicant. The custodian must, if reasonably practicable, attach the statement to the record
                        in question, and provide a copy of the statement of disagreement to any person to whom the custodian has disclosed the record
                        in the year preceding the applicant’s correction or amendment request.
                    </li>
                </ul>
                
                <h3>Disclosure of information</h3>
                <p>The HIA provides limited and specific circumstances where a custodian can disclose your information to a third party
                    without your consent. Some examples include disclosing information:</p>
                <ul>
                    <li>
                        to another custodian, for the purpose of providing an individual with health services
                    </li>
                    <li>
                        to any person, if the custodian reasonably believes that the disclosure will avert or minimize a risk of harm
                        to the health or safety of a minor, or an imminent danger to any person
                    </li>
                    <li>
                        if authorized or required by another enactment of Alberta or Canada, for example, the Public Health Act
                    </li>
                    <li>
                        to a police service if the custodian reasonably believes the information relates to the possible commission of
                        an offence under an enactment of Alberta or Canada, for example, the Criminal Code of Canada, and the disclosure
                        will protect the health and safety of Albertans
                    </li>
                </ul>
                <p>Except for limited circumstances specified in the HIA, a custodian must get your written consent before releasing information
                    to a third party, such as a family member, lawyer, or insurance company. Consent allows for disclosure to anyone for any purpose,
                    according to the terms of the consent.</p>
                
                <h3>Records retention</h3>
                <p>The HIA does not explicitly speak to records retention. Current retention requirements are set by the organizations named
                    as custodians or by professional colleges of regulated health professions. For questions regarding how long records are to be kept,
                    contact the appropriate organization or professional college. The HIA does require custodians to protect the confidentiality
                    and security of health information (section 60) throughout the entire record lifecycle, including during storage,
                    but does not prescribe the means by which this should be done.</p>
                <p>Other legislation may place retention requirements on health information. For example, under the Hospitals Act (section 15(1)),
                    hospitals must retain diagnostic and treatment records for 10 years after the date of discharge, or 2 years after the patient reaches
                    or would have reached the age of 18, whichever is longer.</p>
                <p>The HIA does not establish fees for the transfer of records to another care provider.
                    For questions regarding fees for records transfer, contact the appropriate professional college.</p>
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
