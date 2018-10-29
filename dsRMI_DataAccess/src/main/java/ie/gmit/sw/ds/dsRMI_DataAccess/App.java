package ie.gmit.sw.ds.dsRMI_DataAccess;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class App 
{
	public static void main(String[] args) throws Exception {
		IBookingService bookingService = new BookingServiceImpl();

		LocateRegistry.createRegistry(1099); // Start the RMI registry on port
												// 1099

		Naming.rebind("bookingService", bookingService); // Bind our remote
															// object to the
															// registry with the
															// human-readable
															// name
															// "bookingService"

		System.out.println("Server ready."); // Print a message to standard
												// output
	}
}
