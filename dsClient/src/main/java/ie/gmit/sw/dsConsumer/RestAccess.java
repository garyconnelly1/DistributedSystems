package ie.gmit.sw.dsConsumer;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import ie.gmit.sw.dsMarshal.BookingMarshal;
import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.dsModels.Bookings;

public class RestAccess extends BookingMarshal {

	private String base = "http://localhost:8080/dsRESTfulServiceProject/webapi/bookings";
	// 1. Create a client.
	private Client client = ClientBuilder.newClient(); // Factory class.

	private // 2. Set a target for the client.
	WebTarget target = client.target(base);

	public List<Booking> getBookings() {

		// 3. Get response from web service.

		String xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		
		Bookings bookings = getBookingsFromXML(xmlResponse);
		
		List<Booking> bookingList;
		
		bookingList = bookings.getBooking();

		return bookingList;

	}

}
