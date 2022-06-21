<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<html lang="${language}">
<head>
    <title>Catalog</title>
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

<main>
    <div class="container">
        <div class="container">
        <form method="get" action="${Path.CATALOG_PATH}">
            <input type="hidden" name="currentPage" value="1">
            <div class="form-group col-md-4">
                <label for="records"><fmt:message key="catalog.label.records"/>:</label>
                <select class="form-control" id="records" name="recordsPerPage" >
                    <option value="2" selected></option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="5">5</option>
                </select>
                <label for="sorting"><fmt:message key="catalog.label.sorting"/>:</label>
                <select class="form-control" id="sorting" name="sorting">
                    <option hidden value="standard" selected></option>
                    <option value="byDays"><fmt:message key="catalog.button.byDays"/></option>
                    <option value="byDate"><fmt:message key="catalog.button.byDate"/></option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="catalog.button.select"/></button>
        </form>
        </div>
        <div class="row d-flex h-75 justify-content-center align-items-center p-0">
            <div class="col-lg-3 col-mb-6 mb-4">
                <div class="row">
                    <c:forEach items="${cruises}" var="cruise">
                    <div class="view overlay">
                        <img class ="row" src="https://travel-tours.com.ua/wp-content/uploads/2016/02/kruizi.jpg">
                        <a href="">
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="row ">
                            <h5><fmt:message key="catalog.label.name"/>: <c:out value="${cruise.name}"/><br/></h5>
                        <h5>
                            <strong>
                                <h5><fmt:message key="catalog.label.regions"/>: <c:out value="${cruise.regions}"/></h5>
                                <h5><fmt:message key="catalog.label.liner"/>: <c:out value="${cruise.liner}"/></h5>
                                <h5><fmt:message key="catalog.label.date"/>: <c:out value="${cruise.start_day}"/> - <c:out value="${cruise.finish_day}"/><br/></h5>
                                <h5><fmt:message key="catalog.label.days"/>: <c:out value="${cruise.days}"/></h5>
                                <h5><fmt:message key="catalog.label.ports"/>: <c:out value="${cruise.from_port}"/> - <c:out value="${cruise.to_port}"/></h5>
                            </strong>
                        </h5>
                        <div>
                            <h4 class="front-weight-bold blue-text">
                                <a href="/Cruise_Liner/details?cruise=${cruise.name}">
                                    <strong><fmt:message key="catalog.button.details"/></strong>
                                </a>
                            </h4>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <nav aria-label="Navigation for cruises">
                    <ul class="pagination">
                        <c:if test="${currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link"href="catalog?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&sorting=${sorting}"><fmt:message key="catalog.button.previous"/></a>
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
                                        <a class="page-link"href="catalog?recordsPerPage=${recordsPerPage}&currentPage=${i}&sorting=${sorting}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item">
                                <a class="page-link"href="catalog?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&sorting=${sorting}"><fmt:message key="catalog.button.next"/></a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</main>
</body>
</html>
