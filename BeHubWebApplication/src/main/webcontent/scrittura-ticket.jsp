<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 03/01/2023
  Time: 09:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BeHub - Inserimento Ticket</title>
    <link rel="stylesheet" href="./css/scrittura-ticket.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">
</head>
<body>

<div class="container">
    <div class="box">
        <form action="ScritturaTicketControl" METHOD="POST">
        <h3>Richiesta di supporto</h3>
        <div class="oggetto">
            <i class="fas fa-envelope"></i>
            <input type="text" maxlength="50" placeholder="Oggetto" id="oggetto" name="oggetto">
        </div>
        <div class="message-box">
            <textarea id="msg" name="msg" cols="30" rows="10" placeholder="Scrivi qui il tuo messaggio!" style="resize: none"></textarea>
        </div>
        <div class="button">
            <button id="send" onclick="message()">Invia</button>
        </div>
        <div class="message">
            <div class="success" id="success">Richiesta di supporto inviata con successo!</div>
            <div class="danger" id="danger">Devi compilare tutti i campi!</div>
        </div>
        </form>
    </div>
</div>

<script>
    function message(){
        var oggetto=document.getElementById('oggetto');
        var msg = document.getElementById('msg');
        const success = document.getElementById('success');
        const danger = document.getElementById('danger');

        if(msg.value === '' || oggetto.value === ''){
            danger.style.display = 'block';
        }
        else{
            setTimeout(() => {
                msg.value = '';
                oggetto.value = '';
            }, 2000);

            success.style.display = 'block';
        }


        setTimeout(() => {
            danger.style.display = 'none';
            success.style.display = 'none';
        }, 4000);

    }
</script>


</body>
</html>
