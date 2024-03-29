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

@WebServlet(name = "ScritturaTicketControl", value = "/ScritturaTicketControl")
public class ScritturaTicketControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oggetto = request.getParameter("oggetto");
        String testo = request.getParameter("msg");
        String email = (String) request.getSession().getAttribute("email");

        //Creazione Ticket ed inserimento nel database
        TicketDAO ticketModel = new TicketDAOModel();
        TicketBean ticket = new TicketBean();

        ticket.setOggetto(oggetto);
        ticket.setTesto(testo);
        ticket.setStato(0);
        ticket.setEmailUtente(email);

        ticketModel.aggiungiTicket(ticket);

        //Trasferimento alla lista ticket dell'utente
        response.sendRedirect(request.getContextPath() + "/ListaTicketControl");
    }
}
