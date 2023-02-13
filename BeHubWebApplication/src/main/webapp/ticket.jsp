<%@ page import="model.bean.TicketBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dettagli Ticket</title>
    <link rel="stylesheet" href="./css/lettura-ticket.css">
    <link rel="stylesheet" href="./css/toast.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">

</head>
<body>
    <jsp:include page="header.jsp"/>
    <%
        TicketBean ticket = (TicketBean) session.getAttribute("ticketInDettaglio");
    %>
    <div class="container2">
        <div class="box">
            <h3>Richiesta di supporto</h3>
        <div class="name">
        <i class="fas fa-user"></i>
        <input type="text" placeholder="<%=ticket.getCodice()%>" id="name" readonly>
    </div>
        <%
            if (ticket.getStato() == 0) {
        %>
        <div class="stato">
            <p class="status status-aperto">Aperto</p>
        </div>
        <%
            }
            else {
        %>
            <div class="stato">
                <p class="status status-nd">Chiuso</p>
            </div>
        <%
            }
        %>
    <div class="email">
        <i class="fas fa-envelope"></i>
        <input type="text" placeholder="<%=ticket.getEmailUtente()%>>" id="email" readonly>
    </div>
    <div class="data">
        <input type="text" placeholder="<%=ticket.getData()%>>" id="data" readonly>
    </div>
    <div class="oggetto">
        <input type="text" placeholder="<%=ticket.getOggetto()%>" id="oggetto" readonly>
    </div>
    <div class="message-box">
        <textarea id="msg" cols="30" rows="10" placeholder="<%=ticket.getTesto()%>" readonly style="resize: none"></textarea>
    </div>
    </div>
</div>

    <div style="margin-top: 480px">
        <jsp:include page="footer.jsp"/>
    </div>
</body>

</html>
