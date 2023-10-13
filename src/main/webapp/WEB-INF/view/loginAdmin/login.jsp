<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<section class="myform-area">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="form-area login-form">
                    <div class="form-content">
                        <h2>Login</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nulla non aperiam cum quas quod reprehenderit.</p>
                        <ul>
                            <li><a href="#" class="facebook"><i class="fa fa-facebook-f"></i><span>facebook</span></a></li>
                            <li><a href="#" class="twitter"><i class="fa fa-twitter"></i><span>twitter</span></a></li>
                        </ul>
                    </div>
                    <div class="form-input">
                        <h2>Login Form</h2>
                        <form action="${pageContext.request.contextPath}/login" method="post">
                            <div class="form-group">
                                <input type="text"  id="username" name="username" required>
                                <label>User Name</label>
                            </div>
                            <div class="form-group">
                                <input type="password" id="password" name="password" required>
                                <label>password</label>
                            </div>
                            <div class="myform-button">
                                <button class="myform-btn">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>