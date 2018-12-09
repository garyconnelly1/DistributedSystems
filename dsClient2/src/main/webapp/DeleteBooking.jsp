<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Booking</title>
</head>
<body>

	<h1>Delete a Booking</h1>

	<div class="form-group">
		<form method="post" action="BookingDeleted.jsp">
			Booking ID:<br> <input type="text" class="form-control"
				name="booking_id"
				value="Enter the ID of the booking you wish to delete."><br>

			<input type="submit" class="btn btn-primary" value="Delete">
		</form>

	</div>

</body>
</html>