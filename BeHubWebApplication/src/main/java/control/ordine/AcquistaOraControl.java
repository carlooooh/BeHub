package control.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOImplementation.ProductDAOModel;
import model.DAOInterfaces.ProductDAO;
import model.bean.CartBean;
import model.bean.ProductBean;

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
        String redirect = null;
        if (request.getParameter("aggiungiAlCarrello") != null) {
            redirect = "/InserimentoProdottoControl?codice=" + request.getParameter("codice") + "&quantity=" + request.getParameter("quantity");
        }
        //Funzionalità di acquisto rapido
        else {
            ProductDAO productModel = new ProductDAOModel();
            int codice = Integer.parseInt(request.getParameter("codice"));
            CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");

            try {
                ProductBean prodotto = productModel.doRetrieveByKey(codice);
                carrello.setCarrello(prodotto);
                request.getSession().setAttribute("carrello", carrello);
                redirect = "/checkout.jsp";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirect);
        dispatcher.forward(request, response);
    }
}
