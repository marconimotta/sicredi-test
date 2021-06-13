package br.com.sicredi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class VoteRequestDTO {

	@NotBlank
	@Size(min = 11, max = 11)
	private String cpf;

	@NotBlank
	private String agendaId;

	@NotNull
	private Boolean choosedVote;

}
