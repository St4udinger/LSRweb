package br.com.servlet;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;
<<<<<<< HEAD
import br.com.util.SecurityUtil;
=======
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

<<<<<<< HEAD
/**
 * Servlet responsável pelo formulário de cadastro (separado do LoginServlet).
 */
=======
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
public class CadastroServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("abaAtiva", "cadastro");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (nome == null || nome.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            req.setAttribute("erro", "Preencha todos os campos.");
            req.setAttribute("abaAtiva", "cadastro");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        if (usuarioDAO.emailJaCadastrado(email)) {
            req.setAttribute("erro", "Esse e-mail já está cadastrado.");
            req.setAttribute("abaAtiva", "cadastro");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        Usuario u = new Usuario();
        u.setNome(nome.trim());
        u.setEmail(email.trim().toLowerCase());
<<<<<<< HEAD

        // Aqui: salvar hash da senha
        String senhaHash = SecurityUtil.hashPassword(senha);
        u.setSenha(senhaHash);

        int id = usuarioDAO.inserir(u);
        if (id == -1) {
            req.setAttribute("erro", "Erro ao criar conta.");
            req.setAttribute("abaAtiva", "cadastro");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        u.setId(id);
=======
        u.setSenha(senha);

        usuarioDAO.inserir(u);
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb

        HttpSession session = req.getSession(true);
        session.setAttribute("usuarioLogado", u);
        session.setAttribute("nomeUsuario", u.getNome());
        session.setAttribute("usuarioEmail", u.getEmail());

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}