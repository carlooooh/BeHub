<%@ page import="model.bean.UserBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"       %>
<% if (session.getAttribute("utente") == null) {
        response.sendRedirect(request.getContextPath() + "/accesso.jsp");
    }
    UserBean utente = (UserBean) session.getAttribute("utente");
    if (utente.getRole().compareTo("AS") != 0) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Accesso non autorizzato");
    }

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BeHub - Pagina Supporto</title>
    <link rel="stylesheet" href="./css/supporto.css">
</head>
<body>
<div class="header">
    <jsp:include page="header.jsp"/>
</div>

<div class="test">
    <div class="container">
        <div class="title">Pagina Supporto</div>
        <div class="content">
            <form action="VisualizzazioneInformazioniUtenteControl" METHOD="POST">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">E-mail utente</span>
                        <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email non valida" maxlength="50" value="" required>
                    </div>
                </div>
                <div class="button">
                    <input type="submit" name="ordiniUtente" value="Visualizza ordini" style="margin-bottom: 40px">
                    <input type="submit" name="prodottiVenduti" value="Visualizza prodotti venduti" style="margin-bottom: 40px">
                    <input type="submit" name="prodottiInVendita" value="Visualizza prodotti in vendita" style="margin-bottom: 40px">
                    <input type="submit" name="ticket" value="Lista ticket aperti" style="margin-bottom: 40px">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="footer">
    <jsp:include page="footer.jsp"/>
</div>
</body>

</html>

