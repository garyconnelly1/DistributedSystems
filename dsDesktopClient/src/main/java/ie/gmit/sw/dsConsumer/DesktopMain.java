package ie.gmit.sw.dsConsumer;

import java.util.Scanner;

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

public class DesktopMain {

	public static void main(String[] args) {
		boolean isContinue = true;
		Scanner reader = new Scanner(System.in); // Reading from System.in

		

		while (isContinue) {

			int id;
			

			System.out.println("Welcome to Distributed Systems Projecr(Desktop version)");
			System.out.println("Press 1: ----------------------> Read all Bookings.");
			System.out.println("Press 2: ----------------------> Update a Booking.");
			String s = reader.nextLine();
			if (s.equals("1")) {
				System.out.println("You pressed: " + s);
				System.out.println("Loading(Please wait a moment...) ");
				getAllBookings();
			}
			else if(s.equals("2")) {
				
				System.out.println("Enter the ID of the booking you wish to update.");
				id = reader.nextInt();
				updateBooking(id);
				
				
			}

			System.out.println("Type 'QUIT' to exit Program, Any other key to continue:");
			s = reader.nextLine();

			if (s.equals("QUIT")) {
				isContinue = false;
			}
		}

		reader.close();

	}

	public static void getAllBookings() {
		RestAccess access = new RestAccess();
		List<Booking> bookingList = access.getBookings();
		
		for(Booking b: bookingList) {
			System.out.println("===============================");
			System.out.println("Booking ID: \t" + b.getBookingId());
			System.out.println("Customer ID: \t" + b.getCustomer().getCustomerId());
			System.out.println("Vehicle ID: \t" + b.getVehicle().getId());
			System.out.println("Start Date: \t" + b.getStartDate());
			System.out.println("End Date: \t" + b.getEndDate());
			System.out.println("===============================");
			System.out.println("");
		}
		
	}
	
	public static void updateBooking(int id) {
		RestAccess access = new RestAccess();
		
		Booking booking = access.getBookingById(id);
		
		System.out.println("===============================");
		System.out.println("Booking ID: \t" + booking.getBookingId());
		System.out.println("Customer ID: \t" + booking.getCustomer().getCustomerId());
		System.out.println("Vehicle ID: \t" + booking.getVehicle().getId());
		System.out.println("Start Date: \t" + booking.getStartDate());
		System.out.println("End Date: \t" + booking.getEndDate());
		System.out.println("===============================");
		System.out.println("");
		
	}

}
