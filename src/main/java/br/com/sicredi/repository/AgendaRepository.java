package br.com.sicredi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.document.Agenda;

@Repository
public interface AgendaRepository extends MongoRepository<Agenda, String> {

}
