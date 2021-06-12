package br.com.sicredi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.document.Agenda;
import br.com.sicredi.document.VoteSession;
import br.com.sicredi.dto.AgendaRequestDTO;
import br.com.sicredi.dto.AgendaResponseDTO;
import br.com.sicredi.dto.VoteSessionRequestDTO;
import br.com.sicredi.exceptions.NotFoundException;
import br.com.sicredi.repository.AgendaRepository;
import br.com.sicredi.util.Constants;


@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaRepository agendaRepository;

	@Override
	public List<AgendaResponseDTO> findAll() {
		return AgendaResponseDTO.convertEntityListToDTO(agendaRepository.findAll());
	}

	private Agenda findAgendaById(final String id) {
		return agendaRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.AGENDA_NOT_FOUND));
	}

	@Override
	public AgendaResponseDTO findById(final String id) {
		return AgendaResponseDTO.convertEntityToDTO(findAgendaById(id));
	}

	@Override
	public AgendaResponseDTO save(final AgendaRequestDTO agendaRequest) {

		final Agenda agenda = agendaRepository
				.save(Agenda.builder().subject(agendaRequest.getSubject()).build());
		return AgendaResponseDTO.convertEntityToDTO(agenda);
	}

	@Override
	public AgendaResponseDTO update(final AgendaRequestDTO agendaRequest) {
		final Agenda agenda = findAgendaById(agendaRequest.getId());
		agenda.patch(agendaRequest);
		return AgendaResponseDTO.convertEntityToDTO(agendaRepository.save(agenda));

	}

	@Override
	public AgendaResponseDTO openVoteSession(final VoteSessionRequestDTO voteSessionRequest) {
		final VoteSession voteSession = VoteSession.builder().open(true).closeDate(voteSessionRequest.getCloseDate()).build();
		if(Objects.isNull(voteSessionRequest.getCloseDate())) {
			voteSession.setCloseDate(LocalDateTime.now().plusMinutes(Constants.AGENDA_DEFAULT_TIME));
		}
		final Agenda agenda = findAgendaById(voteSessionRequest.getAgendaId());
		agenda.setVoteSession(voteSession);

		return AgendaResponseDTO.convertEntityToDTO(agendaRepository.save(agenda));
	}




}
