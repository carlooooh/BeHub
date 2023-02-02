<%@ page import="java.util.Collection" %>
<%@ page import="model.bean.TicketBean" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BeHub - Lista Ticket</title>
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
                <%
                    if (request.getAttribute("listaTicket") != null) {
                        Collection<TicketBean> listaTicket = (Collection<TicketBean>) request.getAttribute("listaTicket");
                        Iterator<TicketBean> iterator = listaTicket.iterator();
                        while (iterator.hasNext()) {
                            TicketBean ticket = iterator.next();
                %>
                    <tr>
                        <td><%=ticket.getCodice()%></td>
                        <td><%=ticket.getOggetto()%></td>
                        <%
                            if (ticket.getStato() == 0) {
                        %>
                        <td> <p class="status status-aperto">Aperto</p></td>
                        <%  }
                            else {
                        %>
                        <td> <p class="status status-nd">Chiuso</p></td>
                        <%  }
                        %>
                        <td><a href="DettagliTicketControl?codiceTicket=<%=ticket.getCodice()%>" class="btn">Visualizza</a></td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>

            <form action="scrittura-ticket.jsp">
                <button>Compila Ticket</button>
            </form>
        </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
