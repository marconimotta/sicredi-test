package br.com.sicredi.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.sicredi.document.Agenda;
import br.com.sicredi.document.Vote;
import br.com.sicredi.document.VoteSession;
import br.com.sicredi.dto.AgendaRequestDTO;
import br.com.sicredi.dto.AgendaResponseDTO;
import br.com.sicredi.dto.VoteRequestDTO;
import br.com.sicredi.dto.VoteResponseDTO;
import br.com.sicredi.dto.VoteSessionRequestDTO;

public class AgendaMock {

	public static String BASE_AGENDA = "/api/agenda/v1";

	public static String SUBJECT_EXAMPLE = "Example Test";
	public static String CPF_EXAMPLE_1 = "18029657005";
	public static String CPF_EXAMPLE_2 = "90922682089";

	public static List<Agenda> getList() {
		final List<Agenda> list = new ArrayList<Agenda>();
		list.add(getAgendaOk());
		return list;
	}

	public static List<AgendaResponseDTO> getListAgendaResponse() {
		return AgendaResponseDTO.convertEntityListToDTO(getList());
	}

	public static Agenda getAgendaOk() {
		return Agenda.builder().id(UUID.randomUUID().toString()).subject(SUBJECT_EXAMPLE)
				.voteSession(getVoteSessionOk())
				.build();
	}

	public static AgendaResponseDTO getAgendaResponseOk() {
		return AgendaResponseDTO.convertEntityToDTO(getAgendaOk());
	}

	public static Agenda getAgendaWithClosedVoteSessionOk() {
		return Agenda.builder().id(UUID.randomUUID().toString()).subject(SUBJECT_EXAMPLE)
				.voteSession(getVoteSessionCloseOk()).build();
	}

	public static Agenda getAgendaWithOutSession() {
		return Agenda.builder().id(UUID.randomUUID().toString()).subject(SUBJECT_EXAMPLE).build();
	}

	public static Agenda getAgendaWithoutVotes() {
		return Agenda.builder().id(UUID.randomUUID().toString()).subject(SUBJECT_EXAMPLE)
				.voteSession(getVoteSessionWithoutVotes()).build();
	}

	public static AgendaRequestDTO getNewAgendaOk() {
		return AgendaRequestDTO.builder().subject(SUBJECT_EXAMPLE).build();
	}

	public static VoteSession getVoteSessionOk() {
		return VoteSession.builder().closeDate(LocalDateTime.now().plusHours(2)).totalVotes(2L).votesNo(1L).votesYes(1L)
				.votes(listVotesOk())
				.build();
	}

	public static VoteSession getVoteSessionCloseOk() {
		return VoteSession.builder().closeDate(LocalDateTime.now().minusMinutes(1)).totalVotes(2L).votesNo(1L)
				.votesYes(1L).votes(listVotesOk()).build();
	}

	public static VoteSession getVoteSessionWithoutVotes() {
		return VoteSession.builder().closeDate(LocalDateTime.now().plusHours(2)).totalVotes(0L).votesNo(0L).votesYes(0L)
				.build();
	}

	public static List<Vote> listVotesOk() {
		final ArrayList<Vote> list = new ArrayList<Vote>();
		list.add(voteYes());
		list.add(voteNo());
		return list;
	}

	public static Vote voteYes() {
		return Vote.builder().dateVote(LocalDateTime.now()).cpf(CPF_EXAMPLE_1).choosedVote(true).build();
	}

	public static Vote voteNo() {
		return Vote.builder().dateVote(LocalDateTime.now()).cpf(CPF_EXAMPLE_2).choosedVote(false).build();
	}

	public static VoteSessionRequestDTO getNewVoteSessionOk() {
		return VoteSessionRequestDTO.builder().agendaId(UUID.randomUUID().toString())
				.closeDate(LocalDateTime.now().plusHours(2)).build();
	}

	public static VoteSessionRequestDTO getNewVoteSessionWithoutCloseDateOk() {
		return VoteSessionRequestDTO.builder().agendaId(UUID.randomUUID().toString()).build();
	}

	public static VoteRequestDTO getNewVoteYesOk() {
		return VoteRequestDTO.builder().agendaId(UUID.randomUUID().toString()).cpf(CPF_EXAMPLE_1).choosedVote(true)
				.build();
	}

	public static VoteRequestDTO getNewVoteNoOk() {
		return VoteRequestDTO.builder().agendaId(UUID.randomUUID().toString()).cpf(CPF_EXAMPLE_1).choosedVote(false)
				.build();
	}

	public static VoteResponseDTO getVoteResponseYesOk() {
		return VoteResponseDTO.convertEntityToDTO(voteYes());
	}

}
