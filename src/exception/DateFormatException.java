package exception;

public class DateFormatException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public static final String MESSAGE_ERROR_FORMAT = "La fecha debe ser ingresada en formato dd/MM/yyyy";
	
	public DateFormatException() {
		super( MESSAGE_ERROR_FORMAT );
	}
}