<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="ie.gmit.sw.dsConsumer.Controller"%>
     <%@page import="ie.gmit.sw.dsModels.Booking"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit booking</title>
</head>
<body>
<%
Controller controller = new Controller();
 int id = Integer.parseInt(request.getParameter("id"));
Booking booking = controller.getBookingId(id);
%>

<h1>Edit booking</h1>

<p>
<%=booking.getBookingId() %>
</p>
<br>

<p>
<%=booking.getStartDate() %>
</p>

<p>
<%=booking.getEndDate() %>
</p>


</body>
</html>