package ie.gmit.sw.dsConsumer;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.gmit.sw.dsMarshal.BookingMarshal;
import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.dsModels.Bookings;

public class RestAccess extends BookingMarshal { // This is the class that is responsible for interacting with the REST API. 

	private String base = "http://localhost:8080/dsRESTfulServiceProject/webapi/bookings"; // The base URI for the api.
	
	private Client client = ClientBuilder.newClient(); // Factory class to create a client object.

	private WebTarget target = client.target(base); // Set a target for the client.
	
	private String xmlResponse; // Set an xml response variable.
	
	private String send; // Set a variable that will be used to send xml objects over the database.
	
	Response response; // Set a response object.

	public List<Booking> getBookings() { // Get all the bookings from the api.

		
		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class); // Send the GET request to the api.
		
		Bookings bookings = getBookingsFromXML(xmlResponse); // Marshal the xml response into a Bookings object.
		
		List<Booking> bookingList; // Set a list so we can access the individual Booking objects.
		
		bookingList = bookings.getBooking(); // Initialize the list to the bookings object.

		return bookingList; // Return the list of Booking objects.

	}
	
	public Booking getBookingById(int id) { // Get a single booking based on its id.
		target = client.target(base + "/" + id); // Add the id parameter to the base URI.
		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class); // Send the GET request to the api.
		Booking newBooking = getBookingFromXML(xmlResponse); // Marshal the response into a Booking object.
		return newBooking; // Return that booking object.
		
	}
	
	public void updateBooking(Booking booking) { // Put(update) a booking object. 
		target = client.target(base + "/" + booking.getBookingId()); // Use the booking objects id to appent to the base URI.
		
		send = getBookingAsXML(booking); // Convert the updated booking object to xml.
		
		response = target.request().put(Entity.xml(send)); // Send the PUT request to the api.
		
		System.out.println(response); // Read the response.
		
	}
	
	public void createBooking(Booking booking) { // Post(create) a new booking object.
		target = client.target(base); // reset the target to the base URI.
		
		send = getBookingAsXML(booking); // Convert the new booking object to xml.
		
		response = target.request().post(Entity.xml(send)); // Send the POST request to the api.
		
		System.out.println(response); // Read the response.
	}
	
	public void deleteBooking(int id) { // Delete an existing booking object.
		target = client.target(base + "/" + id); // Append the id the base URI.
		
		response = target.request().delete(); // Send the DELETE request to the api.
		
		System.out.println(response); // Read the response.
		
	}

}
