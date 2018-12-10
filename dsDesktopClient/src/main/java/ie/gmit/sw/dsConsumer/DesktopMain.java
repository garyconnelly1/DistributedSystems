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
			System.out.println("Press 3: ----------------------> Create a new Booking.");

			String s = reader.nextLine();
			if (s.equals("1")) {
				System.out.println("You pressed: " + s);
				System.out.println("Loading(Please wait a moment...) ");
				getAllBookings();
			} else if (s.equals("2")) {

				System.out.println("Enter the ID of the booking you wish to update.");
				id = reader.nextInt();
				updateBooking(id);
			} else if (s.equals("3")) {
				createBooking();
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

		for (Booking b : bookingList) {
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
		Scanner reader = new Scanner(System.in); // Reading from System.in

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

		System.out.println("Press 1: ----------> To update Customer_ID");
		System.out.println("Press 2: ----------> To update Vehicle_ID");
		System.out.println("Press 3: ----------> To update Start_Date");
		System.out.println("Press 4: ----------> To update End_Date");
		String input = reader.nextLine();
		if (input.equals("1")) {
			System.out.println("Enter the new Customer_ID");
			int newId = reader.nextInt();
			Customer customer = new Customer();
			customer.setCustomerId(newId);
			booking.setCustomer(customer);
		} else if (input.equals("2")) {
			System.out.println("Enter the new Customer_ID");
			int newId = reader.nextInt();
			Vehicle vehicle = new Vehicle();
			vehicle.setId(newId);
			booking.setVehicle(vehicle);
		} else if (input.equals("3")) {
			System.out.println("Enter the new Start_Date");
			String newDate = reader.nextLine();
			booking.setStartDate(newDate);
		} else if (input.equals("d")) {
			System.out.println("Enter the new End_Date");
			String newDate = reader.nextLine();
			booking.setEndDate(newDate);
		} else {
			System.out.println("Not one of the options.");
		}

		access.updateBooking(booking);
		System.out.println("Booking updated!");
	}

	public static void createBooking() {

		Scanner reader = new Scanner(System.in); // Reading from System.in

		RestAccess access = new RestAccess();
		
		System.out.println("===============================");
		System.out.println("Enter the Booking ID: ");
		int bookingId = reader.nextInt();
		System.out.println("Enter the Customer ID: ");
		int customerId = reader.nextInt();
		System.out.println("Enter the vehicle ID: ");
		int vehicleId = reader.nextInt();
		System.out.println("Enter the Start Date: ");
		String startDate = reader.next();
		System.out.println("End the End Date: ");
		String endDate = reader.next();
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(vehicleId);
		Booking booking = new Booking();
		
		booking.setBookingId(bookingId);
		booking.setCustomer(customer);
		booking.setVehicle(vehicle);
		booking.setStartDate(startDate);
		booking.setEndDate(endDate);
		
		System.out.println("===============================");
		System.out.println("Creating Booking...");
		
		access.createBooking(booking);
		
		System.out.println("Booking Created!");
		
		
		
	}

}
