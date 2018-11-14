package ie.gmit.sw.ds.dsRMI_DataAccess;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.sql.SQLException;

import java.rmi.*;
import java.rmi.server.*;

public class BookingServiceImpl extends UnicastRemoteObject implements IBookingService {
	private static final long serialVersionUID = 1L;
	// connect to the database
	DataSource mysqlDS;
	Statement stmt;
	

	protected BookingServiceImpl() throws RemoteException, SQLException {
		super();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carbookingproject?useSSL=false", "root",
				""); // connect to the database

		stmt = conn.createStatement(); // create the statement
	}

	
	

	// Override implement methods
	public void createBooking(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // just pass the query to the database
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ReturnedBooking> readBookings() throws RemoteException {
		System.out.println("inside readBookings");
		String strSelect = "select * from bookings";
		ResultSet rset = null;
		ArrayList<ResultSet> resultSetSerialized=new ArrayList<ResultSet>();
		ReturnedBooking booking = new ReturnedBooking();
		List<ReturnedBooking> bookings = new ArrayList<ReturnedBooking>();

		try {
			rset = stmt.executeQuery(strSelect); // generate the result set
			//resultSetSerialized.add(rset);
		} catch (SQLException e) {
			System.out.println("sql error");
		}
		
		try {
			while(rset.next()) {
				booking.setBookingId(rset.getInt("booking_id"));
				booking.setVehicleId(rset.getInt("vehicle_id"));
				booking.setCustomerId(rset.getInt("customer_id"));
				booking.setStartDate(rset.getString("start_date"));
				booking.setEndDate(rset.getString("end_date"));
				
				bookings.add(booking);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		System.out.println(rset);

		return bookings;
	}

	public void updateBooking(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // pass the query to the database
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBooking(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // pass the query to the database
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
