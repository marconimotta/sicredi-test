package br.com.sicredi.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class APIException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final ErrorMessage error;

  protected APIException(final Throwable cause) {
    super(cause);
    this.error = ErrorMessage.builder().error(cause.getMessage()).build();
  }

  protected APIException(final ErrorMessage error) {
    this.error = error;
  }

  protected APIException(final String error) {
    this.error = ErrorMessage.builder().error(error).build();
  }

  protected APIException(final String error, final HttpStatus httpStatus) {
    this.error = ErrorMessage.builder().error(error).statusCode(httpStatus.value()).build();
  }
}
