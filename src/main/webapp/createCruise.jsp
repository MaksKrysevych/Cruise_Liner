<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<html lang="${language}">
<head>
    <title>Create Cruise</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <header class="d-flex justify-content-center py-3">
        <div class="navbar navbar-header">
            <h5>|Cruise|</h5>
        </div>
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="/Cruise_Liner" class="nav-link" aria-current="page"><fmt:message key="main.button.home"/></a></li>
            <li class="nav-item"><a href="/Cruise_Liner/contacts.jsp" class="nav-link"><fmt:message key="main.button.contacts"/></a></li>
            <li class="nav-item"><a href="/Cruise_Liner/catalog" class="nav-link"><fmt:message key="main.button.catalog"/></a></li>
            <c:if test = "${sessionScope.user != null}">
                <li class="nav-item"><a href="/Cruise_Liner/profile" class="nav-link active"><fmt:message key="main.button.profile"/></a></li>
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
    </header>
    <div class="container">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="/Cruise_Liner/profile" class="nav-link" aria-current="page"><fmt:message key="profile.button.myProfile"/></a></li>
            <li class="nav-item"><a href="/Cruise_Liner/requests" class="nav-link"><fmt:message key="profile.button.requests"/></a></li>
            <li class="nav-item"><a href="/Cruise_Liner/cruises" class="nav-link"><fmt:message key="profile.button.requests"/></a></li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="row">
        <form action="${Path.CREATE_CRUISE}" method="post">
            <label><fmt:message key="cruises.label.name"/></label>
            <input type="text" class="form-control" id="name" name="name" required value="" default=""/>
            <label><fmt:message key="cruises.label.regions"/></label>
            <input type="text" class="form-control" id="regions" name="regions" required value="" default=""/>
            <label><fmt:message key="cruises.label.liner"/></label>
            <input type="text" class="form-control" id="liner" name="liner" required value="" default=""/>
            <label><fmt:message key="cruises.label.startDay"/></label>
            <input type="date" class="form-control" id="startDay" name="startDay" required value="" default=""/>
            <label><fmt:message key="cruises.label.finishDay"/></label>
            <input type="date" class="form-control" id="finishDay" name="finishDay" required value="" default=""/>
            <label><fmt:message key="cruises.label.fromPort"/></label>
            <input type="text" class="form-control" id="fromPort" name="fromPort" required value="" default=""/>
            <label><fmt:message key="cruises.label.toPort"/></label>
            <input type="text" class="form-control" id="toPort" name="toPort" required value="" default=""/>
            <label><fmt:message key="cruises.label.days"/></label>
            <input type="text" class="form-control" id="days" name="days" required value="" default=""/>
            <label><fmt:message key="cruises.label.description"/></label>
            <input type="text" class="form-control" id="description" name="description" required value="" default=""/>
            <button class="w-100 btn btn-lg btn-primary" type="submit"><fmt:message key="book.button.submit"/></button>
        </form>
    </div>
</div>
</body>
</html>
