package control.carrello;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
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

        cart = cartModel.aggiungiAlCarrello(cart, codice); //Aggiunge il prodotto al carrello

        request.getSession().setAttribute("carrello", cart);
        request.getRequestDispatcher("/carrello.jsp").forward(request, response);
    }
}
