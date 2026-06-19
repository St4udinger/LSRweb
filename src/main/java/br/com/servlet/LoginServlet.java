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

/**
 * Servlet de autenticação: processa login e cadastro de usuários.
 * Mapeado em /login via web.xml.
 */
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Se já estiver logado, vai direto para a home
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("usuarioLogado") != null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String acao  = req.getParameter("acao");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if ("cadastro".equals(acao)) {
<<<<<<< HEAD
            // -------- CADASTRO via mesmo endpoint (mantive lógica original)
            String nome = req.getParameter("nome");

            if (nome == null || nome.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    senha == null || senha.trim().isEmpty()) {
                req.setAttribute("erro", "Preencha todos os campos.");
                req.setAttribute("abaAtiva", "cadastro");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

=======
            // -------- CADASTRO
            String nome = req.getParameter("nome");

>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
            if (usuarioDAO.emailJaCadastrado(email)) {
                req.setAttribute("erro", "Este e-mail já está cadastrado.");
                req.setAttribute("abaAtiva", "cadastro");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

<<<<<<< HEAD
            // Criar usuário com senha em hash
            Usuario novo = new Usuario();
            novo.setNome(nome.trim());
            novo.setEmail(email.trim().toLowerCase());
            String senhaHash = SecurityUtil.hashPassword(senha);
            novo.setSenha(senhaHash);

            int idGerado = usuarioDAO.inserir(novo);
            if (idGerado == -1) {
                req.setAttribute("erro", "Erro ao criar conta. Tente novamente.");
                req.setAttribute("abaAtiva", "cadastro");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

            // Buscar o usuário recém-criado para popular sessão
            Usuario criado = usuarioDAO.buscarPorEmail(novo.getEmail());
            if (criado == null) {
                req.setAttribute("erro", "Conta criada, mas ocorreu um erro ao efetuar login automático.");
                req.setAttribute("abaAtiva", "login");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

=======
            Usuario novo = new Usuario();
            novo.setNome(nome);
            novo.setEmail(email);
            novo.setSenha(senha);

            usuarioDAO.inserir(novo);

            Usuario criado = usuarioDAO.buscarPorEmailSenha(email, senha);
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
            criarSessao(req, criado);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");

        } else {
            // -------- LOGIN
<<<<<<< HEAD
            if (email == null || email.trim().isEmpty() || senha == null || senha.isEmpty()) {
                req.setAttribute("erro", "Preencha e-mail e senha.");
                req.setAttribute("abaAtiva", "login");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

            // Buscar usuário pelo e-mail (senha no DB é hash)
            Usuario usuario = usuarioDAO.buscarPorEmail(email.trim().toLowerCase());
=======
            Usuario usuario = usuarioDAO.buscarPorEmailSenha(email, senha);
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb

            if (usuario == null) {
                req.setAttribute("erro", "E-mail ou senha incorretos.");
                req.setAttribute("abaAtiva", "login");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

<<<<<<< HEAD
            // Verificar a senha usando SecurityUtil (Password4j / Argon2)
            boolean ok = SecurityUtil.verifyPassword(senha, usuario.getSenha());
            if (!ok) {
                req.setAttribute("erro", "E-mail ou senha incorretos.");
                req.setAttribute("abaAtiva", "login");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }

=======
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
            criarSessao(req, usuario);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    private void criarSessao(HttpServletRequest req, Usuario u) {
        HttpSession session = req.getSession(true);
        session.setAttribute("usuarioLogado", u);
        session.setAttribute("nomeUsuario", u.getNome());
        session.setAttribute("usuarioEmail", u.getEmail());
    }
}