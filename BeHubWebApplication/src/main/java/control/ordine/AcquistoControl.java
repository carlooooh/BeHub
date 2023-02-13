package control.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.OrderDAO;
import model.bean.CartBean;
import model.DAOImplementation.OrderDAOModel;
import model.bean.UserBean;

import java.io.IOException;

@WebServlet(name = "AcquistoControl", value = "/AcquistoControl")
public class AcquistoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderModel = new OrderDAOModel();
        CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");
        UserBean utente = (UserBean) request.getSession().getAttribute("utente");

        Boolean control = orderModel.doOrder(carrello, utente); //crea ordine

        if (control == true) {
            carrello.removeAllItems();
            request.getSession().setAttribute("carrello", carrello);
            String email = (String) request.getSession().getAttribute("email");


            response.sendRedirect(request.getContextPath() + "/ListaOrdiniUtenteControl?emailOrdini=" + email);

        }
        else {
            response.sendRedirect(request.getContextPath() + "/carrello.jsp?acquisto=failure");

        }
    }
}
