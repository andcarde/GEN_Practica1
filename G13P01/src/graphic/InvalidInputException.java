package graphic;

import java.util.List;

class InvalidInputException extends Exception {

	private static final long serialVersionUID = -4409023998892587339L;
	private List<String> errors;

	InvalidInputException(List<String> errors) {
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
