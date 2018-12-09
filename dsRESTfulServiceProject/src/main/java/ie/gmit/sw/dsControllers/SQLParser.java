package ie.gmit.sw.dsControllers;

public class SQLParser {
	
	public String parseSQL(String query) {
		int syntax = 0;
		
		for (int i = 0; i < query.length(); i++){
		    char c = query.charAt(i);  
		    
		    if(c == ';')
		    {
		    	syntax++;
		    }
		}
		
		if(syntax == 0) {
			return query + ";";
		}
		else {
			return null;
		}
		
		
	}

}
