<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<html lang="${language}">
<head>
    <title>Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <header class="d-flex justify-content-center py-3">
        <div class="navbar navbar-header">
            <h5>|Cruise|</h5>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="/Cruise_Liner" class="nav-link" aria-current="page"><fmt:message key="main.button.home"/></a></li>
                <li class="nav-item"><a href="/Cruise_Liner/contacts.jsp" class="nav-link"><fmt:message key="main.button.contacts"/></a></li>
                <li class="nav-item"><a href="/Cruise_Liner/catalog" class="nav-link active"><fmt:message key="main.button.catalog"/></a></li>
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
        </div>
    </header>
</div>
<div class="container">
    <div class="form-row">
        <div class="form-group col-md-6">
            <img class ="row" src="https://travel-tours.com.ua/wp-content/uploads/2016/02/kruizi.jpg">
            <h5><fmt:message key="details.label.name"/>: <c:out value="${cruise.name}"/><br/></h5>
            <h5><fmt:message key="details.label.regions"/>: <c:out value="${cruise.regions}"/></h5>
            <h5><fmt:message key="details.label.liner"/>: <c:out value="${cruise.liner}"/></h5>
            <h5><fmt:message key="details.label.date"/>: <c:out value="${cruise.start_day}"/> - <c:out value="${cruise.finish_day}"/><br/></h5>
            <h5><fmt:message key="details.label.days"/>: <c:out value="${cruise.days}"/></h5>
            <h5><fmt:message key="details.label.ports"/>: <c:out value="${cruise.from_port}"/> - <c:out value="${cruise.to_port}"/></h5>
            <h5><fmt:message key="details.label.description"/>: <c:out value="${cruise.description}"/></h5>
            <c:if test = "${sessionScope.user != null}">
                <h4 class="front-weight-bold blue-text">
                    <a href="/Cruise_Liner/book?cruise=${cruise.name}">
                        <strong><fmt:message key="catalog.button.book"/></strong>
                    </a>
                </h4>
            </c:if>
            <c:if test = "${sessionScope.user == null}">
                <h4 class="front-weight-bold blue-text">
                    <a href="/Cruise_Liner/login">
                        <strong><fmt:message key="catalog.button.book"/></strong>
                    </a>
                </h4>
            </c:if>
        </div>
    </div>
    </div>
</div>


</body>
</html>
