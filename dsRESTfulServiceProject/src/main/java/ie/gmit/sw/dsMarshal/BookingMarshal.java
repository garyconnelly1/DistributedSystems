package ie.gmit.sw.dsMarshal;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import ie.gmit.sw.dsModels.Booking;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;

public class BookingMarshal {
	
	public BookingMarshal() {} // empty constructor
	
	protected String getBookingAsXML(Booking booking){
		// Marshal the Booking into XML
    	StringWriter sw = new StringWriter();
		Marshaller m;
		
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.dsModels");
			m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(booking, sw);
		} catch (JAXBException e) {
			System.out.println("Problem marshalling booking");
			e.printStackTrace();
		}
		
		return sw.toString();
	}// end getBookingAsXML
	
	protected Booking getBookingFromXML(String input) {
		// Unmarshal the Booking from XML
		StringReader sr1 = new StringReader(input);
		Unmarshaller um1;
		Booking bookingFromXml = null;
		
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.dsModels");
			um1 = jc.createUnmarshaller();
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Booking> bookingElement1 = um1.unmarshal(source1, Booking.class);
			bookingFromXml = (Booking) bookingElement1.getValue();
		} catch (JAXBException e) {
			System.out.println("Problem unmarshalling booking object");
			e.printStackTrace();
		}
		
		return bookingFromXml;
	}

}
