package ie.gmit.sw.dsConsumer;

import java.util.List;

import ie.gmit.sw.dsModels.Booking;

public class Controller {
	
	public Controller() {
		
	}
	
private List<Booking> bookingList;
	
	RestAccess access = new RestAccess();

	public List<Booking> getBookings(){
		bookingList = access.getBookings();
		return bookingList;
	}
	
	public String sayHello() {
		return "Hello user";
	}

}
