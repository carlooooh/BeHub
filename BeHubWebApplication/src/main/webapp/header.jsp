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
  <script src="https://kit.fontawesome.com/2a950a9b0a.js" crossorigin="anonymous"></script>
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
          <a href="index.jsp" data-item="home" class="left-nav-link">Home</a>
        </div>
      </li>
      <li>
        <div class="areapersonale">
          <i class="fa-solid fa-user-astronaut fa-2xl"></i>
          <a href="account.jsp" data-item="area personale" class="left-nav-link">Area Personale</a>
        </div>
      </li>
      <li>
        <div class="vendi">
          <i class="fa-solid fa-sack-dollar fa-2xl"></i>
          <a href="vendi.jsp" data-item="vendi" class="left-nav-link">Vendi</a>
        </div>
      </li>

      <%--
       <%}else{
           %>


       <li>
         <div class="home">
           <a href="index.jsp"><img src="./immagini/logoBeHub2.png"></a>
           <a href="index.jsp" data-item="home" class="left-nav-link">Home</a>
         </div>
       </li>
       <li>
         <div class="accedi">
           <i class="fa-regular fa-address-card fa-2xl color" style="color: white"></i>
           <a href="account.jsp" data-item="accedi" class="left-nav-link">Accedi</a>
         </div>
       </li>
       <%
         }
       %>

       --%>
    </ul>
  </div>



  <div class="ms-auto right-nav">

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
        <div class="logout">
          <a href="">
            <i class="fa-solid fa-person-through-window fa-2xl"></i>
          </a>
        </div>
      </li>
      <li>
        <div class="carrello">
          <a href="carrello.jsp">
            <i class="fa-solid fa-basket-shopping fa-2xl"></i>
          </a>

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

