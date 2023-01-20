package control.ordine;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.CartBean;
import model.OrderModel;
import model.UserBean;

import java.io.IOException;

@WebServlet(name = "AcquistoControl", value = "/AcquistoControl")
public class AcquistoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderModel orderModel = new OrderModel();
        CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");
        UserBean utente = (UserBean) request.getSession().getAttribute("utente");

        Boolean control = orderModel.doOrder(carrello, utente); //crea ordine

        if (control == true) {
            carrello.removeAllItems();
            request.getSession().setAttribute("carrello", carrello);
            String email = (String) request.getSession().getAttribute("email");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaOrdiniUtenteControl?emailOrdini=" + email);
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp?acquisto=failure");
            dispatcher.forward(request, response);
        }
    }
}
