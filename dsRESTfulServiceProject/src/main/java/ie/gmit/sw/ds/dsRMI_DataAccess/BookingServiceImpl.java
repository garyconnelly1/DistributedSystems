package ie.gmit.sw.ds.dsRMI_DataAccess;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.rmi.server.*;

public class BookingServiceImpl extends UnicastRemoteObject implements IBookingService { // This is the remote object
																							// that will be accessed by
																							// the rmi client to access
																							// the database.
	private static final long serialVersionUID = 1L; // Remote objects need a serialVersionUID.
	// Connect to the database.
	DataSource mysqlDS;
	Statement stmt;

	protected BookingServiceImpl() throws RemoteException, SQLException {
		super();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carbookingproject?useSSL=false",
				"root", ""); // Connect to the database.

		stmt = conn.createStatement(); // Create the statement.
	}

	// Override implement methods.
	public void createBooking(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // Just pass the query to the database.
		} catch (SQLException e) {
			System.out.println("error processing sql query"); // Catch any sql Error.
			e.printStackTrace();
		}
	}

	public List<ReturnedBooking> readBookings() throws RemoteException { // Method that returns all the bookings in the
																			// table.
		String strSelect = "select * from bookings"; // Initial query.
		ResultSet rset = null;

		List<ReturnedBooking> bookings = new ArrayList<ReturnedBooking>(); // Initialize a list of ReturnedBooking
																			// objects.

		try {
			rset = stmt.executeQuery(strSelect); // Generate the result set.
		} catch (SQLException e) {
			System.out.println("sql error");
		}

		try {
			while (rset.next()) { // While there is a new result coming through.
				ReturnedBooking booking = new ReturnedBooking(); // Create a Model Booking object to map to the object
																	// that was returned from the database.
				booking.setBookingId(rset.getInt("booking_id")); // The Booking object is one that can be marshalled to
																	// xml to be sent over networks.
				booking.setVehicleId(rset.getInt("vehicle_id"));
				booking.setCustomerId(rset.getInt("customer_id"));
				booking.setStartDate(rset.getString("start_date"));
				booking.setEndDate(rset.getString("end_date"));

				bookings.add(booking);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bookings;
	}

	public void updateBooking(String query) throws RemoteException { // Method to update an existing booking in the
																		// database.
		try {
			stmt.executeUpdate(query); // Pass the query to the database.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBooking(String query) throws RemoteException { // Method to delete an existing booking from the
																		// database.
		try {
			stmt.executeUpdate(query); // Pass the query to the database.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
