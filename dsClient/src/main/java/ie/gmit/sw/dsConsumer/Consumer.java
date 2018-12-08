package ie.gmit.sw.dsConsumer;

import java.util.ArrayList;
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
import ie.gmit.sw.dsModels.Customer;
import ie.gmit.sw.dsModels.Vehicle;



public class Consumer extends BookingMarshal{

	public static void main(String[] args) {
		
		String base = "http://localhost:8080/dsRESTfulServiceProject/webapi/bookings";
		
		System.out.println("Consumer");
		
		// 1. Create a client.
		Client client = ClientBuilder.newClient(); // Factory class.
		
		System.out.println("Loading ... ");
		
		// 2. Set a target for the client.
		WebTarget target = client.target(base);
		
		
		
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
		
		////////////////////////////////////////////
		// Trying to do a POST with a booking object
		target = client.target(base + "/1");
		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		Booking newBooking = getBookingFromXML(xmlResponse);
		System.out.println("SINGLE BOOKING UNMARSHALLED");
		
		System.out.println("XML RESPONSE 1 BOOKING");
		System.out.println(xmlResponse);
		
		System.out.println(newBooking.getBookingId());
		
		Customer customer = new Customer();
		Vehicle vehicle = new Vehicle();
		Booking booking = new Booking();
		
		customer.setCustomerId(10);
		vehicle.setId(10);
		booking.setBookingId(10);
		booking.setCustomer(customer);
		booking.setVehicle(vehicle);
		booking.setStartDate("03-03-2019");
		booking.setEndDate("04-03-2019");
		booking.setBookingNumber(null);
		
		
		
		target = client.target(base);
		
		String send = getBookingAsXML(booking);
		
		System.out.println(send);
		
		//System.out.println(send);
		
		Response response = target.request().post(Entity.xml(send));
		
		System.out.println("=====================================================");
		System.out.println(response);
		System.out.println("=====================================================");
		
		
		

	}

}
