package br.com.sicredi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends APIException {
  private static final long serialVersionUID = 1L;
  
  public InternalServerErrorException(final Throwable cause) {
    super(cause);
  }

  public InternalServerErrorException(final ErrorMessage error) {
    super(error);
  }

  public InternalServerErrorException(final String error) {
    super(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
