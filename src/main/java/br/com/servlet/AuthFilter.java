package br.com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
<<<<<<< HEAD
 * Filtro de autenticação: libera páginas públicas e protege o restante.
=======
 * Filtro de autenticação: intercepta todas as requisições e redireciona
 * para a página de login caso o usuário não esteja autenticado.
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
 */
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String ctx = req.getContextPath();

<<<<<<< HEAD
        // Recursos públicos
        boolean isPublicPage = uri.equals(ctx + "/")
                || uri.equals(ctx + "/login.jsp")
                || uri.equals(ctx + "/esqueci-senha.jsp")
                || uri.equals(ctx + "/redefinir-senha.jsp")
                || uri.equals(ctx + "/login")
                || uri.equals(ctx + "/cadastro")
                || uri.equals(ctx + "/esqueci-senha")
                || uri.equals(ctx + "/redefinir-senha");
=======
        // Recursos liberados sem login
        boolean isAuthPage = uri.equals(ctx + "/login.jsp")
                || uri.equals(ctx + "/")
                || uri.equals(ctx + "/login")
                || uri.equals(ctx + "/cadastro");
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb

        boolean isStaticAsset = uri.startsWith(ctx + "/css/")
                || uri.startsWith(ctx + "/js/")
                || uri.startsWith(ctx + "/img/")
                || uri.startsWith(ctx + "/fonts/")
                || uri.startsWith(ctx + "/assets/")
                || uri.startsWith(ctx + "/webjars/");

<<<<<<< HEAD
        if (isPublicPage || isStaticAsset) {
=======
        if (isAuthPage || isStaticAsset) {
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
            chain.doFilter(request, response);
            return;
        }

<<<<<<< HEAD
=======
        // Verifica sessão
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        HttpSession session = req.getSession(false);
        boolean logado = (session != null && session.getAttribute("usuarioLogado") != null);

        if (logado) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(ctx + "/login.jsp");
        }
    }

    @Override
    public void init(FilterConfig fc) {}

    @Override
    public void destroy() {}
}