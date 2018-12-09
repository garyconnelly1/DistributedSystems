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

	<h1>
		Edit booking Where ID is
		<%=booking.getBookingId() %></h1>

	<form method="post" action="UpdatedBooking.jsp">
		Booking ID:<br>
		<input type="text" name="booking_id" value="<%=booking.getBookingId() %>"><br>
		Customer ID:<br>
		<input type="text" name="customer_id" value="<%=booking.getCustomer().getCustomerId() %>"><br>
		Vehicle ID:<br>
		<input type="text" name="vehicle_id" value="<%=booking.getVehicle().getId() %>"><br>
		Start Date:<br>
		<input type="text" name="start_date" value="<%=booking.getStartDate() %>"><br>
		End Date:<br>
		<input type="text" name="end_date" value="<%=booking.getEndDate() %>"><br>
		
		<input type="submit" value="Update">
	</form>

	

</body>
</html>