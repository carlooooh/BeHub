package control.ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TicketBean;
import model.TicketModel;

import java.io.IOException;

@WebServlet(name = "ScritturaTicketControl", value = "/ScritturaTicketControl")
public class ScritturaTicketControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oggetto = request.getParameter("oggetto");
        String testo = request.getParameter("msg");
        String email = (String) request.getSession().getAttribute("email");

        //Creazione Ticket ed inserimento nel database
        TicketModel ticketModel = new TicketModel();
        TicketBean ticket = new TicketBean();

        ticket.setOggetto(oggetto);
        ticket.setTesto(testo);
        ticket.setStato(0);
        ticket.setEmailUtente(email);

        ticketModel.aggiungiTicket(ticket);

        //Trasferimento alla lista ticket dell'utente
        response.sendRedirect(request.getContextPath() + "/TicketControl");
    }
}
