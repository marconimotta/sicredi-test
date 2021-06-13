package br.com.sicredi.util;

public class Constants {
	private Constants() {
	}

	public static final Long AGENDA_DEFAULT_TIME = 1L;
	public static final String MANDATORY_DATA_NOT_SENDED = "Dados obrigatórios não enviados";
	public static final String AGENDA_NOT_FOUND = "Pauta não encontrada";

	public static final String VOTE_SESSION_NOT_FOUND = "Sessão de votação não encontrada";
	public static final String VOTE_SESSION_CLOSED = "A sessão de votação está encerrada";
	public static final String VOTE_SESSION_READY_EXISTS = "Já existe uma sessão de votação para esta pauta";

	public static final String UNEXPECTED_ERROR = "Problemas no sistema. Tente mais tarde.";
	public static final String FIELD_REQUEST_PARAM_REQUIRED = "O campo %s é obrigatório.";

	public static final String ASSOCIATE_READY_EXISTS = "Já existe um voto computado para esse associado";
	public static final String ASSOCIATE_NOT_ABLE_TO_VOTE = "Associado não habilitado para votar ou CPF inválido";

	public static final String AGENDA_LIST_ALL_SUMMARY = "Endpoint to list all agenda's";
	public static final String AGENDA_CREATE_SUMMARY = "Endpoint to create agenda";
	public static final String AGENDA_GET_ID_SUMMARY = "Endpoint to get agenda by Id";
	public static final String AGENDA_OPEN_SESSION_SUMMARY = "Endpoint to open a new session into a agenda";
	public static final String AGENDA_VOTE_SUMMARY= "Endpoint to vote in a open session";
}
