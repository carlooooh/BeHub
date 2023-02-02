<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import=" java.util.*,  java.text.DecimalFormat"%>
<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.bean.UserBean" %>


<%
    if (request.getAttribute("products") != null) {
        Collection<?> products = (Collection<?>) request.getAttribute("products");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BeHub - Catalogo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="css/catalogo.css">
</head>
<body>
<div class="header">
<jsp:include page="header.jsp"/>
</div>
<div class = "wrapper" style="margin-bottom: 200px">
    <div class="container">
        <div class = "heading-title" style="margin-top: 75px">
            <h1 style="font-weight: bold;"></h1>
        </div>


        <div class = "item-grid" style="margin-top: 20px">

            <%
                if (products != null && products.size() != 0) {
                    Iterator<?> it = products.iterator();
                    while (it.hasNext()) {
                        ProductBean bean = (ProductBean) it.next();
                        Double tot = bean.getPrezzo() + bean.getSpedizione();
                        DecimalFormat df = new DecimalFormat("0.00");

                        String prezzoTot = df.format(tot) + " €";
                        String image = "immagini/scarpe.png";
            %>

            <div class="item">
                <div class = "item-img">
                    <img src = "./immagini/scarpe.png" alt = "img/productIMG/noimg.jpg">
                    <div class = "item-action">
                        <a href = "DettagliProdottoControl?codice=<%=bean.getCodice()%>" class = "view">
								<span>
									<i class = "fas fa-search-plus"></i>
								</span>
                        </a>
                        <a href = "InserimentoProdottoControl?codice=<%=bean.getCodice()%>&quantity=1" class = "buy">
								<span>
									<i class = "fas fa-cart-plus"></i>
								</span>
                        </a>
                        <% if (session.getAttribute("utente") != null) { %>
                        <a href = "AcquistaOra?codice="<%=bean.getCodice()%> class = "wishlist">
								<span>
									<i class = "fas fa-dollar-sign"></i>
								</span>
                        </a>
                        <% } %>
                        <% if (session.getAttribute("utente") != null) {
                            UserBean utente = (UserBean) session.getAttribute("utente");
                            if (utente.getEmail().compareTo("") != 0) {
                                String ruolo = (String) session.getAttribute("ruolo");
                                String emailUtente = (String) session.getAttribute("email");
                                if (ruolo.compareTo("AC") == 0 || bean.getEmail().compareTo(emailUtente) == 0) {
                        %>


                        <a href = "ProdottoDaModificareControl?codice=<%=bean.getCodice()%>" class = "upd">
								<span>
									<i class = "far fa-edit" style="color: red"></i>
								</span>
                        </a>

                        <a href = "EliminaProdottoCatalogoControl?codice=<%=bean.getCodice()%>" class = "del">
								<span>
									<i class = "fas fa-trash-alt" style="color: red"></i>
								</span>
                        </a>


                        <%
                                    }
                                }
                            }
                        %>


                    </div>
                </div>

                <div class = "item-details">

                    </ul>
                    <p class = "price" style="color: #fff"><%=prezzoTot%></p>
                    <p class = "name" style="color: #fff"><%=bean.getNome()%></p>
                </div>
            </div>
            <%
                    }
                }
                }
            %>
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
