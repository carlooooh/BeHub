<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 27/12/2022
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<%--
<% if (session.getAttribute("registeredUser") == null) {
    response.sendRedirect("loginPage.jsp");
}
%>
<jsp:useBean id="registeredUser"  scope="session"/>
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geek Factory - Vendita</title>
    <link rel="stylesheet" href="./css/account.css">
    <link rel="icon" href="./img/icon.png">
</head>
<body>
<div class="header">
    <jsp:include page="header.jsp"/>
</div>

<div class="test">
    <div class="container" style="height: 540px">
        <div class="title">Inserisci informazioni sul prodotto</div>
        <div class="content">
            <form action="Vendita" enctype="multipart/form-data" METHOD="POST">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">Nome prodotto</span>
                        <input type="text" name="nome" maxlength="50" placeholder="Inserire nome prodotto" autofocus required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Prezzo</span>
                        <input type="number" step="0.01" name="prezzo" max="9999999" placeholder="Inserire prezzo" required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Quantità</span>
                        <input type="number" step="0" name="spedizione" max="999" placeholder="Inserire quantità prodotto" required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Immagine</span>
                        <input type="file" id="immagine" name="immagine" accept="image/png, image/gif, image/jpeg" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Categoria</span>
                        <select id="tipologia" name="tipologia" required>
                            <option value="Action Figures">Abbigliamento</option>
                            <option value="Gadget">Calzature</option>
                            <option value="Arredamento Casa">Elettronica</option>
                            <option value="Action Figures">Libri</option>
                            <option value="Action Figures">Giochi</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Condizioni Prodotto</span>
                        <select id="condizioni" name="condizioni" required>
                            <option value="Action Figures">Nuovo</option>
                            <option value="Gadget">Usato</option>

                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Descrizione</span>
                        <textarea id="descrizione" name="descrizione" rows="4" cols="50" style="resize: none; width: 450px; height: 80px" required></textarea> <!-- 93 -->
                    </div>
                </div>
                <div class="button">
                    <input type="submit" class="vendi" name="vendi" id="vendi" value="Vendi il prodotto" style="margin-bottom: 40px">
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