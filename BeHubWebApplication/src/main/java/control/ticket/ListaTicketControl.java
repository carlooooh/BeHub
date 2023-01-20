package control.ticket;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.TicketBean;
import model.TicketModel;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

@WebServlet(name = "ListaTicketControl", value = "/ListaTicketControl")
public class ListaTicketControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<TicketBean> listaTicket = new LinkedList<TicketBean>();
        TicketModel ticketModel = new TicketModel();
        String email = (String) request.getSession().getAttribute("email");
        listaTicket = ticketModel.getListaTicket(email);

        request.setAttribute("listaTicket", listaTicket);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lista-ticket.jsp");
        dispatcher.forward(request, response);
    }
}
