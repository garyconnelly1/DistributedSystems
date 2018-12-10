package ie.gmit.sw.dsConsumer;

import java.util.Scanner;
import java.util.List;
import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.dsModels.Customer;
import ie.gmit.sw.dsModels.Vehicle;

public class DesktopMain {
	
	/*
	 * This is a Runner class that allows the user to interact with the RESTful service from the console. 
	 * It follows much of the same logic is the web client.
	 */

	public static void main(String[] args) {
		boolean isContinue = true; // Initialize a boolean that allows the user to continue to loop through the program.
		Scanner reader = new Scanner(System.in); // Reading from System.in
		
		System.out.println("Welcome to Distributed Systems Projecr(Desktop version)"); // Greet the user.

		while (isContinue) { // While the user has not entered "QUIT" into the console.

			int id; // Set an id variable to hold any integers the user enters from the menu.
			
			/*
			 * Simple menu to allow the user to select which of the CRUD(Create, Read, Update, Delete) functions they would like to
			 * use on the bookings resources.
			 */
			System.out.println("Press 1: ----------------------> Read all Bookings."); 
			System.out.println("Press 2: ----------------------> Update a Booking.");
			System.out.println("Press 3: ----------------------> Create a new Booking.");
			System.out.println("Press 4: ----------------------> Delete a Booking.");

			String s = reader.nextLine(); // Take the users input as a string.
			if (s.equals("1")) { // Is the user selects 1.
				System.out.println("Loading(Please wait a moment...) "); // Inform the user that the resources are being loaded.
				getAllBookings(); // Call the getAllBookings() static method.
			} else if (s.equals("2")) { // If the user selects 2.

				System.out.println("Enter the ID of the booking you wish to update."); // Prompt the user to enter the ID of the booking object they wish to update.
				id = reader.nextInt(); // Read in the id as an integer.
				updateBooking(id); // Call the updateBooking(id) static method.
			} else if (s.equals("3")) { // If the user selects 3.
				createBooking(); // Call the createBooking() static method.
			} else if (s.equals("4")) { // If the user selects 4.
				deleteBooking(); // Call the deleteBooking static method.
			}
			else {
				System.out.println("Not one of the options."); // If the user enters an input that is not supported.
			}

			System.out.println("Type 'QUIT' to exit Program, Any other key to continue:"); // Allow the user to exit the program by typing "QUIT".
			s = reader.next();

			if (s.equals("QUIT")) { // If the user enters "QUIT	"
				isContinue = false; // Change the isContinue boolean to false, breaking out of the loop and exiting the program.
			}
		}

		reader.close(); // Close the Reader.
	}

	public static void getAllBookings() { // Method to be called when the user wants to output all bookings to the console.
		RestAccess access = new RestAccess(); // Create a new RestAccess object.
		List<Booking> bookingList = access.getBookings(); // Initialize an object to hold a list of Booking objects.

		for (Booking b : bookingList) { // For each Booking object in the list of bookings.
			System.out.println("==============================="); // Print out the details of each object to the console.
			System.out.println("Booking ID: \t" + b.getBookingId());
			System.out.println("Customer ID: \t" + b.getCustomer().getCustomerId());
			System.out.println("Vehicle ID: \t" + b.getVehicle().getId());
			System.out.println("Start Date: \t" + b.getStartDate());
			System.out.println("End Date: \t" + b.getEndDate());
			System.out.println("===============================");
			System.out.println("");
		}
	}

