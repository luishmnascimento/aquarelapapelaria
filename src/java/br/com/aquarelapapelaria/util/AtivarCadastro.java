package br.com.aquarelapapelaria.util;

import br.com.aquarelapapelaria.model.Usuario;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AtivarCadastro
{

    public void enviarEmailRec(Usuario user)
    {

        Properties props = new Properties();
            /**
             * Parâmetros de conexão com servidor Hotmail
             */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator()
                    {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication("aquarelapapelaria01@hotmail.com", "henrique9");
                        }
                    });

            /**
             * Ativa Debug para sessão
             */
            session.setDebug(true);

            //data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = sdf.format(new Date(System.currentTimeMillis()));

            try
            {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("aquarelapapelaria01@hotmail.com")); //Remetente

                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(user.getEmail())); //Destinatário(s)
                message.setSubject("Cadastro de usuário! Aquarela Papelaria");//Assunto
                message.setText("Olá "+user.getUsuario()+"\n\n Você está recebendo esse e-mail porque se cadastrou no sistema Aquarela. \n\n Para ativar seu usuário clique aqui http://localhost:8084/aquarelapapelaria/usuario/ativacadastro.xhtml \n\n Segue seus dados de cadastro\n\n\nUsuário: "+user.getUsuario()+"\nEmail: "+user.getEmail()+"\nSenha: "+user.getSenha()+"\n\n\nSolicitação enviada ás " + data + "\n\nAtenciosamente,\nLuis Henrique - Administrador");
                /**
                 * Método para enviar a mensagem criada
                 */
                Transport.send(message);

                System.out.println("E-mail enviado com sucesso! Verifique sua caixa de entrada");

            } catch (MessagingException e)
            {
                throw new RuntimeException(e);
            }
    }
//    public static void main(String[] args)
//    {
//        Properties props = new Properties();
//        /**
//         * Parâmetros de conexão com servidor Hotmail
//         */
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.host", "smtp.live.com");
//        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator()
//                {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication()
//                    {
//                        return new PasswordAuthentication("lhenriquelh7@hotmail.com", "henrique9");
//                    }
//                });
//
//        /**
//         * Ativa Debug para sessão
//         */
//        session.setDebug(true);
//
//        //data
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String data = sdf.format(new Date(System.currentTimeMillis()));
//
//        try
//        {
//            Usuario usuario = new Usuario();
//            
//            String email = "lhenriquee7@gmail.com";
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("lhenriquelh7@hotmail.com")); //Remetente
//
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(email)); //Destinatário(s)
////                    InternetAddress.parse("lhenriquee7@gmail.com")); //Destinatário(s)
//            message.setSubject("Solicitação de Senha Aquarela Papelaria");//Assunto
//            message.setText("Olá usuário!!\n\n Atendendo à sua solicitação, seguem-se sua identificação e senha de acesso.\n\n\nIdentificação: teste\nSenha: teste\n\n\nSolicitação enviada ás " + data + "\n\nAtenciosamente,\nLuis Henrique - Administrador");
//            /**
//             * Método para enviar a mensagem criada
//             */
//            Transport.send(message);
//
//            System.out.println("E-mail enviado com sucesso! Verifique sua caixa de entrada");
//
//        } catch (MessagingException e)
//        {
//            throw new RuntimeException(e);
//        }
//    }
}
