package ie.gmit.sw.ds.dsRMI_DataAccess;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface IBookingService  extends Remote{ // This is the interface between the remote object and the client stub.
	
	public void createBooking(String query) throws RemoteException; // to create a booking
	
	public List<ReturnedBooking> readBookings() throws RemoteException; // to list all the bookings
	
	public void updateBooking(String query) throws RemoteException; // to update an existing booking
	
	public void deleteBooking(String query) throws RemoteException; // to delete an existing booking
	
}
