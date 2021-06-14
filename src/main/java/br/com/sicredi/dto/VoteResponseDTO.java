package br.com.sicredi.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sicredi.document.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {

	private String cpf;

	private LocalDateTime dateVote;

	public static VoteResponseDTO convertEntityToDTO(final Vote vote) {
		return VoteResponseDTO.builder().cpf(vote.getCpf()).dateVote(vote.getDateVote()).build();
	}

	public static List<VoteResponseDTO> convertListEntityToDTO(final List<Vote> vote) {
		return vote.stream().map(VoteResponseDTO::convertEntityToDTO).collect(Collectors.toList());
	}

}
