<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 24/12/2022
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Header</title>
  <link rel="stylesheet" href="./css/header.css">
</head>
<body>
<header>

  <div class="left-nav">
    <ul>

    <%--  <% if (session.getAttribute("registeredUser") != null) {
        UserBean bean = (UserBean) session.getAttribute("registeredUser");
        if (bean.getEmail().compareTo("") != 0) {
      %>
    --%>

      <li>
        <div class="home">
          <a href="index.jsp"><img src="./immagini/logoBeHub2.png"></a>
          <a class="a-header" href="index.jsp">Home</a>
        </div>
      </li>
      <li>
        <div class="areapersonale">
          <a href="account.jsp"><img src="./immagini/areapersonale.png"></a>
          <a class="a-header" href="account.jsp">Area Personale</a>
        </div>
      </li>
      <li>
        <div class="vendi">
          <a   href="vendi.jsp" class="vendi-btn"> VENDI </a>

        </div>
      </li>

     <%--
      <%}else{
          %>


      <li>
        <div class="home">
          <a href="index.jsp"><img src="./img/icone/home-ico.png"></a>
          <a class="a-header" href="index.jsp">Home</a>
        </div>
      </li>
      <li>
        <div class="cart">
          <a href="accedi.jsp"><img src="./img/icone/carello-ico.png"></a>
          <a class="a-header" href="cart.jsp">Accedi/Registrati</a>
        </div>
      </li>
      <%
        }
      %>

      --%>
    </ul>
  </div>



  <div class="right-nav">

    <ul>
    <%--
      <% if (session.getAttribute("registeredUser") != null) {
        UserBean bean = (UserBean) session.getAttribute("registeredUser");
        if (bean.getEmail().compareTo("") != 0) {
      %>



     <%-- <%
        if (bean.getRole().compareTo("admin") == 0) {
      %>


      <li>
        <div class="account">
          <a href="admin-hub.jsp"><img src="./img/icone/account-ico.png"></a>
          <a class="a-header" href="admin-hub.jsp">Carrello</a>
        </div>
      </li>
      <li>
        <div class="account">
          <a href="admin-hub.jsp"><img src="./img/icone/account-ico.png"></a>
          <a class="a-header" href="admin-hub.jsp">Logout</a>
        </div>
      </li>

      <%			}
      else {
      %>
      --%>


      <li>
        <div class="carrello">
          <a href="carrello.jsp"><img src="./immagini/carrello2.png"></a>
        </div>
      </li>

     <%-- <%	}		}
      %>
    --%>

    </ul>

  </div>


</header>

</body>
</html>

