package ie.gmit.sw.dsConsumer;

import java.util.List;

import ie.gmit.sw.dsModels.Booking;

public class Controller {

	public Controller() {

	}

	RestAccess access = new RestAccess();

	private List<Booking> bookingList;

	public List<Booking> getBookings() {
		bookingList = access.getBookings();
		return bookingList;
	}

	public Booking getBookingId(int id) {
		Booking booking = access.getBookingById(id);
		return booking;
	}
	
	public void updateBooking(Booking booking) {
		access.updateBooking(booking);
	}
	
	public void createBooking(Booking booking) {
		access.createBooking(booking);
	}

}
