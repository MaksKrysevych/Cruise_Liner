<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>
<html lang="${language}">
<head>
    <title>Sign up</title>
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
                <li class="nav-item"><a href="/Cruise_Liner/signup" class="nav-link active"><fmt:message key="main.button.signup"/></a></li>
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
<div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="text-center"><fmt:message key="signup.label.signup"/></h1>
            <form action="${Path.SIGNUP_PATH}" method="post">
                <div class="messages"></div>
                <div class="controls">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="form_login"><fmt:message key="signup.label.login"/></label>
                                <input id="form_login" type="text" name="login" class="form-control" placeholder="<fmt:message key="signup.placeholder.login"/>" required="required" data-error="Login is required.">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="form_password"><fmt:message key="signup.label.password"/></label>
                                <input id="form_password" type="text" name="password" class="form-control" placeholder="<fmt:message key="signup.placeholder.password"/>" required="required" data-error="Password is required.">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="form_name"><fmt:message key="signup.label.firstname"/></label>
                                <input id="form_name" type="text" name="name" class="form-control" placeholder="<fmt:message key="signup.placeholder.firstname"/>" required="required" data-error="Firstname is required.">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="form_lastname"><fmt:message key="signup.label.lastname"/></label>
                                <input id="form_lastname" type="text" name="surname" class="form-control" placeholder="<fmt:message key="signup.placeholder.lastname"/>" required="required" data-error="Lastname is required.">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="form_email"><fmt:message key="signup.label.email"/></label>
                                <input id="form_email" type="email" name="email" class="form-control" placeholder="<fmt:message key="signup.placeholder.email"/>" required="required" data-error="Valid email is required.">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="form_phone"><fmt:message key="signup.label.phone"/></label>
                                <input id="form_phone" type="tel" name="phone" class="form-control" placeholder="">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-md-12">
                            <div class="form-group">
                                <!-- Replace data-sitekey with your own one, generated at https://www.google.com/recaptcha/admin -->
                                <div class="g-recaptcha" data-sitekey="6LfKURIUAAAAAO50vlwWZkyK_G2ywqE52NU7YO0S"></div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <input type="submit" value="<fmt:message key="signup.button.submit"/>">
                        </div>

                    </div>

                </div>

            </form>

        </div><!-- /.col-lg-8 col-lg-offset-2 -->


</div> <!-- /.container-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="https://bootstraptema.ru/snippets/form/2017/recaptcha/validator.js"></script>
<script src="https://bootstraptema.ru/snippets/form/2017/recaptcha/contact.js"></script>
</body>
</html>
