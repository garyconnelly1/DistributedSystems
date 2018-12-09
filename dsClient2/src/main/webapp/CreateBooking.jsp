<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Booking</title>
</head>
<body>

<h1>Create a new Booking</h1>

<div class="form-group">
	<form method="post" action="BookingCreated.jsp">
		Booking ID:<br>
		<input type="text" class="form-control" name="booking_id" value="00"><br>
		Customer ID:<br>
		<input type="text" class="form-control" name="customer_id" value="00"><br>
		Vehicle ID:<br>
		<input type="text" class="form-control" name="vehicle_id" value="00"><br>
		Start Date:<br>
		<input type="text" class="form-control" name="start_date" value="00-00-00"><br>
		End Date:<br>
		<input type="text" class="form-control" name="end_date" value="00-00-00"><br>
		
		<input type="submit" class="btn btn-primary" value="Create">
	</form>
	
	</div>

</body>
</html>