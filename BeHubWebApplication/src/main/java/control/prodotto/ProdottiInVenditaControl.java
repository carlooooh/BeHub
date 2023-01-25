package control.prodotto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductBean;
import model.ProductModel;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ProdottiInVenditaControl", value = "/ProdottiInVenditaControl")
public class ProdottiInVenditaControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email;
        if (request.getParameter("admin") != null) { //Usa email scelta se chiamato da un admin
            email = request.getParameter("emailUtente");
        }
        else
            email = (String) request.getSession().getAttribute("email");
        ProductModel productModel = new ProductModel();
        Collection<ProductBean> prodotti = productModel.getProdottiInVendita(email);  //ottenimento prodotti in vendita per l'utente
        request.getSession().setAttribute("prodottiInVendita", prodotti);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prodotti-in-vendita.jsp");
        dispatcher.forward(request, response);
    }
}
