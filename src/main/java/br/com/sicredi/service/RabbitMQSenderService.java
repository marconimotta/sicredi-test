package br.com.sicredi.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.sicredi.document.Agenda;

@Service
public class RabbitMQSenderService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${sicredi.rabbitmq.exchange}")
	private String exchange;

	@Value("${sicredi.rabbitmq.rountingKey}")
	private String routingkey;

	public void send(final Agenda agenda) {
		rabbitTemplate.convertAndSend(exchange, routingkey, agenda);
		System.out.println("Send msg = " + agenda);

	}
}
