package ie.gmit.sw.dsConsumer;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import ie.gmit.sw.dsMarshal.BookingMarshal;
import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.dsModels.Bookings;



public class Consumer extends BookingMarshal{

	public static void main(String[] args) {
		
		System.out.println("Consumer");
		
		// 1. Create a client.
		Client client = ClientBuilder.newClient(); // Factory class.
		
		System.out.println("Loading ... ");
		
		// 2. Set a target for the client.
		WebTarget target = client.target("http://localhost:8080/dsRESTfulServiceProject/webapi/bookings");
		
		
		
		// 3. Get response from web service.
		
		String xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		
		
		System.out.println(xmlResponse);
		
		Bookings bookings = getBookingsFromXML(xmlResponse);
		//Booking booking = getBookingFromXML(xmlResponse);
		
		List<Booking> bookingList;// = new ArrayList<Booking>();
		
		bookingList = bookings.getBooking();
		
		for(Booking b: bookingList) {
			System.out.println("=====================================================");
			System.out.println(b.getBookingId());
		}
		
		System.out.println("bookings");
	//	System.out.println(booking.getBookingId());
		
	//	System.out.println(
		//		target.request(MediaType.APPLICATION_XML).get(String.class)
		//		);

	}

}
