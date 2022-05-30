<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
                <li class="nav-item"><a href="/Cruise_Liner" class="nav-link " aria-current="page">Home</a></li>
                <li class="nav-item"><a href="/Cruise_Liner/contacts.jsp" class="nav-link">Contacts</a></li>
                <li class="nav-item"><a href="/Cruise_Liner/catalog" class="nav-link active">Catalog</a></li>
                <c:if test = "${sessionScope.user != null}">
                    <li class="nav-item"><a href="/Cruise_Liner/profile" class="nav-link">Profile</a></li>
                    <li class="nav-item"><a href="/Cruise_Liner/logout" class="nav-link">Logout</a></li>
                </c:if>
                <c:if test = "${sessionScope.user == null}">
                    <li class="nav-item"><a href="/Cruise_Liner/login" class="nav-link">Login</a></li>
                    <li class="nav-item"><a href="/Cruise_Liner/signup" class="nav-link">Sign-Up</a></li>
                </c:if>
            </ul>
        </div>
    </header>
</div>

<main>
    <div class="container">
        <div class="row d-flex h-75 justify-content-center align-items-center p-0">

            <div class="col-lg-3 col-mb-6 mb-4">
                <div class="card">
                    <c:forEach items="${cruises}" var="cruise">
                    <div class="view overlay">
                        <img class ="card-img-top" src="https://travel-tours.com.ua/wp-content/uploads/2016/02/kruizi.jpg">
                        <a href="">
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body ">
                            <h5>Name: <c:out value="${cruise.name}"/><br/></h5>
                        <h5>
                            <strong>
                                <h5>Regions: <c:out value="${cruise.regions}"/></h5>
                                <h5>Liner: <c:out value="${cruise.liner}"/></h5>
                                <h5>Data: <c:out value="${cruise.start_day}"/> - <c:out value="${cruise.finish_day}"/><br/></h5>
                                <h5>Days: <c:out value="${cruise.days}"/></h5>
                                <h5>Ports: <c:out value="${cruise.from_port}"/> - <c:out value="${cruise.to_port}"/></h5>
                            </strong>
                        </h5>
                        <c:if test = "${sessionScope.user != null}">
                        <h4 class="front-weight-bold blue-text">
                            <a href="/Cruise_Liner/book">
                            <strong>Book</strong>
                            </a>
                        </h4>
                        </c:if>
                    </div>
                    </c:forEach>
                </div>
            </div>



        </div>

    </div>
</main>

</body>
</html>
