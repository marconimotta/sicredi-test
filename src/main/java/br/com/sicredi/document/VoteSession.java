package br.com.sicredi.document;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteSession {

	private List<Vote> votes;

	private boolean open;

	private LocalDateTime closeDate;


}
