package ie.gmit.sw.dsControllers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ie.gmit.sw.ds.dsRMI_DataAccess.IBookingService;

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
		ArrayList<ResultSet> serializedList = new ArrayList<ResultSet>();
		String startDate = null;
		String resultObject = null;
		
		try {
			resultObject = bookingService.readBookings();
			//resultSet = serializedList.get(0);
			//while(resultSet.next()) {
			//	startDate = resultSet.getString("start_date");
			//}
			System.out.println("=======================================");
		} catch (RemoteException e) {
			
			System.out.println("error accessing data from remote object");
			e.printStackTrace();
		}
		
		
		
		return resultObject;
	}


}
