package ie.gmit.sw.dsConsumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;



public class Consumer {

	public static void main(String[] args) {
		
		System.out.println("Consumer");
		
		// 1. Create a client.
		Client client = ClientBuilder.newClient(); // Factory class.
		
		// 2. Set a target for the client.
		WebTarget target = client.target("http://localhost:8080/dsRESTfulServiceProject/webapi/bookings");
		
		// 3. Get response from web service.
		
		System.out.println(
				target.request(MediaType.APPLICATION_XML).get(String.class)
				);

	}

}
