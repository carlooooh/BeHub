<%@ page import="model.bean.OrderBean" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.DAOImplementation.OrderDAOModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/carrello.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="icon" href="./img/icon.png">
    <title>BeHub - Ordini</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<section id="cart-container">
    <div class="small-container cart-page" style="margin-bottom: 480px; margin-top: 110px">
        <h1 style="margin-bottom: 30px; margin-left: 30px; font-family: Courier; color: white">Ordini</h1>
        <table>
            <tr>
                <th>Immagine</th>
                <th>Nome</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Email Venditore</th>
                <th>Data Acquisto</th>
                <th>Stato</th>
                <th>Tracking</th>
                <th></th>
            </tr>


            <%
                Collection<OrderBean> listaOrdini = (Collection<OrderBean>) session.getAttribute("listaOrdini");
                DecimalFormat df = new DecimalFormat("0.00");
                String email = request.getParameter("email");

                if (listaOrdini != null && listaOrdini.size() != 0) {
                    Iterator<?> it = listaOrdini.iterator();
                    while (it.hasNext()) {
                        OrderBean ordine = (OrderBean) it.next();
                        ProductBean prod = ordine.getProdotto();

                        Double tot = prod.getPrezzo();
                        String prezzoTot = df.format(tot);

                        String image = prod.getImmagine();
            %>
            <tr>
                <td>
                    <div class="order-info">
                        <img src="<%=image%>" alt="immagini/prodotti/scarpe.png" style="width: 120px; height: 120px">
                    </div>
                </td>
                <td><%=prod.getNome()%></td>
                <td id="price"><%=prezzoTot%> &euro;</td>
                <td id="quantity"><%=prod.getQuantity()%></td>
                <td><%=prod.getEmail()%></td>
                <td><%=ordine.getData()%></td>
                <td><%=OrderDAOModel.parseStato(ordine.getStato())%></td>
                <%
                    if (ordine.getTracking() != null) {
                %>
                <td><%=ordine.getTracking()%></td>
                <%  }
                    else {
                %>
                <td>N/D</td>
                <%
                    }
                %>
                <td></td>
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

