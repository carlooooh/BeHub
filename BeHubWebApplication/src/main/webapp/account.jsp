<%@ page import="model.bean.UserBean" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"       %>
<% if (session.getAttribute("utente") == null) {
    response.sendRedirect("/accesso.jsp");
}
    UserBean utente = (UserBean) session.getAttribute("utente");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BeHub - Area Personale</title>
    <link rel="stylesheet" href="./css/account.css">
</head>
<body>
<div class="header">
    <jsp:include page="header.jsp"/>
</div>

<div class="test">
    <div class="container">
        <div class="title">Area Personale</div>
        <div class="content">
            <form action="ModificaInformazioniControl" METHOD="POST">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">E-mail</span>
                        <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email non valida" maxlength="50" value="<%=utente.getEmail()%>" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Nome</span>
                        <input type="text" name="nome" maxlength="50" value="<%=utente.getNome()%>" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Cognome</span>
                        <input type="text" name="cognome" maxlength="50" value="<%=utente.getCognome()%>" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Indirizzo (Via, Città, Procincia, CAP, Stato)</span>
                        <input type="text" name="indirizzo" maxlength="150" value="<%=utente.getIndirizzo()%>" required>
                    </div>
                    <%
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String data = df.format(utente.getData());
                    %>
                    <div class="input-box">
                        <span class="details">Data di Nascita</span>
                        <input type="date" name="dataNascita"  title="Inserisci la tua data di nascita" value="<%=data%>" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Numero di telefono</span>
                        <input type="text" name="telefono" pattern="[0-9]{10,15}" title="Inserisci un numero di telefono di 10-15 cifre" value="<%=utente.getTelefono()%>" required>
                    </div>
                    <%
                        if (utente.getNumero() != null) {
                            String numeroCarta = utente.getNumero();
                            String numeroCartaFormattato = numeroCarta.substring(0, 4) + "xxxxxxxx" + numeroCarta.substring(9);
                    %>
                    <div class="input-box">
                        <span class="details">Numero carta di credito</span>
                        <input type="text" name="carta" pattern="[0-9]{16}" title="Inserisci un numero valido di 16 cifre" value="<%=numeroCartaFormattato%>">
                    </div>
                    <%
                        }
                        else {
                    %>
                    <div class="input-box">
                        <span class="details">Numero carta di credito</span>
                        <input type="text" name="carta" pattern="[0-9]{16}" title="Inserisci un numero valido di 16 cifre" value="">
                    </div>
                    <%
                        }
                        if (utente.getIntestatario() == null) {
                    %>
                    <div class="input-box">
                        <span class="details">Intestatario carta</span>
                        <input type="text" name="intestatario" maxlength="50" value="<%=utente.getIntestatario()%>">
                    </div>
                    <%
                        }
                        else {
                    %>
                    <div class="input-box">
                        <span class="details">Intestatario carta</span>
                        <input type="text" name="intestatario" maxlength="50" value="">
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="button">
                    <input type="submit" name="salva" value="Salva informazioni" style="margin-bottom: 40px">
                    <input type="submit" name="ordini" value="Lista ordini">
                    <input type="submit" name="ticket" value="Ticket" style="margin-bottom: 40px">
                    <input type="submit" name="prodottiVenduti" value="Prodotti Venduti" style="margin-bottom: 40px">
                    <input type="submit" name="prodottiInVendita" value="Prodotti In Vendita" style="margin-bottom: 40px">
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
