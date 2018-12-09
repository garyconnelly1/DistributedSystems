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

public class RestAccess extends BookingMarshal {

	private String base = "http://localhost:8080/dsRESTfulServiceProject/webapi/bookings";
	// 1. Create a client.
	private Client client = ClientBuilder.newClient(); // Factory class.

	private // 2. Set a target for the client.
	WebTarget target = client.target(base);
	
	private String xmlResponse;
	
	private String send;
	
	Response response;

	public List<Booking> getBookings() {

		// 3. Get response from web service.

		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		
		Bookings bookings = getBookingsFromXML(xmlResponse);
		
		List<Booking> bookingList;
		
		bookingList = bookings.getBooking();

		return bookingList;

	}
	
	public Booking getBookingById(int id) {
		target = client.target(base + "/" + id);
		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		Booking newBooking = getBookingFromXML(xmlResponse);
		return newBooking;
		
	}
	
	public void updateBooking(Booking booking) {
		target = client.target(base + "/" + booking.getBookingId());
		
		send = getBookingAsXML(booking); // Convert the updated booking object to xml.
		
		response = target.request().put(Entity.xml(send));
		
		System.out.println(response); // Read the response.
		
	}
	
	public void createBooking(Booking booking) {
		target = client.target(base);
		
		send = getBookingAsXML(booking); // Convert the updated booking object to xml.
		
		response = target.request().post(Entity.xml(send));
		
		System.out.println(response); // Read the response.
		
	}

}
