package ie.gmit.sw.ds.dsRMI_DataAccess;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;



import java.rmi.*;
import java.rmi.server.*;



public class BookingServiceImpl extends UnicastRemoteObject implements IBookingService{
	protected BookingServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
	
	// Override implement methods
	public void createBooking(String query) throws RemoteException {
		
		
	}

	public ResultSet readBookings() throws RemoteException {
		
		return null;
	}

	public void updateBooking(String query) throws RemoteException {
		
		
	}

	public void deleteBooking(String query) throws RemoteException {
		
		
	}

	
}
