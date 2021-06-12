package br.com.sicredi.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class AgendaRequestDTO {

	private String id;

	@NotBlank
	private String subject;
}
