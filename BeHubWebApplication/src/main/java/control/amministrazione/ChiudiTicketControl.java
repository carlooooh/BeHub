package control.amministrazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TicketBean;
import model.TicketModel;

import java.io.IOException;

@WebServlet(name = "ChiudiTicketControl", value = "/ChiudiTicketControl")
public class ChiudiTicketControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceTicket = request.getParameter("codiceTicket");
        String email = request.getParameter("email");
        TicketBean ticket = new TicketBean();
        ticket.setCodice(Integer.parseInt(codiceTicket));

        TicketModel ticketModel = new TicketModel();
        Boolean control = ticketModel.chiudiTicket(ticket);

        if (control == true) {
            request.getSession().setAttribute("erroreChiusuraTicket", false);
        }
        else {
            request.getSession().setAttribute("erroreChiusuraTicket", true);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/VisualizzazioneTicketUtentiControl?email=" + email);
        dispatcher.forward(request, response);
    }
}
