package br.com.sicredi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.document.Agenda;
import br.com.sicredi.document.Vote;
import br.com.sicredi.document.VoteSession;

@Repository
public interface AgendaRepository extends MongoRepository<Agenda, String> {

	@Query("{ 'id': ?0 }, { voteSession: 1, _id: 0}")
	Optional<VoteSession> findVoteSessionByAgendaId(String id);

	@Query("{'id': ?0} , {}")
	void updateVotes(List<Vote> votes);
}
