<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="model.CartBean, model.ProductBean, java.text.DecimalFormat, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/carrello.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="icon" href="./img/icon.png">
    <title>BeHub - Carrello</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<section id="cart-container">

    <div class="small-container cart-page" style="margin-bottom: 300px; margin-top: 110px">
        <table>
            <tr>
                <th>Immagine</th>
                <th>Prodotto</th>
                <th>Prezzo</th>
                <th>Costo spedizione</th>
                <th>Quantità</th>
                <th>Rimuovi</th>
                <th>Subtotal</th>
            </tr>
            <%
                Collection<ProductBean> carrello = null;
                double somma = 0;
                DecimalFormat df = new DecimalFormat("0.00");
                if (session.getAttribute("carrello") != null) {
                    CartBean cart = (CartBean) session.getAttribute("carrello");
                    carrello = cart.getCarrello();
                }
                if (carrello != null && carrello.size() != 0) {
                    Iterator<?> it = carrello.iterator();
                    while (it.hasNext()) {
                        ProductBean bean = (ProductBean) it.next();

                        Double price = bean.getPrezzo();
                        String prezzo = df.format(price);

                        Double sped = bean.getSpedizione();
                        String spedizione = df.format(sped);

                        Double tot = (bean.getPrezzo() + bean.getSpedizione()) * bean.getQuantity();
                        String prezzoTot = df.format(tot);
                        somma += tot;

                        String image = "img/productIMG/" + bean.getImmagine();
            %>
            <tr>
                <td>
                    <div class="cart-info">
                        <img src="<%=image%>" style="width: 160px; height: 160px">
                    </div>
                </td>
                <td><%=bean.getNome()%></td>
                <td id="price"><%=prezzo%> &euro;</td>
                <td id="sped"><%=spedizione%> &euro;</td>
                <td>
                    <a href="DiminuisciQuantitàControl?dimCodice=<%=bean.getCodice()%>"><i class="fa-solid fa-minus"></i></a>
                    <input id="quantity" type="number" value="<%=bean.getQuantity()%>" min="1" max="<%=bean.getMaxQuantity()%>" readonly>
                    <a href="AumentaQuantitàControl?aumCodice=<%=bean.getCodice()%>"><i class="fa-solid fa-plus"></i></a>
                </td>
                <td><a href="RimozioneProdottoControl?codice=<%=bean.getCodice()%>"><i class="fas fa-trash-alt"></i></a></td>
                <td class="product-price" id="subtotal"><%=prezzoTot%> &euro;</td>
            </tr>
            <% 		}
            }
            %>
        </table>

        <%  String totale = df.format(somma);
            if (carrello != null && !carrello.isEmpty()) {
        %>
        <div class="total-price">
            <table>
                <tr>
                    <td>Totale</td>
                    <td><%=totale%> &euro;</td>
                </tr>
            </table>
        </div>
        <%
            if (session.getAttribute("utente") != null) {


        %>
        <div class="button-buy-container">
            <a href="checkout.jsp"><button class="buy-button" style="cursor: pointer">PROCEDI ALL'ACQUISTO</button></a>
        </div>

        <% 	}
        else {
        %>
        <div class="button-buy-container">
            <a href="accesso.jsp"><button class="buy-button" style="cursor: pointer">REGISTRATI PER ACQUISTARE</button></a>
        </div>
        <%
            }
        %>
        <% }
        else {
        %>
        <div class="total-price" style="margin-top: 20px; margin-bottom: 500px">
            <table>
                <tr>
                    <td style="font-weight: 500">Carrello vuoto! Inserisci un prodotto per continuare</td>
                </tr>
            </table>
        </div>
        <% }
        %>

    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
