package br.com.sicredi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.document.Agenda;
import br.com.sicredi.service.RabbitMQSenderService;

@RestController
@RequestMapping(value = "api/row/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSenderService rabbitMQSenderService;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("id") final String id) {
		final Agenda agenda = Agenda.builder().id(id).build();
		rabbitMQSenderService.send(agenda);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

}