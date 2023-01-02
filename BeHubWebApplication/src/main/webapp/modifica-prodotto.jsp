<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 02/01/2023
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<% if (session.getAttribute("registeredUser") == null) {
  response.sendRedirect("loginPage.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Geek Factory - Modifica prodotto</title>
  <link rel="stylesheet" href="./css/account.css">
  <link rel="icon" href="./img/icon.png">
</head>
<body>
<div class="header">
  <jsp:include page="header.jsp"/>
</div>
<%--
<%	if (request.getAttribute("updateProd") != null) {
  ProductBean bean = (ProductBean) request.getAttribute("updateProd");

  String tipologia = bean.getTipologia();
  String tag = bean.getTag();
%>
--%>
<div class="test">
  <div class="container" style="height: 485px">
    <div class="title">Modifica prodotto</div>
    <div class="content">
      <form action="ProductControl?action=modifica&codice=<%=bean.getCodice()%>" METHOD="POST">
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
            <span class="details">Tipologia</span>
            <select id="tipologia" name="tipologia" required>
              <% if (tipologia.compareTo("Action Figures") == 0) { %>
              <option value="Action Figures" selected>Action Figures</option>
              <% } else { %>
              <option value="Action Figures">Action Figures</option>
              <% } %>
              <% if (tipologia.compareTo("Gadget") == 0) { %>
              <option value="Gadget" selected>Gadget</option>
              <% } else { %>
              <option value="Gadget">Gadget</option>
              <% } %>
              <% if (tipologia.compareTo("Arredamento Casa") == 0) { %>
              <option value="Arredamento Casa" selected>Arredamento Casa</option>
              <% } else { %>
              <option value="Arredamento Casa">Arredamento Casa</option>
              <% } %>
            </select>
          </div>
          <div class="input-box">
            <span class="details">Tag</span>
            <select id="tag" name="tag" required>
              <% if (tag.compareTo("Manga/Anime") == 0) { %>
              <option value="Manga/Anime" selected>Manga/Anime</option>
              <% } else { %>
              <option value="Manga/Anime">Manga/Anime</option>
              <% } %>
              <% if (tag.compareTo("Film/Serie TV") == 0) { %>
              <option value="Film/Serie TV" selected>Film/Serie TV</option>
              <% } else { %>
              <option value="Film/Serie TV">Film/Serie TV</option>
              <% } %>
              <% if (tag.compareTo("Videogiochi") == 0) { %>
              <option value="Videogiochi" selected>Videogiochi</option>
              <% } else { %>
              <option value="Videogiochi">Videogiochi</option>
              <% } %>
              <% if (tag.compareTo("Originali") == 0) { %>
              <option value="Originali" selected>Originali</option>
              <% } else { %>
              <option value="Originali">Originali</option>
              <% } %>
            </select>
          </div>
          <div class="input-box">
            <span class="details">Descrizione</span>
            <textarea id="descrizione" name="descrizione" rows="4" cols="60" style="resize: none; width: 310px; height: 80px" required><%=bean.getDescrizione()%></textarea>
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