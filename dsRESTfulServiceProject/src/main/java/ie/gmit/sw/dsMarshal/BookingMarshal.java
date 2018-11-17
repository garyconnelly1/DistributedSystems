package ie.gmit.sw.dsMarshal;

import java.io.StringWriter;

import javax.xml.bind.Marshaller;

import ie.gmit.sw.dsModels.Booking;

import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;

public class BookingMarshal {
	
	public BookingMarshal() {} // empty constructor
	
	private String getBookingAsXML(Booking booking){
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
	}

}
