<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<html lang="${language}">
<head>
    <title>Cruises</title>
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
            <c:if test="${user.role == 'ADMIN'}">
                <li class="nav-item"><a href="/Cruise_Liner/cruises" class="nav-link"><fmt:message key="profile.button.cruises"/></a></li>
                <li class="nav-item"><a href="/Cruise_Liner/liners" class="nav-link"><fmt:message key="profile.button.liners"/></a></li>
            </c:if>
        </ul>
    </div>
</div>
<div class="container">
    <form method="get" action="${Path.CRUISES}">
        <input type="hidden" name="currentPage" value="1">
        <div class="form-group col-md-4">
            <label for="records"><fmt:message key="requests.label.records"/></label>
            <select class="form-control" id="records" name="recordsPerPage">
                <option value="2">2</option>
                <option value="5">5</option>
                <option value="10">10</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="cruises.button.submit"/></button>
        <label> <a href="/Cruise_Liner/createCruise"><fmt:message key="cruises.button.createNew"/></a></label>
    </form>
<table class="table table-bordered">
    <tr>
        <th scope="col"><fmt:message key="cruises.label.name"/></th>
        <th scope="col"><fmt:message key="cruises.label.regions"/></th>
        <th scope="col"><fmt:message key="cruises.label.liner"/></th>
        <th scope="col"><fmt:message key="cruises.label.startDay"/></th>
        <th scope="col"><fmt:message key="cruises.label.finishDay"/></th>
        <th scope="col"><fmt:message key="cruises.label.fromPort"/></th>
        <th scope="col"><fmt:message key="cruises.label.toPort"/></th>
        <th scope="col"><fmt:message key="cruises.label.days"/></th>
        <th scope="col"><fmt:message key="cruises.label.description"/></th>
        <th scope="col"><fmt:message key="cruises.label.actions"/></th>
    </tr>
<c:forEach items="${cruises}" var="cruise">
    <tr>
        <td><c:out value="${cruise.name}"/></td>
        <td><c:out value="${cruise.regions}"/></td>
        <td><c:out value="${cruise.liner}"/></td>
        <td><c:out value="${cruise.start_day}"/></td>
        <td><c:out value="${cruise.finish_day}"/></td>
        <td><c:out value="${cruise.from_port}"/></td>
        <td><c:out value="${cruise.to_port}"/></td>
        <td><c:out value="${cruise.days}"/></td>
        <td><c:out value="${cruise.description}"/></td>
        <td>
            <a href="/Cruise_Liner/updateCruise?cruise=${cruise.name}">
                <strong><fmt:message key="cruises.button.update"/></strong>
            </a>
            <form method="post" action="/Cruise_Liner/deleteCruise" id="delete">
                <input type="hidden" name="delete" value="deleteCruise">
                <input type="hidden"  name="cruise" value="${cruise.name}"/>
                <input type="submit" value="<fmt:message key="requests.button.delete"/>"/>
            </form>
        </td>
    </tr>
</c:forEach>
</table>
    <nav aria-label="Navigation for requests">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link"href="cruises?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="catalog.button.previous"/></a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"href="cruises?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item">
                    <a class="page-link"href="cruises?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="catalog.button.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>
