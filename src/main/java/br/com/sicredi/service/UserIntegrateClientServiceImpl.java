package br.com.sicredi.service;

import static br.com.sicredi.enums.StatusToVoteEnum.ABLE_TO_VOTE;
import static br.com.sicredi.enums.StatusToVoteEnum.toEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import br.com.sicredi.dto.UserIntegrateStatusDTO;
import br.com.sicredi.feign.UserIntegrateClient;

@Service
public class UserIntegrateClientServiceImpl implements UserIntegrateClientService {

	@Autowired
	UserIntegrateClient userIntegrateClient;

	@Override
	public boolean checkCPF(final String cpf) {
		try {
			final UserIntegrateStatusDTO check = userIntegrateClient.existsCpf(cpf);
			return toEnum(check.getStatus()).equals(ABLE_TO_VOTE);
		} catch (final HttpStatusCodeException ex) {
			if (!ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				throw ex;
			}
		}
		return false;
	}
}
