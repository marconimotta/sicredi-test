package br.com.sicredi.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import br.com.sicredi.document.VoteSession;
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
public class VoteSessionResponseDTO {

	private List<VoteResponseDTO> votes;

	private Long totalVotes;

	private Long voteYes;

	private Long votesNo;

	private LocalDateTime closeDate;

	public static VoteSessionResponseDTO convertEntityToDTO(final VoteSession voteSession) {
		final VoteSessionResponseDTO voteSessionResponseDTO = VoteSessionResponseDTO.builder()
				.totalVotes(voteSession.getTotalVotes()).voteYes(voteSession.getVotesYes())
				.votesNo(voteSession.getVotesNo())
				.closeDate(voteSession.getCloseDate()).build();
		if (Objects.nonNull(voteSession.getVotes())) {
			voteSessionResponseDTO.setVotes(VoteResponseDTO.convertListEntityToDTO(voteSession.getVotes()));
		}
		return voteSessionResponseDTO;
	}

}
