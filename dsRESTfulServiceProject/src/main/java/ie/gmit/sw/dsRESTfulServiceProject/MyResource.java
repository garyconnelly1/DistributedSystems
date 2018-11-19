package ie.gmit.sw.dsRESTfulServiceProject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
@Path("bookings")
public class MyResource extends BookingMarshal {
	BookingController controller = new BookingController();

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		String returnStatement = controller.getAllBookings();

		return returnStatement;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{value}")
	public Response getById(@PathParam("value") String value) {
		Booking booking = new Booking();
		Customer customer = new Customer();
		Vehicle vehicle = new Vehicle();
		int id = Integer.parseInt(value); // convert the value to an int
		ReturnedBooking returnedBooking = controller.getBookingById(id);
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

	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
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

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Response update(@PathParam("id") final String id, String input) {
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
}
