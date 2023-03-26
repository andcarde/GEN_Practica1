package control;

import java.util.List;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = -4409023998892587339L;
	private List<String> errors;

	public InvalidInputException(List<String> errors) {
		this.errors = errors;
	}
	
	@Override
	public String getMessage() {
		String message = "The following errors have been detected:" + "\n";
		for (String error : errors)
			message += "- " + error + "\n";
		return message;
	}
}
