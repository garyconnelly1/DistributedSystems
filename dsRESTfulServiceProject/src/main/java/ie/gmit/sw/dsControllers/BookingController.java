package ie.gmit.sw.dsControllers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ie.gmit.sw.ds.dsRMI_DataAccess.IBookingService;

public class BookingController {
	IBookingService bookingService;
	
	public BookingController() throws Exception {
		// get a handle on the remote object
		bookingService = (IBookingService) Naming.lookup("rmi://127.0.0.1:1099/bookingService");
		
	}

}
