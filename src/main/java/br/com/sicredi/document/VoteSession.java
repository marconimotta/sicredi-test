package br.com.sicredi.document;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteSession {

	@Setter
	private List<Vote> votes;

	private Long totalVotes;

	private Long votesYes;

	private Long votesNo;
	@Setter
	private LocalDateTime closeDate;

	public void updateVotesCount(final boolean choosedVote) {
		totalVotes++;
		if (choosedVote)
			votesYes++;
		else
			votesNo++;
	}

}
