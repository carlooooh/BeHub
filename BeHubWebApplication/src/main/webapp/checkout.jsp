<%@ page import="model.CartBean" %>
<%@ page import="model.ProductBean" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>BeHub - Checkout</title>
  <link rel="stylesheet" href="css/acquista.css" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.1.3/TweenMax.min.js"></script>
</head>
<body>
<%
  CartBean carrello = (CartBean) session.getAttribute("carrello");
  Collection<ProductBean> prodotti = carrello.getCarrello();
  Iterator<ProductBean> iterator = prodotti.iterator();
  Double prezzoTot = 0.0;
  while (iterator.hasNext()) {
    ProductBean prodotto = iterator.next();
    Double prezzoProdotto = (prodotto.getPrezzo() + prodotto.getSpedizione()) * prodotto.getQuantity();
    prezzoTot += prezzoProdotto;
  }
  DecimalFormat df = new DecimalFormat("0.00");
  String prezzoTotString = df.format(prezzoTot);
%>
<div class="container">
  <div class="left column">
    <div class="header"><h1 class="ml12">Checkout</h1></div>
    <div class="sottotitolo"> <h3> Concludi il tuo acquisto</h3></div>
    <div class="price">
      <span>Totale</span>
      <span><%=prezzoTotString%> &euro;</span>
    </div>
  </div>
  <div class="right column">
    <div class="nav">
      <div class="nav-item">indirizzo</div>
      ➝
      <div class="nav-item">checkout</div>
    </div>
    <div id="img" class="card-img">
      <img src="immagini/card.png" alt="" />
    </div>


    <div class="container2">
    <form action="AcquistoControl" method="post">

    <div class="step step-1 active">
      <div class="form-group">
        <input type="text" placeholder="indirizzo(via, città, procincia, cap, stato)" id="indirizzo" name="indirizzo" maxlength="150" required>
      </div>
      <div class="form-group">
        <input type="text" id="Ntelefono" name="Ntelefono" pattern="[0-9]{10,15}" title="Inserisci un numero di telefono di 10-15 cifre" placeholder="numero di telefono" required>
      </div>
      <button type="button" class="next-btn">Next</button>
    </div>

    <div class="step step-2">
      <div class="form-group">
        <input type="text" id="numero-carta" name="numero-carta" pattern="[0-9]{16}" title="Inserisci un numero valido di 16 cifre" placeholder="numero carta" required>
      </div>
      <div class="form-group">
        <input type="date" placeholder="scadenza" name="scadenza" required/>
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <input type="text" minlength="3" maxlength="3" placeholder="cvv" name="CVV" required/>
      </div>
      <button type="button" class="previous-btn">Prev</button>
    </div>
    </form>
    </div>


  </div>
</div>

</body>
<script src="prec-suc.js"> </script>
</html>
