package ie.gmit.sw.ds.dsRMI_DataAccess;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.sql.DataSource;

import java.sql.SQLException;

import java.rmi.*;
import java.rmi.server.*;

public class BookingServiceImpl extends UnicastRemoteObject implements IBookingService {
	private static final long serialVersionUID = 1L;

	protected BookingServiceImpl() throws RemoteException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	// connect to the database

	DataSource mysqlDS;
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookingservice?useSSL=false", "root",
			""); // connect to the database

	Statement stmt = conn.createStatement(); // create the statement

	// Override implement methods
	public void createBooking(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // just pass the query to the database
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet readBookings() throws RemoteException {
		String strSelect = "select * from bookings";
		ResultSet rset = null;

		try {
			rset = stmt.executeQuery(strSelect); // generate the result set
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rset;
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
