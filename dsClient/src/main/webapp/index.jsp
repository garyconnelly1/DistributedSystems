<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="ie.gmit.sw.dsConsumer.Controller"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
  <!--  -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
Controller controller= new Controller();//Creating class Object
String hello = controller.getHello();//Calling add Method

int a[]=new int[5];//declaration and instantiation  
a[0]=10;//initialization  
a[1]=20;  
a[2]=70;  
a[3]=40;  
a[4]=50;  
%>
<style>
table, th, td {
	border: 1px solid black;
}
</style>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking System</title>
</head>
<body>
    <h2>lllllllllllllllllllllllJava Client Web Application!!!!!!!</h2>
    <h4><%=hello%></h4>
    
    <c:forEach items="<%=a%>" var="a">
				<h5>a</h5>
			</c:forEach>
    <!-- <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!  -->
    
    <form action = "ShowBookings.jsp">
     <button type="submit" value="submit">Go to next page</button>
    </form>
    
     <p>Visit <a href="ShowBookings.jsp">Project Jersey website</a>
    
    
</body>
</html>
