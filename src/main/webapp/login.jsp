<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
<<<<<<< HEAD
=======
    // Se já estiver logado, redireciona para home
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    if (session.getAttribute("usuarioLogado") != null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

<<<<<<< HEAD
    String erro = (String) request.getAttribute("erro");
=======
    String erro     = (String) request.getAttribute("erro");
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    String abaAtiva = (String) request.getAttribute("abaAtiva");
    if (abaAtiva == null) abaAtiva = "login";
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Achei! — Entrar</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .auth-wrapper {
            background: white;
            border-radius: 16px;
            padding: 48px 40px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
        }

        .auth-title {
            text-align: center;
            font-size: 28px;
            font-weight: 700;
            color: #1a1a1a;
            margin-bottom: 12px;
        }

        .auth-subtitle {
            text-align: center;
            font-size: 14px;
            color: #666;
            margin-bottom: 28px;
            line-height: 1.5;
        }

        .auth-alert {
            background: #fee;
            border: 1px solid #fcc;
            color: #c33;
            border-radius: 8px;
            padding: 12px 16px;
            margin-bottom: 20px;
            font-size: 14px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .auth-alert i {
            font-size: 18px;
        }

<<<<<<< HEAD
=======
        /* Abas */
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        .auth-tabs {
            display: flex;
            gap: 12px;
            margin-bottom: 28px;
            border-bottom: 2px solid #f0f0f0;
        }

        .auth-tabs button {
            flex: 1;
            padding: 12px 16px;
            border: none;
            background: transparent;
            color: #999;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            border-bottom: 3px solid transparent;
            transition: all 0.3s;
            margin-bottom: -2px;
        }

        .auth-tabs button:hover {
            color: #667eea;
        }

        .auth-tabs button.active {
            color: #667eea;
            border-bottom-color: #667eea;
        }

        .auth-tabs button i {
            margin-right: 6px;
        }

<<<<<<< HEAD
=======
        /* Formulários */
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        .auth-form {
            display: none;
        }

        .auth-form.visible {
            display: block;
        }

        .auth-form input {
            width: 100%;
            padding: 13px 16px;
            margin-bottom: 14px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 14px;
            transition: border-color 0.3s;
            font-family: inherit;
        }

        .auth-form input:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        .auth-form input::placeholder {
            color: #999;
        }

        .auth-actions {
            display: flex;
            gap: 12px;
            margin-bottom: 16px;
        }

        .cta {
            flex: 1;
            padding: 14px;
            border: none;
            border-radius: 8px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            font-size: 15px;
            font-weight: 700;
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
            letter-spacing: 0.5px;
        }

        .cta:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
        }

        .cta:active {
            transform: translateY(0);
        }

        .cta i {
            margin-right: 6px;
        }

        .auth-links {
            text-align: center;
            font-size: 14px;
            color: #666;
            display: flex;
            align-items: center;
            justify-content: center;
<<<<<<< HEAD
            gap: 10px;
=======
            gap: 6px;
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        }

        .auth-links a {
            color: #667eea;
            text-decoration: none;
            font-weight: 600;
            cursor: pointer;
            transition: color 0.2s;
        }

        .auth-links a:hover {
            color: #764ba2;
        }

        @media (max-width: 480px) {
            .auth-wrapper {
                padding: 32px 20px;
            }

            .auth-title {
                font-size: 24px;
            }

            .auth-actions {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>

<div class="auth-wrapper">
    <h1 class="auth-title">
        <i class="fas fa-compass" style="color: #667eea; margin-right: 8px;"></i>
        Achei
    </h1>
    <p class="auth-subtitle">O ponto de encontro para achados e perdidos</p>

    <% if (erro != null) { %>
        <div class="auth-alert">
            <i class="fas fa-exclamation-circle"></i>
            <%= erro %>
        </div>
    <% } %>

<<<<<<< HEAD
=======
    <!-- Abas -->
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    <div class="auth-tabs">
        <button id="tabLogin" class="<%= "login".equals(abaAtiva) ? "active" : "" %>" onclick="mostrarAba('login')">
            <i class="fas fa-sign-in-alt"></i> Entrar
        </button>
        <button id="tabCadastro" class="<%= "cadastro".equals(abaAtiva) ? "active" : "" %>" onclick="mostrarAba('cadastro')">
            <i class="fas fa-user-plus"></i> Cadastrar
        </button>
    </div>

<<<<<<< HEAD
=======
    <!-- FORMULÁRIO LOGIN -->
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    <form class="auth-form <%= "login".equals(abaAtiva) ? "visible" : "" %>" id="formLogin" action="${pageContext.request.contextPath}/login" method="post">
        <input type="hidden" name="acao" value="login">
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="senha" placeholder="Senha" required>
        <div class="auth-actions">
            <button class="cta" type="submit">
                <i class="fas fa-arrow-right"></i> Entrar
            </button>
        </div>
    </form>

<<<<<<< HEAD
=======
    <!-- FORMULÁRIO CADASTRO -->
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    <form class="auth-form <%= "cadastro".equals(abaAtiva) ? "visible" : "" %>" id="formCadastro" action="${pageContext.request.contextPath}/login" method="post">
        <input type="hidden" name="acao" value="cadastro">
        <input type="text" name="nome" placeholder="Seu nome" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="senha" placeholder="Senha" minlength="4" required>
        <div class="auth-actions">
            <button class="cta" type="submit">
                <i class="fas fa-user-plus"></i> Cadastrar
            </button>
        </div>
    </form>

<<<<<<< HEAD
    <!-- Link único: muda de texto conforme a aba -->
    <div class="auth-links" id="authLink">
        <span id="linkText">Esqueceu a senha?</span>
        <a href="${pageContext.request.contextPath}/esqueci-senha.jsp" id="linkSwitch">Recuperar senha</a>
=======
    <!-- Links -->
    <div class="auth-links" id="authLink">
        <span id="linkText">Já tem conta?</span>
        <a href="#" id="linkSwitch" onclick="mostrarAba('login'); return false;">Entrar</a>
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    </div>
</div>

<script>
    function mostrarAba(aba) {
        const formLogin = document.getElementById('formLogin');
        const formCadastro = document.getElementById('formCadastro');
        const tabLogin = document.getElementById('tabLogin');
        const tabCadastro = document.getElementById('tabCadastro');
        const linkText = document.getElementById('linkText');
        const linkSwitch = document.getElementById('linkSwitch');
<<<<<<< HEAD
        const ctx = '<%= request.getContextPath() %>';
=======
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb

        if (aba === 'login') {
            formLogin.classList.add('visible');
            formCadastro.classList.remove('visible');
            tabLogin.classList.add('active');
            tabCadastro.classList.remove('active');
<<<<<<< HEAD

            linkText.textContent = 'Esqueceu a senha?';
            linkSwitch.textContent = 'Recuperar senha';
            linkSwitch.href = ctx + '/esqueci-senha.jsp';
            linkSwitch.onclick = null;
        } else if (aba === 'cadastro') {
=======
            linkText.textContent = 'Não tem conta?';
            linkSwitch.textContent = 'Cadastre-se';
            linkSwitch.onclick = function(e) { e.preventDefault(); mostrarAba('cadastro'); };
        } else {
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
            formCadastro.classList.add('visible');
            formLogin.classList.remove('visible');
            tabCadastro.classList.add('active');
            tabLogin.classList.remove('active');
<<<<<<< HEAD

            linkText.textContent = 'Já tem conta?';
            linkSwitch.textContent = 'Entrar';
            linkSwitch.href = '#';
            linkSwitch.onclick = function(e) {
                e.preventDefault();
                mostrarAba('login');
                return false;
            };
        }
    }

=======
            linkText.textContent = 'Já tem conta?';
            linkSwitch.textContent = 'Entrar';
            linkSwitch.onclick = function(e) { e.preventDefault(); mostrarAba('login'); };
        }
    }

    // Inicializar na aba correta
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    window.addEventListener('DOMContentLoaded', function() {
        const abaAtiva = '<%= abaAtiva %>';
        if (abaAtiva === 'cadastro') {
            mostrarAba('cadastro');
<<<<<<< HEAD
        } else {
            mostrarAba('login');
=======
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        }
    });
</script>

</body>
</html>