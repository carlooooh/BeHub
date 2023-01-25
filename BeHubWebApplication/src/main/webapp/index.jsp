<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>BeHub - HomePage</title>
    <link rel="stylesheet" href="./css/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<jsp:include page="header.jsp"/>
<body>

<div class="carosello">
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 4"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4" aria-label="Slide 5"></button>
        </div>
        <h1 >Benvenuto su BeHub</h1>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src=".\immagini\vestiti.png" class="d-block w-50 imgCarosello" alt="..." >
                <div class="carousel-caption">
                    <h2>ABBIGLIAMENTO</h2>
                    <p>Scopri sempre più lati della tua personalità.</p>
                    <a href="ListaProdottiCategoriaControl?categoria=Abbigliamento" class="hero-btn">Esplora</a>
                </div>
            </div>
            <div class="carousel-item">
                <img src=".\immagini\scarpe.png" class="d-block w-50 imgCarosello" alt="..." >
                <div class="carousel-caption">
                    <h2>CALZATURE</h2>
                    <p>Affronta con stile anche le montagne più alte.</p>
                    <a href="ListaProdottiCategoriaControl?categoria=Calzature" class="hero-btn">Esplora</a>
                </div>
            </div>
            <div class="carousel-item">
                <img src=".\immagini\pc.png" class="d-block w-50 imgCarosello" alt="...">
                <div class="carousel-caption">
                    <h2>ELETTRONICA</h2>
                    <p>Chi ha detto che le comodità non piacciono?.</p>
                    <a href="ListaProdottiCategoriaControl?categoria=Elettronica" class="hero-btn">Esplora</a>
                </div>
            </div>
            <div class="carousel-item">
                <img src=".\immagini\libri.png" class="d-block w-50 imgCarosello" alt="...">
                <div class="carousel-caption">
                    <h2>LIBRI</h2>
                    <p>Le parole feriscono più delle spade.</p>
                    <a href="ListaProdottiCategoriaControl?categoria=Libri" class="hero-btn">Esplora</a>
                </div>
            </div>
            <div class="carousel-item">
                <img src=".\immagini\giochi.png" class="d-block w-50  imgCarosello" alt="...">
                <div class="carousel-caption">
                    <h2>GIOCATTOLI</h2>
                    <p>C'è un tempo per studiare ma c'è sempre tempo per giocare.</p>
                    <a href="ListaProdottiCategoriaControl?categoria=Giocattoli" class="hero-btn">Esplora</a>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>

</html>