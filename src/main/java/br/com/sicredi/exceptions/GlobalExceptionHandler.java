package br.com.sicredi.exceptions;

import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.sicredi.util.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String EXCEPTION_LOG_MSG = "e=%s,m=%s";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(APIException.class)
	protected ResponseEntity<ErrorMessage> processAPIException(final APIException ex) {
		final ResponseStatus status = ex.getClass().getDeclaredAnnotation(ResponseStatus.class);
		logE(ex);
		return new ResponseEntity<>(ex.getError(),
				Objects.nonNull(status) ? status.code() : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ErrorMessage> missingRequestParameterException(
			final MissingServletRequestParameterException ex) {
		logE(ex);
		return responseEntityReturn(String.format(Constants.FIELD_REQUEST_PARAM_REQUIRED, ex.getParameterName()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorMessage> processException(final Exception ex) {
		logE(ex);
		return responseEntityReturn(Constants.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ErrorMessage> processHttpMessageNotReadableException(
			final HttpMessageNotReadableException ex) {
		logE(ex);
		return responseEntityReturn(Constants.MANDATORY_DATA_NOT_SENDED, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void invalidFormatException(final InvalidFormatException ex) {
		logE(ex);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorMessage> processBindException(final BindException ex) {
		logE(ex);
		return badRequest(ex);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<ErrorMessage> processInternalServerErrorException(final InternalServerErrorException ex) {
		logE(ex);
		return responseEntityReturn(ex.getError().getError(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessage> processNotFoundException(final NotFoundException ex) {
		logE(ex);
		return responseEntityReturn(ex.getError().getError(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorMessage> processUnauthorizedException(final UnauthorizedException ex) {
		logE(ex);
		return responseEntityReturn(ex.getError().getError(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ValidationFieldException.class)
	public ResponseEntity<ErrorMessage> validationFieldException(final ValidationFieldException ex) {
		logE(ex);
		return responseEntityReturn(ex.getError().getError(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorMessage> processUnauthorizedException(final ForbiddenException ex) {
		logE(ex);
		return responseEntityReturn(ex.getError().getError(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<ErrorMessage> processUnprocessableEntityException(final UnprocessableEntityException ex) {
		logE(ex);
		return responseEntityReturn(ex.getError().getError(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(HttpStatusCodeException.class)
	private ResponseEntity<ErrorMessage> handleHttpStatusCodeException(final HttpStatusCodeException ex) {
		log.error("Http Error Status: " + ex.getStatusCode() + " Response: " + ex.getResponseBodyAsString());
		return responseEntityReturn(ex.getMessage(), ex.getStatusCode());
	}

	private ResponseEntity<ErrorMessage> responseEntityReturn(final String message, final HttpStatus httpStatus) {
		final ErrorMessage errorMessage = ErrorMessage.builder().statusCode(httpStatus.value()).error(message).build();
		return new ResponseEntity<>(errorMessage, httpStatus);
	}

	private String getMessage(final ObjectError objectError) {
		final String code = objectError.getDefaultMessage();
		final Object[] args = objectError.getArguments();
		return messageSource.getMessage(Objects.isNull(code) ? StringUtils.EMPTY : code, args, code,
				Locale.getDefault());
	}

	private ResponseEntity<ErrorMessage> badRequest(final BindException ex) {
		final ErrorMessage errorMessage = ErrorMessage.builder().error(ex.getMessage()).build();
		for (final ObjectError error : ex.getAllErrors()) {
			errorMessage.addError(getMessage(error));
		}
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	private static void logE(final Exception e) {
		final String message = String.format(EXCEPTION_LOG_MSG, e.getClass().getSimpleName(), e.getMessage());
		log.error(message, e);
	}

}
