package ie.gmit.sw.dsConsumer;
import java.util.List;

import ie.gmit.sw.dsModels.Booking;

public class Controller {
	
	private static List<Booking> bookingList;
	
	static RestAccess access = new RestAccess();

	public static List<Booking> getBookings(){
		bookingList = access.getBookings();
		return bookingList;
	}
	
	public String sayHello() {
		System.out.println("Inside sat hello method.");
		return "hello";
	}

}
