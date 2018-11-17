package ie.gmit.sw.dsRESTfulServiceProject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.gmit.sw.ds.dsRMI_DataAccess.ReturnedBooking;
import ie.gmit.sw.dsControllers.BookingController;
import ie.gmit.sw.dsMarshal.BookingMarshal;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource{
	BookingController controller = new BookingController();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	String returnStatement = controller.getAllBookings();
    	
        return returnStatement;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{value}")
    public Response getById(@PathParam("value") String value) {
    	int id = Integer.parseInt(value); // convert the value to an int
    	ReturnedBooking returnedBooking = controller.getBookingById(id);
    	System.out.println(returnedBooking.toString());
		return null;
    	
    }
}
