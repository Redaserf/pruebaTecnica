package com.example.pruebatecnica.services;

import com.example.pruebatecnica.models.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async //respuesta inmediata sin importar si el email fue enviado o no ya que no se lleva un control de eso.
    public void sendEmail(Usuario usuario, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(usuario.getEmail());
        message.setSubject(subject);
        message.setText(usuario.getEmail() + " Bienvenido a la prueba tecnica de Spring Boot. " + body + "");

        javaMailSender.send(message);
    }

    @Async
    public void sendEmailFromTemplate(Usuario usuario) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setFrom(new InternetAddress("pruebaTecnica@example.com"));
            message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
            message.setSubject("Test email from my Spring application");

            String htmlTemplate = readFileFromResources("registro.html");
            htmlTemplate = htmlTemplate.replace("${name}", "John Doe");
            htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

            message.setContent(htmlTemplate, "text/html; charset=utf-8");

            javaMailSender.send(message);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public String readFileFromResources(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("templates/" + filename);

        if (inputStream == null) {
            throw new FileNotFoundException("No se encontró la plantilla: " + filename);
        }

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }


}
