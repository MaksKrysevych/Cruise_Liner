<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<html lang="${language}">
<head>
    <title>Ukrainian Cruise</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <style>
        /* CSS */
        .image {
            display: block;
            margin: 20px auto 30px auto;
            max-width: 100%;
            height: auto;
            margin-bottom: 30px;
            outline: 6px dotted #999;
            outline-offset: -3px;
            border: 8px solid #997;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <header class="d-flex justify-content-center py-3">
        <div class="navbar navbar-header">
            <h5>|Cruise|</h5>

            <ul class="nav nav-pills">
                <li class="nav-item"><a href="/Cruise_Liner" class="nav-link active" aria-current="page"><fmt:message key="main.button.home"/></a></li>
                <li class="nav-item"><a href="/Cruise_Liner/contacts.jsp" class="nav-link"><fmt:message key="main.button.contacts"/></a></li>
                <li class="nav-item"><a href="/Cruise_Liner/catalog" class="nav-link"><fmt:message key="main.button.catalog"/></a></li>
                <c:if test = "${sessionScope.user != null}">
                    <li class="nav-item"><a href="/Cruise_Liner/profile" class="nav-link"><fmt:message key="main.button.profile"/></a></li>
                    <li class="nav-item"><a href="/Cruise_Liner/logout" class="nav-link"><fmt:message key="main.button.logout"/></a></li>
                </c:if>
                <c:if test = "${sessionScope.user == null}">
                    <li class="nav-item"><a href="/Cruise_Liner/login" class="nav-link"><fmt:message key="main.button.login"/></a></li>
                    <li class="nav-item"><a href="/Cruise_Liner/signup" class="nav-link"><fmt:message key="main.button.signup"/></a></li>
                </c:if>
                <form>
                    <select id="language" name="language" onchange="submit()">
                        <option value="eu" ${language == '' ? 'selected' : ''}><fmt:message key="language.label.english"/></option>
                        <option value="ua" ${language == 'ua' ? 'selected' : ''}><fmt:message key="language.label.ukrainian"/></option>
                    </select>
                </form>

            </ul>
        </div>
    </header>
</div>

<img src="https://life.pravda.com.ua/images/doc/4/9/49b1b70-kruize-2.jpg" class="image" alt="https://life.pravda.com.ua/images/doc/4/9/49b1b70-kruize-2.jpg">
<div class="container">
<h2 class="text-center"><fmt:message key="main.text.description1"/></h2>
<div class="row text-center">
    <h4><fmt:message key="main.text.description2"/></h4>
</div>
    <div class="row">
    <h5><fmt:message key="main.text.description3"/></h5>
    </div>
    <h6><fmt:message key="main.text.description4"/></h6>
    <h6><fmt:message key="main.text.description5"/></h6>
    <h6><fmt:message key="main.text.description6"/></h6>
    <h6><fmt:message key="main.text.description7"/></h6>
    <div class="row">
        <h5><fmt:message key="main.text.description8"/></h5>
    </div>
    <div class="row">
        <h5><fmt:message key="main.text.description9"/></h5>
    </div>


</div>


</body>
</html>