package ie.gmit.sw.dsConsumer;

import java.util.List;

import ie.gmit.sw.dsModels.Booking;

public class Controller { 
	/*
	 * This class uses the facade object oriented programming design principle to abstract complicated code from the consumer.
	 * This class is the middle layer between the RestAccess and the view.
	 * All it is responsible for is calling and returning methods to and from the RestAccess and the jsp view.
	 * Provides smoother interaction between the view and data access. 
	 */

	public Controller() {

	}

	RestAccess access = new RestAccess(); // Get a handle on the RestAccess object.

	private List<Booking> bookingList; // Set a list of Booking objects.

	public List<Booking> getBookings() { // Method for returning all Booking objects on the database.
		bookingList = access.getBookings(); // Call the getBookings() method in the access object.
		return bookingList; // Return that list of bookings.
	}

	public Booking getBookingId(int id) { // Method for returning a single book to the view based on its id.
		Booking booking = access.getBookingById(id); // Call the getBookingById(id) method in the access object.
		return booking; // Return that booking object.
	}
	
	public void updateBooking(Booking booking) { // Method for updating a booking object on the server.
		access.updateBooking(booking); // Call the updateBooking(booking) method in the access object.
	}
	
	public void createBooking(Booking booking) { // Method for creating a new booking object on the server.
		access.createBooking(booking); // Call the createBooking(booking) method in the access object.
	}
	
	public void Delete(int id) { // Method for deleting a booking object on the server.
		access.deleteBooking(id); // Call the deleteBooking(id) method in the access object.
	}

}
