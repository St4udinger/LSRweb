package br.com.servlet;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;
import br.com.util.SecurityUtil;
import br.com.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/esqueci-senha")
public class EsqueciSenhaServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String msgGenerica = "Se o e-mail existir, você receberá o link de recuperação.";

        try {
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("msg", "Informe um e-mail válido.");
                request.getRequestDispatcher("/esqueci-senha.jsp").forward(request, response);
                return;
            }

            Usuario usuario = usuarioDAO.buscarPorEmail(email.trim().toLowerCase());

            if (usuario != null) {
                String token = SecurityUtil.generateToken();
                String tokenHash = SecurityUtil.hashToken(token);
                Timestamp expiraEm = Timestamp.valueOf(LocalDateTime.now().plusMinutes(30));

                usuarioDAO.salvarTokenReset(usuario.getId(), tokenHash, expiraEm);

                String link = request.getScheme() + "://" +
                        request.getServerName() + ":" +
                        request.getServerPort() +
                        request.getContextPath() +
                        "/redefinir-senha.jsp?token=" + token;

                try {
                    EmailUtil.enviarEmailRecuperacao(email, link);
                    request.setAttribute("msg", "E-mail de recuperação enviado! Verifique sua caixa de entrada.");
                } catch (Exception e) {
                    System.err.println("Erro ao enviar e-mail: " + e.getMessage());
                    request.setAttribute("msg", "Erro ao enviar e-mail. Tente novamente.");
                }

                request.setAttribute("msg", "Se o e-mail existir, você receberá o link de recuperação.");
                request.getRequestDispatcher("/esqueci-senha.jsp").forward(request, response);
                return;
            }

            request.setAttribute("msg", msgGenerica);
            request.getRequestDispatcher("/esqueci-senha.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erro ao solicitar recuperação de senha", e);
        }
    }
}