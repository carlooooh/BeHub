<%@ page import="model.bean.ProductBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<% if (session.getAttribute("utente") == null) {
  response.sendRedirect("accesso.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>BeHub - Modifica Prodotto</title>
  <link rel="stylesheet" href="./css/account.css">
  <link rel="icon" href="./img/icon.png">
</head>
<body>
<div class="header">
  <jsp:include page="header.jsp"/>
</div>

<%	if (request.getAttribute("updateProd") != null) {
  ProductBean bean = (ProductBean) request.getAttribute("updateProd");

  int categoria = bean.getCategoria();
  int condizione = bean.getCondizione();
%>

<div class="test">
  <div class="container" style="height: 485px">
    <div class="title">Modifica prodotto</div>
    <div class="content">
      <form action="ModificaProdottoControl?codice=<%=bean.getCodice()%>" METHOD="POST">
        <div class="user-details">
          <div class="input-box">
            <span class="details">Nome prodotto</span>
            <input type="text" name="nome" maxlength="50" placeholder="Inserire nome del prodotto" value="<%=bean.getNome()%>" required/>
          </div>
          <div class="input-box">
            <span class="details">Prezzo</span>
            <input type="number" step="0.01" name="prezzo" max="9999999" value="<%=bean.getPrezzo()%>" placeholder="Inserire prezzo" required/>
          </div>
          <div class="input-box">
            <span class="details">Spese di spedizione</span>
            <input type="number" step="0.01" name="spedizione" max="999" value="<%=bean.getSpedizione()%>" placeholder="Inserire spese di spedizione" required/>
          </div>
          <div class="input-box">
            <span class="details">Quantità</span>
            <input type="number" step="1" name="quantity" max="99" value="<%=bean.getQuantity()%>" placeholder="Inserire quantità" required/>
          </div>
          <div class="input-box">
            <span class="details">Categoria</span>
            <select id="categoria" name="categoria" required>
              <% if (categoria == 0) { %>
              <option value="Abbigliamento" selected>Abbigliamento</option>
              <% } else { %>
              <option value="Abbigliamento">Abbigliamento</option>
              <% } %>
              <% if (categoria == 1) { %>
              <option value="Calzature" selected>Calzature</option>
              <% } else { %>
              <option value="Calzature">Calzature</option>
              <% } %>
              <% if (categoria == 2) { %>
              <option value="Elettronica" selected>Elettronica</option>
              <% } else { %>
              <option value="Elettronica">Elettronica</option>
              <% } %>
              <% if (categoria == 3) { %>
              <option value="Libri" selected>Libri</option>
              <% } else { %>
              <option value="Libri">Libri</option>
              <% } %>
              <% if (categoria == 4) { %>
              <option value="Giocattoli" selected>Giocattoli</option>
              <% } else { %>
              <option value="Giocattoli">Giocattoli</option>
              <% } %>
            </select>
          </div>
          <div class="input-box">
            <span class="details">Condizione</span>
            <select id="condizione" name="condizione" required>
              <% if (condizione == 0) { %>
              <option value="Nuovo" selected>Nuovo</option>
              <% } else { %>
              <option value="Nuovo">Nuovo</option>
              <% } %>
              <% if (condizione == 1) { %>
              <option value="Usato" selected>Usato</option>
              <% } else { %>
              <option value="Usato">Usato</option>
              <% } %>
            </select>
          </div>
          <div class="input-box">
            <span class="details">Descrizione</span>
            <textarea style="resize: none" id="descrizione" name="descrizione" rows="4" cols="60" style="resize: none; width: 310px; height: 80px" required><%=bean.getDescrizione()%></textarea>
          </div>
        </div>
        <div class="button">
          <input type="submit" name="salva" value="Salva modifiche" style="margin-bottom: 40px">
        </div>
      </form>

    </div>
  </div>
</div>
<% } %>

<div class="footer">
  <jsp:include page="footer.jsp"/>
</div>
</body>

</html>