package br.com.sicredi.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class ErrorMessage implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private int statusCode;
  private String error;

	@Builder.Default
	@JsonInclude(Include.NON_EMPTY)
	private List<String> details = Collections.synchronizedList(new ArrayList<>());

  public void addError(final String error) {
    details.add(error);
  }

}