	public static void updateBooking(int id) { // Method to be called when the user wants to update a booking object.
		Scanner reader = new Scanner(System.in); // Reading from System.in

		RestAccess access = new RestAccess(); // Create a new RestAccess object.

		Booking booking = access.getBookingById(id); // Get a handle on the booking we want to update from the restful service.

		System.out.println("==============================="); // Print out the current details of the booking object the user wants to update.
		System.out.println("Booking ID: \t" + booking.getBookingId());
		System.out.println("Customer ID: \t" + booking.getCustomer().getCustomerId());
		System.out.println("Vehicle ID: \t" + booking.getVehicle().getId());
		System.out.println("Start Date: \t" + booking.getStartDate());
		System.out.println("End Date: \t" + booking.getEndDate());
		System.out.println("===============================");
		System.out.println("");

		/*
		 * Menu that allows the user to select what attribute of the booking they wish to update.
		 */
		System.out.println("Press 1: ----------> To update Customer_ID");
		System.out.println("Press 2: ----------> To update Vehicle_ID");
		System.out.println("Press 3: ----------> To update Start_Date");
		System.out.println("Press 4: ----------> To update End_Date");
		
		String input = reader.nextLine(); // Initialize a reader to take in the users input.
		if (input.equals("1")) { // Of the user entered 1.
			System.out.println("Enter the new Customer_ID"); // Prompt them to enter the new customer id.
			int newId = reader.nextInt(); // Set an integer variable to whatever the user inputs.
			Customer customer = new Customer(); // Create a new Customer object.
			customer.setCustomerId(newId); // Set the id of the customer to the id the user entered.
			booking.setCustomer(customer); // Reset the booking objects customer to the newly created customer.
		} else if (input.equals("2")) { // If the user entered 2.
			System.out.println("Enter the new Vehicle_ID"); // Prompt the user to enter a new vehicle id.
			int newId = reader.nextInt(); // Set an integer variable to whatever the user inputs.
			Vehicle vehicle = new Vehicle(); // Create a new Vehicle object.
			vehicle.setId(newId); // Set the id of the new vehicle to the id the user entered.
			booking.setVehicle(vehicle); // Reset the booking objects vehicle to the newly created vehicle.
		} else if (input.equals("3")) { // If the user entered 3.
			System.out.println("Enter the new Start_Date"); // Prompt the user to enter the new start date.
			String newDate = reader.nextLine(); // Set a string variable to whatever the user inputs.
			booking.setStartDate(newDate); // Reset the bookings start date to the start date the user entered. 
		} else if (input.equals("4")) { // If the user entered 4.
			System.out.println("Enter the new End_Date"); // Prompt the user to enter the new end date.
			String newDate = reader.nextLine(); // Set a string variable to whatever the user inputs.
			booking.setEndDate(newDate); // Reset the bookings start date to the end date the user entered. 
		} else {
			System.out.println("Not one of the options."); // If the user enters an input that is not supported.
		}

		System.out.println("Updating Booking ..."); // Inform the user that the booking is being updated.
		access.updateBooking(booking); // Call the updateBooking method of the access object.
		System.out.println("Booking updated!"); // Inform the user that the booking has been updated.
		
		//reader.close(); // Close the Reader.
	}

	public static void createBooking() { // Method to be called when the user wants to create a new booking.

		Scanner reader = new Scanner(System.in); // Reading from System.in

		RestAccess access = new RestAccess(); // Create a new RestAccess object.
		
		System.out.println("===============================");
		System.out.println("Enter the Booking ID: "); // Prompt the user to enter the booking ID.
		int bookingId = reader.nextInt(); // Create a bookindId variable and assign it to the integer that was entered.
		System.out.println("Enter the Customer ID: "); // Prompt the user to enter the customer ID.
		int customerId = reader.nextInt(); // Create a customerId variable and assign it to the integer that was entered.
		System.out.println("Enter the vehicle ID: "); // Prompt the user to enter the vehicle ID.
		int vehicleId = reader.nextInt(); // Create a customerId variable and assign it to the integer that was entered.
		System.out.println("Enter the Start Date: ");
		String startDate = reader.next(); // Create a start date variable and assign it to the string that was entered.
		System.out.println("End the End Date: ");
		String endDate = reader.next(); // Create a end date variable and assign it to the string that was entered.
		
		Customer customer = new Customer(); // Create a new Customer object.
		customer.setCustomerId(customerId); // Set the id to the customerId that the user entered.
		Vehicle vehicle = new Vehicle(); //Create a new Vehicle object.
		vehicle.setId(vehicleId); // Set the id to the vehicleId that the user entered.
		Booking booking = new Booking(); // Create a new Booking object.
		
		/*
		 * Set all the attributes of the booking object to all the details the user entered.
		 */
		booking.setBookingId(bookingId);
		booking.setCustomer(customer);
		booking.setVehicle(vehicle);
		booking.setStartDate(startDate);
		booking.setEndDate(endDate);
		
		System.out.println("===============================");
		System.out.println("Creating Booking..."); // Inform the user that a new booking is being created.
		
		access.createBooking(booking); // Call the createBooking method in the RestAccess object.
		
		System.out.println("Booking Created!");	// Inform the user that a new booking has been created.
		
	//	reader.close(); // Close the reader.
	}
	
	public static void deleteBooking() { // Method to be called when the user wants to delete a booking.
		Scanner reader = new Scanner(System.in); // Reading from System.in

		RestAccess access = new RestAccess(); // Create a new RestAcces object.
		
		System.out.println("Enter the ID of the Booking you wish to delete: "); // Prompt the user to enter the ID of the booking they want to delete.
		int id = reader.nextInt(); // Initialize the id to the integer the user just entered.
		
		System.out.println("===============================");
		System.out.println("Deleting booking with ID: " + id + " ..."); // Inform the user that the booking is being deleted.
		
		access.deleteBooking(id); // Call the deleteBooking method from the RetsAccess class.
		
		System.out.println("Booking Deleted! "); // Inform the user that the booking has been deleted.
		
	//	reader.close(); // Close the Reader.
	}

}
