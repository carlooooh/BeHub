<%@ page import="model.bean.CartBean" %>
<%@ page import="model.bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Header</title>
  <link rel="stylesheet" href="./css/header.css">
  <script src="https://kit.fontawesome.com/2a950a9b0a.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
  <%
    if (session.getAttribute("carrello") == null) {
      CartBean carrello = new CartBean();
      session.setAttribute("carrello", carrello);
    }
  %>
  <div class="left-nav2">
    <ul>
      <li>
        <div class="home">
          <a href="index.jsp"><img src="./immagini/logoBeHub2.png"></a>
          <a href="index.jsp" data-item="home" class="left-nav-link2">Home</a>
        </div>
      </li>
      <% if (session.getAttribute("utente") != null) {
        UserBean utente = (UserBean) session.getAttribute("utente");
        if (utente.getRole().compareTo("RU") == 0) {
      %>
      <li>
        <div class="areapersonale">
          <i class="fa-solid fa-user-astronaut fa-2xl"></i>
          <a href="account.jsp" data-item="area personale" class="left-nav-link2">Area Personale</a>
        </div>
      </li>
      <%
      }
      else if (utente.getRole().compareTo("AS") == 0){
      %>
      <li>
        <div class="areapersonale">
          <i class="fa-solid fa-user-astronaut fa-2xl"></i>
          <a href="supporto.jsp" data-item="area personale" class="left-nav-link2">Pagina supporto</a>
        </div>
      </li>
      <%
        }
      %>
      <%
        if (utente.getRole().compareTo("RU") == 0) {
      %>
      <li>
        <div class="vendi">
          <i class="fa-solid fa-sack-dollar fa-2xl"></i>
          <a href="vendi.jsp" data-item="vendi" class="left-nav-link2">Vendi</a>
        </div>
      </li>


      <% }
      }
      else {
      %>

      <li>
        <div class="accedi">
          <i class="fa-regular fa-address-card fa-2xl color" style="color: white"></i>
          <a href="accesso.jsp" data-item="accedi" class="left-nav-link2">Accedi</a>
        </div>
      </li>
      <%
        }
      %>
    </ul>
  </div>

  <div class="ms-auto right-nav2">

    <ul>
      <% if (session.getAttribute("utente") != null) {
      %>
      <li>
        <div class="logout">
          <a href="LogoutControl">
            <i class="fa-solid fa-person-through-window fa-2xl"></i>
          </a>
        </div>
      </li>
      <% }
      %>

      <li>
        <div class="carrello">
          <a href="carrello.jsp">
            <i class="fa-solid fa-basket-shopping fa-2xl"></i>
          </a>
        </div>
      </li>
    </ul>
  </div>
</header>
</body>
</html>

