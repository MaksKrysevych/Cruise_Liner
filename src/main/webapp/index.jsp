<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
                <li class="nav-item"><a href="/Cruise_Liner" class="nav-link active" aria-current="page">Home</a></li>
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
        </div>
    </header>
</div>

<img src="https://life.pravda.com.ua/images/doc/4/9/49b1b70-kruize-2.jpg" class="image" alt="https://life.pravda.com.ua/images/doc/4/9/49b1b70-kruize-2.jpg">
<div class="container">
<h2 class="text-center">Welcome to Ukrainian Cruise!</h2>
<div class="row text-center">
    <h4>It is our new cruise!</h4>
</div>
    <div class="row">
    <h5>We suggest:</h5>
    </div>
    <h6>*unique routes</h6>
    <h6>*our local cuisine</h6>
    <h6>*pleasant staff</h6>
    <h6>*interesting events</h6>
    <div class="row">
        <h5>And lots of another things, that we can suggest you!</h5>
    </div>
    <div class="row">
        <h5>Don't waste your time! Sign up and book suitable for you cruise!</h5>
    </div>


</div>


</body>
</html>