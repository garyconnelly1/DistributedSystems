package ie.gmit.sw.dsRESTfulServiceProject;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ie.gmit.sw.ds.dsRMI_DataAccess.ReturnedBooking;
import ie.gmit.sw.dsControllers.BookingController;
import ie.gmit.sw.dsMarshal.BookingMarshal;
import ie.gmit.sw.dsModels.Booking;
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
@Path("bookings")
public class MyResource extends BookingMarshal implements Resource {
	BookingController controller = new BookingController();

	@Override
	public List<Booking> getIt() {
		List<Booking> bookings = new ArrayList<Booking>();
		Booking booking = new Booking();
		Customer customer = new Customer();
		Vehicle vehicle = new Vehicle();
		List<ReturnedBooking> returnStatement = controller.getAllBookings();

		for (ReturnedBooking rb : returnStatement) {
			customer.setCustomerId(rb.getCustomerId());
			vehicle.setId(rb.getVehicleId());
			booking.setBookingId(rb.getBookingId());
			booking.setCustomer(customer);
			booking.setVehicle(vehicle);
			booking.setStartDate(rb.getStartDate());
			booking.setEndDate(rb.getEndDate());

			bookings.add(booking);
		}

		return bookings;
	}

	@Override
	public Response getById(String value) {
		Booking booking = new Booking();
		Customer customer = new Customer();
		Vehicle vehicle = new Vehicle();
		int id = Integer.parseInt(value); // convert the value to an int
		ReturnedBooking returnedBooking = controller.getBookingById(id);
		if (returnedBooking != null) {
			System.out.println(returnedBooking.toString());
			// construct the booking response object
			booking.setBookingId(returnedBooking.getBookingId());
			customer.setCustomerId(returnedBooking.getCustomerId());
			vehicle.setId(returnedBooking.getVehicleId());
			booking.setCustomer(customer);
			booking.setVehicle(vehicle);
			booking.setStartDate(returnedBooking.getStartDate());
			booking.setEndDate(returnedBooking.getEndDate());
			booking.setBookingNumber(null);

			String msg = getBookingAsXML(booking);

			System.out.println(msg);

			return Response.status(200).entity(msg).build();
		} else {
			return Response.status(404).entity("Resource doesn't exist").build();
		}

	}

	@Override
	public Response create(String input) {
		System.out.println(input);

		Booking booking = getBookingFromXML(input);
		ReturnedBooking returnedBooking = new ReturnedBooking();
		Customer customer = booking.getCustomer();
		Vehicle vehicle = booking.getVehicle();

		// construct the returnedBooking object
		returnedBooking.setBookingId(booking.getBookingId());
		returnedBooking.setCustomerId(customer.getCustomerId());
		returnedBooking.setVehicleId(vehicle.getId());
		returnedBooking.setEndDate(booking.getEndDate());
		returnedBooking.setStartDate(booking.getStartDate());

		controller.createBooking(returnedBooking);

		return Response.ok().build();
	}

	@Override
	public Response update(String id, String input) {
		Booking booking = getBookingFromXML(input);
		Customer customer = booking.getCustomer();
		Vehicle vehicle = booking.getVehicle();
		int value = Integer.parseInt(id); // convert the value to an int
		ReturnedBooking returnedBooking = controller.getBookingById(value); // find the booking object that we need to
																			// update
		returnedBooking.setBookingId(booking.getBookingId());
		returnedBooking.setCustomerId(customer.getCustomerId());
		returnedBooking.setVehicleId(vehicle.getId());
		returnedBooking.setStartDate(booking.getStartDate());
		returnedBooking.setEndDate(booking.getEndDate());

		controller.updateBooking(returnedBooking);

		return Response.ok().build();
	}

	@Override
	public Response delete(String id, String input) {
		int value = Integer.parseInt(id); // convert the value to an int

		ReturnedBooking returnedBooking = controller.getBookingById(value);

		if (returnedBooking != null) {
			controller.deleteBooking(value);
		}

		return Response.ok().build();
	}

}
