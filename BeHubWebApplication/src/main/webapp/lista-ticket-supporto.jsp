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
    <h2>Visualizza i ticket aperti degli utenti</h2>

    <table class="table">
      <thead>
      <tr>
        <th>Codice Ticket</th>
        <th>Oggetto</th>
        <th>Stato</th>
        <th></th>
        <th> </th>
      </tr>
      </thead>
      <tbody>
      <%
        if (request.getAttribute("listaTicket") != null) {
          String email = request.getParameter("email");
          Collection<TicketBean> listaTicket = (Collection<TicketBean>) request.getAttribute("listaTicket");
          Iterator<TicketBean> iterator = listaTicket.iterator();
          while (iterator.hasNext()) {
            TicketBean ticket = iterator.next();
      %>
      <tr>
        <td><%=ticket.getCodice()%></td>
        <td><%=ticket.getOggetto()%></td>
        <td>
          <p class="status status-aperto">Aperto</p>
          <td>
          <a href = "ChiudiTicketControl?codiceTicket=<%=ticket.getCodice()%>&email=<%=email%>" class = "del">
            <span>
              <i class = "fas fa-trash-alt" style="color: red"></i>
            </span>
          </a>
        </td>
        </td>
        <td><a href="DettagliTicketControl?codiceTicket=<%=ticket.getCodice()%>" class="btn">Visualizza</a></td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
