<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("usuarioLogado") != null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Recuperar senha</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display:flex;
            align-items:center;
            justify-content:center;
            padding:20px;
        }
        .auth-wrapper {
            background:#fff;
            border-radius:16px;
            padding:48px 40px;
            width:100%;
            max-width:420px;
            box-shadow:0 20px 60px rgba(0,0,0,.3);
        }
        .auth-title {
            text-align:center;
            font-size:28px;
            font-weight:700;
            color:#1a1a1a;
            margin-bottom:12px;
        }
        .auth-subtitle {
            text-align:center;
            font-size:14px;
            color:#666;
            margin-bottom:28px;
            line-height:1.5;
        }
        .auth-alert {
            background:#eef8ff;
            border:1px solid #cfe6ff;
            color:#2b6cb0;
            border-radius:8px;
            padding:12px 16px;
            margin-bottom:20px;
            font-size:14px;
            display:flex;
            align-items:flex-start;
            gap:8px;
            line-height:1.4;
            word-break: break-word;
            overflow-wrap: anywhere;
        }

        .auth-alert.error {
            background:#fee;
            border:1px solid #fcc;
            color:#c33;
        }

        .auth-alert i {
            margin-top: 2px;
            flex-shrink: 0;
        }

        .auth-alert .msg-text {
            flex: 1;
            min-width: 0;
            word-break: break-word;
            overflow-wrap: anywhere;
        }
        .auth-alert.error {
            background:#fee;
            border:1px solid #fcc;
            color:#c33;
        }
        .auth-form input {
            width:100%;
            padding:13px 16px;
            margin-bottom:14px;
            border:1px solid #ddd;
            border-radius:8px;
            font-size:14px;
            font-family:inherit;
        }
        .auth-form input:focus {
            outline:none;
            border-color:#667eea;
            box-shadow:0 0 0 3px rgba(102,126,234,.1);
        }
        .cta {
            width:100%;
            padding:14px;
            border:none;
            border-radius:8px;
            background:linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color:#fff;
            font-size:15px;
            font-weight:700;
            cursor:pointer;
            margin-bottom:16px;
        }
        .auth-links {
            text-align:center;
            font-size:14px;
        }
        .auth-links a {
            color:#667eea;
            text-decoration:none;
            font-weight:600;
        }
    </style>
</head>
<body>

<div class="auth-wrapper">
    <h1 class="auth-title">
        <i class="fas fa-key" style="color:#667eea; margin-right:8px;"></i>
        Recuperar senha
    </h1>
    <p class="auth-subtitle">Digite seu e-mail para receber o link de redefinição</p>

    <% if (msg != null) { %>
        <div class="auth-alert <%= msg.toLowerCase().contains("erro") ? "error" : "" %>">
            <i class="fas <%= msg.toLowerCase().contains("erro") ? "fa-exclamation-circle" : "fa-check-circle" %>"></i>
            <span class="msg-text"><%= msg %></span>
        </div>
    <% } %>

    <form class="auth-form" action="${pageContext.request.contextPath}/esqueci-senha" method="post">
        <input type="email" name="email" placeholder="Seu e-mail" required>
        <button class="cta" type="submit">
            <i class="fas fa-envelope"></i> Enviar link
        </button>
    </form>

    <div class="auth-links">
        <a href="${pageContext.request.contextPath}/login.jsp">
            <i class="fas fa-arrow-left"></i> Voltar para login
        </a>
    </div>
</div>

</body>
</html>