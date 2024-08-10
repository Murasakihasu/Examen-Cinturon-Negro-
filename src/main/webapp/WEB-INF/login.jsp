<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="/css/form.css" rel="stylesheet">
<title>Register and Login</title>
</head>
<body>
	<div class="container1">
		<h1 Style="color: yellow;">Table Master</h1>
	</div>
	<div class="container2">
		<div class="register">
			<h2>Register</h2>
			<form:form action="/register" method="POST" modelAttribute="user">
			   	<div>
			        <form:label class="form-label" path="name">Name:</form:label>
			       	<form:errors class="alert alert-danger" path="name"/>
			        <form:input class="form-control mb-4" path="name" type= "text" name ="name"/>
			   	</div>
			    <div class="mt-4">
			    	<form:label class="form-label" path="email">Email:</form:label>
			     	<form:errors class="alert alert-danger" path="email"/>
			        <form:input class="form-control mb-4" path="email" type= "email" name ="email"/>
			    </div>
			    <div class="mt-4">
			       	<form:label class="form-label" path="password">Password:</form:label>
			        <form:errors class="alert alert-danger" path="password"/>
			        <form:input class="form-control mb-4" path="password" type= "password" name ="password"/>
			   	</div>
			    <div class="mt-4">
					<form:label class="form-label" path="confirmPassword">Confirm Password</form:label>
					<form:errors class="alert alert-danger" path="confirmPassword"/>
					<form:input class="form-control mb-4" path="confirmPassword" type= "password" name ="confirmPassword"/>
				</div>
				<input class="btn btn-primary mt-4" type="submit" value="Register"/>
			</form:form>
		</div>
		<div class="login">
			<h2 class="mt-4">Log in</h2>
			<form:form action="/login" method="POST" modelAttribute="userLogin">
		   		<div class="mt-4">
		        	<form:label class="form-label" path="loginEmail">Email:</form:label>
		        	<form:errors class="alert alert-danger" path="loginEmail"/>
		       		<form:input class="form-control mb-4" path="loginEmail" type= "email" name ="loginEmail"/>
		   		</div>
		   		<div class="mt-4">
		       		<form:label class="form-label" path="loginPassword">Password</form:label>
		       		<form:errors class="alert alert-danger" path="loginPassword"/>
		       		<form:input class="form-control mb-4" path="loginPassword" type= "password" name ="loginPassword"/>
	    		</div>
	    		<input class="btn btn-success mt-4" type="submit" value="Log in"/>
			</form:form>
		</div> 
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>