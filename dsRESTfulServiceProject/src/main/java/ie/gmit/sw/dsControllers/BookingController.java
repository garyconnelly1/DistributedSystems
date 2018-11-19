package ie.gmit.sw.dsControllers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.ds.dsRMI_DataAccess.IBookingService;
import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.ds.dsRMI_DataAccess.ReturnedBooking;


public class BookingController {
	IBookingService bookingService;
	
	public BookingController()  {
		// get a handle on the remote object
		try {
			this.bookingService = (IBookingService) Naming.lookup("rmi://127.0.0.1:1099/bookingService");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getAllBookings() {
		ResultSet resultSet = null;
		List<ReturnedBooking> bookings = new ArrayList<ReturnedBooking>();
		ReturnedBooking resultBooking;
		String startDate = null;
		String resultObject = null;

		try {
			bookings = bookingService.readBookings();
			System.out.println("=======================================");
		} catch (RemoteException e) {

			System.out.println("error accessing data from remote object");
			e.printStackTrace();
		}

		System.out.println(bookings.size());
		resultBooking = bookings.get(0);

		System.out.println(resultBooking.getBookingId() + "//////////////////////////////////////////////"); // for
																												// testing
																												// purposes
		return resultBooking.toString();
	}
	
	public ReturnedBooking getBookingById(int id) {
		List<ReturnedBooking> bookings = new ArrayList<ReturnedBooking>();
		ReturnedBooking resultBooking = null;

		try {
			bookings = bookingService.readBookings();
		} catch (RemoteException e) {
			System.out.println("error accessing data from remote object");
			e.printStackTrace();
		}

		for (ReturnedBooking b : bookings) {
			if (b.getBookingId() == id) {
				resultBooking = b;
			}

		}

		return resultBooking;
	}
	
	public void createBooking(ReturnedBooking booking) {
		//System.out.println("//////////// CREATE BOOKING" + booking.toString());
		String query = "Insert INTO bookings VALUES(" + booking.getBookingId() + "," + booking.getVehicleId() + ","
				+ booking.getCustomerId() + ",\""  + booking.getStartDate()  + "\",\"" 
				+ booking.getEndDate() + "\");";
		
		//String query = "INSERT INTO bookings VALUES()";
		
		//System.out.pri
		
		

		try {
			bookingService.createBooking(query);
		} catch (RemoteException e) {
			System.out.println("error on sql query in booking controller");
			e.printStackTrace();
		}
	}
	
	public void updateBooking(ReturnedBooking booking) {
		System.out.println("//////////// UPDATE BOOKING" + booking.toString());
		String query = "UPDATE bookings SET vehicle_id =" + booking.getVehicleId()
		+ ", " + "customer_id =" + booking.getCustomerId() + ", " + "start_date =\"" + booking.getStartDate() + "\", "
				+ "end_date =\"" + booking.getEndDate() + "\" WHERE booking_id=" + booking.getBookingId() + ";";
		
		System.out.println("============UPDATE BOOKING QUERY:     " + query);
		
		try {
			bookingService.updateBooking(query);
		} catch (RemoteException e) {
			System.out.println("error updating booking in Booking controller");
			e.printStackTrace();
		}
		
	}


}
