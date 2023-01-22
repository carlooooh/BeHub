package control.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderModel;
import model.ProductBean;
import model.ProductModel;
import model.UserBean;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AcquistaOraControl", value = "/AcquistaOraControl")
public class AcquistaOraControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Smistamento richiesta all'inserimento prodotto nel carrello
        if (request.getParameter("aggiungiAlCarrello") != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InserimentoProdottoControl?codice=" + request.getParameter("codice") + "&quantity=" + request.getParameter("quantity"));
            dispatcher.forward(request, response);
        }
        //Funzionalità di acquisto rapido
        else {
            OrderModel orderModel = new OrderModel();
            ProductModel productModel = new ProductModel();
            int codice = Integer.parseInt(request.getParameter("codice"));
            UserBean utente = (UserBean) request.getSession().getAttribute("utente");
            Boolean control = false;

            try {
                ProductBean prodotto = productModel.doRetrieveByKey(codice);
                control = orderModel.doOrder(prodotto, utente);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (control == true) {
                response.sendRedirect(request.getContextPath() + "/ListaOrdiniUtenteControl?emailOrdini=" + utente.getEmail());
            }
            else {
                response.sendRedirect(request.getContextPath() + "/prodotto.jsp?codiceProdotto=" + codice + "&errormsg=true");
            }
        }
    }
}
