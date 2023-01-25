<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/accesso.css">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<section class="container forms">
    <div class="form login">
        <div class="form-content">
            <header>Accedi</header>
            <form action="LoginControl" method="post">
                <div class="field input-field">
                    <input type="email" placeholder="Email" class="input" name="email" id="email" required>
                </div>

                <div class="field input-field">
                    <input type="password" placeholder="Password" class="password" name="password" id="password" required>
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

    <div class="form signup" style="margin-top: 15px">
        <div class="form-content">
            <header>Registrati</header>
            <form action="RegistrazioneControl" method="post">
                <div class="field input-field">
                    <input type="email" name="email" maxlength="50" placeholder="Email" class="input" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email non valida" autofocus required>
                </div>

                <div class="field input-field">
                    <input type="password" name="password" class="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,50}" title="La password deve contenere almeno una lettera maiuscola, una minuscola e un numero, e deve essere almeno di 8 caratteri" maxlength="50" placeholder="Inserisci la tua password" required>
                </div>

                <div class="field input-field">
                    <input type="password" name="" maxlength="50" placeholder="Conferma password" class="password" required>
                    <i class='bx bx-hide eye-icon'></i>
                </div>

                <div class="field input-field">
                    <input type="text" name="nome" maxlength="50" placeholder="Nome" class="nome" required>
                </div>

                <div class="field input-field">
                    <input type="text" name="cognome" maxlength="50" placeholder="Cognome" class="cognome" required>
                </div>

                <div class="field input-field">
                    <input type="date" name="data" placeholder="Data di Nascita" class="data" required>
                </div>

                <div class="field input-field">
                    <input type="text" name="indirizzo" maxlength="150" placeholder="Indirizzo" class="indirizzo" required>
                </div>

                <div class="field input-field">
                    <input type="text" name="telefono" placeholder="Telefono" class="telefono" pattern="[0-9]{10,15}" title="Inserisci un numero di telefono di 10-15 cifre" required>
                </div>

                <!--
                <div class="field input-field">
                    <input type="text" placeholder="Numero Carta" class="carta">
                </div>

                <div class="field input-field">
                    <input type="text" placeholder="Scadenza Carta" class="scadenza">
                </div>

                <div class="field input-field">
                    <input type="number" placeholder="CVV" class="cvv">
                </div>
                -->

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
</body>
</html>
