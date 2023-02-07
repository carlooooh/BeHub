<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<% if (session.getAttribute("utente") == null) {
    response.sendRedirect("accesso.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BeHub - Vendita</title>
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
            <form action="VenditaControl" enctype="multipart/form-data" METHOD="POST">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">Nome prodotto</span>
                        <input type="text" name="nome" maxlength="50" placeholder="Inserire nome prodotto" autofocus required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Prezzo</span>
                        <input type="text" name="prezzo" pattern="[0-9]{1-6}" title="Inserire numero valido" placeholder="Inserire prezzo" required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Spese di spedizione</span>
                        <input type="text" name="spedizione" pattern="[0-9]{1-4}" title="Inserire numero valido" placeholder="Inserire spese di spedizione" required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Quantità</span>
                        <input type="text" name="quantity" pattern="[0-9]{1-3}" title="Inserire numero valido" placeholder="Inserire quantità prodotto" required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Immagine</span>
                        <input type="file" id="immagine" name="immagine" accept="image/png, image/gif, image/jpeg" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Categoria</span>
                        <select id="categoria" name="categoria" required>
                            <option value="Abbigliamento">Abbigliamento</option>
                            <option value="Calzature">Calzature</option>
                            <option value="Elettronica">Elettronica</option>
                            <option value="Libri">Libri</option>
                            <option value="Giochi">Giochi</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Condizioni Prodotto</span>
                        <select id="condizioni" name="condizioni" required>
                            <option value="Nuovo">Nuovo</option>
                            <option value="Usato">Usato</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Descrizione</span>
                        <textarea id="descrizione" name="descrizione" rows="4" cols="50" style="resize: none; width: 300px; height: 80px" required></textarea> <!-- 93 -->
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