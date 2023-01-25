package control.ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TicketBean;
import model.TicketModel;

import java.io.IOException;

@WebServlet(name = "DettagliTicketControl", value = "/DettagliTicketControl")
public class DettagliTicketControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceString = request.getParameter("codiceTicket");
        int codiceTicket = Integer.parseInt(codiceString);

        TicketModel ticketModel = new TicketModel();
        TicketBean ticket = ticketModel.retrieveByKey(codiceTicket);
        request.getSession().setAttribute("ticketInDettaglio", ticket);

        response.sendRedirect(request.getContextPath() + "/ticket.jsp");
    }
}
