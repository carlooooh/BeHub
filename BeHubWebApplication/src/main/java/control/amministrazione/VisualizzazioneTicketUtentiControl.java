package control.amministrazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.TicketDAO;
import model.bean.TicketBean;
import model.DAOImplementation.TicketDAOModel;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "VisualizzazioneTicketUtentiControl", value = "/VisualizzazioneTicketUtentiControl")
public class VisualizzazioneTicketUtentiControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<TicketBean> listaTicket;
        TicketDAO ticketModel = new TicketDAOModel();
        String email = request.getParameter("email");
        listaTicket = ticketModel.getListaTicketAperti(email);

        request.setAttribute("listaTicket", listaTicket);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lista-ticket-supporto.jsp?email=" + email);
        dispatcher.forward(request, response);
    }
}
