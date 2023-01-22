<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 02/01/2023
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lettura Ticket</title>
    <link rel="stylesheet" href="./css/lettura-ticket.css">
    <link rel="stylesheet" href="./css/toast.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">

</head>

<body>


<div class="container2">
    <div class="box">
    <h3>Richiesta di supporto</h3>
    <div class="name">
        <i class="fas fa-user"></i>
        <input type="text" placeholder="ID TICKET" id="name">
    </div>
        <div class="stato">
            <p class="status status-aperto">Aperto</p
        </div>
    <div class="email">
        <i class="fas fa-envelope"></i>
        <input type="text" placeholder="EMAIL" id="email">
    </div>
    <div class="data">
        <input type="text" placeholder="DATA INVIO" id="data">
    </div>
    <div class="oggetto">
        <input type="text" placeholder="OGGETTO" id="oggetto">
    </div>
    <div class="message-box">
        <textarea id="msg" cols="30" rows="10" placeholder="il tuo messaggio!"></textarea>
    </div>
    </div>
</div>

</body>

</html>
