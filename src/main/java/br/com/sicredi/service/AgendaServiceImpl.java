package br.com.sicredi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.document.Agenda;
import br.com.sicredi.document.Vote;
import br.com.sicredi.document.VoteSession;
import br.com.sicredi.dto.AgendaRequestDTO;
import br.com.sicredi.dto.AgendaResponseDTO;
import br.com.sicredi.dto.VoteRequestDTO;
import br.com.sicredi.dto.VoteResponseDTO;
import br.com.sicredi.dto.VoteSessionRequestDTO;
import br.com.sicredi.exceptions.NotFoundException;
import br.com.sicredi.exceptions.UnauthorizedException;
import br.com.sicredi.repository.AgendaRepository;
import br.com.sicredi.util.Constants;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaRepository agendaRepository;

	@Autowired
	UserIntegrateClientService userIntegrateClientService;

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
		final Agenda agenda = agendaRepository.save(Agenda.builder().subject(agendaRequest.getSubject()).build());
		return AgendaResponseDTO.convertEntityToDTO(agenda);
	}

	@Override
	public AgendaResponseDTO openVoteSession(final VoteSessionRequestDTO voteSessionRequest) {
		final Agenda agenda = findAgendaById(voteSessionRequest.getAgendaId());
		if (Objects.nonNull(agenda.getVoteSession())) {
			throw new UnauthorizedException(Constants.VOTE_SESSION_READY_EXISTS);
		}
		final VoteSession voteSession = VoteSession.builder().closeDate(voteSessionRequest.getCloseDate())
				.totalVotes(0L).votesYes(0L).votesNo(0L).build();
		if (Objects.isNull(voteSessionRequest.getCloseDate())) {
			voteSession.setCloseDate(LocalDateTime.now().plusMinutes(Constants.AGENDA_DEFAULT_TIME));
		}
		agenda.setVoteSession(voteSession);

		return AgendaResponseDTO.convertEntityToDTO(agendaRepository.save(agenda));
	}

	private void validateSessionVoteOpenAndUser(final VoteSession voteSession, final VoteRequestDTO voteRequestDTO) {
		if (Objects.isNull(voteSession)) {
			throw new NotFoundException(Constants.VOTE_SESSION_NOT_FOUND);
		}
		if (voteSession.getCloseDate().isBefore(LocalDateTime.now())) {
			throw new UnauthorizedException(Constants.VOTE_SESSION_CLOSED);
		}
		if (!userIntegrateClientService.checkCPF(voteRequestDTO.getCpf())) {
			throw new UnauthorizedException(Constants.ASSOCIATE_NOT_ABLE_TO_VOTE);
		}

	}

	@Override
	public VoteResponseDTO registerVote(final VoteRequestDTO voteRequestDTO) {
		final Agenda agenda = findAgendaById(voteRequestDTO.getAgendaId());
		validateSessionVoteOpenAndUser(agenda.getVoteSession(), voteRequestDTO);
		if (Objects.nonNull(agenda.getVoteSession().getVotes())) {
			agenda.getVoteSession().getVotes().stream().filter(v -> v.getCpf().equals(voteRequestDTO.getCpf()))
					.findAny().ifPresent(v -> {
						throw new UnauthorizedException(Constants.ASSOCIATE_READY_EXISTS);
					});
		} else {
			agenda.getVoteSession().setVotes(new ArrayList<>());
		}
		final boolean choosedVote = voteRequestDTO.getChoosedVote().booleanValue();
		agenda.getVoteSession().updateVotesCount(choosedVote);

		final Vote vote = Vote.builder().cpf(voteRequestDTO.getCpf())
				.choosedVote(choosedVote)
				.dateVote(LocalDateTime.now()).build();
		agenda.getVoteSession().getVotes().add(vote);
		agendaRepository.save(agenda);

		return VoteResponseDTO.convertEntityToDTO(vote);
	}

}
