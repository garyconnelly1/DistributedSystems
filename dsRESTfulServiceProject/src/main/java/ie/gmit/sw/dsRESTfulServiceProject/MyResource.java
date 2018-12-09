package ie.gmit.sw.dsRESTfulServiceProject;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ie.gmit.sw.ds.dsRMI_DataAccess.ReturnedBooking;
import ie.gmit.sw.dsControllers.BookingController;
import ie.gmit.sw.dsMarshal.BookingMarshal;
import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.dsModels.Bookings;
import ie.gmit.sw.dsModels.Customer;
import ie.gmit.sw.dsModels.Vehicle;

/**
 * Root resource (exposed at "myresource" path)
 */

/**
 * Method handling HTTP GET requests. The returned object will be sent to the
 * client as "text/plain" media type.
 *
 * @return String that will be returned as a text/plain response.
 */
@Path("bookings") // When the client makes a call to the bookings resource
public class MyResource extends BookingMarshal implements Resource {
	
	BookingController controller = new BookingController(); // Initialize the controller.

	@Override
	public Response getIt() { // Method to return all bookings.
		List<Booking> bookings = new ArrayList<Booking>(); // Initialize a list of booking objects.
		
		List<ReturnedBooking> returnStatement = controller.getAllBookings(); // Get a list of ReturnedBooking objects from the controller.

		/*
		 * For each ReturnedBooking object in the list, map all the relevant elements a new instance of
		 * the Booking model class which can be sent over the network.
		 * 
		 */
		for (ReturnedBooking rb : returnStatement) { 
			Booking booking = new Booking();
			Customer customer = new Customer();
			Vehicle vehicle = new Vehicle();
			System.out.println(rb.getBookingId());
			customer.setCustomerId(rb.getCustomerId());
			vehicle.setId(rb.getVehicleId());
			booking.setBookingId(rb.getBookingId());
			booking.setCustomer(customer);
			booking.setVehicle(vehicle);
			booking.setStartDate(rb.getStartDate());
			booking.setEndDate(rb.getEndDate());

			bookings.add(booking);
		}
		
		Bookings bookingList = new Bookings(); // Initialize the bookings object to contain a list of booking objects.
		bookingList.setBooking(bookings); // Set the bookings to the list we just created.
		String msg = getBookingsAsXML(bookingList); // Marshal this list so it can be sent via HTTP.

		return Response.status(200).entity(msg).build(); // Return the xml with a status 200 server message.
	}

	@Override
	public Response getById(String value) { // Method to return 1 booking.
		Booking booking = new Booking(); // Initialize a booking object.
		Customer customer = new Customer(); // Initialize a customer object.
		Vehicle vehicle = new Vehicle(); // Initialize a vehicle object.
		
		int id = Integer.parseInt(value); // Convert the value parameter to an integer.
		ReturnedBooking returnedBooking = controller.getBookingById(id); // Find the booking with that id.
		if (returnedBooking != null) { // If it exists in the database.
			
			/*
			 * Map it to the booking model object that can be sent over a network.
			 */
			
			booking.setBookingId(returnedBooking.getBookingId());
			customer.setCustomerId(returnedBooking.getCustomerId());
			vehicle.setId(returnedBooking.getVehicleId());
			booking.setCustomer(customer);
			booking.setVehicle(vehicle);
			booking.setStartDate(returnedBooking.getStartDate());
			booking.setEndDate(returnedBooking.getEndDate());
			booking.setBookingNumber(null);

			String msg = getBookingAsXML(booking); // Marshal the object to xml.

			return Response.status(200).entity(msg).build(); // Return the xml with a status 200 server message.
		} else {
			return Response.status(404).entity("Resource doesn't exist").build(); // Return a 404 because the resource does not exist.
		}

	}

	@Override
	public Response create(String input) { // When the client wants to create a new booking object.
		
		Booking booking = getBookingFromXML(input); // Unmarshal the xml input to a booking object.
		ReturnedBooking returnedBooking = new ReturnedBooking();
		Customer customer = booking.getCustomer();
		Vehicle vehicle = booking.getVehicle();

		/*
		 * Map it to a ReturnedBooking object so it can be sent to the database.
		 */
		returnedBooking.setBookingId(booking.getBookingId());
		returnedBooking.setCustomerId(customer.getCustomerId());
		returnedBooking.setVehicleId(vehicle.getId());
		returnedBooking.setEndDate(booking.getEndDate());
		returnedBooking.setStartDate(booking.getStartDate());

		controller.createBooking(returnedBooking); // Pass that object to the controller.

		return Response.ok().build(); // Return a status 200 server message.
	}

	@Override
	public Response update(String id, String input) { // When the client wants to update a booking in the database.
		
		Booking booking = getBookingFromXML(input); // Unmarshal the xml to a booking objetc.
		Customer customer = booking.getCustomer();
		Vehicle vehicle = booking.getVehicle();
		int value = Integer.parseInt(id); // Convert the value to an integer.
		ReturnedBooking returnedBooking = controller.getBookingById(value); // Find the booking object that we need to update.
												
		/*
		 * Map it to a ReturnedBooking object so it can be sent to the database.
		 */
		returnedBooking.setBookingId(booking.getBookingId());
		returnedBooking.setCustomerId(customer.getCustomerId());
		System.out.println("PRINTING IDs.");
		System.out.println(customer.getCustomerId());
		System.out.println(vehicle.getId());
		returnedBooking.setVehicleId(vehicle.getId());
		returnedBooking.setStartDate(booking.getStartDate());
		returnedBooking.setEndDate(booking.getEndDate());

		controller.updateBooking(returnedBooking); // Pass that object to the controller.

		return Response.ok().build(); // Return a status 200 server message.
	}

	@Override
	public Response delete(String id, String input) { // When the client wants to delete a booking object from the database.
		int value = Integer.parseInt(id); // Convert the value to an integer.

		ReturnedBooking returnedBooking = controller.getBookingById(value); // Find the object we want to delete.

		if (returnedBooking != null) { // If the object exists.
			controller.deleteBooking(value); // Delete that object.
		}

		return Response.ok().build(); // Return a status 200 server message.
	}

}
