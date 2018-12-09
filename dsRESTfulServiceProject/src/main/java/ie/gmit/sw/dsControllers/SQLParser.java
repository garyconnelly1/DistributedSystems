package ie.gmit.sw.dsControllers;

public class SQLParser {
	/*
	 * This is a very simple class the provides the application with protection
	 * against sql injection attacks. All it does is parse each query and checks
	 * each character to see if it contains a ';', meaning that the user can't for
	 * example enter "12-12-2018;Drop tables;" into a an input field on a form on
	 * the client side, which could be very damaging to the database.
	 */

	public String parseSQL(String query) { // Take in an SQL query as a parameter.

		int syntax = 0; // Initialize a counter and set it to 0.

		for (int i = 0; i < query.length(); i++) { // Create a for loop to loop through each character in the query.
			char c = query.charAt(i); // Initialize a char variable to the current character the loop is on in the
										// query.

			if (c == ';') // If the character is a semi-colon.
			{
				syntax++; // Increase the counter by one.
			}
		}

		if (syntax == 0) { // Only if the counter is equal to 0 at the end of the loop.
			return query + ";"; // Append a semi-colon to the end of the query and send it back to the
								// controller.
		} else { // If the counter is greater than 0.
			return null; // Simply return null, meaning a harmless null query will be sent to the
							// database.
		}

	}

}
