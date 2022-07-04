<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<html lang="${language}">
<head>
    <title>Book</title>
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
    </header>
</div>
<div class="container-fluid d-flex h-75 justify-content-center align-items-center p-5">

    <div class="col-lg-8 col-lg-offset-2">

        <form action="${Path.SIGNUP_PATH}" method="post">

            <div class="col-md-6">
                        <h1 class="text-center"><fmt:message key="book.label.book"/></h1>

                        <div class="form-group">
                            <input id="login" type="hidden" name="login" class="form-control" placeholder="Please enter your login" required value="${user.login}">
                            <input type="hidden"  name="cruise" value="${cruise}"/>
                        </div>

                        <div class="form-group">
                            <label for="people"><fmt:message key="book.label.people"/></label>
                            <input id="people" type="text" name="people" class="form-control"  required="required" data-error="Count people is required.">
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="book.label.room"/> </label>
                            <select name="room">
                                <option><fmt:message key="book.label.room.inner"/></option>
                                <option><fmt:message key="book.label.room.withWindow"/></option>
                                <option><fmt:message key="book.label.room.withBalcony"/></option>
                                <option><fmt:message key="book.label.room.luxury"/></option>
                            </select>
                        </div>
                <c:choose>
                    <c:when test="${requestScope.error != null}">
                        <div style="color:red; text-align: center;"><fmt:message key="${requestScope.error}"/></div>
                    </c:when>
                </c:choose>
                        <button class="w-100 btn btn-lg btn-primary" type="submit"><fmt:message key="book.button.submit"/></button>

                    </div>


        </form>

    </div><!-- /.col-lg-8 col-lg-offset-2 -->


</div> <!-- /.container-->
</body>
</html>
