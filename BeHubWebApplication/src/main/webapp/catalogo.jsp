<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 27/12/2022
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import=" java.util.*,  java.text.DecimalFormat"%>


<%--
<%
   Collection<?> products = (Collection<?>) request.getAttribute("products");
    if(products == null) {
        response.sendRedirect("./ProductControl");
        return;
    }
    String tipologia = request.getParameter("tipologia");

%>
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geek Factory - Prodotti</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="css/catalogo.css">
</head>
<body>
<%--
<%
    if (session.getAttribute("refreshProduct") != null && (Boolean) session.getAttribute("refreshProduct") == true) {
        request.getSession().setAttribute("refreshProduct", false);
%>

--%>


<%--
<%
    }
%>
--%>
<jsp:include page="header.jsp"/>

<div class = "wrapper" style="margin-bottom: 200px">
    <div class="container">
        <div class = "heading-title" style="margin-top: 75px">
            <h1 style="font-weight: bold;"></h1>
        </div>


        <div class = "item-grid" style="margin-top: 20px">

         <%--   <%
                if (products != null && products.size() != 0) {
                    Iterator<?> it = products.iterator();
                    while (it.hasNext()) {
                        ProductBean bean = (ProductBean) it.next();
                        Double tot = bean.getPrezzo() + bean.getSpedizione();
                        DecimalFormat df = new DecimalFormat("0.00");

                        String prezzoTot = df.format(tot) + " €";
                        String image = "img/productIMG/" + bean.getImmagine();
            %>
        --%>
            <div class="item">
                <div class = "item-img">
                    <img src = "./immagini/scarpe.png" alt = "img/productIMG/noimg.jpg">
                    <div class = "item-action">
                        <a href = "prodotto.jsp?codice=" class = "view">
								<span>
									<i class = "fas fa-search-plus"></i>
								</span>
                        </a>
                        <a href = "CartControl?action=aggiungi&codice=" class = "buy">
								<span>
									<i class = "fas fa-cart-plus"></i>
								</span>
                        </a>
                      <%--  <% if (session.getAttribute("registeredUser") != null) { %>  --%>
                        <a href = "AcquistaOra?codice=" class = "wishlist">
								<span>
									<i class = "fas fa-dollar-sign"></i>
								</span>
                        </a>
                      <%--  <% } %>
                        <% if (session.getAttribute("registeredUser") != null) {
                            UserBean utente = (UserBean) session.getAttribute("registeredUser");
                            if (utente.getEmail().compareTo("") != 0) {
                                String ruolo = (String) session.getAttribute("role");
                                String emailUtente = (String) session.getAttribute("email");
                                if (ruolo.compareTo("admin") == 0 || bean.getEmail().compareTo(emailUtente) == 0) {
                        %>

                        --%>
                        <a href = "ProductControl?action=modificaForm&codice=" class = "upd">
								<span>
									<i class = "far fa-edit" style="color: red"></i>
								</span>
                        </a>

                        <a href = "ProductControl?action=elimina&codice=" class = "del">
								<span>
									<i class = "fas fa-trash-alt" style="color: red"></i>
								</span>
                        </a>


                     <%--   <%
                                    }
                                }
                            }
                        %>

                        --%>
                    </div>
                </div>

                <div class = "item-details">

                    </ul>
                    <p class = "price" style="color: #fff"></p>
                    <p class = "name" style="color: #fff"></p>
                </div>
            </div>
        <%--    <%
                    }
                }
            %>
        --%>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<script>
    let view = document.querySelectorAll('.view');
    let buy = document.querySelectorAll('.buy');
    let wishlist = document.querySelectorAll('.wishlist');
    let del = document.querySelectorAll('.del');

    setContent(view, 'Dettagli');
    setContent(buy, 'Aggiungi al carrello');
    setContent(wishlist, 'Acquista Ora');
    setContent(del, 'Elimina prodotto');

    function setContent(list, text){
        list.forEach((listItem) => {
            listItem.setAttribute('data-before', text);
        });
    }
</script>
</body>
</html>
