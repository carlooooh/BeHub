<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 02/01/2023
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Acquisto</title>
  <link rel="stylesheet" href="css/acquista.css" />
  <script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.1.3/TweenMax.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
</head>


<body>
<div class="container">
  <div class="left column">
    <div class="logo"><ion-icon name="finger-print"></ion-icon></div>
    <div class="header"><h1 class="ml12">checkout</h1></div>
    <div class="back-btn"> <h3> Concludi il tuo acquisto</h3></div>
    <div class="price">
      <span>totale</span>
      <span>$979.80</span>
    </div>
  </div>
  <div class="right column">
    <div class="nav">
      <div class="nav-item">catalogo</div>
      ➝
      <div class="nav-item">indirizzo</div>
      ➝
      <div class="nav-item active">checkout</div>
    </div>
    <div class="card-img">
      <img src="immagini/card.png" alt="" />
    </div>
    <div class="form">
      <div class="form-row">
        <input type="text" placeholder="numero carta" />
      </div>
      <div class="form-row">
        <input type="text" placeholder="data" />
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <input type="text" placeholder="cvv" />
      </div>
    </div>
    <div class="btn">
      <button onclick="location.href='index.jsp'">checkout</button>
    </div>
  </div>
</div>

</body>
</html>