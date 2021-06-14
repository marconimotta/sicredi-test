package br.com.sicredi.enums;

import lombok.Getter;

public enum StatusToVoteEnum {

	ABLE_TO_VOTE("ABLE_TO_VOTE"), UNABLE_TO_VOTE("UNABLE_TO_VOTE");

	@Getter
	private String name;

	StatusToVoteEnum(final String name) {
		this.name = name;
	}

	public static StatusToVoteEnum toEnum(final String description) {
		for (final StatusToVoteEnum x : StatusToVoteEnum.values()) {
			if (x.getName().equals(description)) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid status to vote: " + description);
	}
}
