package control.ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.TicketDAO;
import model.bean.TicketBean;
import model.DAOImplementation.TicketDAOModel;

import java.io.IOException;

@WebServlet(name = "ChiudiTicketControl", value = "/ChiudiTicketControl")
public class ChiudiTicketControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceTicket = (String) request.getParameter("codiceTicket");
        TicketBean ticket = new TicketBean();
        ticket.setCodice(Integer.parseInt(codiceTicket));

        TicketDAO ticketModel = new TicketDAOModel();
        ticketModel.chiudiTicket(ticket);
    }
}
