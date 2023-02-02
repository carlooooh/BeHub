<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.text.DecimalFormat, java.util.*"%>
<%@ page import="model.bean.OrderBean" %>
<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.DAOImplementation.OrderDAOModel" %>
<%@ page import="model.bean.UserBean" %>
<%
  if (session.getAttribute("utente") == null) {
    response.sendRedirect("/accesso.jsp");
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="css/carrello.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="icon" href="./img/icon.png">
  <title>BeHub - Prodotti Venduti</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<section id="cart-container">
  <div class="small-container cart-page" style="margin-bottom: 480px; margin-top: 110px">
    <h1 style="margin-bottom: 30px; margin-left: 30px; font-family: Courier; color: white">Prodotti Venduti</h1>
    <table>
      <tr>
        <th>Immagine</th>
        <th>Nome</th>
        <th>Prezzo</th>
        <th>Quantità Venduta</th>
        <th>Data Vendita</th>
        <th>Email cliente</th>
        <th>Stato</th>
        <th>Tracking</th>
        <th></th>
      </tr>


      <%
        Collection<OrderBean> ordini = (Collection<OrderBean>) session.getAttribute("listaProdottiVenduti");
        DecimalFormat df = new DecimalFormat("0.00");
        UserBean utente = (UserBean) session.getAttribute("utente");

        if (ordini != null && ordini.size() != 0) {
          Iterator<?> it = ordini.iterator();
          while (it.hasNext()) {
            OrderBean ord = (OrderBean) it.next();
            ProductBean prod = ord.getProdotto();

            Double tot = prod.getPrezzo();
            String prezzoTot = df.format(tot);

            String image = "img/productIMG/" + prod.getImmagine();
      %>
      <tr>
        <td>
          <div class="order-info">
            <img src="<%=image%>" style="width: 120px; height: 120px">
          </div>
        </td>
        <td><%=prod.getNome()%></td>
        <td id="price"><%=prezzoTot%> &euro;</td>
        <td id="quantity"><%=prod.getQuantity()%></td>
        <td><%=ord.getData()%></td>
        <td class="product-price" id="subtotal"><%=ord.getEmail()%></td>
        <td><%=OrderDAOModel.parseStato(ord.getStato())%></td>
        <%
          if (ord.getTracking() != null) {
        %>
        <td><%=ord.getTracking()%></td>
        <%
          }
          else if (utente.getRole().compareTo("RU") == 0) { //non viene visualizzato dall'addetto al supporto
        %>
        <td>
          <form action="InserimentoTrackingControl?codiceOrdine=<%=ord.getCodice()%>" method="post">
            <input type="text" name="tracking" maxlength="50" size="10" required><br>
            <input type="submit" value="&#10004;">
          </form>
        </td>
        <%
          }
          else {
        %>
        <td>N/D</td>
        <%
          }
        %>
        <td></td>
      </tr>
      <%  }
        }
      %>
    </table>
  </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
