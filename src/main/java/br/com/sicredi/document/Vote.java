package br.com.sicredi.document;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

	private String cpf;

	private boolean choosedVote;

	private LocalDateTime dateVote;

}
