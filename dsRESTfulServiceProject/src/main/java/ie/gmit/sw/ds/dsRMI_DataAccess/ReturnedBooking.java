package ie.gmit.sw.ds.dsRMI_DataAccess;

import java.io.Serializable;

public class ReturnedBooking implements Serializable{ // This is a booking pojo that has the same make-up as the booking object in the database table.
	// This objetc will be used to map to the Booking object that can be marshalled to xml.
	private static final long serialVersionUID = 1190476516911661470L;
	private int bookingId;
	private int vehicleId;
	private int customerId;
	private String startDate;
	private String endDate;
	
	public ReturnedBooking() { // Create empty constructor.
		
	}
	
	public ReturnedBooking(int bookingId, int vehicleId, int customerId, String startDate, String endDate) { // Create populated constructor.
		super();
		this.bookingId = bookingId;
		this.vehicleId = vehicleId;
		this.customerId = customerId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// Getter and setter methods.
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() { // ToString method to be used for debbugging.
		return "Booking [bookingId=" + bookingId + ", vehicleId=" + vehicleId + ", customerId=" + customerId
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	

}
