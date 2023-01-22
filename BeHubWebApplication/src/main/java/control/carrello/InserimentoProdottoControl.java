package control.carrello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartBean;
import model.CartModel;

import java.io.IOException;

@WebServlet(name = "InserimentoProdottoControl", value = "/InserimentoProdottoControl")
public class InserimentoProdottoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempCodice = request.getParameter("codice");
        int codice = Integer.parseInt(tempCodice);
        CartBean cart = (CartBean) request.getSession().getAttribute("carrello");
        CartModel cartModel = new CartModel();

        if (request.getParameter("quantity") != null) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart = cartModel.aggiungiAlCarrello(cart, codice, quantity);
        }
        else {
            cart = cartModel.aggiungiAlCarrello(cart, codice); //Aggiunge il prodotto al carrello
        }

        request.getSession().setAttribute("carrello", cart);
        response.sendRedirect(request.getContextPath() + "/carrello.jsp");
    }
}
