<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 02/01/2023
  Time: 18:47
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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.1.3/TweenMax.min.js"></script>


</head>


<body>
<div class="container">
  <div class="left column">
    <div class="header"><h1 class="ml12">checkout</h1></div>
    <div class="sottotitolo"> <h3> Concludi il tuo acquisto</h3></div>
    <div class="price">
      <span>totale</span>
      <span>$979.80</span>
    </div>
  </div>
  <div class="right column">
    <div class="nav">
      <div class="nav-item">indirizzo</div>
      ➝
      <div class="nav-item">checkout</div>
    </div>
    <div id="img" class="card-img">
      <img src="immagini/card.png" alt="" />
    </div>


    <div class="container2">
    <form>

    <div class="step step-1 active">
      <div class="form-group">
        <input type="text" placeholder="indirizzo(via, città, procincia, cap, stato)" id="indirizzo" >
      </div>
      <div class="form-group">
        <input type="text" id="Ntelefono" placeholder="numero di telefono" >
      </div>
      <button type="button" class="next-btn">Next</button>
    </div>

    <div class="step step-2">
      <div class="form-group">
        <input type="text" id="numero-carta"  placeholder="numero carta" >
      </div>
      <div class="form-group">
        <input type="text" placeholder="data" />
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <input type="text" placeholder="cvv" />
      </div>
      <button type="button" class="previous-btn">Prev</button>
    </div>
    </form>
    </div>


  </div>
</div>

</body>
<script src="main.js"> </script>
</html>
