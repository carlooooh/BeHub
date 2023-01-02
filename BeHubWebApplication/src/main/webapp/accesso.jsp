<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 29/12/2022
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/accesso.css">
</head>
<body>


<div class="hero">
    <div id="bk" class="form-box">
        <div class="button-box">
            <div id="btn"></div>
            <button type="button" class="toggle-btn" onclick="login()">Login</button>
            <button type="button" class="toggle-btn"onclick="register()">Registrati</button>
        </div>
            <form id="login" class="input-group">
                <input type="email" class="input-field" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email non valida" maxlength="50" placeholder="Email" autofocus required>
                <input type="password" class="input-field" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,50}" title="La password deve contenere almeno una lettera maiuscola, una minuscola e un numero, e deve essere almeno di 8 caratteri" placeholder="Password" required>
                <input type="checkbox" class="check-box"><span>Ricordami!</span>
                <button type="submit" class="submit-btn">Login</button>
            </form>
            <form id="register" class="input-group">
                <input type="email" class="input-field" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email non valida" maxlength="50" placeholder="Email" autofocus required>
                <input type="password" class="input-field" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,50}" title="La password deve contenere almeno una lettera maiuscola, una minuscola e un numero, e deve essere almeno di 8 caratteri" placeholder="Password" required>
                <input type="text" class="input-field" name="nome" maxlength="50" placeholder="Nome" required>
                <input type="text" class="input-field" name="cognome" maxlength="50" placeholder="Cognome" required>
                <input type="text" class="input-field" name="città" maxlength="50" placeholder="Città" required>
                <input type="text" class="input-field" name="stato" maxlength="50" placeholder="STATO" required>
                <input type="text" class="input-field" name="cap" maxlength="5" placeholder="CAP" required>
                <input type="text" class="input-field" name="provincia" maxlength="50" placeholder="Provincia" required>
                <input type="text" class="input-field" name="indirizzo" maxlength="50" placeholder="Indirizzo" required>
                <input type="text" class="input-field" name="telefono" pattern="[0-9]{15}" title="Inserisci un numero di telefono di 15 cifre" placeholder="Cellulare" required>
                <input type="text" class="input-field" name="carta" pattern="[0-9]{16}" title="Inserisci un numero valido di 16 cifre" placeholder="Numero della Carta" required>
                <input type="text" class="input-field" name="cvv" pattern="[0-9]{3}" title="Inserisci un numero di 3 cifre" placeholder="CVV" required>
                <input type="data" class="input-field" name="scadenza" min="2000-01-01" max="2040-12-31" pattern="\d{2}-\d{2}-\d{4}" placeholder="Scadenza" required>
                <input type="checkbox" class="check-box"><span>Ricordami!</span>
                <button type="submit" class="submit-btn">Login</button>
            </form>
    </div>
</div>

<script>
    
    var x= document.getElementById("login");
    var y= document.getElementById("register");
    var z= document.getElementById("btn");
    var c=document.getElementById("bk");
    
    function register() {
        c.style.height="830px"
        x.style.left ="-400px"
        z.style.left ="115px"
        y.style.top="-200px"
        y.style.left="0px"
    }
    function login() {
        c.style.height="480px"
        x.style.left ="0px"
        y.style.left="-400px"
        z.style.left ="0px"

    }
</script>

</body>
</html>
