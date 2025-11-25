package com.academix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired(required = false)
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username:noreply@academix.com}")
    private String fromEmail;
    
    public void enviarEmail(String destinatario, String asunto, String mensaje) {
        try {
            if (mailSender != null) {
                SimpleMailMessage email = new SimpleMailMessage();
                email.setFrom(fromEmail);
                email.setTo(destinatario);
                email.setSubject(asunto);
                email.setText(mensaje);
                mailSender.send(email);
            } else {
                System.out.println("Email Service no configurado. Email a: " + destinatario);
            }
        } catch (Exception e) {
            System.err.println("Error enviando email: " + e.getMessage());
        }
    }
    
    public void enviarConfirmacionInscripcion(String destinatario, String nombreEstudiante,
                                             String nombreCurso, String codigoCurso) {
        String asunto = "Confirmación de Inscripción - AcademiX";
        String mensaje = String.format(
            "Hola %s,\n\n" +
            "Tu inscripción al curso %s (%s) ha sido confirmada exitosamente.\n\n" +
            "Saludos,\nEquipo AcademiX",
            nombreEstudiante, nombreCurso, codigoCurso
        );
        enviarEmail(destinatario, asunto, mensaje);
    }
    
    public void enviarConfirmacionPago(String destinatario, String nombreEstudiante,
                                       String nombreCurso, Double monto) {
        String asunto = "Confirmación de Pago - AcademiX";
        String mensaje = String.format(
            "Hola %s,\n\n" +
            "Tu pago de $%.2f para el curso %s ha sido procesado exitosamente.\n\n" +
            "Saludos,\nEquipo AcademiX",
            nombreEstudiante, monto, nombreCurso
        );
        enviarEmail(destinatario, asunto, mensaje);
    }
}