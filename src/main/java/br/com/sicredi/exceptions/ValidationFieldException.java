package br.com.sicredi.exceptions;

public class ValidationFieldException extends APIException {

	private static final long serialVersionUID = 1L;

	public ValidationFieldException(final Throwable cause) {
		super(cause);
	}

	public ValidationFieldException(final ErrorMessage error) {
	    super(error);
	  }

	public ValidationFieldException(final String error) {
		super(error);
	}
}
