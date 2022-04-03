package com.sam.api.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmailSenderService {

	private JavaMailSender javaMailSender;
	
	public void enviarEmail(String emailDestinatario, String body, String assunto) {
		
		var mensagem = new SimpleMailMessage();
		
		mensagem.setFrom("logistica.api.suporte@gmail.com");
		mensagem.setTo(emailDestinatario);
		mensagem.setText(body);
		mensagem.setSubject(assunto);
		
		javaMailSender.send(mensagem);
	}
}
