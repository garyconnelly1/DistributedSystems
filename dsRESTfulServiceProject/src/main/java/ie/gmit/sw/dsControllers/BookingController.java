package ie.gmit.sw.dsControllers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.ds.dsRMI_DataAccess.IBookingService;
//import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.ds.dsRMI_DataAccess.ReturnedBooking;

public class BookingController { // This is an RMI client that will be used to access the remote object and pass data to the RESTful resource.
	IBookingService bookingService;

	public BookingController() {
		// Get a handle on the remote object.
		try {
			this.bookingService = (IBookingService) Naming.lookup("rmi://127.0.0.1:1099/bookingService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	public List<ReturnedBooking> getAllBookings() { // Method to get all bookings from database and pass them straight to the RESTful resource.
		
		List<ReturnedBooking> bookings = new ArrayList<ReturnedBooking>(); // Initialize a list to hold the booking objects.
		
		try {
			bookings = bookingService.readBookings(); // Try to invoke the remote method.
			System.out.println("=======================================");
		} catch (RemoteException e) {

			System.out.println("error accessing data from remote object"); // Catch any exceptions.
			e.printStackTrace();
		}

		return bookings;
	}

	public ReturnedBooking getBookingById(int id) { // Method to return a booking accessed by its ID.
		List<ReturnedBooking> bookings = new ArrayList<ReturnedBooking>();
		ReturnedBooking resultBooking = null;

		try {
			bookings = bookingService.readBookings(); // Call the same remote method as the getAllBookings method.
		} catch (RemoteException e) {
			System.out.println("error accessing data from remote object"); // Catch any exceptions.
			e.printStackTrace();
		}

		for (ReturnedBooking b : bookings) { // For loop to select the booking object based on the id passed into the method.
			if (b.getBookingId() == id) {
				resultBooking = b;
			}

		}
		
		if(resultBooking != null) { // If the object is in the database.
			return resultBooking; // Return that object.
		}
		else { // If not.
			return null; // Return null.
		}
	}

	public void createBooking(ReturnedBooking booking) { // Method to create a new booking in the database.
		
		
		String query = "Insert INTO bookings VALUES(" + booking.getBookingId() + "," + booking.getVehicleId() + ","
				+ booking.getCustomerId() + ",\"" + booking.getStartDate() + "\",\"" + booking.getEndDate() + "\")"; // Construct the sql query to create a database record.
		
		SQLParser parser = new SQLParser();
		query = parser.parseSQL(query);
		
		try {
			bookingService.createBooking(query); // Invoke the remote method.
		} catch (RemoteException e) {
			System.out.println("error on sql query in booking controller"); // Catch any exceptions.
			e.printStackTrace();
		}
	}

	public void updateBooking(ReturnedBooking booking) { // Method for updating a booking in the database.

		String query = "UPDATE bookings SET vehicle_id =" + booking.getVehicleId() + ", " + "customer_id ="
				+ booking.getCustomerId() + ", " + "start_date =\"" + booking.getStartDate() + "\", " + "end_date =\""
				+ booking.getEndDate() + "\" WHERE booking_id=" + booking.getBookingId() + ";"; // Construct the sql query to update a database record.

		SQLParser parser = new SQLParser();
		query = parser.parseSQL(query);
		
		
		try {
			bookingService.updateBooking(query); // Invoke the remote method.
		} catch (RemoteException e) {
			System.out.println("error updating booking in Booking controller"); // Catch any exceptions.
			e.printStackTrace();
		}

	}
	
	public void deleteBooking(int id) { // Method for deleting a booking from the database.
		String query = "DELETE FROM bookings WHERE booking_id =" + id + ";"; // Construct the sql query to delete a database record
		
		SQLParser parser = new SQLParser();
		query = parser.parseSQL(query);
		
		try {
			bookingService.deleteBooking(query); // Invoke the remote method.
		} catch (RemoteException e) {
			System.out.println("error deleting booking in Booking controller"); // Catch any exceptions.
			e.printStackTrace();
		}
		
	}

}
