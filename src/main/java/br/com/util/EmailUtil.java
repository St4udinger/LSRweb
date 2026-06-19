package br.com.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

    // Suas credenciais Gmail
    private static final String GMAIL_EMAIL = "levistaudinger@gmail.com"; // SUBSTITUA aqui
    private static final String GMAIL_SENHA = "rpuegpnbjqgnwoqr"; // SUBSTITUA com a senha de app (sem espaços)

    public static void enviarEmailRecuperacao(String destino, String link) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_EMAIL, GMAIL_SENHA.replace(" ", ""));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(GMAIL_EMAIL, "Achei"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            message.setSubject("Recuperação de senha - Achei");

            String conteudo = "<html><body>"
                    + "<h2>Recuperação de Senha</h2>"
                    + "<p>Olá!</p>"
                    + "<p>Você solicitou a recuperação de sua senha. Clique no link abaixo para redefinir:</p>"
                    + "<p><a href='" + link + "'>Redefinir Senha</a></p>"
                    + "<p>Se você não solicitou isso, ignore este e-mail.</p>"
                    + "<p>O link expira em 30 minutos.</p>"
                    + "</body></html>";

            message.setContent(conteudo, "text/html; charset=utf-8");
            Transport.send(message);

            System.out.println("✓ E-mail enviado para: " + destino);
        } catch (MessagingException e) {
            System.err.println("✗ Erro ao enviar e-mail: " + e.getMessage());
            throw e;
        }
    }
}