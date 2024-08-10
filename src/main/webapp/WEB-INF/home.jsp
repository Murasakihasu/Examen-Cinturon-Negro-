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
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="header">
		<h2>Welcome Back, ${user.name}.</h2>
		<a href="/logout">Log out</a>
	</div>
	<div>
		<h3>Your Tables</h3>
		<table class="table table-striped mt-4">
	   		<thead>
	        	<tr>
	            	<th>Guest Name</th>
	            	<th># Guests</th>
	            	<th>Arrived at</th>
	            	<th>Actions</th>
	        	</tr>
	    	</thead>
	    	<tbody>
	        	<c:forEach items="${user.tablas}" var="tabla">
		        	<tr>
		        		<%--<td>
		        		<c:set var="encontrado" value="false" />
			        	<c:forEach items="${baby.users}" var="users">
			        		<c:if test="${users.name eq user.name}">
			        			<p class="text-danger"> You voted! </p>
				        		<c:set var="encontrado" value="true" />
			        		</c:if>
			        	</c:forEach>
			        	<c:if test="${not encontrado}">
			        		<form action="/baby/${baby.id}/vote" method="GET">
					        	<button class="btn btn-success" type="submit">upvote!</button>
				        	</form>
			        	</c:if>
		        		</td> --%>
		        		<td>${tabla.guestName}</td>
		        		<td>${tabla.guests}</td>
		            	<td>${tabla.createdAt}</td>
		            	<td>
		            		<a class="mx-4" href="/delete/${tabla.id}">Finished</a>
		            		<a class="mx-4" href="/edittabla/${tabla.id}">Edit</a>
		            		<a class="mx-4" href="/delete/${tabla.id}/user">Give up Table</a>
		            	</td>
		        	</tr>
		        </c:forEach>
	    	</tbody>
		</table>
		<div class="mt-4"><a href="/tablas">see Other Tables</a></div>
		<div class="mt-4"><a href="/newtabla" class="btn btn-success">new table</a></div>	
	</div>
</div>
</body>
</html>