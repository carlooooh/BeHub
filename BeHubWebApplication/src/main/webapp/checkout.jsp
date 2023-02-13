<%@ page import="model.bean.CartBean" %>
<%@ page import="model.bean.ProductBean" %>
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
<div class="mainscreen">
  <div class="card">
    <div class="leftside">
      <img
              src="https://i.pinimg.com/originals/18/9d/dc/189ddc1221d9c1c779dda4ad37a35fa1.png"
              class="product"
      />
    </div>
    <div class="rightside">
      <form action="AcquistoControl" method="post">
        <h1>Checkout</h1>
        <h2>Informazioni di pagamento</h2>
        <h3>Totale: <%=prezzoTotString + " euro"%></h3>
        <p>Indirizzo</p>
        <input type="text" class="inputbox" placeholder="Indirizzo(via, città, provincia, cap, stato)" maxlength="150" name="indirizzo" id="indirizzo" required />
        <p>Telefono</p>
        <input type="text" class="inputbox" name="Ntelefono" placeholder="**********" pattern="[0-9]{10,15}" title="Inserisci un numero di telefono di 10-15 cifre" id="telefono" required />

        <p>Numero Carta</p>
        <input type="text" class="inputbox" placeholder="****************" name="numero-carta" id="card_number" required />
        <div class="expcvv">
          <p class="expcvv_text">Expiry</p>
          <input type="date" class="inputbox" name="exp_date" name="scadenza" id="exp_date" required />

          <p class="expcvv_text2">CVV</p>
          <input type="password" class="inputbox" name="cvv" id="cvv" placeholder="***" required />
        </div>
        <p></p>
        <button type="submit" class="button" name="acquisto-button">Concludi acquisto</button>
      </form>
    </div>
  </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
