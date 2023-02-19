package graphic;

import java.util.List;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = -4409023998892587339L;
	private List<String> errors;

	InvalidInputException(List<String> errors) {
		this.errors = errors;
	}
	
	List<String> getErrors() {
		return this.errors;
	}
}
