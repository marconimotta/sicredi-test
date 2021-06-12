package br.com.sicredi.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.sicredi.dto.AgendaRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Document
@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {

	@Id
	private String id;

	private String subject;

	private VoteSession voteSession;

	public void patch(final AgendaRequestDTO agendaRequestDTO) {
		subject = agendaRequestDTO.getSubject();
	}

}
