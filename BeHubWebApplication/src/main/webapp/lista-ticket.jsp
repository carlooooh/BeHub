<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 02/01/2023
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/lista-ticket.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="container">
        <div class="details">
            <h1>Lista Ticket</h1>
            <h2>Ecco la lista dei ticket che hai inviato</h2>

            <table class="table">
                <thead>
                    <tr>
                        <th>Codice Ticket</th>
                        <th>Oggetto</th>
                        <th>Stato</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>XXXXXX</td>
                        <td>Oggetto...</td>
                        <td> <p class="status status-nd">N/D</p></td>
                        <td><a href="#" class="btn">Visualizza</a></td>
                    </tr>
                    <tr>
                        <td>12345</td>
                        <td>Informazioni utili per pagare</td>
                        <td> <p class="status status-inviato">Inviato</p></td>
                        <td><a href="#" class="btn">Visualizza</a></td>
                    </tr>
                    <tr>
                        <td>12345</td>
                        <td>okay letsgoooooo andiamoo</td>
                        <td> <p class="status status-aperto">Aperto</p></td>
                        <td><a href="#" class="btn">Visualizza</a></td>
                    </tr>
                </tbody>
            </table>

            <form>
                <button onclick="location.href='acquista.jsp'">Compila Ticket</button>
            </form>
        </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
