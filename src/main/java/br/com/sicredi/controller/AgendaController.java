package br.com.sicredi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.dto.AgendaRequestDTO;
import br.com.sicredi.dto.AgendaResponseDTO;
import br.com.sicredi.dto.VoteSessionRequestDTO;
import br.com.sicredi.service.AgendaService;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaController {

	@Autowired
	AgendaService agendaService;

	@GetMapping(value = "/")
	@ResponseStatus(value = HttpStatus.OK)
	public List<AgendaResponseDTO> getAgendas() {
		return agendaService.findAll();
	}

	@GetMapping(value = "/{id}")
	public AgendaResponseDTO getAgenda(@PathVariable final String id) {
		return agendaService.findById(id);
	}

	@PostMapping(value = "/")
	public AgendaResponseDTO save(@RequestBody final @Valid AgendaRequestDTO agendaRequest) {
		return agendaService.save(agendaRequest);
	}

	@PutMapping(value = "/")
	public AgendaResponseDTO update(@RequestBody final @Valid AgendaRequestDTO agendaRequest) {
		return agendaService.update(agendaRequest);
	}

	@PostMapping(value = "/open-session")
	public AgendaResponseDTO openVoteSession(@RequestBody @Valid final VoteSessionRequestDTO voteSessionRequest) {
		return agendaService.openVoteSession(voteSessionRequest);
	}


}
