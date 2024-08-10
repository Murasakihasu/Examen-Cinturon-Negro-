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
<title>New</title>
</head>
<body>
<div class="container">
	<div class="header">
		<h1>New Table</h1>
		<a href="/logout">Log out</a>
	</div>
	<div>
	<form:form action="/newtabla" method="POST" modelAttribute="tabla">
		    <div>
		        <form:label class="form-label" path="guestName">Guest name</form:label>
		        <form:errors class="alert alert-danger" path="guestName"/>
		        <form:input class="form-control mb-4" path="guestName" type= "text" name ="guestName"/>
		    </div>
		    <div>
		        <form:label class="form-label" path="guests">Number of Guests:</form:label>
		        <form:errors class="alert alert-danger" path="guests"/>
		        <form:input class="form-control mb-4" path="guests" type="number" name ="guests" min="1" max="10"/>
		    </div>
		    <div>
		        <form:label class="form-label" path="note">Note</form:label>
		        <form:errors class="alert alert-danger" path="note"/>
		        <form:textarea class="form-control mb-4" path="note" type="text" name="note"></form:textarea>
		    </div>
		    <div class="botones">
		    	<a href="/home/${user}" class="btn btn-danger">Cancel</a>
		    	<input class="btn btn-success" type="submit" value="Submit"/>
		    </div>
		</form:form>
	</div>
</div> 
</body>
</html>