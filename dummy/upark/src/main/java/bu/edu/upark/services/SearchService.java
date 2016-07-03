package bu.edu.upark.services;

import javax.servlet.http.HttpServletRequest;
import bu.edu.upark.entities.UserInput;

public interface SearchService {
	public boolean doSearch(HttpServletRequest req , UserInput userInput);
}
