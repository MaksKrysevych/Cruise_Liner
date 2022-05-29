<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
            <li class="nav-item"><a href="/Cruise_Liner" class="nav-link" aria-current="page">Home</a></li>
            <li class="nav-item"><a href="/Cruise_Liner/contacts.jsp" class="nav-link">Contacts</a></li>
            <li class="nav-item"><a href="/Cruise_Liner/catalog" class="nav-link">Catalog</a></li>
            <c:if test = "${sessionScope.user != null}">
                <li class="nav-item"><a href="/Cruise_Liner/profile" class="nav-link">Profile</a></li>
                <li class="nav-item"><a href="/Cruise_Liner/logout" class="nav-link">Logout</a></li>
            </c:if>
            <c:if test = "${sessionScope.user == null}">
                <li class="nav-item"><a href="/Cruise_Liner/login" class="nav-link">Login</a></li>
                <li class="nav-item"><a href="/Cruise_Liner/signup" class="nav-link">Sign-Up</a></li>
            </c:if>
        </ul>
    </header>
</div>
<div class="container-fluid d-flex h-75 justify-content-center align-items-center p-5">

    <div class="col-lg-8 col-lg-offset-2">

        <form action="${Path.SIGNUP_PATH}" method="post">
                    <div class="col-md-6">
                        <h1 class="text-center">Book</h1>

                        <div class="form-group">
                            <label for="login">Login</label>
                            <input id="login" type="text" name="login" class="form-control" placeholder="Please enter your login" required="required" data-error="Login is required.">
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="cruise">Cruise name</label>
                            <input id="cruise" type="text" name="cruise" class="form-control" placeholder="Please enter cruise name" required="required" data-error="Cruise name is required.">
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
                            <label for="people">Count people</label>
                            <input id="people" type="text" name="people" class="form-control" placeholder="Please enter your count people" required="required" data-error="Count people is required.">
                            <div class="help-block with-errors"></div>
                        </div>
                        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>

                    </div>


        </form>

    </div><!-- /.col-lg-8 col-lg-offset-2 -->


</div> <!-- /.container-->
</body>
</html>
