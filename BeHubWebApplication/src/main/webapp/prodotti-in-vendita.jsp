<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 02/01/2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.text.DecimalFormat, java.util.*"%>
<%@ page import="model.OrderBean" %>
<%@ page import="model.ProductBean" %>
<%@ page import="model.ProductModel" %>
<% if (session.getAttribute("utente") == null) {
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
  <title>BeHub - Prodotti in Vendita</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<section id="cart-container">
  <div class="small-container cart-page" style="margin-bottom: 480px; margin-top: 110px">
    <h1 style="margin-bottom: 30px; margin-left: 30px; font-family: Courier; color: white">Prodotti In Vendita</h1>
    <table>
      <tr>
        <th>Immagine</th>
        <th>Nome</th>
        <th>Prezzo</th>
        <th>Quantità</th>
        <th>Categoria</th>
        <th>Condizione</th>
        <th></th>
      </tr>


      <%
        Collection<ProductBean> prodottiInVendita = (Collection<ProductBean>) session.getAttribute("prodottiInVendita");
        DecimalFormat df = new DecimalFormat("0.00");
        String email = request.getParameter("email");

        if (prodottiInVendita != null && prodottiInVendita.size() != 0) {
          Iterator<?> it = prodottiInVendita.iterator();
          while (it.hasNext()) {
            ProductBean prod = (ProductBean) it.next();

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
        <td><%=ProductModel.controllaCategoria(prod.getCategoria())%></td>
        <td><%=ProductModel.controllaCondizione(prod.getCondizione())%></td>
        <td><i class = "far fa-edit" style="color: red; cursor: pointer" href="ProdottoDaModificareControl?codice=<%=prod.getCodice()%>"></i></td>
      </tr>
    <%
        }
      }
    %>
    </table>
  </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
