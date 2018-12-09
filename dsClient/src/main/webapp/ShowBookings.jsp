<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--  -->
<%@page import="ie.gmit.sw.dsConsumer.Controller"%>
<%@page import="java.util.List"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Bookings</title>
</head>
<body>
	<%
		Controller controller= new Controller(); //Creating class Object
		String hello = controller.sayHello(); //Calling sayHello Method
	%>


	<h1>Show Bookings view</h1>

	<table>
		<tr>
			<th>ID</th>

		</tr>

		<tr>
			<td>hello</td>
		</tr>

	</table>

	
</body>
</html>