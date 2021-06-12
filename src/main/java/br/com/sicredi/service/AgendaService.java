package br.com.sicredi.service;

import java.util.List;

import br.com.sicredi.dto.AgendaRequestDTO;
import br.com.sicredi.dto.AgendaResponseDTO;
import br.com.sicredi.dto.VoteSessionRequestDTO;


public interface AgendaService {

	List<AgendaResponseDTO> findAll();

	AgendaResponseDTO findById(String id);

	AgendaResponseDTO save(AgendaRequestDTO agendaRequest);

	AgendaResponseDTO openVoteSession(VoteSessionRequestDTO voteSessionRequest);

	AgendaResponseDTO update(AgendaRequestDTO agendaRequest);
}
