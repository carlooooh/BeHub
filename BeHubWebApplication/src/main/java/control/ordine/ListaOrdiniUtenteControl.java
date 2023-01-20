package control.ordine;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.OrderBean;
import model.OrderModel;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

@WebServlet(name = "ListaOrdiniUtenteControl", value = "/ListaOrdiniUtenteControl")
public class ListaOrdiniUtenteControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderModel orderModel = new OrderModel();
        Collection<OrderBean> listaOrdini = new LinkedList<OrderBean>();

        listaOrdini = orderModel.getOrdini(request.getParameter("emailOrdini")); //ottenimento lista ordini dell'utente

        if (listaOrdini != null) {
            request.setAttribute("listaOrdini", listaOrdini);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Da aggiungere
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Da aggiungere
            dispatcher.forward(request, response);
        }
    }
}
