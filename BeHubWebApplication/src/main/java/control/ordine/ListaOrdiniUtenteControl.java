package control.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            request.getSession().setAttribute("listaOrdini", listaOrdini);
            response.sendRedirect(request.getContextPath() + "/ordini.jsp");
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/account.jsp?errormsg=true");
            dispatcher.forward(request, response);
        }
    }
}
