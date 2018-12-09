package ie.gmit.sw.dsMarshal;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import ie.gmit.sw.dsModels.Booking;
import ie.gmit.sw.dsModels.Bookings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;

public class BookingMarshal { // Class to be extended by the RestAccess so that is can access the following methods easily.

	public BookingMarshal() { // Empty constructor.
	} 

	protected static String getBookingAsXML(Booking booking) { // Method to turn a booking model object into xml.
		
		StringWriter sw = new StringWriter(); // Initialize a StringWriter.
		Marshaller m; // Set a Marshaller.

		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.dsModels"); // Point the JAXBContext to the package containing the models generated by the schema.
			m = jc.createMarshaller(); // Initialize the Marshaller.
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(booking, sw); // Marshal the object into the StringWriter.
		} catch (JAXBException e) {
			System.out.println("Problem marshalling booking"); // Catch any exceptions.
			e.printStackTrace();
		}

		return sw.toString(); // Return the StringWriter.
	}

	protected static Booking getBookingFromXML(String input) { // Method for creating a Booking model object out of an xml string.
		
		StringReader sr1 = new StringReader(input); // Initialize a StringReader using the xml that was passed into the method.
		Unmarshaller um1; // Set an Unmarshaller.
		Booking bookingFromXml = null; // Initialize a booking object.

		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.dsModels"); // Point the JAXBContext to the package containing the models generated by the schema.
			um1 = jc.createUnmarshaller(); // Initialize the Unmarshaller.
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Booking> bookingElement1 = um1.unmarshal(source1, Booking.class); // Unmarshal the xml to the StringReader.
			bookingFromXml = (Booking) bookingElement1.getValue(); // Cast the JAXBElement to a Booking object.
		} catch (JAXBException e) {
			System.out.println("Problem unmarshalling booking object"); // Catch any exceptions.
			e.printStackTrace();
		}

		return bookingFromXml; // Return the Booking object.
	}
	
	protected static Bookings getBookingsFromXML(String input) { // Method to unmarshal a list of bookings to xml(For the GET() method that returns all bookings.)
	
		StringReader sr1 = new StringReader(input); // Initialize a StringWriter.
		Unmarshaller um1; // Set the Uarshaller.
		Bookings bookingsFromXml = null; // Initialize a bookings object.

		try {
			JAXBContext jc = JAXBContext.newInstance(Bookings.class); // Point the JAXBContext to the Bookings class generated by the schema.
			um1 = jc.createUnmarshaller(); // Initialize the Marshaller.
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Bookings> bookingElement1 = um1.unmarshal(source1, Bookings.class); // Unmarshal the xml to the StringReader.
			bookingsFromXml = (Bookings) bookingElement1.getValue(); // Cast the JAXBElement to a Bookings object.
		} catch (JAXBException e) {
			System.out.println("Problem unmarshalling booking object"); // Catch any exceptions.
			e.printStackTrace();
		}

		return bookingsFromXml; // Return the Bookings object.

	}

}