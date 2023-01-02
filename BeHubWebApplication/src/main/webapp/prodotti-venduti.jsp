<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 02/01/2023
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.text.DecimalFormat, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="css/carrello.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="icon" href="./img/icon.png">
  <title>Prodotti in Vendita</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<section id="cart-container">
  <div class="small-container cart-page" style="margin-bottom: 480px; margin-top: 110px">
    <h1 style="margin-bottom: 30px; margin-left: 30px; font-family: Courier; color: white">Prodotti In Vendita</h1>
    <table>
      <tr>
        <th>Codice Prodotto</th>
        <th>Immagine</th>
        <th>Nome</th>
        <th>Prezzo</th>
        <th>Quantità Venduta</th>
        <th>Condizioni Prodotto</th>
        <th>Categoria</th>
        <th>
        <form action=track-code>
          <input type="text"><br>
          <input type="submit" value="Applica">
        </form>
        </th>
        <th>
          <select>
            <option value="lavoro">In Lavorazione...</option>
            <option value="spedito">Spedito</option>

          </select>
        </th>
        <th></th>
      </tr>


      <%
        Collection<OrderBean> ordini = null;
        DecimalFormat df = new DecimalFormat("0.00");


		/*if (session.getAttribute("ControlOrd") != null) {
			Boolean control = (Boolean) session.getAttribute("ControlOrd");
			if (control == true) {
				response.sendRedirect("OrderControl?action=ottieni");
			}
		}
		*/
        ordini = (Collection<OrderBean>) session.getAttribute("listaOrdini");
        String email = null;
        if (request.getParameter("email") != null) {
          email = request.getParameter("email");
        }
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
        <td><%=ord.getCodice()%></td>
        <td>
          <div class="order-info">
            <img src="<%=image%>" style="width: 120px; height: 120px">
          </div>
        </td>
        <td><%=prod.getNome()%></td>
        <td id="price"><%=tot%> &euro;</td>
        <td id="quantity"><%=prod.getQuantity()%></td>
        <td><%=ord.getData() %></td>
        <td class="product-price" id="subtotal"><%=ord.getVenditore()%></td>


      </tr>

    </table>
  </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
