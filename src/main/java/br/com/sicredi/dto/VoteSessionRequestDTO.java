package br.com.sicredi.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class VoteSessionRequestDTO {

	@NotBlank
	private String agendaId;

	private LocalDateTime closeDate;

}
