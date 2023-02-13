<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.DAOImplementation.ProductDAOModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductBean bean = new ProductBean();
    if (request.getAttribute("prodottoInDettaglio") != null) {
        bean = (ProductBean) request.getAttribute("prodottoInDettaglio");
    }
%>
<html>
<head>
    <title>BeHub - Dettagli Prodotto</title>
    <link rel="stylesheet" href="css/prodotto.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
  <div class="product">
    <div class="gallery">
        <img src="<%=bean.getImmagine()%>" alt="immagini/prodotti/scarpe.png">
    </div>
    <div class="details">
        <h1><%=bean.getNome()%></h1>
        <h2><%=bean.getPrezzo()%> euro + <%=bean.getSpedizione()%> euro di spedizione</h2>

        <h3>E-mail venditore: <%=bean.getEmail()%></h3>
        <h3>Condizione: <%=ProductDAOModel.controllaCondizione(bean.getCondizione())%></h3>
        <p><%=bean.getDescrizione()%></p>
        <form action="AcquistaOraControl?codice=<%=bean.getCodice()%>" method="post">

            <div class="quantity-select">
                <p>Quantità</p>
                <input type="number" name="quantity" value="1" min="1" max="<%=bean.getQuantity()%>">
            </div>

            <%
                if (session.getAttribute("utente") != null) {
            %>
            <button name="acquistaOra" value="Acquista Ora" href="AcquistaOra?codice="<%=bean.getCodice()%>>Acquista Ora</button>
            <%  }
            %>
            <button name="aggiungiAlCarrello" value="Aggiungi al Carrello">Aggiungi al Carrello</button>
        </form>
    </div>
  </div>

</div>
<jsp:include page="footer.jsp"/>


</body>
</html>
