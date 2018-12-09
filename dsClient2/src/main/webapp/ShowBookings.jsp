<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ie.gmit.sw.dsConsumer.Controller"%>
<%@page import="ie.gmit.sw.dsModels.Booking"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Bookings</title>
</head>
<body>

	<%
		Controller controller = new Controller();
	
	
		List<Booking> bookings = controller.getBookings();
	%>
	<h2>Show All Bookings</h2>



	<br>

	<table class="table table-dark">
		<thead class="thead-dark">
			<tr>
				<th>ID</th>
				<th>Customer_ID</th>
				<th>Vehicle_ID</th>
				<th>Start_Date</th>
				<th>End_Date</th>
				<th>Edit</th>

			</tr>
		</thead>

		<tbody>
			<c:forEach items="<%=bookings %>" var="booking">
				<tr>
					<td id="bookingid">${booking.getBookingId()}</td>
					<td>${booking.getCustomer().getCustomerId()}</td>
					<td>${booking.getVehicle().getId()}</td>
					<td>${booking.getStartDate()}</td>
					<td>${booking.getEndDate()}</td>
					<td><form method="post" action="EditBooking.jsp">
							<input type="text" name="id" value="${booking.getBookingId()}">
							<input type="submit" class="btn btn-primary" value="Edit">
						</form></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>


</body>
</html>