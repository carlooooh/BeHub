<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 29/12/2022
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <!-- CSS -->
    <link rel="stylesheet" href="css/accesso.css">


    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>

</head>
<body>




<section class="container forms">
    <div class="form login">
        <div class="form-content">
            <header>Accedi</header>
            <form action="#">
                <div class="field input-field">
                    <input type="email" placeholder="Email" class="input">
                </div>

                <div class="field input-field">
                    <input type="password" placeholder="Password" class="password">
                    <i class='bx bx-hide eye-icon'></i>
                </div>



                <div class="field button-field">
                    <button>Accedi</button>
                </div>
            </form>

            <div class="form-link">
                <span>Non hai un'account? <a href="#" class="link signup-link">Registrati</a></span>
            </div>
            <div class="form-link">
                <span>Torna alla pagina principale:<a href="index.jsp" >Home</a></span>
            </div>
        </div>



    </div>

    <!-- Registrazione Form -->

    <div class="form signup">
        <div class="form-content">
            <header>Registrati</header>
            <form action="#">
                <div class="field input-field">
                    <input type="email" placeholder="Email" class="input">
                </div>

                <div class="field input-field">
                    <input type="password" placeholder="Crea password" class="password">
                </div>

                <div class="field input-field">
                    <input type="password" placeholder="Conferma password" class="password">
                    <i class='bx bx-hide eye-icon'></i>
                </div>

                <div class="field input-field">
                    <input type="text" placeholder="Nome" class="nome">
                </div>

                <div class="field input-field">
                    <input type="text" placeholder="Cognome" class="cognome">
                </div>

                <div class="field input-field">
                    <input type="date" placeholder="Data di Nascita" class="data">
                </div>

                <div class="field input-field">
                    <input type="text" placeholder="Indirizzo" class="indirizzo">
                </div>

                <div class="field input-field">
                    <input type="text" placeholder="Numero Carta" class="carta">
                </div>

                <div class="field input-field">
                    <input type="text" placeholder="Scadenza Carta" class="scadenza">
                </div>

                <div class="field input-field">
                    <input type="number" placeholder="CVV" class="cvv">
                </div>

                <div class="field button-field">
                    <button>Registrati</button>
                </div>
            </form>

            <div class="form-link">
                <span>Hai già un account? <a href="#" class="link login-link">Accedi</a></span>
            </div>
            <div class="form-link">
                <span>Torna alla pagina principale:<a href="index.jsp" >Home</a></span>
            </div>
        </div>



    </div>
</section>
<script>
<!-- JavaScript -->
const forms = document.querySelector(".forms"),
pwShowHide = document.querySelectorAll(".eye-icon"),
links = document.querySelectorAll(".link");

pwShowHide.forEach(eyeIcon => {
eyeIcon.addEventListener("click", () => {
let pwFields = eyeIcon.parentElement.parentElement.querySelectorAll(".password");

pwFields.forEach(password => {
if(password.type === "password"){
password.type = "text";
eyeIcon.classList.replace("bx-hide", "bx-show");
return;
}
password.type = "password";
eyeIcon.classList.replace("bx-show", "bx-hide");
})

})
})

links.forEach(link => {
link.addEventListener("click", e => {
e.preventDefault(); //preventing form submit
forms.classList.toggle("show-signup");
})
})
</script>
<!--<script src="js/script.js"></script>-->




</body>
</html>
