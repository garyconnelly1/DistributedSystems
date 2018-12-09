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
		target = client.target(base + "/1"); // Set the base to base + id because first we need to do a get on a single resource.
		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		Booking newBooking = getBookingFromXML(xmlResponse);
		System.out.println("SINGLE BOOKING UNMARSHALLED");
		
		System.out.println("XML RESPONSE 1 BOOKING");
		System.out.println(xmlResponse);
		
		System.out.println(newBooking.getBookingId());
		
		Customer customer = new Customer();  // Create a new booking object to post to the server.
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
		
		
		
		target = client.target(base); // Reset the target back to base.
		
		String send = getBookingAsXML(booking); // Convert the booking object to xml.
		
		System.out.println(send); // Output the marshaled object before sending.
		
		Response response = target.request().post(Entity.xml(send)); // Execute the POST method.
		
		System.out.println("=====================================================");
		System.out.println(response); // Read the response.
		System.out.println("=====================================================");
		
		
		////////////////////////////////////////////
		// Trying to do a PUT with a booking object
		target = client.target(base + "/10"); // Reset the target to the ID of the resource we want to update(10).
		Customer customer2 = new Customer(); // Update some booking details.
		customer2.setCustomerId(11);
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setId(11);
		booking.setCustomer(customer2);
		booking.setVehicle(vehicle2);
		
		send = getBookingAsXML(booking); // Convert the updated booking object to xml.
		
		System.out.println(send); // Output the marshaled object before sending.
		
		response = target.request().put(Entity.xml(send)); // Execute the PUT method.
		
		System.out.println("=====================================================");
		System.out.println("UPDATED:");
		System.out.println(response); // Read the response.
		System.out.println("=====================================================");
		
		////////////////////////////////////////////
		// Trying to do a DELETE with a booking object
		response = target.request().delete();
		
		System.out.println("=====================================================");
		System.out.println("DELETED:");
		System.out.println(response); // Read the response.
		System.out.println("=====================================================");
		
		
		
		

	}

}
