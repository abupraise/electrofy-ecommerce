<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 12/03/2024
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" dir="ltr">
<head>
    <title>Login to Continue</title>
    <link rel="stylesheet" href="css/styles.css">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
</head>
<body>
<%-- Display signup error message if it exists --%>
<% if (request.getAttribute("signupError") != null) { %>
<p class="error-message"><%= request.getAttribute("signupError") %></p>
<% } %>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form action="signup" method="post">
            <h1>Create Account</h1>
            <input type="text" name="firstName" placeholder="First Name">
            <input type="text" name="lastName" placeholder="Last Name" />
            <input type="email" name="email" placeholder="Email" />
            <input type="password" name="password" id="password" placeholder="Password" />
            <input type="password" id="confirmPassword" placeholder="Confirm Password" />
            <button>Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="login-servlet" method="post">
            <h1>Log in</h1>
            <div class="social-container">
                <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
            </div>
            <span>or use your account</span>
            <input type="email" name="email" placeholder="Email" />
            <input type="password" name="password" placeholder="Password" />
            <a href="#">Forgot your password?</a>
            <button>Log In</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>To stay connected with us please log in with your personal info</p>
                <button class="ghost" id="signIn">Log In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
                <p>Enter your personal details and make purchases with us.</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>
<%--<script src="js/script.js"></script>--%>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const container = document.getElementById('container');
        const signUpForm = document.querySelector('.sign-up-container form'); // Define signUpForm
        const path = window.location.pathname.split("/").pop();

        if (path === 'signup.jsp') {
            container.classList.add("right-panel-active");
        } else if (path === 'login.jsp') {
            container.classList.remove("right-panel-active");
        }

        const signUpButton = document.getElementById('signUp');
        const signInButton = document.getElementById('signIn');

        signUpButton.addEventListener('click', () => {
            container.classList.add("right-panel-active");
        });

        signInButton.addEventListener('click', () => {
            container.classList.remove("right-panel-active");
        });

        // Confirm password functionality
        signUpForm.addEventListener('submit', function(e) {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;

            if (password !== confirmPassword) {
                e.preventDefault();
                alert('Your Passwords do not match. Please try again.');
            }
        });
    });
</script>
</body>
</html>
