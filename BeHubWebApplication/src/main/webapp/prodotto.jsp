<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 01/01/2023
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dettagli Prodotto</title>
    <link rel="stylesheet" href="css/prodotto.css">


</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
  <div class="product">
    <div class="gallery">
        <img src="immagini/scarpe.png">
    </div>
    <div class="details">
        <h1>Nome Prodotto</h1>
        <h2>0.00$</h2>

        <h3>E-mail venditore:</h3>
        <h3>Stato:</h3>
        <p>Una descrizione per il prodotto selezionato</p>
        <form>

            <div class="quantity-select">
                <p>Quantità</p>
                <input type="number" value="1">
            </div>

            <button onclick="location.href='acquista.jsp'">Acquista Ora</button>
            <button onclick="location.href='carrello.jsp'">Aggiungi al Carrello</button>
        </form>
    </div>
  </div>

</div>
<jsp:include page="footer.jsp"/>


</body>
</html>
