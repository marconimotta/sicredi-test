package br.com.sicredi.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {

	@Id
	private String id;

	private String subject;

	@Setter
	private VoteSession voteSession;

}
