<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ie.gmit.sw.dsConsumer.Controller"%>
<%@page import="ie.gmit.sw.dsModels.Booking"%>
<%@page import="ie.gmit.sw.dsModels.Customer"%>
<%@page import="ie.gmit.sw.dsModels.Vehicle"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking Updated</title>
</head>
<body>
<%
Controller controller = new Controller();
 int bookingId = Integer.parseInt(request.getParameter("booking_id"));
 int customerId = Integer.parseInt(request.getParameter("customer_id"));
 int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
 String startDate = (request.getParameter("start_date"));
 String endDate = (request.getParameter("end_date"));


Booking booking = new Booking();
Customer customer = new Customer();
Vehicle vehicle = new Vehicle();

customer.setCustomerId(customerId);
vehicle.setId(vehicleId);

booking.setBookingId(bookingId);
booking.setCustomer(customer);
booking.setVehicle(vehicle);
booking.setStartDate(startDate);
booking.setEndDate(endDate);

controller.updateBooking(booking);
%>

	<h1>Booking has been updated!</h1>
	
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<p></p>

</body>
</html>