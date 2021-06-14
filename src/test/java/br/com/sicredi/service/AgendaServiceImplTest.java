package br.com.sicredi.service;

import static br.com.sicredi.mock.AgendaMock.getAgendaOk;
import static br.com.sicredi.mock.AgendaMock.getAgendaWithClosedVoteSessionOk;
import static br.com.sicredi.mock.AgendaMock.getAgendaWithOutSession;
import static br.com.sicredi.mock.AgendaMock.getAgendaWithoutVotes;
import static br.com.sicredi.mock.AgendaMock.getList;
import static br.com.sicredi.mock.AgendaMock.getNewAgendaOk;
import static br.com.sicredi.mock.AgendaMock.getNewVoteNoOk;
import static br.com.sicredi.mock.AgendaMock.getNewVoteSessionOk;
import static br.com.sicredi.mock.AgendaMock.getNewVoteSessionWithoutCloseDateOk;
import static br.com.sicredi.mock.AgendaMock.getNewVoteYesOk;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.sicredi.document.Agenda;
import br.com.sicredi.document.Vote;
import br.com.sicredi.document.VoteSession;
import br.com.sicredi.dto.AgendaRequestDTO;
import br.com.sicredi.dto.AgendaResponseDTO;
import br.com.sicredi.dto.VoteRequestDTO;
import br.com.sicredi.dto.VoteResponseDTO;
import br.com.sicredi.dto.VoteSessionRequestDTO;
import br.com.sicredi.dto.VoteSessionResponseDTO;
import br.com.sicredi.exceptions.NotFoundException;
import br.com.sicredi.exceptions.UnauthorizedException;
import br.com.sicredi.repository.AgendaRepository;

@SpringBootTest
class AgendaServiceImplTest {

	@Autowired
	AgendaService agendaService;

	@MockBean
	AgendaRepository agendaRepository;

	@MockBean
	UserIntegrateClientService userIntegrateClientService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void initialTestBasics() {

		final Agenda agenda = new Agenda();
		final AgendaRequestDTO agendaRequestDTO = new AgendaRequestDTO();
		final AgendaResponseDTO agendaResponseDTO = new AgendaResponseDTO();
		final VoteSession voteSession = new VoteSession();
		final VoteSessionRequestDTO voteSessionRequestDTO = new VoteSessionRequestDTO();
		final VoteSessionResponseDTO voteSessionResponseDTO = new VoteSessionResponseDTO();
		final Vote vote = new Vote();
		final VoteRequestDTO voteRequestDTO = new VoteRequestDTO();
		final VoteResponseDTO voteResponseDTO = new VoteResponseDTO();

		assertNotNull(agenda);
		assertNotNull(agendaRequestDTO);
		assertNotNull(agendaResponseDTO);
		assertNotNull(voteSession);
		assertNotNull(voteSessionRequestDTO);
		assertNotNull(voteSessionResponseDTO);
		assertNotNull(vote);
		assertNotNull(voteRequestDTO);
		assertNotNull(voteResponseDTO);

		assertNotNull(agenda.toString());
		assertNotNull(agendaRequestDTO.toString());
		assertNotNull(agendaResponseDTO.toString());
		assertNotNull(voteSession.toString());
		assertNotNull(voteSessionRequestDTO.toString());
		assertNotNull(voteSessionResponseDTO.toString());
		assertNotNull(vote.toString());
		assertNotNull(voteRequestDTO.toString());
		assertNotNull(voteResponseDTO.toString());

	}

	@Test
	void whenFindAllReturnItens() {
		when(agendaRepository.findAll()).thenReturn(getList());
		assertEquals(false, agendaService.findAll().isEmpty());
	}

	@Test
	void whenFindAllReturnEmpty() {
		when(agendaRepository.findAll()).thenReturn(Collections.emptyList());
		final List<AgendaResponseDTO> list = agendaService.findAll();
		assertEquals(Collections.emptyList(), list);
	}
	
	@Test
	void whenFindByIdReturnItem() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaOk()));
		assertNotNull(agendaService.findById(UUID.randomUUID().toString()));
	}
	
	@Test
	void whenFindByIdReturnNull() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> agendaService.findById(UUID.randomUUID().toString()));
	}

	@Test
	void whenSaveReturnItem() {
		when(agendaRepository.save(Mockito.any())).thenReturn(getAgendaOk());
		assertNotNull(agendaService.save(getNewAgendaOk()));
	}

	@Test
	void whenOpenVoteSessionReturnItem() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaWithOutSession()));
		when(agendaRepository.save(Mockito.any())).thenReturn(getAgendaOk());
		assertNotNull(agendaService.openVoteSession(getNewVoteSessionOk()));
	}

	@Test
	void whenOpenVoteSessionWithoutClosedDate() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaWithOutSession()));
		when(agendaRepository.save(Mockito.any())).thenReturn(getAgendaOk());
		assertNotNull(agendaService.openVoteSession(getNewVoteSessionWithoutCloseDateOk()));
	}

	@Test
	void whenOpenVoteSessionErrorNotFoundAgenda() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> agendaService.openVoteSession(getNewVoteSessionOk()));
	}

	@Test
	void whenOpenVoteSessionErrorExistsVoteSession() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaOk()));
		assertThrows(UnauthorizedException.class, () -> agendaService.openVoteSession(getNewVoteSessionOk()));
	}

	@Test
	void whenRegisterVoteYesReturnItem() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaWithoutVotes()));
		when(userIntegrateClientService.checkCPF(Mockito.anyString())).thenReturn(Boolean.TRUE);
		assertNotNull(agendaService.registerVote(getNewVoteYesOk()));
	}

	@Test
	void whenRegisterVoteNoReturnItem() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaWithoutVotes()));
		when(userIntegrateClientService.checkCPF(Mockito.anyString())).thenReturn(Boolean.TRUE);
		assertNotNull(agendaService.registerVote(getNewVoteNoOk()));
	}

	@Test
	void whenRegisterVoteErrorExistVoteEqual() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaOk()));
		when(userIntegrateClientService.checkCPF(Mockito.anyString())).thenReturn(Boolean.TRUE);
		assertThrows(UnauthorizedException.class, () -> agendaService.registerVote(getNewVoteYesOk()));
	}

	@Test
	void whenRegisterVoteErrorNotExistsSessionVote() {
		when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getAgendaWithOutSession()));
		assertThrows(NotFoundException.class, () -> agendaService.registerVote(getNewVoteYesOk()));
	}

	@Test
	void whenRegisterVoteErrorClosedSessionVote() {
		when(agendaRepository.findById(Mockito.anyString()))
				.thenReturn(Optional.of(getAgendaWithClosedVoteSessionOk()));
		assertThrows(UnauthorizedException.class, () -> agendaService.registerVote(getNewVoteYesOk()));
	}

	@Test
	void whenRegisterVoteErrorCpfNotAbleToVote() {
		when(agendaRepository.findById(Mockito.anyString()))
				.thenReturn(Optional.of(getAgendaOk()));
		when(userIntegrateClientService.checkCPF(Mockito.anyString())).thenReturn(Boolean.FALSE);
		assertThrows(UnauthorizedException.class, () -> agendaService.registerVote(getNewVoteYesOk()));
	}

}
