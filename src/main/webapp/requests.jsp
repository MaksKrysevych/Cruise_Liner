<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 29.05.2022
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
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
            <li class="nav-item"><a href="/Cruise_Liner" class="nav-link" aria-current="page">Home</a></li>
            <li class="nav-item"><a href="/Cruise_Liner/contacts.jsp" class="nav-link">Contacts</a></li>
            <li class="nav-item"><a href="/Cruise_Liner/catalog" class="nav-link">Catalog</a></li>
            <c:if test = "${sessionScope.user != null}">
                <li class="nav-item"><a href="/Cruise_Liner/profile" class="nav-link active">Profile</a></li>
                <li class="nav-item"><a href="/Cruise_Liner/logout" class="nav-link">Logout</a></li>
            </c:if>
            <c:if test = "${sessionScope.user == null}">
                <li class="nav-item"><a href="/Cruise_Liner/login" class="nav-link">Login</a></li>
                <li class="nav-item"><a href="/Cruise_Liner/signup" class="nav-link">Sign-Up</a></li>
            </c:if>
        </ul>
    </header>
    <div class="container">

        <ul class="nav nav-pills">
            <li class="nav-item"><a href="/Cruise_Liner/profile" class="nav-link" aria-current="page">My profile</a></li>
            <li class="nav-item"><a href="/Cruise_Liner/requests" class="nav-link">Requests</a></li>
        </ul>
    </div>
</div>
<div class="container">

<table class="table table-bordered">

    <tr>
        <th scope="col">Login</th>
        <th scope="col">Cruise</th>
        <th scope="col">Peoples</th>
        <th scope="col">Data</th>
        <th scope="col">Status</th>
        <th scope="col">Actions</th>

    </tr>

<c:forEach items="${userRequests}" var="userRequest">
    <tr>
        <td><input type="text" readonly required value=<c:out value="${userRequest.login}"/>></td>
        <td><c:out value="${userRequest.cruiseName}"/></td>
        <td><c:out value="${userRequest.countPeople}"/></td>
        <td><c:out value="${userRequest.createTime}"/></td>
        <td><c:out value="${userRequest.status}"/></td>
        <td>
            <form method="post" action="#" id="update">
                <input type="hidden" name="action" value="updateUserRequest">
                <input type="hidden"  name="id" value="${userRequest.login}"/>
                <input type="submit" value="update"/>
            </form>
            <form method="post" action="#" id="delete">
                <input type="hidden" name="delete" value="deleteUserRequest">
                <input type="hidden"  name="id" value="${userRequest.login}"/>
                <input type="submit" value="delete"/>
            </form>

        </td>
    </tr>
</c:forEach>


</table>
</div>

</body>
</html>
