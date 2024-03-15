<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/12/24
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" dir="ltr">
<head>
    <title>ADMIN</title>
    <link rel="stylesheet" href="css/styles.css">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
</head>
<body>
<h2>Admin Log in || Sign up Form</h2>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form action="admin-signup" method="post">
            <h1>Create Account</h1>
            <input type="text" name="fullName" placeholder="Full Name">
            <input type="email" name="email" placeholder="Email" />
            <input type="password" name="password" id="password" placeholder="Password" />
            <input type="password" id="confirmPassword" placeholder="Confirm Password" />
            <input type="password" name="adminToken" placeholder="Admin Token" />
            <button>Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="admin-servlet" method="post">
            <h1>Log in</h1>
            <div class="social-container">
                <a href="#" class="social"><i class="fas fa-user"></i></a>
            </div>
            <span>enter your details correctly</span>
            <input type="email" name="email" placeholder="Email" />
            <input type="password" name="adminToken" placeholder="Admin Token" />
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
                <h1>Hello, Admin!</h1>
                <p>Enter your personal details and don't forget your token.</p>
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
