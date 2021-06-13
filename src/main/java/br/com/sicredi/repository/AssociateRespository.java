package br.com.sicredi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.document.Associate;

@Repository
public interface AssociateRespository extends MongoRepository<Associate, String> {

	boolean existsByCpf(String cpf);

}
