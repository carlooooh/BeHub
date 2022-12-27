<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" href="./css/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>

<jsp:include page="header.jsp"/>


<section>
    <div class="container">

        <div class="categories">
            <div class="wrapper">
                <div class="content">

                    <h1>Benvenuto</h1>

                    <p>Vendi o compra sulla nostra piattaforma</p>

                </div>
                <h4 class="category-subtitle">Titolo categoria</h4>


                <div class="categories-container">

                    <div class="category">
                        <a href="Tipologia?tipologia=Action-Figures">

                            <img src="./img/cat1x.jpg" alt="">
                            <div class="category-body">
                                <h5>Action Figures</h5>
                            </div>
                        </a>
                    </div>

                    <a href="Tipologia?tipologia=Arredamento-Casa">
                        <div class="category">

                            <img src="./img/cat2x.jpg" alt="">
                            <div class="category-body">
                                <h5>Arredamento Casa</h5>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="mysteryObj">
                    <button id="mistObj" type="button">Esplora Catalogo</button>
                </div>

            </div>
        </div>
    </div>

</section>

<jsp:include page="footer.jsp"/>
</body>

</html>