package exception;

public class ExceptionReadName extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE_NOT_CHOOSE_OPTION = "Error el nombre o apellido debe empezar por Mayuscula \n";
	
	public ExceptionReadName() {
		super(MESSAGE_NOT_CHOOSE_OPTION);
	}
}
