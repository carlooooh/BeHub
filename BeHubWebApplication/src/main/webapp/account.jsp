<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 27/12/2022
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"       %>
<%--<% if (session.getAttribute("registeredUser") == null) {
    response.sendRedirect("loginPage.jsp");
}
%>
<jsp:useBean id="registeredUser" class="model.UserBean" scope="session"/>
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Area Personale</title>
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
            <form action="AccountSettings" METHOD="POST">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">E-mail</span>
                        <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email non valida" maxlength="50" <%--value="<jsp:getProperty name = "registeredUser" property = "email"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Nome</span>
                        <input type="password" name="password" maxlength="50" <%--value="<jsp:getProperty name = "registeredUser" property = "password"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Nome</span>
                        <input type="text" name="nome" maxlength="50" <%--value="<jsp:getProperty name = "registeredUser" property = "nome"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Cognome</span>
                        <input type="text" name="cognome" maxlength="50" <%--value="<jsp:getProperty name = "registeredUser" property = "cognome"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Indirizzo</span>
                        <input type="text" name="indirizzo" maxlength="50" <%--value="<jsp:getProperty name = "registeredUser" property = "indirizzo"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Data di Nascita</span>
                        <input type="date" name="dataNascita" title="Inserisci la tua data di nascita" <%--value="<jsp:getProperty name = "registeredUser" property = "telefono"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Numero di telefono</span>
                        <input type="text" name="telefono" pattern="[0-9]{10,15}" title="Inserisci un numero di telefono di 10-15 cifre" <%--value="<jsp:getProperty name = "registeredUser" property = "telefono"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Numero carta di credito</span>
                        <input type="text" name="carta" pattern="[0-9]{16}" title="Inserisci un numero valido di 16 cifre" <%--value="<jsp:getProperty name = "registeredUser" property = "numero"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">Scadenza Carta</span>
                        <input type="text" name="scadenza" pattern="[0-9]*" inputmode="numeri c" <%-->value="<jsp:getProperty name = "registeredUser" property = "intestatario"/>"--%>>
                    </div>
                    <div class="input-box">
                        <span class="details">CVV</span>
                        <input type="text" name="cvv" pattern="[0-9]{3}" title="Inserisci un numero di 3 cifre" <%--value="<jsp:getProperty name = "registeredUser" property = "cvv"/>"--%>>
                    </div>
                </div>
                <div class="button">
                    <input type="submit" name="salva" value="Salva informazioni" style="margin-bottom: 40px">
                    <input type="submit" class="ordini" name="ordini" value="Lista ordini">
                    <input  onclick="location.href='ticket.jsp'" type="submit" class="ticket" name="ticket" value="Ticket" style="margin-bottom: 40px">
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
